package com.l2jwalker.character.skills;

public enum SkillType {

    Usual(0),
    Fishing(1),
    Clan(2),
    SubUnit(3),
    unk4(4),
    unk5(5),
    Special(6);

    public final int id;

    SkillType(int id) {
        this.id = id;
    }

    public static SkillType getById(int id) {
        for (SkillType skillType : SkillType.values()) {
            if (skillType.id == id) {
                return skillType;
            }
        }
        return null;
    }
}
