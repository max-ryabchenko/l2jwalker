package com.l2jwalker.util;

public class NullRestriction {

    private String property;
    private NullRestrictionKind restriction = NullRestrictionKind.SPECIFIED;
    /**
     * @param property the property on which the restriction applies.
     */
    public NullRestriction(String property) {
        this.property = property;
    }

    public final String getProperty() {
        return property;
    }

    public NullRestrictionKind getRestriction() {
        return restriction;
    }

    public void setRestriction(NullRestrictionKind restriction) {
        this.restriction = restriction;
    }

    public static enum NullRestrictionKind {
        SPECIFIED, ANY, IS_NOT_NULL, IS_NULL
    }
}
