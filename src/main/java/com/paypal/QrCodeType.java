package com.paypal;

import com.paypal.*;
import jakarta.json.bind.annotation.JsonbProperty;

public class QrCodeType {

    /**
     * UTM campaign.
     *
     * Max length: 50 characters
     */
    @JsonbProperty("utm_campaign")
    public String UtmCampaign;

    /**
     * UTM source.
     *
     * Max length: 50 characters
     */
    @JsonbProperty("utm_source")
    public String UtmSource;

    /**
     * The organization this QR code belongs to.
     */
    @JsonbProperty("provider")
    public ProviderType Provider;

    /**
     * The identifier of the owner of this QR code.
     *
     * Max length: 50 characters
     */
    @JsonbProperty("owner_id")
    public String OwnerId;

    /**
     * What type of identifier was provided in the <code>owner_id</code> property.
     */
    @JsonbProperty("owner_id_type")
    public OwnerIdTypeType OwnerIdType;

    /**
     * The identifier of the tenant of this QR code.
     */
    @JsonbProperty("tenant_name")
    public TenantNameType TenantName;

    /**
     * Unique QR code identifier.
     *
     * Max length: 250 characters
     */
    @JsonbProperty("id")
    public String Id;

    /**
     * The geographic coordinates of the user's location at the time of the request. (?)
     */
    @JsonbProperty("geo_coordinates")
    public GeoLocationType GeoCoordinates;

    /** The date and time of when the QR code was last modified, in
     * <a href="https://tools.ietf.org/html/rfc3339#section-5.6">Internet date and time format</a>.
     * For example, Monday, September 21, 2020 at 3:35:14pm Central Daylight Time would be expressed as
     * <code>2020-09-21T15:35:14-05:00</code>.  Seconds are required while fractional seconds are optional.
     */
    @JsonbProperty("modified_time")
    public String ModifiedTime;

    /**
     * Specifies whether the QR code is presented by a consumer or merchant.
     */
    @JsonbProperty("type")
    public QrCodeTypeType Type;

    /**
     * Specifies the type of medium where the QR code will be displayed.
     */
    @JsonbProperty("display_medium")
    public DisplayMediumType DisplayMedium;

    /**
     * The status of the QR code.
     */
    @JsonbProperty("status")
    public QrCodeStatusType Status;

    /**
     * Specifies whether tips are accepted for this QR code of merchant.
     */
    @JsonbProperty("tips_accepted")
    public Boolean TipsAccepted;

    /**
     * Meta data for the QR code.
     */
    @JsonbProperty("metadata")
    public MetadataType Metadata[];

    /**
     * Specifies details required to define the intent of the QR code.
     */
    @JsonbProperty("intent")
    public IntentType Intent;

    /**
     * Tip Configuration object.
     */
    @JsonbProperty("tip_configuration")
    public TipConfigurationType TipConfiguration;

    /**
     * Determines the usage type behavior of the QR code.
     */
    @JsonbProperty("usage_type")
    public UsageTypeType UsageType;

    /**
     * Specifies client metadata ID passed by the client in the header.
     *
     * Max length: 200 characters
     */
    @JsonbProperty("client_metadata_id")
    public String ClientMetadataId;

    /**
     * The date/time that the QR code expires, in
     * <a href="https://tools.ietf.org/html/rfc3339#section-5.6">Internet date and time format</a>.
     * For example, Monday, September 21, 2020 at 3:26:54pm Central Daylight Time would be specified as
     * <code>2020-09-21T15:26:54-05:00</code>.  Seconds are required while fractional sections are optional.
     */
    @JsonbProperty("soft_expiration_time")
    public String SoftExpirationTime;

    public static class IntentType {
        /**
         * Specifies the intent of the QR code.
         */
        @JsonbProperty("type")
        public IntentTypeType Type;

        /**
         * Specifies the resource locator for the step after scanning the QR code.
         *
         * Max length: 200 characters
         */
        @JsonbProperty("redirection_url")
        public String RedirectionUrl;

        /**
         * Specifies the POS transaction details required to paint the confirm payment page on app.
         */
        @JsonbProperty("pos_sale_details")
        public PosSaleDetailsType PosSaleDetails;

        /** Indicates the initiator for payment process. */
        @JsonbProperty("payment_initiator")
        public PaymentInitiatorType PaymentInitiator;

        public static class PosSaleDetailsType {
            /**
             * The status of the POS transaction.
             */
            @JsonbProperty("transaction_status")
            public PosTransactionStatusType TransactionStatus;

            /**
             * The amount of the transaction.
             */
            @JsonbProperty("transaction_amount")
            public AmountType TransactionAmount;

