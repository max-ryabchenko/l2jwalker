package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum WeaponType {
    sword,
    blunt,
    dagger,
    bow,
    pole,
    dual,
    etc,
    fist,
    dualfist,
    fishingrod,
    rapier,
    ancientsword,
    crossbow,
    flag,
    ownthing,
    dualdagger,
    bigsword,
    bigblunt;
    private static final Logger _log = Logger.getLogger(WeaponType.class);
    public final String name;

    WeaponType() {
        this.name = this.name().intern();
    }

    public static WeaponType getWeaponType(final String name) {
        if (null == name) {
            return null;
        }
        for (WeaponType weaponType : WeaponType.values()) {
            if (weaponType.name.equals(name))
                return weaponType;
        }
        _log.warn("WeaponType not contain: " + name);
        return null;
    }
}
