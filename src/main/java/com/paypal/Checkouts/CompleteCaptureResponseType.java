package com.paypal.Checkouts;

import com.paypal.AmountType;
import com.paypal.HATEOASLinkType;
import jakarta.json.bind.annotation.JsonbProperty;

public class CompleteCaptureResponseType {
    /**
     * The amount approved by the consumer.
     */
    @JsonbProperty("amount_approved")
    public AmountType AmountApproved;

    /**
     * HATEOAS links.
     *
     * Max length: 5 items
     */
    @JsonbProperty("links")
    public HATEOASLinkType Links[];
}
