package com.paypal;

public enum DataVerifiedType {
    /** Street number in line 1 of the address. */
    STREET_NUMBER,

    /** Line 1 of the address. */
    ADDRESS_LINE1,

    /** Line 2 of the address. */
    ADDRESS_LINE2,

    /** City of the address. */
    CITY,

    /** State of the address. */
    STATE,

    /** Country of the address. */
    COUNTRY,

    /** Postal Code of the address. */
    POSTAL_CODE,

    /** Postal Code Extension of the address. */
    POSTAL_CODE_EXTENSION,

    /** First name. */
    FIRST_NAME,

    /** Last name. */
    LAST_NAME,

    /** Email Address. */
    EMAIL_ADDRESS,

    /** Country code of the phone number. */
    PHONE_NUMBER_COUNTRY_CODE,

    /** Phone number. */
    PHONE_NUMBER,

    /** Extension of the phone number. */
    PHONE_NUMBER_EXTENSION,

    /** The Card Identifier (CVV2) printed on the back of the payment card. */
    CARD_IDENTIFIER
}
