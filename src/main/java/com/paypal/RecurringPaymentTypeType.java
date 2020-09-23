package com.paypal;

public enum RecurringPaymentTypeType {
    /** Not a recurring payment. */
    NONE,

    /** Recurring payment but the specific type is unknown. */
    UNKNOWN,

    /** Recurring billing agreement between buyer and merchant. */
    RECURRING_BILLING,

    /** Recurring payment made in multiple regular installments. */
    INSTALLMENTS,

    /** Recurring payment for a subscription of goods or services. */
    SUBSCRIPTION
}
