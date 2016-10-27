package com.l2jwalker.character.etc;


public enum Castle {

    Gludio(1),
    Dion(2),
    Giran(3),
    Oren(4),
    Aden(5),
    Innadril(6),
    Goddard(7),
    Rune(8),
    Schuttgart(9);

    public final int id;

    Castle(int id) {
        this.id = id;
    }

    public static Castle getById(int id) {
        for (Castle castle : Castle.values()) {
            if (castle.id == id) {
                return castle;
            }
        }
        return null;
    }

}
