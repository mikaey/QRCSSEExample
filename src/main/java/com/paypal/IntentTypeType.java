package com.paypal;

public enum IntentTypeType {
    /** Specifies that the intent for this QR code is to donate. */
    DONATION,

    /** Specifies that the intent for this QR code is to make payments for goods and services. */
    PAYMENT_P2P_GOODS_AND_SERVICES,

    /** Specifies that the intent for this QR code is peer-to-peer payments. */
    PAYMENT_P2P,

    /** Specifies that the intent for this QR code is marketing. */
    MARKETING,

    /** Specifies that the intent for this QR code is payment through the integrated POS solution. */
    PAYMENT_INTEGRATED_POS_SOLUTION
}
