package com.l2jwalker.character.buff;

import com.l2jwalker.character.etc.KnownObject;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.SkillData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;

public class Buff implements KnownObject {

    private final Version version;
    private final SkillData data;
    private final long skillId;
    private int lvl;
    private long startTime;
    private long remainingTime;

    public Buff(SkillData data, int lvl, long remainingTime) {
        this.data = data;
        this.version = data.getId().getVersion();
        if (null != getData()) {
            this.skillId = getData().getId().getId();
        } else {
            this.skillId = -1;
        }
        this.startTime = System.currentTimeMillis();
        this.lvl = lvl;
        this.remainingTime = remainingTime;
    }

    public Buff(long id, int lvl, long remainingTime, Version version) {
        this.skillId = id;
        this.version = version;
        this.data = ServiceHolder.getInstance().getSkillDataService().getById(new ID(id, getVersion()));
        this.startTime = System.currentTimeMillis();
        this.lvl = lvl;
        this.remainingTime = remainingTime;
    }

    @Override
    public long getId() {
        return getData().getId().getId();
    }

    @Override
    public String toString() {
        return null != data ? data.getName() : ("skill=" + skillId);
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof Buff && ((Buff) o).data.equals(this.data) && this.lvl == ((Buff) o).lvl;
    }

    @Override
    public int hashCode() {
        return 31 * getLvl() + 17 * (int) getSkillId();
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getCurrentRemainingTimeStr() {
        long sec = Math.max(0, this.startTime + 1000 * this.remainingTime - System.currentTimeMillis()) / 1000;
        if (sec > 3600) {
            return sec / 3600 + "h " + ((sec % 3600) / 60) + "m " + (sec % 60) + "s";
        } else if (sec > 60) {
            return sec / 60 + "m " + (sec % 60) + "s";
        } else {
            return sec + " s";
        }
    }

    public long getCurrentRemainingTime() {
        return Math.max(0, this.startTime + 1000 * this.remainingTime - System.currentTimeMillis()) / 1000;
    }

    public boolean isExpired() {
        return this.startTime + 1000 * this.remainingTime < System.currentTimeMillis();
    }

    public void setRemainingTimeSec(int sec) {
        this.remainingTime = 1000 * sec;
    }

    public SkillData getData() {
        return data;
    }

    public long getSkillId() {
        return skillId;
    }

    public Version getVersion() {
        return version;
    }

}
