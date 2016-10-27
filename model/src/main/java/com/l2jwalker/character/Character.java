package com.l2jwalker.character;

import com.l2jwalker.character.action.Actions;
import com.l2jwalker.character.buff.Buffs;
import com.l2jwalker.character.chat.Chat;
import com.l2jwalker.character.gameobject.ActiveChar;
import com.l2jwalker.character.gameobject.CharacterStatus;
import com.l2jwalker.character.inventory.Inventory;
import com.l2jwalker.character.knownlist.KnownList;
import com.l2jwalker.character.lobby.Lobby;
import com.l2jwalker.character.party.Party;
import com.l2jwalker.character.skills.MagicSkillUsing;
import com.l2jwalker.character.skills.Skills;
import com.l2jwalker.character.trade.Trade;

public class Character {

    private final CharacterStatus characterStatus = new CharacterStatus();
    private final Party party = new Party();
    private final MagicSkillUsing magicSkillUsing = new MagicSkillUsing();
    private final Inventory inventory = new Inventory();
    private final KnownList knownList = new KnownList();
    private final Skills skills = new Skills();
    private final Actions actions = new Actions();
    private final Buffs buffs = new Buffs();
    private final Lobby lobby = new Lobby();
    private final Trade trade = new Trade();
    private final Chat chat = new Chat();
    private volatile long activeCharId;

    public ActiveChar getActiveChar() {
        return (ActiveChar) getKnownList().get(getActiveCharId());
    }

    public void clear() {
        getCharacterStatus().clear();
        getParty().clear();
        getMagicSkillUsing().clear();
        getInventory().clear();
        getKnownList().clear();
        getSkills().clear();
        getActions().clear();
        getBuffs().clear();
        getLobby().clear();
        getTrade().clear();
        getChat().clear();
    }

    public long getActiveCharId() {
        return activeCharId;
    }

    public void setActiveCharId(long activeCharId) {
        this.activeCharId = activeCharId;
    }

    public CharacterStatus getCharacterStatus() {
        return characterStatus;
    }

    public Party getParty() {
        return party;
    }

    public MagicSkillUsing getMagicSkillUsing() {
        return magicSkillUsing;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public KnownList getKnownList() {
        return knownList;
    }

    public Skills getSkills() {
        return skills;
    }

    public Actions getActions() {
        return actions;
    }

    public Buffs getBuffs() {
        return buffs;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public Trade getTrade() {
        return trade;
    }

    public Chat getChat() {
        return chat;
    }

}
