package com.paypal.Checkouts;

import com.paypal.AmountType;
import com.paypal.FundingPreferenceTypeType;
import com.paypal.TipConvenienceFeeType;
import jakarta.json.bind.annotation.JsonbProperty;

public class CompleteCaptureRequestType {
    /**
     * Reference ID of the transaction that is being processed or just completed.  This is
     * short-lived and is different from the transaction ID.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("reference_id")
    public String ReferenceId;

    /**
     * Indicates whether to continue with the completion of the transaction or abort it
     */
    @JsonbProperty("complete_payment")
    public Boolean CompletePayment;

    /**
     * The tip(s) or convenience fee(s) included in the total.
     *
     * Max length: 3 items
     */
    @JsonbProperty("tip_convenience_fees")
    public TipConvenienceFeeType TipConvenienceFees[];

    /**
     * The amount being approved by the consumer. (?)
     */
    @JsonbProperty("amount")
    public AmountType Amount;

    /**
     * This can be used to send the encrypted financial instrument identifier to process
     * the transaction.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("financial_instrument_id")
    public String FinancialInstrumentId;

    /**
     * Information about the funding preference that will be used for the transaction.
     */
    @JsonbProperty("funding_preference")
    public FundingPreferenceType FundingPreference;

    /**
     * Metadata associated with the external funding source. (?)
     *
     * Min length: 3 characters
     * Max length: 2000 characters
     */
    @JsonbProperty("external_funding_metadata")
    public String ExternalFundingMetadata;

    /**
     * PayPal Credit financing code for the transaction.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("credit_financing_code")
    public String CreditFinancingCode;

    public static class FundingPreferenceType {
        /**
         * The funding preference type.
         */
        @JsonbProperty("type")
        public FundingPreferenceTypeType Type;

        /**
         * Specifies the financial instrument ID in the PayPal Wallet.  Mandatory when type is DIRECT or EXPLICIT.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("instrument_id")
        public String InstrumentId;
    }
}
