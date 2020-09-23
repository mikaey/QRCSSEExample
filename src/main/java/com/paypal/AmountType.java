package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class AmountType {
    /**
     * The <a href="https://developer.paypal.com/docs/api/reference/currency-codes/">three-character ISO-4217
     * currency code</a> that identifies the currency.  <strong>Required.</strong>
     */
    @JsonbProperty(value = "currency_code", nillable = true)
    public CurrencyCodeType CurrencyCode;

    /**
     * The currency value, which must be:
     * <li>
     *     <ul>An integer for HUF, JPY, and TWD</ul>
     *     <ul>For all other currencies, a number with at most two decimal places of precision</ul>
     * </li>
     *
     * <strong>Required.</strong>
     */
    @JsonbProperty(value = "value", nillable = true)
    public String Value;
}
