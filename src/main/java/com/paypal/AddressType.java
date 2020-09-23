package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class AddressType {
    /**
     * The first line of the address, such as the number and/or street.  For example, "173 Drury Lane".
     *
     * Max length: 300 characters
     */
    @JsonbProperty("address_line_1")
    public String AddressLine1;

    /**
     * The second line of the address.  For example, the suite or apartment number.
     *
     * Max length: 300 characters
     */
    @JsonbProperty("address_line_2")
    public String AddressLine2;

    /**
     * The third line of the address, if needed.  For example, a street complement for Brazil,
     * direction text, such as "next to Walmart", or a landmark in an Indian address.
     *
     * Max length: 100 characters
     */
    @JsonbProperty("address_line_3")
    public String AddressLine3;

    /**
     * The neighborhood, ward, or district.  Should be the next smallest area after
     * <code>admin_area_3</code>.
     *
     * Max length: 100 characters
     */
    @JsonbProperty("admin_area_4")
    public String AdminArea4;

    /**
     * A sub-locality, suburb, neighborhood, or district.  Should be the next smallest area
     * after <code>admin_area_2</code>.  The value should be:
     * <ul>
     *     <li><strong>Brazil:</strong> The suburb, bairro, or neighborhood.</li>
     *     <li><strong>India:</strong> The sub-locality or district.  Street name information is not
     *     always available, but a sob-locality or district can be a very small area.</li>
     * </ul>
     *
     * Max length: 100 characters
     */
    @JsonbProperty("admin_area_3")
    public String AdminArea3;

    /**
     * A city, town, or village.  Should be the next smallest area after <code>admin_area_1</code>.
     *
     * Max length: 120 characters
     */
    @JsonbProperty("admin_area_2")
    public String AdminArea2;

    /**
     * The highest level sub-division in a country, such as a province, state, or ISO 3166-2 subdivision.
     * Use the format that would be normally used for postal delivery in that country (for example,
     * use <code>CA</code> for the US state of California).  For the specific countries below, the value
     * should be:
     * <ul>
     *     <li><strong>UK:</strong> The county</li>
     *     <li><strong>US:</strong> The state</li>
     *     <li><strong>Canada:</strong> The province</li>
     *     <li><strong>Japan:</strong> The prefecture</li>
     *     <li><strong>Switzerland:</strong> The kanton</li>
     * </ul>
     *
     * Max length: 300 characters
     */
    @JsonbProperty("admin_area_1")
    public String AdminArea1;

    /**
     * The postal code, such as the ZIP code for US addresses or its equivalent for other countries.
     *
     * Max length: 60 characters
     */
    @JsonbProperty("postal_code")
    public String PostalCode;

    @JsonbProperty("address_details")
    public AddressDetailsType AddressDetails;

    public static class AddressDetailsType {
        /**
         * The street number; for example, just "173" in "173 Drury Lane".
         *
         * Max length: 100 characters
         */
        @JsonbProperty("street_number")
        public String StreetNumber;

        /**
         * The street name; for example, just "Drury" in "173 Drury Lane".
         *
         * Max length: 100 characters
         */
        @JsonbProperty("street_name")
        public String StreetName;

        /**
         * The street type; for example, just "Lane" in "173 Drury Lane".
         *
         * Max length: 100 characters
         */
        @JsonbProperty("street_type")
        public String StreetType;

        /**
         * The delivery service, such as tthe post office box, bag number, or post office name.
         *
         * Max length: 100 characters
         */
        @JsonbProperty("delivery_service")
        public String DeliveryService;

        /**
         * A named location that represents the premise.  Usually a building name or collection of
         * buildings with a common name or number.  For example, "Craven House".
         *
         * Max length: 100 characters
         */
        @JsonbProperty("building_name")
        public String BuildingName;

        /**
         * The first-order entity below a named building or location that represents the sub-premise.
         * Usually a single building within a collection of buildings with a common name, but can also
         * be a flat, story, floor, room, or apartment.
         *
         * Max length: 100 characters
         */
        @JsonbProperty("sub_building")
        public String SubBuilding;
    }
}
