package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class BusinessDetailsType {
    /**
     * The name of the person who is responsible for the PayPal account.
     */
    @JsonbProperty("name")
    public PersonNameType Name;

    /**
     * The address of the business (for example, the address of the business's corporate office).
     */
    @JsonbProperty("address")
    public AddressType Address;

    /**
     * The merchant's 4-digit merchant category code (MCC).
     *
     * Match regex: ^\d{4}$
     */
    @JsonbProperty("merchant_category_code")
    public String MerchantCategoryCode;

    /**
     * The business's PayPal payer ID.
     *
     * Match regex: <code>^[2-9A-HJ-NP-Z]{13}$</code>
     */
    @JsonbProperty("account_id")
    public String AccountId;

    /**
     * The business's email address.
     *
     * Min length: 3 characters
     * Max length: 254 characters
     */
    @JsonbProperty("email_address")
    public String EmailAddress;

    /**
     * The business's phone number.
     */
    @JsonbProperty("phone_number")
    public PhoneNumberType PhoneNumber;

    /**
     * The partner-specific identifier for the merchant.
     *
     * Min length: 1 character
     * Max length: 256 characters
     */
    @JsonbProperty("external_id")
    public String ExternalId;
}
