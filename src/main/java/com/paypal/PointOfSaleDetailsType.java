package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class PointOfSaleDetailsType {
    /**
     * Details about the physical store where this transaction is placed.
     */
    @JsonbProperty("store_details")
    public StoreDetailsType StoreDetails;

    /**
     * Details about the point-of-sale terminal (cash register) where this transaction is placed.
     */
    @JsonbProperty("terminal_details")
    public TerminalDetailsType TerminalDetails;

    /**
     * Name of the retailer.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("retailer_name")
    public String RetailerName;

    /**
     * Unique PayPal-assigned ID of the retailer.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("retailer_id")
    public String RetailerId;

    /**
     * PayPal-provided ID.  For Sandbox, use the value <code>APP-80W284485P519543T</code>.
     *
     * Max length: 256 characters
     */
    @JsonbProperty("application_id")
    public String ApplicationId;

    public static class StoreDetailsType {
        /**
         * The store ID of the location where the purchase is being made, as provided in the
         * Create Location API.  <strong>Required.</strong>
         *
         * Min length: 1 character
         * Max length: 256 characters
         */
        @JsonbProperty(value = "external_id", nillable = true)
        public String ExternalId;

        /**
         * The publicly visible name of the store.
         *
         * Min length: 1 character
         * Max length: 256 characters
         */
        @JsonbProperty("name")
        public String Name;

        /**
         * A short message that is displayed on the store details screen.
         *
         * Min length: 1 character
         * Max length: 256 characters
         */
        @JsonbProperty("display_message")
        public String DisplayMessage;

        /**
         * Indicates whether this location is fixed (for example, a retail store)
         * or mobile (for example, a food truck or roaming vendor).
         */
        @JsonbProperty("mobility")
        public MobiltyType Mobility;

        /**
         * The address of the retail location where the purchase is being made.
         */
        @JsonbProperty("address")
        public AddressType Address;

        /**
         * The telephone number of the retail location where the purchase is being made.
         */
        @JsonbProperty("phone_number")
        public PhoneNumberType PhoneNumber;

        /**
         * The geographic coordinates of the retail location where the purchase is being made.
         */
        @JsonbProperty("geo_location")
        public GeoLocationType geo_location;

        /**
         * The type of gratuity that is accepted by this store.
         */
        @JsonbProperty("gratuity_type")
        public GratuityTypeType GratuityType;

        /**
         * The PayPal payer ID of the retailer that is accepting payment for this transaction.
         *
         * Match regex: <code>^[2-9A-HJ-NP-Z]{13}$</code>
         */
        @JsonbProperty("retailer_account_id")
        public String RetailerAccountId;
    }

    public static class TerminalDetailsType {
        /**
         * The ID of the point-of-sale terminal (cash register) as defined by the retailer.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("external_id")
        public String ExternalId;

        /**
         * Merchant's name of the point-of-sale client used in this transaction.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("name")
        public String Name;

        /**
         * The type of terminal used for this transaction.
         */
        @JsonbProperty("type")
        public TerminalTypeType Type;

        /**
         * Manufacturer's model number of the point-of-sale client used in this transaction.
         *
         * Max length: 256 characters
         */
        @JsonbProperty("model")
        public String Model;

        /**
         * The list of all security attributes supported by the terminal used for the transaction.
         *
         * Max length: 32 items
         */
        @JsonbProperty("security_attributes")
        public SecurityAttributesType SecurityAttributes[];

        /**
         * List of capabilities the terminal has for reading card information.
         *
         * Max length: 32 items
         */
        @JsonbProperty("card_reading_capabilities")
        public CardReadingCapabilityType CardReadingCapabilities[];

        /**
         * Indicates which card reading capability was used on this transaction. (?)
         */
        @JsonbProperty("card_reading_capability_used")
        public CardReadingCapabilityType CardReadingCapabilityUsed;

        /**
         * List of capabilities the terminal has for verifying the card holder.
         *
         * Max length: 32 items
         */
        @JsonbProperty("card_holder_verification_capabilities")
        public CardHolderVerificationCapabilityType CardHolderVerificationCapabilities[];

        /**
         * Indicates which card holder verification capability was used on this transaction. (?)
         */
        @JsonbProperty("card_holder_verification_capability_used")
        public CardHolderVerificationCapabilityType CardHolderVerificationCapabilityUsed;

        /**
         * Information about the location of a point-of-sale terminal.
         */
        @JsonbProperty("location")
        public TerminalLocationType Location;

        /**
         * The terminal's IP address.  This field supports both IPv4 and IPv6 addresses. (?)
         */
        @JsonbProperty("ip_address")
        public String IpAddress;

        public static class TerminalLocationType {
            /**
             * The address of the location where the point-of-sale temrinal is located.
             */
            @JsonbProperty("address")
            public AddressType Address;

            /**
             * The type of premise where the point-of-sale terminal is located.
             */
            @JsonbProperty("location_type")
            public LocationTypeType LocationType;

            /**
             * The geographic latitude and longitude coordinates of the point-of-sale terminal.
             */
            @JsonbProperty("geo_location")
            public GeoLocationType GeoLocation;
        }
    }
}
