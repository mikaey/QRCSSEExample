package com.paypal;

import jakarta.json.bind.annotation.JsonbProperty;

public class GeoLocationType {
    /**
     * The geographic longitude coordinate.
     *
     * Match regex: <code>^-?(1?[1-7][1-9]|1?[1-8]0|[1-9]?\d)\.\d{1,6}</code>
     */
    @JsonbProperty("longitude")
    public String Longitude;

    /**
     * The geographic latitude coorindate.
     *
     * Match regex: <code>^-?([1-8]?[1-9]|[1-9]0)\.\d{1,6}</code>
     */
    @JsonbProperty("latitude")
    public String Latitude;
}
