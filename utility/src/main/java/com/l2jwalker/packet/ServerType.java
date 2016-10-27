package com.l2jwalker.packet;

public enum ServerType {

    Login("login"),
    Game("game");

    public final String name;

    ServerType(String packageName) {
        name = packageName;
    }

    public static ServerType getByName(String name) {
        for (ServerType serverType : ServerType.values()) {
            if (serverType.name.equalsIgnoreCase(name)){
                return serverType;
            }
        }
        return null;
    }


}
