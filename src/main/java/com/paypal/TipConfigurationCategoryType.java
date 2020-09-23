package com.paypal;

public enum TipConfigurationCategoryType {
    /**
     * Tip configuration is Tip Only type.  This value states that transaction contains only
     * tip amount and no payment.
     * */
    TIP_ONLY,

    /**
     * Tip configuration is Tip Option type.  This value states that the transaction contains
     * tip amount as well as payment amount.
     */
    TIP_OPTION
}
