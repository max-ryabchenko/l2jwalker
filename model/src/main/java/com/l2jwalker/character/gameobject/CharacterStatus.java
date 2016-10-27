package com.l2jwalker.character.gameobject;

public class CharacterStatus {

    protected int charges;
    protected int weightPenalty;
    protected boolean messageRefusal;
    protected boolean insideDangerZone;
    protected boolean expertisePenalty; //interlude
    protected int expertiseWeaponPenalty; //freya
    protected int expertiseArmorPenalty; //freya
    protected boolean affected;
    protected int deathPenaltyBuffLevel;
    protected int souls;

    public CharacterStatus() {
        clear();
    }

    public CharacterStatus(CharacterStatus characterStatus) {
        copyFrom(characterStatus);
    }

    public void clear() {
        charges = 0;
        weightPenalty = 0;
        messageRefusal = false;
        insideDangerZone = false;
        expertisePenalty = false;
        expertiseWeaponPenalty = 0;
        expertiseArmorPenalty = 0;
        affected = false;
        deathPenaltyBuffLevel = 0;
        souls = 0;
    }

    public void copyFrom(CharacterStatus characterStatus) {
        charges = characterStatus.charges;
        weightPenalty = characterStatus.weightPenalty;
        messageRefusal = characterStatus.messageRefusal;
        insideDangerZone = characterStatus.insideDangerZone;
        expertisePenalty = characterStatus.expertisePenalty;
        expertiseWeaponPenalty = characterStatus.expertiseWeaponPenalty;
        expertiseArmorPenalty = characterStatus.expertiseArmorPenalty;
        affected = characterStatus.affected;
        deathPenaltyBuffLevel = characterStatus.deathPenaltyBuffLevel;
        souls = characterStatus.souls;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getWeightPenalty() {
        return weightPenalty;
    }

    public void setWeightPenalty(int weightPenalty) {
        this.weightPenalty = weightPenalty;
    }

    public boolean getMessageRefusal() {
        return messageRefusal;
    }

    public void setMessageRefusal(boolean messageRefusal) {
        this.messageRefusal = messageRefusal;
    }

    public boolean isInsideDangerZone() {
        return insideDangerZone;
    }

    public void setInsideDangerZone(boolean insideDangerZone) {
        this.insideDangerZone = insideDangerZone;
    }

    public int getExpertiseWeaponPenalty() {
        return expertiseWeaponPenalty;
    }

    public void setExpertiseWeaponPenalty(int expertiseWeaponPenalty) {
        this.expertiseWeaponPenalty = expertiseWeaponPenalty;
    }

    public int getExpertiseArmorPenalty() {
        return expertiseArmorPenalty;
    }

    public void setExpertiseArmorPenalty(int expertiseArmorPenalty) {
        this.expertiseArmorPenalty = expertiseArmorPenalty;
    }

    public boolean isAffected() {
        return affected;
    }

    public void setAffected(boolean affected) {
        this.affected = affected;
    }

    public int getDeathPenaltyBuffLevel() {
        return deathPenaltyBuffLevel;
    }

    public void setDeathPenaltyBuffLevel(int deathPenaltyBuffLevel) {
        this.deathPenaltyBuffLevel = deathPenaltyBuffLevel;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public boolean isExpertisePenalty() {
        return expertisePenalty;
    }

    public void setExpertisePenalty(boolean expertisePenalty) {
        this.expertisePenalty = expertisePenalty;
    }

}
