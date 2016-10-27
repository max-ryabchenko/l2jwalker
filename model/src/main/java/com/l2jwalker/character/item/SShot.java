package com.l2jwalker.character.item;

import com.l2jwalker.character.etc.FighterType;
import com.l2jwalker.character.etc.Grade;

public enum SShot {
    BeastSoulshot(FighterType.Fighter, Grade.NoGrade, true, false, 6645, 20332),
    BeastSpiritshot(FighterType.Mage, Grade.NoGrade, true, false, 6646, 20333),
    BlessedBeastSpiritshot(FighterType.Mage, Grade.NoGrade, true, false, 6647, 20334),
    FishingShot_NonGrade(FighterType.Fighter, Grade.NoGrade, false, true, 6535),
    FishingShot_D(FighterType.Fighter, Grade.D, false, true, 6536),
    FishingShot_C(FighterType.Fighter, Grade.C, false, true, 6537),
    FishingShot_B(FighterType.Fighter, Grade.B, false, true, 6538),
    FishingShot_A(FighterType.Fighter, Grade.A, false, true, 6539),
    FishingShot_S(FighterType.Fighter, Grade.S, false, true, 6540),
    Spiritshot_NoGrade(FighterType.Mage, Grade.NoGrade, false, false, 2509),
    Spiritshot_D(FighterType.Mage, Grade.D, false, false, 2510, 22077),
    Spiritshot_C(FighterType.Mage, Grade.C, false, false, 2511, 22078),
    Spiritshot_B(FighterType.Mage, Grade.B, false, false, 2512, 22079),
    Spiritshot_A(FighterType.Mage, Grade.A, false, false, 2513, 22080),
    Spiritshot_S(FighterType.Mage, Grade.S, false, false, 2514, 22081),
    BlessedSpiritshot_NoGrade(FighterType.Mage, Grade.NoGrade, false, false, 3947),
    BlessedSpiritshot_D(FighterType.Mage, Grade.D, false, false, 3948, 22072),
    BlessedSpiritshot_C(FighterType.Mage, Grade.C, false, false, 3949, 22073),
    BlessedSpiritshot_B(FighterType.Mage, Grade.B, false, false, 3950, 22074),
    BlessedSpiritshot_A(FighterType.Mage, Grade.A, false, false, 3951, 22075),
    BlessedSpiritshot_S(FighterType.Mage, Grade.S, false, false, 3952, 22076),
    Spiritshot_Beginners(FighterType.Mage, Grade.NoGrade, false, false, 5790),
    Soulshot_Beginners(FighterType.Fighter, Grade.NoGrade, false, false, 5789),
    Soulshot_NoGrade(FighterType.Fighter, Grade.NoGrade, false, false, 1835),
    Soulshot_D(FighterType.Fighter, Grade.D, false, false, 1463, 22082),
    Soulshot_C(FighterType.Fighter, Grade.C, false, false, 1464, 22083),
    Soulshot_B(FighterType.Fighter, Grade.B, false, false, 1465, 22084),
    Soulshot_A(FighterType.Fighter, Grade.A, false, false, 1466, 22085),
    Soulshot_S(FighterType.Fighter, Grade.S, false, false, 1467, 22086),;

    public final int[] itemIds;
    public final boolean pets;
    public final FighterType fighterType;
    public final Grade grade;
    public final boolean fishing;


    SShot(final FighterType fighterType, final Grade grade, final boolean pets, final boolean fishing, final int... itemIds) {
        this.fighterType = fighterType;
        this.grade = grade;
        this.pets = pets;
        this.fishing = fishing;
        this.itemIds = itemIds;
    }

    public static boolean isShotType(final int itemTemplateId) {
        for (SShot sShot : SShot.values()) {
            for (int id : sShot.itemIds) {
                if (itemTemplateId == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public static SShot findShot(int id) {
        for (SShot sShot : SShot.values()) {
            if (sShot.isThisType(id)) {
                return sShot;
            }
        }
        return null;
    }

    public boolean isThisType(final int itemId) {
        for (int i : this.itemIds) {
            if (itemId == i) {
                return true;
            }
        }
        return false;
    }

}