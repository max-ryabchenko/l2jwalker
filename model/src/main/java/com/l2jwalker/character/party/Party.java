package com.l2jwalker.character.party;

import com.l2jwalker.util.PropertiesReader;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Party {
    public static final int MAX_PARTY_MEMBERS = 9;
    public static final int REQUEST_DIALOG_SHOW_TIME = PropertiesReader.getIntegerProperty("request_dialog.show_time");
    private static final Logger LOG = Logger.getLogger(Party.class);
    private Set<PartyMember> partyMembers = Collections.synchronizedSet(new HashSet<PartyMember>(MAX_PARTY_MEMBERS - 1));
    private PartyMember leader;
    private ItemLoot itemLoot;
    private volatile boolean invited = false;
    private String inviterName;
    private ItemLoot itemLootInvited;
    private long invitedStartTime;
    private volatile boolean nowInviting = false;
    private volatile long requestPartyDialogOpenTime = 0;
    public Party() {

    }

    public int getMaxPartyMembers() {
        return MAX_PARTY_MEMBERS;
    }

    public synchronized boolean isLeader() {
        for (PartyMember partyMember : partyMembers) {
            if (partyMember.isLeader()) {
                return false;
            }
        }
        return true;
    }

    public synchronized PartyMember getPartyMember(String name) {
        for (PartyMember partyMember : partyMembers) {
            if (partyMember.getName().equalsIgnoreCase(name)) {
                return partyMember;
            }
        }
        return null;
    }

    public boolean isNowInviting() {
        return nowInviting;
    }

    public void setNowInviting(boolean accept) {
        nowInviting = accept;
    }

    public synchronized int getPartySize() {
        if (!isHaveParty()) {
            return 0;
        }
        return getPartyMembers().size() + 1;
    }

    public synchronized boolean isCharacterInParty(final String name) {
        if (null == name || !isHaveParty()) {
            return false;
        }
        for (PartyMember partyMember : getPartyMembers()) {
            if (partyMember.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void invited(final String whoName, final ItemLoot itemLoot) {
        inviterName = whoName;
        itemLootInvited = itemLoot;
        invited = true;
        invitedStartTime = System.currentTimeMillis();
    }

    public void answerInvite() {
        invited = false;
    }

    public boolean isInvited() {
        return invited;
    }

    public String getInviterName() {
        return inviterName;
    }

    public ItemLoot getInvitedItemLoot() {
        return itemLootInvited;
    }

    public long getInvitedStartTime() {
        return invitedStartTime;
    }

    public synchronized boolean addPartyMember(final PartyMember member) {
        if ((MAX_PARTY_MEMBERS - 1) == getPartyMembers().size()) {
            return false;
        }
        getPartyMembers().add(member);
        return true;
    }


    public synchronized void addAll(final Collection<PartyMember> memberList) {
        getPartyMembers().addAll(memberList);
    }

    public synchronized boolean setLeader(final PartyMember member) {
        if (!getPartyMembers().contains(member)) {
            return false;
        }
        leader = member;
        return true;
    }

    public synchronized boolean setLeader(final int leaderId) {
        for (PartyMember partyMember : getPartyMembers()) {
            if (leaderId == partyMember.getObjId()) {
                partyMember.setLeader(true);
                leader = partyMember;
            } else {
                partyMember.setLeader(false);
            }
        }
        return true;
    }

    public PartyMember getLeader() {
        return leader;
    }

    public synchronized boolean removeMember(final PartyMember member) {
        if (member.equals(leader)) {
            leader = null;
        }
        return partyMembers.remove(member);
    }

    public synchronized void clear() {
        PartyMember[] pm = new PartyMember[partyMembers.size()];
        partyMembers.toArray(pm);
        partyMembers.clear();
        leader = null;
        itemLoot = null;
        setNowInviting(false);
    }

    public synchronized boolean isHaveParty() {
        return !partyMembers.isEmpty();
    }

    public ItemLoot getItemLoot() {
        return itemLoot;
    }

    public void setItemLoot(ItemLoot itemLoot) {
        itemLoot = itemLoot;
    }

    public Set<PartyMember> getPartyMembers() {
        return partyMembers;
    }

    public synchronized PartyObject findPartyObject(final int id) {
        for (PartyMember partyMember : getPartyMembers()) {
            if (partyMember.getObjId() == id) {
                return partyMember;
            }
            if (partyMember.isHavePet() && partyMember.getPet().getObjId() == id) {
                return partyMember.getPet();
            }
        }
        return null;
    }

}
