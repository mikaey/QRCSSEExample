package com.paypal;

public enum SecurityAttributesType {
    /** Unknown characteristic. */
    UNKNOWN,

    /** Private network. */
    PRIVATE_NETWORK,

    /** Open network (Internet). */
    OPEN_NETWORK,

    /** Channel MACing. */
    CHANNEL_MACING,

    /** Pass-through MACing. */
    PASS_THROUGH_MACING,

    /** Channel encryption. */
    CHANNEL_ENCRYPTION,

    /** End-to-end encryption. */
    END_TO_END_ENCRYPTION,

    /** Private algorithm encryption. */
    PRIVATE_ALGORITHM_ENCRYPTION,

    /** PKI encryption. */
    PKI_ENCRYPTION,

    /** Card holder managed end-to-end encryption. */
    CARDHOLDER_MANAGED_E2E_ENCRYPTION,

    /** Card holder managed point-to-point encryption. */
    CARDHOLDER_MANAGED_P2P_ENCRYPTION,

    /** Merchant managed end-to-end encryption. */
    MERCHANT_MANAGED_E2E_ENCRYPTION,

    /** Merchant managed point-to-point encryption. */
    MERCHANT_MANAGED_P2P_ENCRYPTION,

    /** Acquirer managed end-to-end encryption. */
    ACQUIRER_MANAGED_E2E_ENCRYPTION,

    /** Acquirer managed point-to-point encryption. */
    ACQUIRER_MANAGED_P2P_ENCRYPTION,

    /** Private algorithm MACing. */
    PRIVATE_ALGORITHM_MACING,

    /** Standard algorithm MACing. */
    STANDARD_ALGOIRTHM_MACING,

    /** SET without certificate. */
    SET_WITHOUT_CERTIFICATE_USED,

    /** Non-secure e-commerce transaction. */
    NO_ENCRYPTION
}
