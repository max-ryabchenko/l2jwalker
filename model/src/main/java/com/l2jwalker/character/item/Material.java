package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum Material {
    steel,
    fine_steel,
    wood,
    bone,
    bronze,
    leather,
    cloth,
    fish,
    gold,
    mithril,
    liquid,
    oriharukon,
    damascus,
    adamantaite,
    blood_steel,
    paper,
    silver,
    chrysolite,
    crystal,
    horn,
    scale_of_dragon,
    cotton,
    dyestuff,
    cobweb,
    rune_xp,
    rune_sp,
    rune_remove_penalty;
    private static final Logger _log = Logger.getLogger(Material.class);
    public final String name;

    Material() {
        this.name = this.name().intern();
    }

    public static Material getMaterial(final String name) {
        if (null == name) {
            return null;
        }
        for (Material material : Material.values()) {
            if (material.name.equals(name))
                return material;
        }
        _log.warn("Material not contain: " + name);
        return null;
    }
}
