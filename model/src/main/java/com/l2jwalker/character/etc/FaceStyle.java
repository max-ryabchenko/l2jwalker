package com.l2jwalker.character.etc;

public enum FaceStyle {
    A((byte) 0x00),
    B((byte) 0x01),
    C((byte) 0x02);
    public final byte order;

    FaceStyle(final byte t) {
        this.order = t;
    }

    public static FaceStyle[] getRange(char c) {
        FaceStyle[] result = null;
        for (FaceStyle faceStyle : FaceStyle.values()) {
            if (faceStyle.name().equals(String.valueOf(c))) {
                result = new FaceStyle[faceStyle.order + 1];
                break;
            }
        }
        int i = 0;
        for (FaceStyle faceStyle : FaceStyle.values()) {
            result[i++] = faceStyle;
            if (result.length == i)
                return result;
        }
        return null;
    }

    public static FaceStyle getFaceStyle(final byte s) {
        for (FaceStyle faceStyle : FaceStyle.values())
            if (faceStyle.order == s)
                return faceStyle;
        return FaceStyle.A;
    }
}
