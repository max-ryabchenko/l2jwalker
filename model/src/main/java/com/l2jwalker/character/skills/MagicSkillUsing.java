package com.l2jwalker.character.skills;

import org.apache.log4j.Logger;

public class MagicSkillUsing {

    private static final Logger LOG = Logger.getLogger(MagicSkillUsing.class);

    private Skill skill;
    private int lvl;
    private long startTime;
    private long hitTime;
    private long reuseDelay;
    private volatile boolean casting = false;
    private volatile Integer usingSkillId = null;
    private int[] targets;

    public MagicSkillUsing() {

    }

    public void clear() {

    }

}
