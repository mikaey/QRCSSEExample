package com.paypal;

public enum TerminalTypeType {
    /** No terminal. */
    NONE,

    /** Unknown terminal. */
    UNKNOWN,

    /** Self service terminal with agents available to assist. */
    SELF_SERVICE,

    /** Electronic cash register. */
    CASH_REGISTER,

    /** Swipe terminals at petrol stations. */
    FUEL,

    /** Self checkout, kiosk, and other terminals where there are no human service agents. */
    UNATTENDED,

    /** Merchant employee attended terminal. */
    ATTENDED,

    /** Terminal is a system that manages mail order and/or telephone order transactions. */
    MAIL_ORDER_TELEPHONE_ORDER,

    /** Terminal is a system that manages electronic commerce transactions. */
    ELECTRONIC_COMMERCE,

    /** Terminal is a system that manages mobile commmerce transactions. */
    MOBILE_COMMERCE,

    /** Terminal is a system that manages recurring transactions. */
    RECURRING_TRANSACTION,

    /** System uses stored details for the transaction. */
    STORED_DETAILS,

    /** An automated device that is operated solely by the cardholder, e.g. an automated fuel dispenser. */
    CARDHOLDER_ACTIVATED_TERMINAL,

    /** Terminal is an ATM that is on bank premises. */
    ATM_ON_BANK_PREMISES,

    /** Terminal is an ATM that is off bank premises. */
    ATM_OFF_BANK_PREMISES,

    /** Terminal is a system that manages deferred transactions. */
    DEFERRED_TRANSACTION,

    /** Terminal is a system that manages installments. */
    INSTALLMENT_TRANSACTION
}
