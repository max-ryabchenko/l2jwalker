package com.l2jwalker.character.etc;

public enum Race {
    Human((byte) 0x00),
    Elf((byte) 0x01),
    DarkElf((byte) 0x02),
    Orc((byte) 0x03),
    Dwarf((byte) 0x04),
    Kamael((byte) 0x05);
    public final byte order;

    Race(final byte order) {
        this.order = order;
    }

    public static Race getRace(int ord) {
        for (Race r : Race.values()) {
            if (r.order == ord)
                return r;
        }
        return Human;
    }
}
