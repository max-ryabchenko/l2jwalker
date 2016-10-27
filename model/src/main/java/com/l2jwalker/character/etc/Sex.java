package com.l2jwalker.character.etc;

public enum Sex {

    Male((byte) 0x00),
    Female((byte) 0x01),
    etc((byte) 0x02);

    public final byte id;

    Sex(byte id) {
        this.id = id;
    }

    public static Sex get(byte id) {
        if (id == Sex.Male.id) {
            return Sex.Male;
        } else {
            return Sex.Female;
        }
    }

}
