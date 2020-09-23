package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class ErrorDetailsType {
    /**
     * The field that caused the error.  If this field is in the body, set this value to
     * the field's JSON pointer value.  Required for client-side errors.
     */
    @JsonbProperty("field")
    public String Field;

    /**
     * The value of the field that caused the error.
     */
    @JsonbProperty("value")
    public String Value;

    /**
     * The location of the field that caused the error.
     */
    @JsonbProperty("location")
    public ErrorLocationType location;

    /**
     * The unique, fine-grained application-level error code.
     */
    @JsonbProperty("issue")
    public String Issue;

    /**
     * The human-readable description for an issue.  The description can change over the lifetime
     * of an API, so clients must not depend on this value.
     */
    @JsonbProperty("description")
    public String Description;
}
