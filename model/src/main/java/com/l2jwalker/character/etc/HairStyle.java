package com.l2jwalker.character.etc;

public enum HairStyle {
    A((byte) 0x00),
    B((byte) 0x01),
    C((byte) 0x02),
    D((byte) 0x03),
    E((byte) 0x04),
    F((byte) 0x05),
    G((byte) 0x06);
    public final byte order;

    HairStyle(byte t) {
        this.order = t;
    }

    public static HairStyle[] getRange(char c) {
        HairStyle[] result = null;
        for (HairStyle hairStyle : HairStyle.values()) {
            if (hairStyle.name().equals(String.valueOf(c))) {
                result = new HairStyle[hairStyle.order + 1];
                break;
            }
        }
        int i = 0;
        for (HairStyle hairStyle : HairStyle.values()) {
            result[i++] = hairStyle;
            if (result.length == i)
                return result;
        }
        return null;
    }

    public static HairStyle getHairStyle(final byte c) {
        for (HairStyle hairStyle : HairStyle.values())
            if (hairStyle.order == c)
                return hairStyle;
        return HairStyle.A;
    }
}
