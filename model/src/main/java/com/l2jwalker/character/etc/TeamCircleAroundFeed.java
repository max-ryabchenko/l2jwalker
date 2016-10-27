package com.l2jwalker.character.etc;

public enum TeamCircleAroundFeed {
    None(0), Blue(1), Red(2);
    public final byte id;

    TeamCircleAroundFeed(final int c) {
        this.id = (byte) c;
    }

    public static TeamCircleAroundFeed getTeamCircleAroundFeed(int c) {
        if (c == TeamCircleAroundFeed.Blue.id)
            return TeamCircleAroundFeed.Blue;
        if (c == TeamCircleAroundFeed.Red.id)
            return TeamCircleAroundFeed.Red;
        return TeamCircleAroundFeed.None;
    }
}
