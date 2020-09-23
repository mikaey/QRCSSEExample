package com.paypal;

public enum PosTransactionStatusType {
    /** The transaction is waiting for some input from the consumer. */
    AWAITING_USER_INPUT,

    /** The transaction has failed. */
    FAILED,

    /** The transaction is cancelled. */
    CANCELLED,

    /** The transaction is aborted.  This can happen when the consumer hasn't responded within a fixed pre-defined time. */
    ABORTED
}
