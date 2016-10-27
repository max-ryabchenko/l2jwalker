package com.l2jwalker.character.inventory;

import com.l2jwalker.character.etc.Elemental;
import com.l2jwalker.character.etc.KnownObject;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;

public class Item implements KnownObject {

    protected final long id;
    private final Version version;
    protected boolean blocked = false;
    protected long itemId;
    protected int locationSlot;
    protected long count;
    protected int type1;
    protected int type2;
    protected short customType1;
    protected boolean equipped;
    protected int bodyPart;
    protected short enchantLevel;
    protected short customType2;
    protected int augmentId;
    protected int mana;
    protected int remainingTime;
    protected Elemental attackElementType;
    protected short attackElementPower;
    protected short[] elementDefAttr = new short[Elemental.values().length];
    protected Change change = Change.loaded;
    private ItemData data;

    public Item(long id, long itemId, Version version) {
        this.id = id;
        this.itemId = itemId;
        this.version = version;
        this.data = ServiceHolder.getInstance().getItemDataService().getById(new ID(getId(), getVersion()));
    }

    public void update(Item item) {
        this.itemId = item.itemId;
        this.locationSlot = item.locationSlot;
        this.count = item.count;
        this.type2 = item.type2;
        this.customType1 = item.customType1;
        this.equipped = item.equipped;
        this.bodyPart = item.bodyPart;
        this.enchantLevel = item.enchantLevel;
        this.customType2 = item.customType2;
        this.augmentId = item.augmentId;
        this.mana = item.mana;
        this.remainingTime = item.remainingTime;
        this.attackElementType = item.attackElementType;
        this.attackElementPower = item.attackElementPower;
        this.elementDefAttr = item.elementDefAttr;
    }

    @Override
    public long getId() {
        return id;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public ItemData getData() {
        return data;
    }

    public void setData(ItemData data) {
        this.data = data;
    }

    public Version getVersion() {
        return version;
    }

    public Change getChange() {
        return this.change;
    }

    public void setChange(short c) {
        this.change = Change.getChange((byte) c);
    }

    @Override
    public boolean equals(Object o) {
        return !(null == o || !(o instanceof Item)) && ((Item) o).id == this.id;
    }

    @Override
    public String getName() {
        if (null == getData()) {
            return "[id=" + this.getItemId() + "]";
        } else {
            return getData().getName();
        }
    }

    public void setElementDefAttr(Elemental elementals, short value) {
        if (Elemental.FIRE == elementals)
            elementDefAttr[0] = value;
        if (Elemental.WATER == elementals)
            elementDefAttr[1] = value;
        if (Elemental.WIND == elementals)
            elementDefAttr[2] = value;
        if (Elemental.EARTH == elementals)
            elementDefAttr[3] = value;
        if (Elemental.HOLY == elementals)
            elementDefAttr[4] = value;
        if (Elemental.DARK == elementals)
            elementDefAttr[5] = value;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public long getItemId() {
        return itemId;
    }

    public int getLocationSlot() {
        return locationSlot;
    }

    public void setLocationSlot(int locationSlot) {
        this.locationSlot = locationSlot;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public short getCustomType1() {
        return customType1;
    }

    public void setCustomType1(short customType1) {
        this.customType1 = customType1;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public int getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(int bodyPart) {
        this.bodyPart = bodyPart;
    }

    public short getEnachantLevel() {
        return enchantLevel;
    }

    public void setEnchantLevel(short enchantLevel) {
        this.enchantLevel = enchantLevel;
    }

    public short getCustomType2() {
        return customType2;
    }

    public void setCustomType2(short customType2) {
        this.customType2 = customType2;
    }

    public int getAugmentId() {
        return augmentId;
    }

    public void setAugmentId(int augmentId) {
        this.augmentId = augmentId;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Elemental getAttackElementType() {
        return attackElementType;
    }

    public void setAttackElementType(short attackElementType) {
        this.attackElementType = Elemental.getElemental(attackElementType);
    }

    public void setAttackElementType(Elemental attackElementType) {
        this.attackElementType = attackElementType;
    }

    public short getAttackElementPower() {
        return attackElementPower;
    }

    public void setAttackElementPower(short attackElementPower) {
        this.attackElementPower = attackElementPower;
    }

    @Override
    public String toString() {
        return null == getData() ? "unknown[" + this.itemId + "]" : getData().getName();
    }

    public enum Change {
        loaded((byte) 0x00),//first loaded item
        add((byte) 0x01),
        modify((byte) 0x02),
        remove((byte) 0x03);
        public final byte code;

        Change(byte c) {
            this.code = c;
        }

        static public Change getChange(final byte c) {
            for (Change change : Change.values()) {
                if (change.code == c) {
                    return change;
                }
            }
            return loaded;
        }
    }
}
