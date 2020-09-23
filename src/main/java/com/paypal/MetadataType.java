package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class MetadataType {
    /**
     * Name of custom data entity.  <strong>Required.</strong>
     *
     * Max length: 256 characters
     */
    @JsonbProperty(value = "name", nillable = true)
    public String Name;

    /**
     * Value of custom data entity.  <strong>Required.</strong>
     *
     * Max length: 256 characters
     */
    @JsonbProperty(value = "value", nillable = true)
    public String Value;
}
