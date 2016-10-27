package com.l2jwalker.character.etc;

import java.util.ArrayList;
import java.util.List;

public class Henna {

    private byte[] hennaStat;
    private List<Integer> hennaId = new ArrayList<Integer>(3);
    private int hennaCount = 0;

    public Henna(byte _int, byte str, byte con, byte men, byte dex, byte wit) {
        hennaStat = new byte[]{_int, str, con, men, dex, wit};
    }

    public byte getHennaStat(Stat stat) {
        if (stat == Stat.INT) return hennaStat[0];
        if (stat == Stat.STR) return hennaStat[1];
        if (stat == Stat.CON) return hennaStat[2];
        if (stat == Stat.MEN) return hennaStat[3];
        if (stat == Stat.DEX) return hennaStat[4];
        if (stat == Stat.WIT) return hennaStat[5];
        return 0;
    }

    public List<Integer> getHennaId() {
        return hennaId;
    }

    public void addHennaId(int id) {
        hennaId.set(hennaCount++, id);
    }

}
