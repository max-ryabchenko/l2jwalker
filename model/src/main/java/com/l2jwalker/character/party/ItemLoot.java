package com.l2jwalker.character.party;

import org.apache.log4j.Logger;

public enum ItemLoot {

    Looter((byte) 0x00, "Looter"),
    Random((byte) 0x01, "Random"),
    RandomSpoil((byte) 0x02, "Random Spoil"),
    Order((byte) 0x03, "Order"),
    OrderSpoil((byte) 0x04, "Order Spoil");
    public final byte code;
    public final String name;

    ItemLoot(byte c, final String name) {
        this.code = c;
        this.name = name;
    }

    public static ItemLoot getItemLoot(int c) {
        switch (c) {
            case 0x00:
                return Looter;
            case 0x01:
                return Random;
            case 0x02:
                return RandomSpoil;
            case 0x03:
                return Order;
            case 0x04:
                return OrderSpoil;
            default:
                Logger _log = Logger.getLogger(ItemLoot.class);
                _log.warn("UNKNOWN ItemLoot!");
                return null;

        }
    }

    public static ItemLoot getItemLoot(String s) {
        if (null == s) {
            return Looter;
        }
        for (ItemLoot itemLoot : ItemLoot.values()) {
            if (itemLoot.name.endsWith(s)) {
                return itemLoot;
            }
        }
        return Looter;
    }

    public String getName() {
        return this.name;
    }
}
