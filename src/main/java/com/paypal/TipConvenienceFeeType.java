package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class TipConvenienceFeeType {
    /**
     * The amount of the tip or convenience fee.
     */
    @JsonbProperty("amount")
    public AmountType Amount;

    /**
     * The tip or convenience fee, specified as a percentage of the item total, expressed
     * as a fixed-point, signed decimal number.  For example, use <code>19.99</code> to
     * indicate a 19.99% tip. (?)
     *
     * Match regex: ^((-?\d+)|(-?\d*\.\d+))$
     */
    @JsonbProperty("percentage")
    public String Percentage;
}
