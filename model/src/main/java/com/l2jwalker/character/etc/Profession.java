package com.l2jwalker.character.etc;

public enum Profession {
    H_Fighter(0, null),
    H_Warrior(1, H_Fighter),
    H_Gladiator(2, H_Warrior),
    H_Warlord(3, H_Warrior),
    H_Knight(4, H_Fighter),
    H_Paladin(5, H_Knight),
    H_DarkAvenger(6, H_Knight),
    H_Rogue(7, H_Fighter),
    H_TreasureHunter(8, H_Rogue),
    H_Hawkeye(9, H_Rogue),
    H_Mage(10, null),
    H_Wizard(11, H_Mage),
    H_Sorceror(12, H_Wizard),
    H_Necromancer(13, H_Wizard),
    H_Warlock(14, H_Wizard),
    H_Cleric(15, H_Mage),
    H_Bishop(16, H_Cleric),
    H_Prophet(17, H_Cleric),
    E_Fighter(18, null),
    E_Knight(19, E_Fighter),
    E_TemplateKnight(20, E_Knight),
    E_SwordSinger(21, E_Knight),
    E_Scout(22, E_Fighter),
    E_PlainsWalker(23, E_Scout),
    E_SilverRanger(24, E_Scout),
    E_Mage(25, null),
    E_Wizard(26, E_Mage),
    E_SpellSinger(27, E_Wizard),
    E_ElementalSummoner(28, E_Wizard),
    E_Oracle(29, E_Mage),
    E_Elder(30, E_Oracle),
    DE_Fighter(31, null),
    DE_PaulusKnight(32, DE_Fighter),
    DE_ShillienKnight(33, DE_PaulusKnight),
    DE_BladeDancer(34, DE_PaulusKnight),
    DE_Assassin(35, DE_Fighter),
    DE_AbyssWalker(36, DE_Assassin),
    DE_PhantomRanger(37, DE_Assassin),
    DE_Mage(38, null),
    DE_DarkWizard(39, DE_Mage),
    DE_Spellhowler(40, DE_DarkWizard),
    DE_PhantomSummoner(41, DE_DarkWizard),
    DE_ShillienOracle(42, DE_Mage),
    DE_ShillienElder(43, DE_ShillienOracle),
    O_Fighter(44, null),
    O_Raider(45, O_Fighter),
    O_Destroyer(46, O_Raider),
    O_Monk(47, O_Fighter),
    O_Tyrant(48, O_Monk),
    O_Mage(49, null),
    O_Shaman(50, O_Mage),
    O_Overlord(51, O_Shaman),
    O_Warcryer(52, O_Shaman),
    D_Fighter(53, null),
    D_Scavenger(54, D_Fighter),
    D_BountyHunter(55, D_Scavenger),
    D_Artisan(56, D_Fighter),
    D_Warsmith(57, D_Artisan),
    H_Duelist(88, H_Gladiator),
    H_Dreadnought(89, H_Warlord),
    H_PhoenixKnight(90, H_Paladin),
    H_HellKnight(91, H_DarkAvenger),
    H_Sagittarius(92, H_Hawkeye),
    H_Adventurer(93, H_TreasureHunter),
    H_Archmage(94, H_Sorceror),
    H_Soultaker(95, H_Necromancer),
    H_ArcanaLord(96, H_Warlock),
    H_Cardinal(97, H_Bishop),
    H_Hierophant(98, H_Prophet),
    E_EvaTemplar(99, E_TemplateKnight),
    E_SwordMuse(100, E_SwordSinger),
    E_WindRider(101, E_PlainsWalker),
    E_MoonlightSentinel(102, E_SilverRanger),
    E_MysticMuse(103, E_SpellSinger),
    E_ElementalMaster(104, E_ElementalSummoner),
    E_EvaSaint(105, E_Elder),
    DE_ShillienTemplar(106, DE_ShillienKnight),
    DE_SpectralDancer(107, DE_BladeDancer),
    DE_GhostHunter(108, DE_AbyssWalker),
    DE_GhostSentinel(109, DE_PhantomRanger),
    DE_StormScreamer(110, DE_Spellhowler),
    DE_SpectralMaster(111, DE_PhantomSummoner),
    DE_ShillienSaint(112, DE_ShillienElder),
    O_Titan(113, O_Destroyer),
    O_GrandKhauatari(114, O_Tyrant),
    O_Dominator(115, O_Overlord),
    O_Doomcryer(116, O_Warcryer),
    D_FortuneSeeker(117, D_BountyHunter),
    D_Maestro(118, D_Warsmith),
    K_Male_Soldier(123, null),
    K_Female_Soldier(124, null),
    K_Male_Trooper(125, K_Male_Soldier),
    K_Female_Warder(126, K_Female_Soldier),
    K_Male_Berserker(127, K_Male_Trooper),
    K_Male_Soulbreaker(128, K_Male_Trooper),
    K_Female_Soulbreaker(129, K_Female_Warder),
    K_Female_Arbalester(130, K_Female_Warder),
    K_Male_Doombringer(131, K_Male_Berserker),
    K_Male_Soulhound(132, K_Male_Soulbreaker),
    K_Female_Soulhound(133, K_Female_Soulbreaker),
    K_Trickster(134, K_Female_Arbalester),
    K_Inspector(135, K_Female_Warder), //DS: yes, both male/female inspectors use skills from warder
    K_Judicator(136, K_Inspector);


    public final int id;
    public final Profession parent;

    Profession(int id, Profession parent) {
        this.id = id;
        this.parent = parent;
    }

    public static Profession getProfById(int id) {
        for (Profession profession : Profession.values()) {
            if (profession.id == id)
                return profession;
        }
        return null;
    }

    public static Profession getProfession(final int p) {
        for (Profession profession : Profession.values())
            if (profession.id == p)
                return profession;
        return Profession.K_Female_Soldier;
    }

    public static Profession getProfession(Race race, FighterType fighterType, Sex sex) {
        switch (race) {
            case Human:
                return (fighterType == FighterType.Fighter) ? H_Fighter : H_Mage;
            case Elf:
                return (fighterType == FighterType.Fighter) ? E_Fighter : E_Mage;
            case DarkElf:
                return (fighterType == FighterType.Fighter) ? DE_Fighter : DE_Mage;
            case Orc:
                return (fighterType == FighterType.Fighter) ? O_Fighter : O_Mage;
            case Dwarf:
                return D_Fighter;
            case Kamael:
                return (sex == Sex.Male) ? K_Male_Soldier : K_Female_Soldier;
        }
        return null;
    }
}
