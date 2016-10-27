package com.l2jwalker.character.party;

import com.l2jwalker.character.buff.Buff;
import com.l2jwalker.character.etc.Point;
import javolution.util.FastSet;

import java.util.Collection;
import java.util.Set;

public abstract class PartyObject {

    private final int objId;
    private final Point point = new Point();
    private final Set<Buff> buffsSet = FastSet.newInstance();
    private String name;
    private int currentHP;
    private int maxHP;
    private int currentMP;
    private int maxMP;
    private int lvl;

    PartyObject(final int id) {
        this.objId = id;
    }

    public int getObjId() {
        return objId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getHPPercents() {
        return (100 * this.getCurrentHP()) / this.getMaxHP();
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentMP() {
        return currentMP;
    }

    public void setCurrentMP(int currentMP) {
        this.currentMP = currentMP;
    }

    public int getMPPercents() {
        return (100 * this.getCurrentMP()) / this.getMaxMP();
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(final Point point) {
        this.getPoint().copyFrom(point);
    }

    public synchronized Set<Buff> getBuffsSet() {
        return this.buffsSet;
    }

    public synchronized void setBuffsSet(final Collection<Buff> buffs) {
        getBuffsSet().clear();
        getBuffsSet().addAll(buffs);
    }

    public synchronized long haveBuff(String buffName) {
        for (Buff buff : getBuffsSet()) {
            if (null != buff.getData() && buff.getData().getName().equalsIgnoreCase(buffName)) {
                return buff.getCurrentRemainingTime();
            }
        }
        return 0;
    }

    void update(final PartyObject partyObject) {
        setName(partyObject.getName());
        setCurrentHP(partyObject.getCurrentHP());
        setMaxHP(partyObject.getMaxHP());
        setCurrentMP(partyObject.getCurrentMP());
        setMaxMP(partyObject.getMaxMP());
        setLvl(partyObject.getLvl());
    }

}
