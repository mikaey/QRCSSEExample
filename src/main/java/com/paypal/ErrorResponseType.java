package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class ErrorResponseType {
    /**
     * The human-readable, unique name of the error.
     */
    @JsonbProperty("name")
    public String Name;

    /**
     * The message that describes the error.
     */
    @JsonbProperty("message")
    public String Message;

    /**
     * The PayPal internal ID.  Used for correlation purposes.
     */
    @JsonbProperty("debug_id")
    public String DebugId;

    /**
     * The information link, or URI, that shows detailed information about this error
     * for the developer.
     */
    @JsonbProperty("information_link")
    public String InformationLink;

    /**
     * An array of additional details about the error.
     */
    @JsonbProperty("details")
    public ErrorDetailsType Details[];

    /**
     * An array of request-related <a href="https://developer.paypal.com/docs/api/reference/api-responses/#hateoas-links">HATEOAS
     * links</a>.
     */
    @JsonbProperty("links")
    public HATEOASLinkType Links[];
}
