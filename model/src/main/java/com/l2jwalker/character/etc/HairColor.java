package com.l2jwalker.character.etc;

public enum HairColor {
    A((byte) 0x00),
    B((byte) 0x01),
    C((byte) 0x02),
    D((byte) 0x03);
    public final byte order;

    HairColor(byte t) {
        this.order = t;
    }

    public static HairColor[] getRange(char c) {
        HairColor[] result = null;
        for (HairColor haircolor : HairColor.values()) {
            if (haircolor.name().equals(String.valueOf(c))) {
                result = new HairColor[haircolor.order + 1];
                break;
            }
        }
        int i = 0;
        for (HairColor haircolor : HairColor.values()) {
            result[i++] = haircolor;
            if (result.length == i)
                return result;
        }
        return null;
    }

    public static HairColor getHairColor(final byte c) {
        for (HairColor hairColor : HairColor.values())
            if (hairColor.order == c)
                return hairColor;
        return HairColor.A;
    }
}
