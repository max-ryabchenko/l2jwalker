package com.l2jwalker.character.buff;

import com.l2jwalker.character.etc.AbstractKnownList;

import java.util.NoSuchElementException;

public class Buffs extends AbstractKnownList<Buff> {

    public synchronized long remaining(String name) {
        try {
            return get(name).getCurrentRemainingTime();
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

}
