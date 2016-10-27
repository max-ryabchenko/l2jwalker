package com.l2jwalker.character.etc;

public enum RestartPoint {
    Town(0),
    ClanHall(1),
    Castle(2),
    Fortress(3),
    Siege(4),
    Agathion(5),
    Jail(27);
    public final int id;

    RestartPoint(final int id) {
        this.id = id;
    }

    public static RestartPoint getByName(final String name) {
        for (RestartPoint restartPoint : RestartPoint.values()) {
            if (restartPoint.name().equalsIgnoreCase(name)) {
                return restartPoint;
            }
        }
        return null;
    }

    public static RestartPoint getById(final int id) {
        for (RestartPoint restartPoint : RestartPoint.values()) {
            if (restartPoint.id == id) {
                return restartPoint;
            }
        }
        return null;
    }
}
