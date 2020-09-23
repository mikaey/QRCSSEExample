package com.paypal;

public enum ItemCountUnitType {
    /** Each */
    EA,

    /** Hours */
    HOURS,

    /** Days */
    DAYS,

    /** Seconds */
    SECONDS,

    /** Crate of 12, e.g. bottles of beer. */
    CRATE_OF_12,

    /** 6 Pack. */
    SIX_PACK,

    /** Gallon (UK) */
    GLI,

    /** Gallon (US) */
    GLL,

    /** Liter */
    LTR,

    /** Inch */
    INH,

    /** Foot */
    FOT,

    /** Millimeter */
    MMT,

    /** Centimeter */
    CMQ,

    /** Meter */
    MTR,

    /** Square Meter */
    MTK,

    /** Cubic Meter */
    MTQ,

    /** Gram */
    GRM,

    /** Kilogram */
    KGM,

    /** Kilogram */
    KG,

    /** Pound */
    LBR,

    /** Annual */
    ANN,

    /** Degrees Celsius */
    CEL,

    /** Degrees Fahrenheit */
    FAH;

    @Override
    public String toString() {
        if(this == SIX_PACK) {
            return "6_PACK";
        } else {
            return super.toString();
        }
    }
}
