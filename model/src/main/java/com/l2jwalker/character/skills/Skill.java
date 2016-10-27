package com.l2jwalker.character.skills;

import com.l2jwalker.character.etc.KnownObject;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.SkillData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;
import org.apache.log4j.Logger;

public class Skill implements KnownObject {

    private static final Logger LOG = Logger.getLogger(Skill.class);
    private final Version version;
    protected int id;
    protected int level;
    protected boolean passive;
    protected boolean disabled;
    protected boolean enchanted;
    //using
    protected volatile boolean ready = true;
    protected long startTime;
    protected long reuseDelay;
    private SkillData data;

    public Skill(boolean pPassive, int pLevel, int pId, boolean pDisabled, boolean pEnchanted, Version version) {
        this.id = pId;
        this.version = version;
        this.setData(ServiceHolder.getInstance().getSkillDataService().getById(new ID(getId(), getVersion())));
        this.level = pLevel;
        this.passive = pPassive;
        this.disabled = pDisabled;
        this.enchanted = pEnchanted;
    }

    public synchronized void lunch(final long reuseDelay) {
        this.startTime = System.currentTimeMillis();
        this.reuseDelay = reuseDelay;
        if (0 != reuseDelay) {
            this.ready = false;
        }
    }

    public synchronized void coolDown(final SkillCT skillCT) {
        this.startTime = System.currentTimeMillis() - 1000 * (skillCT.reuse - skillCT.remaining);
        this.reuseDelay = 1000 * skillCT.reuse;
        this.ready = false;
    }

    public synchronized int readyPercents() {
        int r;
        if (this.ready) {
            r = 100;
            return r;
        }
        final long currentTime = System.currentTimeMillis();
        if (this.startTime + this.reuseDelay > currentTime) {
            r = (int) ((100 * (double) (currentTime - this.startTime)) / ((double) this.reuseDelay));
            return r;
        } else {
            this.ready = true;
            r = 100;
            return r;
        }
    }

    public long getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public boolean isPassive() {
        return passive;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isEnchanted() {
        return enchanted;
    }

    @Override
    public String toString() {
        return null == getData() ? "unknown[" + getId() + "]" : getData().getName() + "[" + getLevel() + "]";
    }

    @Override
    public String getName() {
        return toString();
    }

    public Version getVersion() {
        return version;
    }

    public SkillData getData() {
        return data;
    }

    public void setData(SkillData data) {
        this.data = data;
    }
}
