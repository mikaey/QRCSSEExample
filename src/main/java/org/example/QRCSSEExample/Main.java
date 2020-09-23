package org.example.QRCSSEExample;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import com.paypal.*;
import com.paypal.Checkouts.*;
import jakarta.json.bind.JsonbBuilder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    // Before running this example, fill in the properties in src/main/resources/ExampleCredentialsExample.properties,
    // then rename it to ExampleCredentials.properties!
    public static void main(String[] args) {
        ResourceBundle exampleCredentialsBundle = ResourceBundle.getBundle("ExampleCredentials");

        // Transaction info
        String transactionAmount = "24.99";
        CurrencyCodeType transactionCurrency = CurrencyCodeType.USD;

        // Barcode that the merchant wants shown on the buyer's receipt.  This should be a value that
        // the merchant can use to look up the transaction later.
        String barcodeValue = "ABCD1234";

        String callerAccessToken, buyerAccessToken;

        Logger logger = Logger.getLogger("Main");

        // STEP 1: Request an access token.
        try {
            callerAccessToken = GetAccessToken(exampleCredentialsBundle.getString("checkoutsApp.clientId"),
                    exampleCredentialsBundle.getString("checkoutsApp.secret"));
            logger.info("Got access token ".concat(callerAccessToken));
        } catch(AccessTokenException e) {
            logger.severe("Error requesting access token!");
            if(e.PayPalResponse != null) {
                logger.severe("Error response from PayPal: ".concat(e.PayPalResponse.Message));
            }
            return;
        }

        // STEP 2: Get an access token for the buyer.
        try {
            buyerAccessToken = GetAccessToken(exampleCredentialsBundle.getString("buyerCredentialsApp.clientId"),
                    exampleCredentialsBundle.getString("buyerCredentialsApp.secret"),
                    exampleCredentialsBundle.getString("buyer.email"),
                    exampleCredentialsBundle.getString("buyer.password"));
            logger.info("Got access token for buyer: ".concat(buyerAccessToken));
        } catch(AccessTokenException e) {
            logger.severe("Error requesting access token!");
            if(e.PayPalResponse != null) {
                logger.severe("Error response from PayPal: ".concat(e.PayPalResponse.Message));
            }
            return;
        }

        // STEP 3: Request QR codes from the buyer's account
        String qrCode = null;
        try {
            String qrCodes[] = CreateQRCodes(buyerAccessToken,
                    exampleCredentialsBundle.getString("buyer.payerId"), 1);
            if(qrCodes.length < 1) {
                logger.severe("Response did not contain any QR codes!");
                return;
            }
            qrCode = qrCodes[0];
        } catch (CreateQrCodeRequestException e) {
            logger.severe("Error requesting QR codes!");
            if(e.PayPalResponse != null) {
                logger.severe("Error response from PayPal: ".concat(e.PayPalResponse.Message));
            }
            return;
        }

        logger.info("Got QR code for buyer: ".concat(qrCode));

        // Step 4: Fire off the Capture call and approve the payment

        // A couple variables to help us pass information between threads
        // referenceId is the reference ID returned by the initial Create Capture call -- the buyer approval
        // step will need that
        final String[] referenceId = {null};
        // transactionCompleted is a flag to indicate whether we're in a state where we can exit...it's
        // not necessarily to indicate that a transaction completed successfully
        final Boolean[] transactionCompleted = {false};

        // Set up a thread that we'll use to fire off the Complete Capture call once we get the
        // AWAITING_USER_INPUT status message
        Thread buyerApprovalThread = new Thread() {
            public void run() {
                try {
                    CompleteCapture(buyerAccessToken, referenceId[0]);
                    logger.info("Complete Capture request completed successfully");
                } catch (CompleteCaptureRequestException e) {
                    logger.severe("Error completing the buyer capture!");
                    if(e.PayPalResponse != null) {
                        logger.severe("Error response from PayPal: ".concat(e.PayPalResponse.Message));
                    }
                    transactionCompleted[0] = true;
                }
            }
        };

        // Set up an event handler for processing SSE events
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void onOpen() throws Exception {}

            @Override
            public void onClosed() throws Exception {
                transactionCompleted[0] = true;
            }

            @Override
            public void onMessage(String s, MessageEvent messageEvent) throws Exception {
                String data = messageEvent.getData();
                logger.info("Got message: ".concat(messageEvent.getData()));

                // Try to parse the response as an SSECaptureResponseType so that we can at least look
                // at the data type
                SSECaptureResponseType captureResponse = JsonbBuilder.create().fromJson(data,
                        SSECaptureResponseType.class);

                if(!captureResponse.DataContentType.equals("application/json") ||
                    !captureResponse.Type.equals("com.paypal.retail.pos.transaction.status")) {
                    if(captureResponse.DataContentType.equals("application/paypal-error+json")) {
                        // Re-parse the response as an SSEErrorResponseType
                        SSEErrorResponseType sseErrorResponse = JsonbBuilder.create().fromJson(data,
                                SSEErrorResponseType.class);

                        throw new CaptureRequestException(sseErrorResponse.Data);
                    } else {
                        // We don't know what content type this is, we can't deal with it
                        throw new CaptureRequestException(String.format("Event with unknown content type \"%s\"/data type \"%s\" received in response stream",
                                captureResponse.DataContentType, captureResponse.Type));
                    }
                }
                if(captureResponse.Data.TransactionResult.Status == TransactionStatusType.AWAITING_USER_INPUT) {
                    logger.info("Got AWAITING_USER_INPUT, firing off thread to perform buyer approval");
                    referenceId[0] = captureResponse.Data.TransactionResult.ReferenceId;
                    buyerApprovalThread.start();
                } else if(captureResponse.Data.TransactionResult.Status == TransactionStatusType.SUCCESS) {
                    transactionCompleted[0] = true;
                    logger.info("Transaction completed!  Transaction ID: "
                            .concat(captureResponse.Data.TransactionResult.TransactionId));
                }
            }

            @Override
            public void onComment(String s) throws Exception {}

            @Override
            public void onError(Throwable throwable) {
                logger.info("Got error: ".concat(throwable.toString()));
                transactionCompleted[0] = true;
            }
        };

        // Set up the capture request
        CaptureRequestType captureRequest = new CaptureRequestType();

        captureRequest.PointOfSaleDetails = new PointOfSaleDetailsType();
        captureRequest.PointOfSaleDetails.StoreDetails = new PointOfSaleDetailsType.StoreDetailsType();
        captureRequest.PointOfSaleDetails.StoreDetails.ExternalId =
                exampleCredentialsBundle.getString("merchant.storeId");
        captureRequest.PointOfSaleDetails.TerminalDetails = new PointOfSaleDetailsType.TerminalDetailsType();
        captureRequest.PointOfSaleDetails.TerminalDetails.ExternalId =
                exampleCredentialsBundle.getString("merchant.terminalId");
        captureRequest.PointOfSaleDetails.TerminalDetails.Type = TerminalTypeType.CASH_REGISTER;
        captureRequest.PointOfSaleDetails.RetailerName =
                exampleCredentialsBundle.getString("merchant.name");

        captureRequest.CredentialInfo = new CaptureRequestType.CredentialInfoType();
        captureRequest.CredentialInfo.Retailer = new CaptureRequestType.CredentialInfoType.CustomerType();
        captureRequest.CredentialInfo.Retailer.AccountId =
                exampleCredentialsBundle.getString("merchant.payerId");
        captureRequest.CredentialInfo.Customer = new CaptureRequestType.CredentialInfoType.CustomerType();
        captureRequest.CredentialInfo.Customer.QrCode = qrCode;

        captureRequest.Transaction = new CaptureRequestType.TransactionType();
        captureRequest.Transaction.Amount = new AmountType();
        captureRequest.Transaction.Amount.CurrencyCode = transactionCurrency;
        captureRequest.Transaction.Amount.Value = transactionAmount;

        captureRequest.ReceiptInfo = new CaptureRequestType.ReceiptInfoType();
        captureRequest.ReceiptInfo.Barcode = barcodeValue;
        captureRequest.ReceiptInfo.RetailerTransactionId = barcodeValue;

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        captureRequest.ReceiptInfo.TerminalSalesTime = dateFormat.format(new Date());

        String request = JsonbBuilder.create().toJson(captureRequest);

        Headers.Builder headersBuilder = new Headers.Builder();
        headersBuilder.add("PayPal-Request-Id", UUID.randomUUID().toString());
        headersBuilder.add("Authorization", "Bearer ".concat(callerAccessToken));
        headersBuilder.add("PayPal-Partner-Attribution-Id", "Some_BN_Code_Here");

        EventSource.Builder builder = new EventSource.Builder(eventHandler,
                URI.create("https://api.sandbox.paypal.com/v2/retail/captures"))
                .headers(headersBuilder.build())
                .method("POST")
                .body(RequestBody.create(request, MediaType.get("application/json")))
                .reconnectTime(Duration.ofSeconds(30))
                .readTimeout(Duration.ofSeconds(30));

        EventSource eventSource = builder.build();

        logger.info("Initiating capture request");
        eventSource.start();

        while(!transactionCompleted[0]) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /**
     * Requests an access token using an API caller's client ID and secret.  This version always
     * makes API calls against the PayPal Sandbox.
     * @param clientId The API caller's client ID
     * @param secret   The API caller's secret
     * @return An access token representing the API caller
     * @throws AccessTokenException if an error occurred while requesting the access token
     */
    public static String GetAccessToken(String clientId, String secret) throws AccessTokenException {
        return GetAccessToken(clientId, secret, "https://api-m.sandbox.paypal.com");
    }

    /**
     * Requests an access token using an API caller's client ID and secret.
     * @param clientId The API caller's client ID
     * @param secret   The API caller's secret
     * @param endpoint The endpoint against which to make API calls; e.g., "https://api-m.sandbox.paypal.com"
     * @return An access token representing the API caller
     * @throws AccessTokenException if an error occurred while requesting the access token
     */
    public static String GetAccessToken(String clientId, String secret, String endpoint) throws AccessTokenException {
        try {
            String authString = new String(Base64.getEncoder().
                    encode(clientId.concat(":").concat(secret).getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.US_ASCII);

            HttpRequest request = HttpRequest.newBuilder(new URI(endpoint.concat("/v1/oauth2/token")))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic ".concat(authString))
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            AccessTokenResponseType AccessTokenResponse =
                    JsonbBuilder.create().fromJson(response.body(), AccessTokenResponseType.class);

            if(response.statusCode() >= 400) {
                // This particular endpoint is weird, as it returns a non-standard response object
                // in the event of an authentication failure.  (In this case, the response fields
                // for the error are technically part of the AccessTokenResponseType object.)
                // We're going to check to see if the required fields are present; if not, we'll
                // map them from the AccessTokenResponse object.
                ErrorResponseType errorResponse;

                if(AccessTokenResponse.Error != null && AccessTokenResponse.ErrorDescription != null) {
                    errorResponse = new ErrorResponseType();

                    // Map Error to Name and ErrorDescription to Message
                    errorResponse.Name = AccessTokenResponse.Error;
                    errorResponse.Message = AccessTokenResponse.ErrorDescription;
                } else {
                     errorResponse = JsonbBuilder.create().fromJson(response.body(), ErrorResponseType.class);
                }

                // The debug ID doesn't always come back in the body, but it does always come back
                // in the HTTP headers.  If it wasn't present in the response, grab it out of there.
                if(errorResponse.DebugId == null) {
                    Optional<String> debugId = response.headers().firstValue("PayPal-Debug-Id");
                    debugId.ifPresent(s -> errorResponse.DebugId = s);
                }

                throw new AccessTokenException(errorResponse);
            }

            return AccessTokenResponse.AccessToken;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new AccessTokenException("Failed to retrieve access token", e);
        }
    }

    /**
     * Requests an access token using an API caller's client ID and secret, as well as a consumer's
     * email address and password.  This version always makes API calls against the PayPal Sandbox.
     * @param clientId The API caller's client ID
     * @param secret   The API caller's secret
     * @param username The consumer's email address or login ID
     * @param password The consumer's password
     * @return An access token representing the consumer
     * @throws AccessTokenException if an error occurred while requesting the access token
     */
    public static String GetAccessToken(String clientId, String secret, String username, String password) throws AccessTokenException {
        return GetAccessToken(clientId, secret, username, password, "https://api-m.sandbox.paypal.com");
    }

    /**
     * Requests an access token using an API caller's client ID and secret, as well as a consumer's
     * email address and password.
     * @param clientId The API caller's client ID
     * @param secret   The API caller's secret
     * @param username The consumer's email address or login ID
     * @param password The consumer's password
     * @param endpoint The endpoint against which to make API calls; e.g., "https://api-m.sandbox.paypal.com".
     * @return An access token representing the consumer
     * @throws AccessTokenException if an error occurred while requesting the access token
     */
    public static String GetAccessToken(String clientId, String secret, String username, String password, String endpoint) throws AccessTokenException {
        try {
            String authString = new String(Base64.getEncoder().
                    encode(clientId.concat(":").concat(secret).getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.US_ASCII);

            HashMap<String, String> params = new HashMap<>();
            params.put("grant_type", "password");
            params.put("response_type", "token");
            params.put("redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
            params.put("remember_me", "true");
            params.put("email", username);
            params.put("password", password);

            ArrayList<String> encodedParamList = new ArrayList<>();
            params.forEach((key, value) -> {
                encodedParamList.add(key.concat("=").concat(URLEncoder.encode(value, StandardCharsets.UTF_8)));
            });

            String requestBody = String.join("&", encodedParamList);

            HttpRequest request = HttpRequest.newBuilder(new URI(endpoint.concat("/v1/oauth2/login")))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic ".concat(authString))
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            AccessTokenResponseType AccessTokenResponse =
                    JsonbBuilder.create().fromJson(response.body(), AccessTokenResponseType.class);

            if(response.statusCode() >= 400) {
                // This particular endpoint is weird, as it returns a non-standard response object
                // in the event of an authentication failure.  (In this case, the response fields
                // for the error are technically part of the AccessTokenResponseType object.)
                // We're going to check to see if the required fields are present; if not, we'll
                // map them from the AccessTokenResponse object.
                ErrorResponseType errorResponse;

                if(AccessTokenResponse.Error != null && AccessTokenResponse.ErrorDescription != null) {
                    errorResponse = new ErrorResponseType();

                    // Map Error to Name and ErrorDescription to Message
                    errorResponse.Name = AccessTokenResponse.Error;
                    errorResponse.Message = AccessTokenResponse.ErrorDescription;
                } else {
                    errorResponse = JsonbBuilder.create().fromJson(response.body(), ErrorResponseType.class);
                }

                // The debug ID doesn't always come back in the body, but it does always come back
                // in the HTTP headers.  If it wasn't present in the response, grab it out of there.
                if(errorResponse.DebugId == null) {
                    Optional<String> debugId = response.headers().firstValue("PayPal-Debug-Id");
                    debugId.ifPresent(s -> errorResponse.DebugId = s);
                }

                throw new AccessTokenException(errorResponse);
            }

            return AccessTokenResponse.AccessToken;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new AccessTokenException("Failed to retrieve access token", e);
        }
    }

    /**
     * Request QR codes from the consumer's account.
     * @param accessToken The access token obtained from the consumer's account
     * @param ownerAccountId The consumer's PayPal payer ID
     * @param numberOfQRCodes Number of QR codes to request
     * @return An array of QR codes obtained from the consumer's account
     * @throws CreateQrCodeRequestException if an error occurs while requesting QR codes from the consumer's
     *      account.
     */
    public static String[] CreateQRCodes(String accessToken, String ownerAccountId, int numberOfQRCodes) throws CreateQrCodeRequestException {
        return CreateQRCodes(accessToken, ownerAccountId, numberOfQRCodes,
                "https://api-m.sandbox.paypal.com");
    }

    /**
     * Request QR codes from the consumer's account.
     * @param accessToken The access token obtained from the consumer's account
     * @param ownerAccountId The consumer's PayPal payer ID
     * @param numberOfQRCodes Number of QR codes to request
     * @param endpoint The endpoint against which to make API calls; e.g., "https://api-m.sandbox.paypal.com"
     * @return An array of QR codes obtained from the consumer's account
     * @throws CreateQrCodeRequestException if an error occurs while requesting QR codes from the consumer's
     *      account.
     */
    public static String[] CreateQRCodes(String accessToken, String ownerAccountId, int numberOfQRCodes, String endpoint) throws CreateQrCodeRequestException {
        CreateQrCodesRequestType createQrCodesRequest = new CreateQrCodesRequestType();
        createQrCodesRequest.TotalQrCodes = numberOfQRCodes;
        createQrCodesRequest.Provider = ProviderType.PAYPAL;
        createQrCodesRequest.OwnerId = ownerAccountId;
        createQrCodesRequest.OwnerIdType = OwnerIdTypeType.PAYPAL_ACCOUNT;
        createQrCodesRequest.Status = QrCodeStatusType.ACTIVE;
        createQrCodesRequest.Intent = new QrCodeType.IntentType();
        createQrCodesRequest.Intent.Type = IntentTypeType.PAYMENT_INTEGRATED_POS_SOLUTION;
        createQrCodesRequest.Intent.PosSaleDetails = new QrCodeType.IntentType.PosSaleDetailsType();
        createQrCodesRequest.UsageType = UsageTypeType.SINGLE_TIME_USE;

        String requestBody = JsonbBuilder.create().toJson(createQrCodesRequest);

        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(endpoint.concat("/v1/customer/generate-qr-codes")))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer ".concat(accessToken))
                    .header("PayPal-Request-Id", UUID.randomUUID().toString())
                    .timeout(Duration.ofSeconds(10))
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() >= 400) {
                ErrorResponseType e = JsonbBuilder.create().fromJson(response.body(), ErrorResponseType.class);

                // The debug ID doesn't always come back in the body, but it does always come back
                // in the HTTP headers.  If it wasn't present in the response, grab it out of there.
                if(e.DebugId == null) {
                    Optional<String> debugId = response.headers().firstValue("PayPal-Debug-Id");
                    debugId.ifPresent(s -> e.DebugId = s);
                }

                throw new CreateQrCodeRequestException(e);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new CreateQrCodeRequestException("Failed to request QR codes", e);
        }

        CreateQrCodesResponseType createQrCodesResponse = JsonbBuilder.create().fromJson(response.body(),
                CreateQrCodesResponseType.class);

        ArrayList<String> qrCodes = new ArrayList<>(createQrCodesResponse.QrCodes.length);
        for(QrCodeType q : createQrCodesResponse.QrCodes) {
            // The QR code is Base64-encoded, we need to decode it first
            qrCodes.add(new String(Base64.getDecoder().decode(q.Id), StandardCharsets.UTF_8));
        }

        return qrCodes.toArray(new String[0]);
    }

    /**
     * Attempts to complete a pending capture.  This has the same effect as the consumer clicking on
     * the "Pay" button from within the PayPal consumer app.
     * @param accessToken The access token obtained from the consumer's account
     * @param referenceId The reference ID obtained from the Create Capture call
     * @throws CompleteCaptureRequestException if an error occurs while completing the capture
     */
    public static void CompleteCapture(String accessToken, String referenceId) throws CompleteCaptureRequestException {
        CompleteCapture(accessToken, referenceId, "https://api-m.sandbox.paypal.com");
    }

    /**
     * Attempts to complete a pending capture.  This has the same effect as the consumer clicking on
     * the "Pay" button from within the PayPal consumer app.
     * @param accessToken The access token obtained form the consumer's account
     * @param referenceId The reference ID obtained from the Create Capture call
     * @param endpoint The endpoint against which to make API calls; e.g., "https://api-m.sandbox.paypal.com"
     * @throws CompleteCaptureRequestException if an error occurs while completing the capture
     */
    public static void CompleteCapture(String accessToken, String referenceId, String endpoint) throws CompleteCaptureRequestException {
        CompleteCaptureRequestType completeCaptureRequest = new CompleteCaptureRequestType();
        completeCaptureRequest.ReferenceId = referenceId;

        String requestBody = JsonbBuilder.create().toJson(completeCaptureRequest);

        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(endpoint.concat("/v2/retail/complete-capture")))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer ".concat(accessToken))
                    .timeout(Duration.ofSeconds(20))
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() >= 400) {
                ErrorResponseType e = JsonbBuilder.create().fromJson(response.body(), ErrorResponseType.class);

                // The debug ID doesn't always come back in the body, but it does always come back
                // in the HTTP headers.  If it wasn't present in the response, grab it out of there.
                if(e.DebugId == null) {
                    Optional<String> debugId = response.headers().firstValue("PayPal-Debug-Id");
                    debugId.ifPresent(s -> e.DebugId = s);
                }

                throw new CompleteCaptureRequestException(e);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new CompleteCaptureRequestException("Failed to request QR codes", e);
        }
    }

    public static class PayPalException extends Exception {
        public ErrorResponseType PayPalResponse;
        public PayPalException(String message, Throwable cause) {
            super(message, cause);
        }

        public PayPalException(String message) {
            super(message);
        }
    }

    public static class AccessTokenException extends PayPalException {
        public AccessTokenException(ErrorResponseType response) {
            super("PayPal returned an error while requesting an access token");
            PayPalResponse = response;
        }
        public AccessTokenException(String message, Throwable cause) {
            super(message, cause);
        }
        public AccessTokenException(String message) {
            super(message);
        }
    }

    public static class CreateQrCodeRequestException extends PayPalException {
        public CreateQrCodeRequestException(ErrorResponseType response) {
            super("PayPal returned an error while requesting QR codes");
            PayPalResponse = response;
        }
        public CreateQrCodeRequestException(String message, Throwable cause) {
            super(message, cause);
        }
        public CreateQrCodeRequestException(String message) {
            super(message);
        }
    }

    public static class CompleteCaptureRequestException extends PayPalException {
        public CompleteCaptureRequestException(ErrorResponseType response) {
            super("PayPal returned an error while completing the capture");
            PayPalResponse = response;
        }
        public CompleteCaptureRequestException(String message, Throwable cause) {
            super(message, cause);
        }
        public CompleteCaptureRequestException(String message) {
            super(message);
        }
    }

    public static class CaptureRequestException extends PayPalException {
        public CaptureRequestException(ErrorResponseType response) {
            super("PayPal returned an error while creating the capture");
            PayPalResponse = response;
        }
        public CaptureRequestException(String message, Throwable cause) {
            super(message, cause);
        }
        public CaptureRequestException(String message) {
            super(message);
        }
    }

 }
