package com.paypal;

public enum DeclineReasonsType {
    /** Account is in the negative file. */
    ACCOUNT_IN_NEGATIVE_FILE,

    /** The CVV1 on card track is not valid. */
    CVV1_MISMATCH,

    /** The transaction amount exceeds one of the limits set. */
    LIMIT_EXCEEDED,

    /** The transaction is not supported by PayPal. */
    NON_SUPPORTED_TRANSACTION,

    /** Any other reason. */
    OTHER
}
