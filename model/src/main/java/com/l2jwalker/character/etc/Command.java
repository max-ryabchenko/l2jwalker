package com.l2jwalker.character.etc;

public class Command {

    private Macros.MacrosTypes macrosTypes;
    private int skillId;
    private byte shortcutId;
    private String commandName;

    public Command(Macros.MacrosTypes macrosTypes, int skillId, byte shortcutId, String commandName) {
        this.macrosTypes = macrosTypes;
        this.skillId = skillId;
        this.shortcutId = shortcutId;
        this.commandName = commandName;
    }

    public Macros.MacrosTypes getType() {
        return macrosTypes;
    }

    public int getSkillId() {
        return skillId;
    }

    public byte getShortcutId() {
        return shortcutId;
    }

    public String getCommandName() {
        return commandName;
    }
}
