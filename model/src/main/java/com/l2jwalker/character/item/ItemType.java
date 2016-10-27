package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum ItemType {
    Armor,
    Weapon,
    EtcItem;
    private static final Logger _log = Logger.getLogger(ItemType.class);
    public final String name;

    ItemType() {
        this.name = this.name().intern();
    }

    public static ItemType getItemType(String name) {
        if (null == name) {
            return null;
        }
        for (ItemType itemType : ItemType.values()) {
            if (itemType.name.equals(name))
                return itemType;
        }
        _log.warn("ItemType not contain: " + name);
        return null;
    }
}
