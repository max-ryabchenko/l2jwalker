package com.l2jwalker.character.party;

import com.l2jwalker.character.etc.Profession;
import com.l2jwalker.character.etc.Race;

public class PartyMember extends PartyObject {

    private int currentCP;
    private int maxCP;
    private boolean leader = false;
    private Profession profession;
    private Race race;

    private PartyMemberPet partyMemberPet;

    public PartyMember(final int objId, final String name) {
        super(objId);
        this.setName(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof PartyMember) && ((PartyMember) o).getObjId() == this.getObjId();
    }

    public void update(final PartyMember partyMember) {
        super.update(partyMember);
        this.setCurrentCP(partyMember.getCurrentCP());
        this.setMaxCP(partyMember.getMaxCP());
        this.setProfession(partyMember.getProfession());
    }

    public boolean isLeader() {
        return this.leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public int getCurrentCP() {
        return currentCP;
    }

    public void setCurrentCP(int currentCP) {
        this.currentCP = currentCP;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public void setMaxCP(int maxCP) {
        this.maxCP = maxCP;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public PartyMemberPet getPet() {
        return partyMemberPet;
    }

    public void setPet(PartyMemberPet partyMemberPet) {
        this.partyMemberPet = partyMemberPet;
    }

    public boolean havePet() {
        return null != this.partyMemberPet;
    }

    public void removePet() {
        this.partyMemberPet = null;
    }

    public boolean isHavePet() {
        return null != this.partyMemberPet;
    }

}
