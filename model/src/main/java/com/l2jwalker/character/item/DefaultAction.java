package com.l2jwalker.character.item;

import org.apache.log4j.Logger;

public enum DefaultAction {
    equip,
    peel,
    skill_reduce,
    soulshot,
    recipe,
    skill_maintain,
    spiritshot,
    dice,
    calc,
    seed,
    harvest,
    capsule,
    xmas_open,
    show_html,
    show_ssq_status,
    fishingshot,
    summon_soulshot,
    summon_spiritshot,
    call_skill,
    show_adventurer_guide_book,
    keep_exp,
    create_mpcc,
    nick_color,
    hide_name,
    start_quest;
    private static final Logger _log = Logger.getLogger(DefaultAction.class);
    public final String name;

    DefaultAction() {
        this.name = this.name().intern();
    }

    public static DefaultAction getDefaultAction(final String name) {
        if (null == name) {
            return null;
        }
        for (DefaultAction defaultAction : DefaultAction.values()) {
            if (defaultAction.name.equals(name))
                return defaultAction;
        }
        _log.warn("DefaultAction not contain: " + name);
        return null;
    }
}
