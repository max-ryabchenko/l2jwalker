package com.l2jwalker.character.etc;

import org.apache.log4j.Logger;

public class Hit {

    protected static final int HITFLAG_USESS = 0x10;
    protected static final int HITFLAG_CRIT = 0x20;
    protected static final int HITFLAG_SHLD = 0x40;
    protected static final int HITFLAG_MISS = 0x80;
    private static final Logger LOG = Logger.getLogger(Hit.class);
    protected final int targetId;
    protected final int damage;
    protected final int flags;

    public Hit(int targetId, int damage, int flags) {
        this.targetId = targetId;
        this.damage = damage;
        this.flags = flags;
    }

    public boolean isMissed() {
        return ((byte) flags) == HITFLAG_MISS;
    }

    public boolean isCrit() {
        return ((byte) flags) == HITFLAG_CRIT;
    }

    public boolean isUsesSS() {
        return ((byte) flags) == HITFLAG_USESS;
    }

    public boolean isShieldDef() {
        return ((byte) flags) == HITFLAG_SHLD;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getDamage() {
        return damage;
    }

}
