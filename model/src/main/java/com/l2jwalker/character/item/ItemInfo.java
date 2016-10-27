package com.l2jwalker.character.item;

import com.l2jwalker.character.etc.Elemental;
import javolution.util.FastMap;

import java.util.Map;

public class ItemInfo {
    private int enchantLevel;
    private int augmentId;
    private Elemental element;
    private int elementPower;
    private Map<Elemental, Short> elementals = FastMap.newInstance();
    private int mana;

    public synchronized int getEnchantLevel() {
        return enchantLevel;
    }

    public synchronized void setEnchantLevel(int enchantLevel) {
        this.enchantLevel = enchantLevel;
    }

    public synchronized int getAugmentId() {
        return augmentId;
    }

    public synchronized void setAugmentId(int augmentId) {
        this.augmentId = augmentId;
    }

    public synchronized Elemental getElement() {
        return element;
    }

    public synchronized void setElement(Elemental element) {
        this.element = element;
    }

    public synchronized Short getFromElementals(Elemental elemental) {
        return this.elementals.get(elemental);
    }

    public synchronized void clearElementals() {
        this.elementals.clear();
    }

    public synchronized void setElementalDef(Elemental elemental, short power) {
        this.elementals.put(elemental, power);
    }

    public int getElementPower() {
        return elementPower;
    }

    public void setElementPower(int elementPower) {
        this.elementPower = elementPower;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}
