package com.l2jwalker.character.lobby;

import com.l2jwalker.character.etc.FaceStyle;
import com.l2jwalker.character.etc.FighterType;
import com.l2jwalker.character.etc.HairColor;
import com.l2jwalker.character.etc.HairStyle;
import com.l2jwalker.character.etc.Race;
import com.l2jwalker.character.etc.Sex;

public class LobbyCreateCharacterMap {
    public static FighterType[] getClasses(Race race) {
        switch (race) {
            case Human:
            case Elf:
            case DarkElf:
            case Orc:
                return new FighterType[]{FighterType.Fighter, FighterType.Mage};
            case Dwarf:
            case Kamael:
                return new FighterType[]{FighterType.Fighter};
        }
        return null;
    }

    public static StyleContainer getStyle(Race race, FighterType _class, Sex sex) {
        switch (sex) {
            case Male:
                switch (_class) {
                    case Fighter:
                        switch (race) {
                            case Human:
                            case Elf:
                            case DarkElf:
                            case Orc:
                            case Dwarf:
                                return new StyleContainer('E', 'D', 'C');
                            case Kamael:
                                return new StyleContainer('E', 'C', 'C');
                        }
                    case Mage:
                        switch (race) {
                            case Human:
                            case Elf:
                            case DarkElf:
                                return new StyleContainer('E', 'D', 'C');
                            case Orc:
                                return new StyleContainer('E', 'D', 'C');
                        }
                }
            case Female:
                switch (_class) {
                    case Fighter:
                        switch (race) {
                            case Human:
                            case Elf:
                            case DarkElf:
                            case Orc:
                            case Dwarf:
                                return new StyleContainer('G', 'D', 'C');
                            case Kamael:
                                return new StyleContainer('G', 'C', 'C');
                        }
                    case Mage:
                        switch (race) {
                            case Human:
                            case Elf:
                            case DarkElf:
                                return new StyleContainer('G', 'D', 'C');
                            case Orc:
                                return new StyleContainer('G', 'D', 'C');
                        }
                }
        }
        return null;
    }

    public static class StyleContainer {
        public final HairStyle[] hairStyles;
        public final HairColor[] hairColors;
        public final FaceStyle[] faceStyles;

        StyleContainer(final char hairStyle, final char hairColor, char faceStyle) {
            this.hairStyles = HairStyle.getRange(hairStyle);
            this.hairColors = HairColor.getRange(hairColor);
            this.faceStyles = FaceStyle.getRange(faceStyle);
        }
    }
}
