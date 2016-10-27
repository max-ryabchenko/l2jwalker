package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum ArmorType {
    light,
    heavy,
    magic,
    sigil;
    private static final Logger _log = Logger.getLogger(ArmorType.class);
    public final String name;

    ArmorType() {
        this.name = this.name().intern();
    }

    public static ArmorType getArmorType(final String name) {
        if (null == name) {
            return null;
        }
        for (ArmorType armorType : ArmorType.values()) {
            if (armorType.name.equals(name))
                return armorType;
        }
        _log.warn("ArmorType not contain: " + name);
        return null;
    }
}
