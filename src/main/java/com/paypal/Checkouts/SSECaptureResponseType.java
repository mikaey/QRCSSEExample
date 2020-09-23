package com.paypal.Checkouts;

import jakarta.json.bind.annotation.JsonbProperty;

public class SSECaptureResponseType {
    @JsonbProperty("datacontenttype")
    public String DataContentType;

    @JsonbProperty("data")
    public CaptureResponseType Data;

    @JsonbProperty("type")
    public String Type;

    @JsonbProperty("paypal_debug_id")
    public String PayPalDebugId;
}
