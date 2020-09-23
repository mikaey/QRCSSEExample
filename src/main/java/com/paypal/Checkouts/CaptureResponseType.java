package com.paypal.Checkouts;

import com.paypal.*;
import jakarta.json.bind.annotation.JsonbProperty;

public class CaptureResponseType {
    /**
     * Results of payment execution.
     */
    @JsonbProperty("transaction_result")
    public TransactionResultType TransactionResult;

    /**
     * PayPal info related to the payer.
     */
    @JsonbProperty("payer_info")
    public PayerInfoType PayerInfo;

    /**
     * Details of the business (corporate) level of the merchant (seller) in this transaction.
     */
    @JsonbProperty("business_details")
    public BusinessDetailsType BusinessDetails;

    /**
     * Data from PayPal to be printed on the retailer's paper receipt.
     *
     * Max length: 1000 characters
     */
    @JsonbProperty("receipt_data")
    public String ReceiptData;

    /**
     * Results of the verification checks.
     *
     * Max length: 100 items
     */
    @JsonbProperty("verification_results")
    public VerificationResultsType VerificationResults[];

    /**
     * HATEOAS links.
     *
     * Max length: 5 items
     */
    @JsonbProperty("links")
    public HATEOASLinkType Links[];

    public static class TransactionResultType {
        /**
         * Externalized transaction ID of the authorization or capture.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("transaction_id")
        public String TransactionId;

        /**
         * This is short-lived and can be used to identify the transaction request while it
         * is being processed and can be used to lookup the transaction.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("reference_id")
        public String ReferenceId;

        /**
         * The status of the transaction.
         */
        @JsonbProperty("status")
        public TransactionStatusType Status;

        /**
         * Will be optionally populated with additional information.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("message")
        public String Message;

        /**
         * The amount approved.
         */
        @JsonbProperty("amount_approved")
        public AmountType AmountApproved;

        /**
         * The tip(s) or convenience fee(s) included in the total.
         */
        @JsonbProperty("tip_convenience_fees")
        public TipConvenienceFeeType TipConvenienceFees[];

        /**
         * The 6-character approval code for the transaction.
         */
        @JsonbProperty("approval_code")
        public String ApprovalCode;
    }

    public static class PayerInfoType {
        /**
         * The consumer's PayPal payer ID.
         *
         * Match regex: ^[2-9A-HJ-NP-Z]{13}$
         */
        @JsonbProperty(value = "account_id", nillable = true)
        public String AccountId;

        /**
         * Set to true for business accounts.
         */
        @JsonbProperty("purchase_order_required")
        public Boolean PurchaseOrderRequired;

        /**
         * The name of the party.
         */
        @JsonbProperty("name")
        public PersonNameType Name;

        /**
         * URL of the customer's profile picture.  This will typically only be available for users
         * who have used the PayPal Mobile application and set a profile picture.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("profile_picture_url")
        public String ProfilePictureUrl;

        /**
         * The email address of the customer's PayPal account.
         *
         * Max length: 254 characters
         */
        @JsonbProperty("email_address")
        public String EmailAddress;
    }

    public static class VerificationResultsType {
        /**
         * Indicates which data element was verified. (?)
         */
        @JsonbProperty("data_verified")
        public DataVerifiedType DataVerified;

        /**
         * Indicates the level of verification that occurred on the data element. (?)
         */
        @JsonbProperty("data_verification_level")
        public DataVerificationLevelType DataVerificationLevel;

        /**
         * Indicates the amount of time that the given data has been on file with PayPal, in
         * <a href="https://www.iso.org/standard/40874.html">ISO 8601-format</a>.  For example,
         * 7 days would be specified as <code>P7D</code>. (?)
         */
        @JsonbProperty("duration_on_file")
        public String DurationOnFile;
    }
}
