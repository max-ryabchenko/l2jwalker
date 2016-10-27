package com.l2jwalker.character.etc;

public enum WaitType {

    Sitting(0),
    Standing(1),
    StartFakeDeath(2),
    StopFakeDeath(3);

    public final int id;

    WaitType(int id) {
        this.id = id;
    }

    public static WaitType getWaitType(int n) {
        for (WaitType waitType : WaitType.values()) {
            if (waitType.id == n) {
                return waitType;
            }
        }
        return Standing;
    }
}
