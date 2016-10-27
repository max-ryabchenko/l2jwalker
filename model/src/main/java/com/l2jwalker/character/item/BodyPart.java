package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum BodyPart {
    shirt(0x0000),
    rhand(0x0080),
    lrhand(0x4000),
    lhand(0x0100),
    chest(0x0400),
    legs(0x0800),
    feet(0x1000),
    head(0x0040),
    gloves(0x0200),
    onepiece(0x8000),
    rear(0x0002),
    lear(0x0004),
    rfinger(0x0010),
    lfinger(0x0020),
    neck(0x0008),
    back(0x2000),
    underwear(0x0001),
    hair(0x010000),
    alldress(0x020000),
    hair2(0x040000),
    hairall(0x080000),
    rbracelet(0x100000),
    lbracelet(0x200000),
    deco1(0x400000),
    waist(0x10000000);

    private static final Logger _log = Logger.getLogger(BodyPart.class);
    private static final int SLOT_NONE = 0x0000;
    private static final int SLOT_UNDERWEAR = 0x0001;
    private static final int SLOT_R_EAR = 0x0002;
    private static final int SLOT_L_EAR = 0x0004;
    private static final int SLOT_LR_EAR = 0x00006;
    private static final int SLOT_NECK = 0x0008;
    private static final int SLOT_R_FINGER = 0x0010;
    private static final int SLOT_L_FINGER = 0x0020;
    private static final int SLOT_LR_FINGER = 0x0030;
    private static final int SLOT_HEAD = 0x0040;
    private static final int SLOT_R_HAND = 0x0080;
    private static final int SLOT_L_HAND = 0x0100;
    private static final int SLOT_GLOVES = 0x0200;
    private static final int SLOT_CHEST = 0x0400;
    private static final int SLOT_LEGS = 0x0800;
    private static final int SLOT_FEET = 0x1000;
    private static final int SLOT_BACK = 0x2000;
    private static final int SLOT_LR_HAND = 0x4000;
    private static final int SLOT_FULL_ARMOR = 0x8000;
    private static final int SLOT_HAIR = 0x010000;
    private static final int SLOT_ALLDRESS = 0x020000;
    private static final int SLOT_HAIR2 = 0x040000;
    private static final int SLOT_HAIRALL = 0x080000;
    private static final int SLOT_R_BRACELET = 0x100000;
    private static final int SLOT_L_BRACELET = 0x200000;
    private static final int SLOT_DECO = 0x400000;
    private static final int SLOT_BELT = 0x10000000;
    private static final int SLOT_WOLF = -100;
    private static final int SLOT_HATCHLING = -101;
    private static final int SLOT_STRIDER = -102;
    private static final int SLOT_BABYPET = -103;
    private static final int SLOT_GREATWOLF = -104;
    private static final int SLOT_MULTI_ALLWEAPON = SLOT_LR_HAND | SLOT_R_HAND;
    public final String name;
    public final int code;
    BodyPart(final int code) {
        this.name = this.name().intern();
        this.code = code;
    }

    public static BodyPart getBodyPart(final String name) {
        if (null == name) {
            return null;
        }
        for (BodyPart bodyPart : BodyPart.values()) {
            if (bodyPart.name.equals(name))
                return bodyPart;
        }
        _log.warn("BodyPart not contain: " + name);
        return null;
    }

    public static BodyPart getBodyPart(final int code) {
        for (BodyPart bodyPart : BodyPart.values()) {
            if (bodyPart.code == code) {
                return bodyPart;
            }
        }
        _log.warn("BodyPart id not found:" + code);
        return null;
    }
}
