package com.l2jwalker.character.etc;

public enum FighterType {

    Fighter((byte) 0x00),
    Mage((byte) 0x01);

    public final byte type;

    FighterType(byte t) {
        this.type = t;
    }

    public static FighterType[] getTypes(Race race) {
        if (Race.Dwarf == race || Race.Kamael == race)
            return new FighterType[]{FighterType.Fighter};
        return new FighterType[]{FighterType.Fighter, FighterType.Mage};
    }
}
