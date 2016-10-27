package com.l2jwalker.character.lobby;

import com.l2jwalker.character.etc.AbstractKnownList;

import java.util.NoSuchElementException;

public class Lobby extends AbstractKnownList<CharSelectInfoPackage> {

    private volatile Integer activeCharId;

    public synchronized boolean isValidSelectNumber(int n) {
        return 0 <= n && n < size();
    }

    public synchronized CharSelectInfoPackage searchCharByName(String name) throws NoSuchElementException {
        return getKnownList().values().stream()
                .filter(object -> null != name && name.equalsIgnoreCase(object.getName()))
                .findFirst().get();
    }

    public int getActiveCharId() {
        return activeCharId;
    }

    public void setActiveCharId(int activeCharId) {
        this.activeCharId = activeCharId;
    }

}
