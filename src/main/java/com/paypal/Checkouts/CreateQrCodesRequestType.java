package com.paypal.Checkouts;

import com.paypal.QrCodeType;
import jakarta.json.bind.annotation.JsonbProperty;

public class CreateQrCodesRequestType extends QrCodeType {
    /**
     * The number of QR codes that should be created as a part of this request.
     */
    @JsonbProperty("total_qr_codes")
    public Integer TotalQrCodes;
}
