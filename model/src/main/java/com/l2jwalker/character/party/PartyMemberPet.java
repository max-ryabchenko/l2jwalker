package com.l2jwalker.character.party;

import com.l2jwalker.entity.NpcData;

public class PartyMemberPet extends PartyObject {

    private final NpcData data;
    private final int summonType;

    public PartyMemberPet(int objId, NpcData data, int summonType, String name) {
        super(objId);
        this.data = data;
        this.summonType = summonType;
        this.setName(name);
    }

    @Override
    public void update(final PartyObject partyObject) {
        super.update(partyObject);
    }
}
