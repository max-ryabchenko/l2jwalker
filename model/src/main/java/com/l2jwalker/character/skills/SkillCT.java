package com.l2jwalker.character.skills;

public class SkillCT {
    protected int id;
    protected int reuse;
    protected int remaining;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReuse() {
        return reuse;
    }

    public void setReuse(int reuse) {
        this.reuse = reuse;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
