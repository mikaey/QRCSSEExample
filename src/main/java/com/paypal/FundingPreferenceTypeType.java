package com.paypal;

public enum FundingPreferenceTypeType {
    /** Balance will be used along with the default or selected funding instrument for the transaction. */
    DIRECT,

    /** Only the selected funding instrument will be used for the transaction. */
    EXPLICIT,

    /** Auto Topup will be performed if needed (and previously setup) for the transaction. */
    TOPUP
}
