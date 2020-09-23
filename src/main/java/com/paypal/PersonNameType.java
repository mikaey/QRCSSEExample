package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class PersonNameType {
    /**
     * The prefix, or title, to the party's name, such as "Mr.", "Mrs.", "Ms.", "Dr.", etc.
     *
     * Max length: 140 characters
     */
    @JsonbProperty("prefix")
    public String Prefix;

    /**
     * The party's first name or given name.
     *
     * Max length: 140 characters
     */
    @JsonbProperty("given_name")
    public String GivenName;

    /**
     * The party's surname, last name, or family name.  When the party has multiple surnames, use
     * this field to provide all of them.
     *
     * Max length: 140 characters
     */
    @JsonbProperty("surname")
    public String Surname;

    /**
     * The party's middle name.
     *
     * Max length: 140 characters
     */
    @JsonbProperty("middle_name")
    public String MiddleName;

    /**
     * The party's suffix, such as "Sr.", "Jr.", "III", etc.
     *
     * Max length: 140 characters
     */
    @JsonbProperty("suffix")
    public String Suffix;

    /**
     * The party's full name.
     *
     * Max length: 300 characters
     */
    @JsonbProperty("full_name")
    public String FullName;
}
