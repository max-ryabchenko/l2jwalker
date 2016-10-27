package com.l2jwalker.character.etc;

public enum MoveType {
    Run((byte) 0x01),
    Walk((byte) 0x00);
    public final byte code;

    MoveType(byte c) {
        this.code = c;
    }
}
