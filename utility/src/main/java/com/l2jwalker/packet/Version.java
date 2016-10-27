package com.l2jwalker.packet;


public enum Version {

    C1("Harbingers of War"),
    C2("Age of Splendor"),
    C3("Rise of Darkness"),
    C4("Scions of Destiny"),
    C5("Oath of Blood"),
    Interlude("Interlude"),
    Kamael("The Kamael"),
    HB("The Kamael Hellbound"),
    G1("Gracia Part 1"),
    G2("Gracia Part 2"),
    GF("Gracia Final"),
    GE("Gracia Epilogue"),
    Freya("Freya"),
    HV("High Five"),
    GOD("Goddess of Destruction");

    private final String title;

    Version(String title) {
        this.title = title;
    }


    public static Version getByName(String title) {
        if (null == title) {
            return null;
        }
        for (Version packetModel : Version.values()) {
            if (null != packetModel && packetModel.getTitle().equalsIgnoreCase(title)) {
                return packetModel;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public boolean isGreaterThan(Version version) {
        return ordinal() > version.ordinal();
    }

    public boolean isGreaterOrEqualThan(Version version) {
        return ordinal() >= version.ordinal();
    }

    public boolean isLowerThan(Version version) {
        return !isGreaterOrEqualThan(version);
    }

    public boolean isLowerOrEqualThan(Version version) {
        return !isGreaterThan(version);
    }

}
