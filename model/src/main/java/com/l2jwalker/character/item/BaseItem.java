package com.l2jwalker.character.item;

import com.l2jwalker.character.etc.Elemental;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;
import javolution.util.FastMap;

import java.util.Map;

public abstract class BaseItem {

    private final Version version;
    private final long itemId;
    private long count;
    private short type1;
    private short type2;
    private int objectId;
    private BodyPart bodyPart;
    private short attackPower = 0;
    private Elemental attackType;
    private Map<Elemental, Short> defences = FastMap.newInstance();
    private ItemData data;

    public BaseItem(long itemId, long count, Version version) {
        this.itemId = itemId;
        this.count = count;
        this.version = version;
        if (this.getItemId() > 0) {
            this.data = ServiceHolder.getInstance().getItemDataService().getById(new ID(getItemId(), getVersion()));
        }
    }

    public abstract BaseItem copy();

    public long getItemId() {
        return itemId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public BaseItem add(final long add) {
        this.count += add;
        return this;
    }

    public BaseItem sub(final long sub) {
        this.count -= sub;
        return this;
    }

    public short getType1() {
        return type1;
    }

    public void setType1(short type1) {
        this.type1 = type1;
    }

    public short getType2() {
        return type2;
    }

    public void setType2(short type2) {
        this.type2 = type2;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public ItemData getData() {
        return data;
    }

    @Override
    public String toString() {
        if (getData() != null) {
            return getData().getName();
        }
        return "[" + getItemId() + "]";
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public short getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(short attackPower) {
        this.attackPower = attackPower;
    }

    public Elemental getAttackType() {
        return attackType;
    }

    public void setAttackType(Elemental attackType) {
        this.attackType = attackType;
    }

    public Map<Elemental, Short> getDefences() {
        return defences;
    }

    public void setDefences(Map<Elemental, Short> defences) {
        this.defences = defences;
    }

    public Version getVersion() {
        return version;
    }

}
