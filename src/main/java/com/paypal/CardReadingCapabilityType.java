package com.paypal;

public enum CardReadingCapabilityType {
    /** Terminal capability unknown. */
    TERMINAL_UNKNOWN,

    /** Terminal not used. */
    TERMINAL_NOT_USED,

    /** Has ability to read magnetic stripe card. */
    READ_MAGNETIC_STRIPE_CARD,

    /** Has capability to read integrated circuit (chip) card. */
    READ_IC_CARD,

    /** Has capability to read contactless chip card. */
    READ_CONTACTLESS_CHIP,

    /** Has capability to read contactless magnetic stripe card. */
    READ_CONTACTLESS_MAGNETIC_STRIPE_CARD,

    /** Has manual entry capability. */
    MANUALLY_ENTERED,

    /** Bar code scanned. */
    BAR_CODE,

    /** Information not taken from card e.g. RFID. */
    NON_CARD,

    /** OCR reading.  Image grab of the card and OCR. */
    OCR,

    /** Card data on file was used. */
    DATA_ON_FILE,

    /** Terminal has no card capture capability. */
    NO_CARD_CAPTURE_CAPABILITY,

    /** Terminal has card capture capability. */
    HAS_CARD_CAPTURE_CAPABILITY,

    /** Unknown card capture capability. */
    UNKNOWN_CARD_CAPTURE_CAPABILITY,

    /** Hybrid - Chip and contactless. */
    IC_CARD_CONTACTLESS_HYBRID,

    /** Secure Electronic Transaction (SET) with certificate. */
    SECURE_ELECTRONIC_TRANSACTION,

    /** Secure Electronic Transaction (SET) without certificate. */
    SET_WITHOUT_CERTIFICATE_USED,

    /** Channel encrypted e-commerce (SSL). */
    ECOMMERCE_TERMINAL,

    /** Chip fallback. */
    CHIP_FALLBACK,

    /** Voice authorization. */
    VOICE_AUTHORIZATION,

    /** Voice response unit. */
    VOICE_RESPONSE_UNIT,

    /** Batch authorization. */
    BATCH_AUTHORIZATION,

    /** Batch authorization cash access. */
    BATCH_AUTHORIZATION_CASH_ACCESS,

    /** Mobile device. */
    MOBILE_DEVICE,

    /** Other capability. */
    OTHER
}
