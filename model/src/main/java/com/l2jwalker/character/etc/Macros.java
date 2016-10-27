package com.l2jwalker.character.etc;

import java.util.ArrayList;
import java.util.List;

public class Macros {

    private int revision;
    private int id;
    private String name;
    private String desc;
    private String acronym;
    private byte icon;
    private List<Command> commands = new ArrayList<Command>();

    public List<Command> getCommands() {
        return commands;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public byte getIcon() {
        return icon;
    }

    public void setIcon(byte icon) {
        this.icon = icon;
    }

    public enum MacrosTypes {
        skill, action, shortcut
    }
}
