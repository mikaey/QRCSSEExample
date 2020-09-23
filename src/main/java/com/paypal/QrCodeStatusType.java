package com.paypal;

public enum QrCodeStatusType {
    /** Specifies the state when the QR code is activated and can be used by customers. */
    ACTIVE,

    /** Specifies the state when the QR code is paused for some time by the owner. */
    PAUSED,

    /** Specifies the state when the QR code is deleted. */
    TERMINATED,

    /** Specifies the state when the QR code is created but not yet activated. */
    CREATED,

    /** Specifies the state when due to risk issues the QR code is put on hold. */
    INVALID
}
