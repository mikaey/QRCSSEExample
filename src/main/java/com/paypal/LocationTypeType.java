package com.paypal;

public enum LocationTypeType {
    /** Unknown. */
    UNKNOWN,

    /** On the premises of a merchant facility. */
    MERCHANT_PREMISES,

    /** Off the premises of merchant facility (merchant terminal, remote location). */
    MERCHANT_REMOTE_LOCATION,

    /** On premises of cardholder, e.g. home PC. */
    CARD_HOLDER_PREMISES,

    /** No terminal (point-of-sale device) used, e.g. voice/ARU authorization. */
    NO_TERMINAL_USED
}
