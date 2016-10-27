package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum CrystalType {
    d,
    s84,
    c,
    b,
    a,
    s,
    s80;
    private static final Logger _log = Logger.getLogger(CrystalType.class);
    public final String name;

    CrystalType() {
        this.name = this.name().intern();
    }

    public static CrystalType getCrystalType(final String name) {
        if (null == name) {
            return null;
        }
        for (CrystalType crystalType : CrystalType.values()) {
            if (crystalType.name.equals(name))
                return crystalType;
        }
        _log.warn("CrystalType not contain: " + name);
        return null;
    }
}
