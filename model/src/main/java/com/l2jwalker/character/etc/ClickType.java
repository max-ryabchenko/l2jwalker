package com.l2jwalker.character.etc;

public enum ClickType {

    SimpleClick((byte) 0x00),
    ShiftClick((byte) 0x01);

    public final byte id;

    ClickType(byte id) {
        this.id = id;
    }
}
