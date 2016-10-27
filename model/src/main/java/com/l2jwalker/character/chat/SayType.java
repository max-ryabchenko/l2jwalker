package com.l2jwalker.character.chat;

import java.util.ArrayList;
import java.util.List;

public enum SayType {
    SYSTEM(-1),
    ALL(0, "All", false),
    SHOUT(1, "Shout", false),
    TELL(2, "Whisp", true),
    PARTY(3, "Party", false),
    CLAN(4, "Clan", false),
    GM(5),
    PETITION_PLAYER(6),
    PETITION_GM(7),
    TRADE(8, "Trade", false),
    ALLIANCE(9, "Ally", false),
    ANNOUNCEMENT(10),
    BOAT(11),
    L2FRIEND(12, "Friend", true),
    MSNCHAT(13),
    PARTYMATCH_ROOM(14),
    PARTYROOM_COMMANDER(15),
    PARTYROOM_ALL(16),
    HERO_VOICE(17),
    CRITICAL_ANNOUNCE(18),
    SCREEN_ANNOUNCE(19),
    BATTLEFIELD(20),
    MPCC_ROOM(21);
    private static List<SayType> userChatTypes;

    public final int id;
    public final String userName;
    public final boolean needTarget;

    SayType(int id) {
        this.id = id;
        this.userName = null;
        this.needTarget = false;
    }

    SayType(int id, String name, boolean needTarget) {
        this.id = id;
        this.userName = name;
        this.needTarget = needTarget;
    }

    public static SayType getById(final int id) {
        for (SayType chatType : SayType.values()) {
            if (chatType.id == id) {
                return chatType;
            }
        }
        return null;
    }

    public static List getUserTypes() {
        if (null == userChatTypes) {
            userChatTypes = new ArrayList<SayType>();
            for (SayType chatType : SayType.values()) {
                if (null != chatType.userName) {
                    userChatTypes.add(chatType);
                }
            }
        }
        return userChatTypes;
    }

    @Override
    public String toString() {
        return null != this.userName ? this.userName : this.name();
    }

}
