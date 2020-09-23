package com.paypal;

public enum DataVerificationLevelType {
    /** Data verification was not done. */
    VERIFICATION_NOT_PERFORMED,

    /** Data was partially matched. */
    PARTIALLY_MATCHED,

    /** Data was fully verified. */
    FULLY_MATCHED,

    /** Data did not match. */
    NOT_MATCHED
}
