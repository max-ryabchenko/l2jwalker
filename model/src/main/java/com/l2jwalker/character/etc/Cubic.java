package com.l2jwalker.character.etc;

public enum Cubic {
    STORM_CUBIC(1),
    VAMPIRIC_CUBIC(2),
    LIFE_CUBIC(3),
    VIPER_CUBIC(4),
    POLTERGEIST_CUBIC(5),
    BINDING_CUBIC(6),
    AQUA_CUBIC(7),
    SPARK_CUBIC(8),
    ATTRACT_CUBIC(9),
    SMART_CUBIC_EVATEMPLAR(10),
    SMART_CUBIC_SHILLIENTEMPLAR(11),
    SMART_CUBIC_ARCANALORD(12),
    SMART_CUBIC_ELEMENTALMASTER(13),
    SMART_CUBIC_SPECTRALMASTER(14);
    public final short id;

    Cubic(int code) {
        id = (short) code;
    }

    public static Cubic getCubic(int id) {
        for (Cubic cubic : Cubic.values())
            if (cubic.id == id)
                return cubic;
        return Cubic.SMART_CUBIC_ARCANALORD;
    }
}
