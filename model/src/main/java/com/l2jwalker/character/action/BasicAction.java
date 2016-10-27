package com.l2jwalker.character.action;

import com.l2jwalker.character.etc.KnownObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum BasicAction implements KnownObject {

    SitStand(0),
    WalkRun(1),
    PrivateStoreSell(10),
    PrivateStoreBuy(28),
    ChangePetMovementMode(15, 21),
    PetAttack(16, 22),
    PetCancelAction(17, 23),
    UnsummonPet(19),
    MountDismount(38),

    // SocialPackets
    Greeting(12),
    Victory(13),
    Advance(14),
    Yes(24),
    No(25),
    Bow(26),
    Unaware(29),
    SocialWaiting(30),
    Laugh(31),
    Applaud(33),
    Dance(34),
    Sorrow(35),
    Charm(62),
    Shyness(66),

    WildHogCannonSwitchMode(32),
    SoullessToxicSmoke(36),
    DwarvenManufacture(37),
    SoullessParasiteBurst(39),
    WildHogCannonAttack(41),
    KaiTheCatSelfDamageShield(42),
    UnicornMerrowHydroScrew(43),
    BigBoomBoomAttack(44),
    UnicornBoxerMasterRecharge(45),
    MewtheCatMegaStormStrike(46),
    SilhouetteStealBlood(47),
    MechanicGolemMechCannon(48),
    GeneralManufacture(51),
    Unsummon(52),
    MoveToTarget(53),
    MoveToTargetHatchStrider(54),
    PrivateStorePackageSell(61),
    BotReportButton(65),
    Steer(67),
    CancelControl(68),
    DestinationMap(69),
    ExitAirship(70),
    UseCoupleSocial(71, 72, 73),//???
    SiegeGolemSiegeHammer(1000),
    SinEaterUltimateBombasticBuster(1001),
    WindHatchlingStriderWildStun(1003),
    WindHatchlingStriderWildDefense(1004),
    StarHatchlingStriderBrightBurst(1005),
    StarHatchlingStriderBrightHeal(1006),
    CatQueenBlessingOfQueen(1007),
    CatQueenGiftOfQueen(1008),
    CatQueenCureOfQueen(1009),
    UnicornSeraphimBlessingOfSeraphim(1010),
    UnicornSeraphimGiftOfSeraphim(1011),
    UnicornSeraphimCureOfSeraphim(1012),
    NightshadeCurseOfShade(1013),
    NightshadeMassCurseOfShade(1014),
    NightshadeShadeSacrifice(1015),
    CursedManCursedBlow(1016),
    CursedManCursedStrikeStun(1017),
    FelineKingSlash(1031),
    FelineKingSpinningSlash(1032),
    FelineKingGripOfTheCat(1033),
    MagnusTheUnicornWhiplash(1034),
    MagnusTheUnicornTridalWave(1035),
    SpectralLordCorpseKaboom(1036),
    SpectralLordDicingDeath(1037),
    SpectralLordForceCurse(1038),
    SwoopCannonCannonFodder(1039),
    SwoopCannonBigBang(1040),
    GreatWolfBiteAttack(1041),
    GreatWolfMaul(1042),
    GreatWolfCryOfTheWolf(1043),
    GreatWolfAwakening(1044),
    GreatWolfHowl(1045),
    StriderRoar(1046),
    DivineBeastBite(1047),
    DivineBeastStunAttack(1048),
    DivineBeastFireBreath(1049),
    DivineBeastRoar(1050),
    FelineQueenBlessTheBody(1051),
    FelineQueenBlessTheSoul(1052),
    FelineQueenHaste(1053),
    UnicornSeraphimAcumen(1054),
    UnicornSeraphimClarity(1055),
    UnicornSeraphimEmpower(1056),
    UnicornSeraphimWildMagic(1057),
    NightshadeDeathWhisper(1058),
    NightshadeFocus(1059),
    NightshadeGuidance(1060),
    WildBeastFighterWhiteWeaselDeathBlow(1061),
    WildBeastFighterDoubleAttack(1062),
    WildBeastFighterSpinAttack(1063),
    WildBeastFighterMeteorShower(1064),
    ForShamanWildBeastFighterWhiteWeaselFairyPrincessAwakening(1065),
    FoxShamanSpiritShamanThunderBolt(1066),
    FoxShamanSpiritShamanFlash(1067),
    FoxShamanSpiritShamanLightningWave(1068),
    FoxShamanFairyPrincessFlare(1069),
    BuffControl1(1070),//White Weasel, Fairy Princess, Improved Baby Buffalo, Improved Baby Kookaburra, Improved Baby Cougar, Spirit Shaman, Toy Knight, Turtle Ascetic
    TigressPowerStrike(1071),
    ToyKnightPiercingAttack(1072),
    ToyKnightWhirlwind(1073),
    ToyKnightLanceSmash(1074),
    ToyKnightBattleCry(1075),
    TurtleAsceticPowerSmash(1076),
    TurtleAsceticEnergyBurst(1077),
    TurtleAsceticShockwave(1078),
    TurtleAsceticHowl(1079),
    PhoenixRush(1080),
    PhoenixCleanse(1081),
    PhoenixFlameFeather(1082),
    PhoenixFlameBeak(1083),
    SwitchState(1084),
    PantherCancel(1086),
    PantherDarkClaw(1087),
    PantherFatalClaw(1088),
    DeinonychusTailStrike(1089),
    GuardiansStriderStriderBite(1090),
    GuardiansStriderStriderFear(1091),
    GuardiansStriderStriderDash(1092),
    MaguenMaguenStrike(1093),
    MaguenMaguenWindWalk(1094),
    EliteMaguenMaguenPowerStrike(1095),
    EliteMaguenEliteMaguenWindWalk(1096),
    MaguenMaguenReturn(1097),
    EliteMaguenMaguenPartyReturn(1098),
    BabyRudolphReindeerScratch(5000),
    RosySeduction(5001),//Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum
    CriticalSeduction(5002),//// Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum
    ThunderBolt(5003),// Hyum, Lapham, Hyum, Lapham
    Flash(5004),// Hyum, Lapham, Hyum, Lapham
    LightningWave(5005),// Hyum, Lapham, Hyum, Lapham -
    BuffControl2(5006),// Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum, Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum
    PiercingAttack(5007),// Deseloph, Lilias, Deseloph, Lilias
    SpinAttack(5008),// Deseloph, Lilias, Deseloph, Lilias
    Smash(5009),// Deseloph, Lilias, Deseloph, Lilias
    Ignite1(5010),// Deseloph, Lilias, Deseloph, Lilias
    PowerSmash(5011),// Rekang, Mafum, Rekang, Mafum
    EnergyBurst(5012),// Rekang, Mafum, Rekang, Mafum
    Shockwave(5013),//Rekang, Mafum, Rekang, Mafum
    Ignite2(5014),// Rekang, Mafum, Rekang, Mafum
    SwitchStance(5015);//Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum, Deseloph, Hyum, Rekang, Lilias, Lapham, Mafum

    private static final Map<Integer, BasicAction> actionMap = new HashMap<Integer, BasicAction>();

    static {
        for (BasicAction action : BasicAction.values()) {
            for (int id : action.ids) {
                BasicAction.actionMap.put(id, action);
            }
        }
    }

    private final int[] ids;

    BasicAction(int... ids) {
        this.ids = ids;
    }

    public static Set<BasicAction> getActions(int[] actionIds) throws NullPointerException {
        Set<BasicAction> resultSet = new HashSet<BasicAction>();
        for (int actionId : actionIds) {
            if (BasicAction.actionMap.containsKey(actionId)) {
                resultSet.add(BasicAction.actionMap.get(actionId));
            }
        }
        return resultSet;
    }

    public static BasicAction getAction(int id) throws NullPointerException {
        return BasicAction.actionMap.get(id);
    }

    public int[] getIds() {
        return this.ids;
    }

    public long getId() {
        return ids[0];
    }

    public String getName() {
        return name();
    }


}
