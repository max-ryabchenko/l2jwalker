package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum EtcItemType {
    arrow,
    potion,
    scrl_enchant_wp,
    scrl_enchant_am,
    scroll,
    recipe,
    material,
    pet_collar,
    castle_guard,
    lotto,
    race_ticket,
    dye,
    seed,
    crop,
    maturecrop,
    harvest,
    seed2,
    ticket_of_lord,
    lure,
    bless_scrl_enchant_wp,
    bless_scrl_enchant_am,
    coupon,
    elixir,
    scrl_enchant_attr,
    bolt,
    scrl_inc_enchant_prop_wp,
    scrl_inc_enchant_prop_am,
    ancient_crystal_enchant_wp,
    ancient_crystal_enchant_am,
    rune_select,
    rune;
    private static final Logger _log = Logger.getLogger(EtcItemType.class);
    public final String name;

    EtcItemType() {
        this.name = this.name().intern();
    }

    public static EtcItemType getEtcItemType(final String name) {
        if (null == name) {
            return null;
        }
        for (EtcItemType etcItemType : EtcItemType.values()) {
            if (etcItemType.name.equals(name))
                return etcItemType;
        }
        _log.warn("EtcItemType not contain: " + name);
        return null;
    }
}
