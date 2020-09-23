package com.paypal.Checkouts;

import com.paypal.QrCodeType;
import jakarta.json.bind.annotation.JsonbProperty;

public class CreateQrCodesResponseType {
    /**
     * The common handle for the batch of QR codes created.
     *
     * Max length: 100 characters
     */
    @JsonbProperty("batch_id")
    public String BatchId;

    /**
     * Array of QR codes.
     *
     * Max length: 10 items
     */
    @JsonbProperty("qr_codes")
    public QrCodeType QrCodes[];
}
