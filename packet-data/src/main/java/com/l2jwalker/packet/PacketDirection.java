package com.l2jwalker.packet;

public enum PacketDirection {

    /**
     * From server.
     */
    Server("server"),

    /**
     * From client.
     */
    Client("client");

    public final String name;

    PacketDirection(String packageName){
        name = packageName;
    }

    public static PacketDirection getByName(String name) {
        for (PacketDirection packetDirection : PacketDirection.values()) {
            if (packetDirection.name.equalsIgnoreCase(name)){
                return packetDirection;
            }
        }
        return null;
    }

}
