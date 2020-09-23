package com.paypal;

public enum AuthorizationSourceType {
    /** Originator of the transaction. */
    MERCHANT,

    /** Customer or buyer. */
    BUYER,

    /** The processor that processes transactions on behalf of the acquirer (merchant). */
    ACQUIRING_PROCESSOR,

    /** The merchant. */
    ACQUIRER,

    /** The network routing the transaction. */
    NETWORK,

    /** The processor that processes transactions on behalf of the issuer (PayPal). */
    ISSUING_PROCESSOR,

    /** The entity who issues the payment vehicle, i.e. PayPal. */
    ISSUER
}
