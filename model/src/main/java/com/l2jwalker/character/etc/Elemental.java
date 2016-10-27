package com.l2jwalker.character.etc;

public enum Elemental {

    NONE(null, (short) 0xff), //-1
    FIRE(0, (short) 0x00), //0
    WATER(1, (short) 0x01), //1
    WIND(2, (short) 0x02), //2
    EARTH(3, (short) 0x03), //3
    HOLY(4, (short) 0x04), //4
    DARK(5, (short) 0x05); //5

    public final short id;
    public final Integer order;

    Elemental(Integer order, short id) {
        this.order = order;
        this.id = id;
    }

    public static Elemental getOrder(int order) {
        for (Elemental elemental : Elemental.values()) {
            if (elemental.order == order) {
                return elemental;
            }
        }
        return NONE;
    }

    public static Elemental getElemental(short id) {
        for (Elemental elemental : Elemental.values()) {
            if (elemental.id == id) {
                return elemental;
            }
        }
        return NONE;
    }
}