            /**
             * The ID that uniquely identifies the funding instrument used in the transaction.
             *
             * Max length: 200 characters
             */
            @JsonbProperty("funding_instrument_id")
            public String FundingInstrumentId;

            /**
             * The unique identifier referencing to payment details.
             *
             * Max length: 100 characters
             */
            @JsonbProperty("payment_reference_id")
            public String PaymentReferenceId;

            /**
             * The PayPal payer ID of the merchant.
             *
             * Match regex: ^[2-9A-HJ-NP-Z]{13}$
             */
            @JsonbProperty("merchant_account_id")
            public String MerchantAccountId;

            /**
             * The merchant's address.
             */
            @JsonbProperty("merchant_address")
            public AddressType MerchantAddress;

            /**
             * Business name.
             *
             * Max length: 200 characters
             */
            @JsonbProperty("merchant_name")
            public String MerchantName;

            /**
             * MCC for the merchant receiving the payment.
             *
             * Match regex: ^\d{4}$
             */
            @JsonbProperty("merchant_category_code")
            public String MerchantCategoryCode;

            /**
             * The PayPal payer ID of the partner who is facilitating the payment (?)
             *
             * Match regex: ^[2-9A-HJ-NP-Z]{13}$
             */
            @JsonbProperty("partner_account_id")
            public String PartnerAccountId;

            /**
             * The ID that uniquely identifies a store/location.
             *
             * Max length: 100 characters
             */
            @JsonbProperty("location_id")
            public String LocationId;

            /**
             * The ID that uniquely identifies a retailer.
             *
             * Max length: 100 characters
             */
            @JsonbProperty("retailer_id")
            public String RetailerId;

            /**
             * Unique identifier associated to a terminal at the merchant's location.
             *
             * Max length: 100 characters
             */
            @JsonbProperty("terminal_id")
            public String TerminalId;

            /**
             * The URL to the store logo.
             *
             * Max length: 200 characters
             */
            @JsonbProperty("logo_url")
            public String LogoUrl;

            /**
             * Captures the source/flow as a result of which the QR code was created.
             */
            @JsonbProperty("qr_code_source")
            public QrCodeSourceType QrCodeSource;

            /**
             * Indicates the point of initiation method used for this partner QR code.
             */
            @JsonbProperty("point_of_initiation_method")
            public PointOfInitiationMethodType PointOfInitiationMethod;
        }
    }

    public static class TipConfigurationType {
        /**
         * Unique tip configuration identifier.
         *
         * Max length: 64 characters
         */
        @JsonbProperty("id")
        public String Id;

        /**
         * Tip configuration payment type category.
         */
        @JsonbProperty("category")
        public TipConfigurationCategoryType Category;

        /**
         * Tip calculation format.
         */
        @JsonbProperty("threshold_type")
        public TipThresholdTypeType ThresholdType;

        /**
         * Tip value suggestions if tip type is fixed amount.
         *
         * Min length: 3 items
         * Max length: 5 items
         */
        @JsonbProperty("amount_settings")
        public AmountType AmountSettings[];

        /**
         * Tip value suggestions if tip type is percentage of actual payment.
         *
         * Min length: 3 items
         * Max length: 5 items
         */
        @JsonbProperty("percentage_settings")
        public String PercentageSettings[];

        /**
         * This value indicates whether customized tip amounts should be allowed.
         */
        @JsonbProperty("allow_custom")
        public Boolean AllowCustom;

        /**
         * Status of tip configuration.
         */
        @JsonbProperty("status")
        public TipConfigurationStatusType Status;

        /**
         * Date/time that the tip configuration was updated, in
         * <a href="https://tools.ietf.org/html/rfc3339#section-5.6">Internet date and time format</a>.
         * For example, Monday, September 21, 2020 at 3:18:00PM Central Daylight Time would be specified
         * as <code>2020-09-21T15:18:00-05:00</code>.  Seconds are required while fractional seconds are
         * optional. (?)
         */
        @JsonbProperty("update_time")
        public String UpdateTime;

        /**
         * Default Tip Configuration object.
         */
        @JsonbProperty("default_configurations")
        public DefaultConfigurationsType DefaultConfigurations;

        public static class DefaultConfigurationsType {
            /**
             * Array of default tip value suggestions if tip type is fixed amount.
             *
             * Max length: 5 items
             */
            @JsonbProperty("amount_settings")
            public AmountType AmountSettings[];

            /**
             * Array of default tip value suggestions if tip type is percentage of actual payment.
             *
             * Max length: 5 items
             */
            @JsonbProperty("percentage_settings")
            public String PercentageSettings[];

            /**
             * This value indicates whether customized tip amounts are allowed.
             */
            @JsonbProperty("allow_custom")
            public Boolean AllowCustom;
        }
    }
}
