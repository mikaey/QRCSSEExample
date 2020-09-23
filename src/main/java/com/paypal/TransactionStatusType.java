package com.paypal;

public enum TransactionStatusType {
    /** The transaction is successful. */
    SUCCESS,

    /** The transaction is still being processed. */
    PROCESSING,

    /** The transaction is accepted for further processing. */
    ACCEPTED,

    /** The transaction is waiting for input from the consumer. */
    AWAITING_USER_INPUT,

    /**
     * The transaction is aborted.  This can happen when the consumer hasn't responded
     * within a fixed pre-defined time.
     */
    ABORTED,

    /** The transaction is cancelled. */
    CANCELLED,

    /** The transaction has failed. */
    FAILED
}
