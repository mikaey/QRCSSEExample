package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class SSEErrorResponseType {
    @JsonbProperty("datacontenttype")
    public String DataContentType;

    @JsonbProperty("data")
    public ErrorResponseType Data;

    @JsonbProperty("type")
    public String Type;

    @JsonbProperty("paypal_debug_id")
    public String PayPalDebugId;
}
