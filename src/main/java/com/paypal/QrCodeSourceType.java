package com.paypal;

public enum QrCodeSourceType {
    /** Indicates that this QR code was created as a part of scanning another QR code. */
    SCAN,

    /** Indicates that this QR code was created as part of a show flow. */
    SHOW
}
