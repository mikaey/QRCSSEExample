package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class AccessTokenResponseType {
    /**
     * A list of space-separated permissions associated with the access token.  The scopes
     * depend on the type of credentials in the request.  Some scopes require both client ID
     * and secret, so those scopes are not returned if only the client ID is provided.
     */
    @JsonbProperty("scope")
    public String Scope;

    /**
     * The authorization-server issued access token.
     *
     * Max length: 1024 characters
     */
    @JsonbProperty("access_token")
    public String AccessToken;

    /**
     * The issued token type, as described in <a href="https://tools.ietf.org/html/rfc6749#section-7.1">OAuth2.0
     * RFC6749 Section 7.1</a>.  Value is case insensitive.
     */
    @JsonbProperty("token_type")
    public TokenTypeType TokenType;

    /**
     * The refresh token.  Use it to get new access tokens by using the same authorization grant as
     * described in <a href="https://tools.ietf.org/html/rfc6749#section-6">OAuth2.0 RFC6749 Section 6</a>.
     * Value is returned for only <code>POST /token</code> calls with a <code>grant_type</code> of
     * <code>authorization_code</code>.
     *
     * Max length: 1024 characters
     */
    @JsonbProperty("refresh_token")
    public String RefreshToken;

    /**
     * A legacy PayPal application ID.
     */
    @JsonbProperty("app_id")
    public String AppId;

    /**
     * The lifetime of the access token, in seconds.
     */
    @JsonbProperty("expires_in")
    public Integer ExpiresIn;

    /**
     * A one-time use random string generated from server-specific data.
     */
    @JsonbProperty("nonce")
    public String Nonce;

    /**
     * A set of space-separated codes for issues that occurred during request processing.
     * These error codes can represent validation errors, such as a bad request structure,
     * processing errors, such as risk declines due to bad IPs, or obligations for the caller
     * to take further actions, such as take a user through authentication flows and increase
     * user trust levels.  Use these codes for user messaging, user experience control, and
     * fault monitoring purposes.
     */
    @JsonbProperty("error")
    public String Error;

    /**
     * An EN-US text description of the errors encountered during processing.
     */
    @JsonbProperty("error_description")
    public String ErrorDescription;
}
