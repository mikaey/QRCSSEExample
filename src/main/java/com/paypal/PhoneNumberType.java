package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class PhoneNumberType {
    /**
     * The country calling code, as defined by the International Telecommunications Union in
     * <a href="https://www.itu.int/rec/T-REC-E.164/en">publication E.164</a>.  <strong>Required.</strong>
     *
     * Min length: 1 character
     * Max length: 3 characters
     * Match regex: <code>^\d{1,3}$</code>
     */
    @JsonbProperty(value = "country_code", nillable = true)
    public String CountryCode;

    /**
     * The intra-country phone number, in its canonical international numbering plan format as
     * specified by the International Telecommunications Union in
     * <a href="https://www.itu.int/rec/T-REC-E.164/en">publication E.164</a>.  <strong>Required.</strong>
     *
     * Min length: 1 character
     * Max length: 14 characters
     * Match regex: <code>^\d{1,14}$</code>
     */
    @JsonbProperty(value = "national_number", nillable = true)
    public String NationalNumber;

    /**
     * The extension number.
     *
     * Min length: 1 character
     * Max length: 15 characters
     * Match regex: <code>^\d{1,15}$</code>
     */
    @JsonbProperty("extension_number")
    public String ExtensionNumber;
}
