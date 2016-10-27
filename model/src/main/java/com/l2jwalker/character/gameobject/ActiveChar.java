package com.l2jwalker.character.gameobject;

import com.l2jwalker.character.etc.Elemental;
import com.l2jwalker.character.etc.MoveType;
import com.l2jwalker.character.etc.Sex;
import com.l2jwalker.character.etc.TeamCircleAroundFeed;
import com.l2jwalker.packet.Version;
import com.l2jwalker.util.PropertiesReader;
import org.apache.log4j.Logger;

import java.util.Map;

public class ActiveChar extends NPC implements ActiveCharInfo {

    private static final Logger LOG = Logger.getLogger(ActiveChar.class);
    private static int validatePeriod = PropertiesReader.getIntegerProperty("movement.validate_period");
    protected final Map<Elemental, Short> defenseElementalValue = javolution.util.FastMap.newInstance();
    protected final CharacterStatus characterStatus = new CharacterStatus();
    protected int sessionId;
    protected int classId;
    protected int sp = 0;
    protected long exp = 0;
    protected int lvl = 1;
    protected int karma;
    protected int pk;
    protected int INT;
    protected int STR;
    protected int CON;
    protected int MEN;
    protected int DEX;
    protected int WIT;
    protected int currentLoad = 100;
    protected int maxLoad = 100;
    protected boolean activeWeaponItem;
    protected int maxTalismanCount;
    protected int cloakStatus;
    protected int pAtk;
    protected int pDef;
    protected int evasion;
    protected int accuracy;
    protected int criticalHit;
    protected int mAtk;
    protected int mDef;
    protected boolean isGM;
    protected int relation;
    protected byte mountType;
    protected byte privateStoreType;
    protected boolean dwarvenCraft;
    protected int pvp;
    protected boolean partyMatchRoom;
    protected int abnormalEffect;
    protected boolean flyingMounted;
    protected int clanPrivilegs;
    protected short recomLeft;
    protected int mountNpcId;
    protected short inventoryLimit;
    protected int maxCP = 100;
    protected int currentCP = 100;
    protected TeamCircleAroundFeed teamCircleAroundFeed;
    protected boolean noble;
    protected boolean hero;
    protected boolean fishing;
    protected int nameColor;
    protected int pledgeClass;
    protected int pledgeType;
    protected int titleColor;
    protected int cursedWeaponEquipped;
    protected int transformationId;
    protected short attackAttribute;
    protected int fame;
    protected boolean minimapAllowed;
    protected int vitalityPoints;
    protected int specialEffect;
    private long lastValidateTime;

    public ActiveChar(long id, Version version) {
        super(id, version);
        gameObjectType = GameObjectType.ActiveChar;
        setMoveType(MoveType.Run);
    }

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof ActiveChar && ((ActiveChar) o).id == this.id;
    }

    @Override
    public boolean isUnknown() {
        return false;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    @Override
    public void statusUpdate(Map<Integer, Integer> stats) {
//        for (Integer key : stats.keySet()) {
//            switch (key) {
//                case StatusUpdate.LEVEL:
//                    this.lvl = stats.get(key);
//                    break;
//                case StatusUpdate.EXP:
//                    this.exp = stats.get(key);
//                    break;
//                case StatusUpdate.STR:
//                    this.STR = stats.get(key);
//                    break;
//                case StatusUpdate.DEX:
//                    this.DEX = stats.get(key);
//                    break;
//                case StatusUpdate.CON:
//                    this.CON = stats.get(key);
//                    break;
//                case StatusUpdate.INT:
//                    this.INT = stats.get(key);
//                    break;
//                case StatusUpdate.WIT:
//                    this.WIT = stats.get(key);
//                    break;
//                case StatusUpdate.MEN:
//                    this.MEN = stats.get(key);
//                    break;
//                case StatusUpdate.SP:
//                    this.sp = stats.get(key);
//                    break;
//                case StatusUpdate.CUR_LOAD:
//                    this.currentLoad = stats.get(key);
//                    break;
//                case StatusUpdate.MAX_LOAD:
//                    this.maxLoad = stats.get(key);
//                    break;
//                case StatusUpdate.P_ATK:
//                    this.pAtk = stats.get(key);
//                    break;
//                case StatusUpdate.ATK_SPD:
//                    this.pAtkSpd = stats.get(key);
//                    break;
//                case StatusUpdate.P_DEF:
//                    this.pDef = stats.get(key);
//                    break;
//                case StatusUpdate.EVASION:
//                    this.evasion = stats.get(key);
//                    break;
//                case StatusUpdate.ACCURACY:
//                    this.accuracy = stats.get(key);
//                    break;
//                case StatusUpdate.CRITICAL:
//                    this.criticalHit = stats.get(key);
//                    break;
//                case StatusUpdate.M_ATK:
//                    this.mAtk = stats.get(key);
//                    break;
//                case StatusUpdate.CAST_SPD:
//                    this.mAtkSpd = stats.get(key);
//                    break;
//                case StatusUpdate.M_DEF:
//                    this.mDef = stats.get(key);
//                    break;
//                case StatusUpdate.PVP_FLAG:
//                    this.pvpFlag = stats.get(key) == 1;
//                    break;
//                case StatusUpdate.KARMA:
//                    this.karma = stats.get(key);
//                    break;
//                case StatusUpdate.CUR_CP:
//                    this.currentCP = stats.get(key);
//                    break;
//                case StatusUpdate.MAX_CP:
//                    this.maxCP = stats.get(key);
//                    break;
//                default:
//                    super.statusUpdate(key, stats.get(key));
//                    break;
//            }
//        }
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(int sex) {
        if (1 == sex) this.sex = Sex.Female;
        else this.sex = Sex.Male;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getMEN() {
        return MEN;
    }

    public void setMEN(int MEN) {
        this.MEN = MEN;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getWIT() {
        return WIT;
    }

    public void setWIT(int WIT) {
        this.WIT = WIT;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public boolean isActiveWeaponItem() {
        return activeWeaponItem;
    }

    public void setActiveWeaponItem(boolean activeWeaponItem) {
        this.activeWeaponItem = activeWeaponItem;
    }

    public int getMaxTalismanCount() {
        return maxTalismanCount;
    }

    public void setMaxTalismanCount(int maxTalismanCount) {
        this.maxTalismanCount = maxTalismanCount;
    }

    public int getCloakStatus() {
        return cloakStatus;
    }

    public void setCloakStatus(int cloakStatus) {
        this.cloakStatus = cloakStatus;
    }

    public int getPAtk() {
        return pAtk;
    }

    public void setPAtk(int pAtk) {
        this.pAtk = pAtk;
    }

    public int getPDef() {
        return pDef;
    }

    public void setPDef(int pDef) {
        this.pDef = pDef;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getCriticalHit() {
        return criticalHit;
    }

    public void setCriticalHit(int criticalHit) {
        this.criticalHit = criticalHit;
    }

    public int getMAtk() {
        return mAtk;
    }

    public void setMAtk(int mAtk) {
        this.mAtk = mAtk;
    }

    public int getMDef() {
        return mDef;
    }

    public void setMDef(int mDef) {
        this.mDef = mDef;
    }

    public boolean isGM() {
        return isGM;
    }

    public void setGM(boolean GM) {
        isGM = GM;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public byte getMountType() {
        return mountType;
    }

    public void setMountType(byte mountType) {
        this.mountType = mountType;
    }

    public byte getPrivateStoreType() {
        return privateStoreType;
    }

    public void setPrivateStoreType(byte privateStoreType) {
        this.privateStoreType = privateStoreType;
    }

    public boolean isDwarvenCraft() {
        return dwarvenCraft;
    }

    public void setDwarvenCraft(boolean dwarvenCraft) {
        this.dwarvenCraft = dwarvenCraft;
    }

    public boolean isPartyMatchRoom() {
        return partyMatchRoom;
    }

    public void setPartyMatchRoom(boolean partyMatchRoom) {
        this.partyMatchRoom = partyMatchRoom;
    }

    public int getAbnormalEffect() {
        return abnormalEffect;
    }

    public void setAbnormalEffect(int abnormalEffect) {
        this.abnormalEffect = abnormalEffect;
    }

    public boolean isFlyingMounted() {
        return flyingMounted;
    }

    public void setFlyingMounted(boolean flyingMounted) {
        this.flyingMounted = flyingMounted;
    }

    public int getClanPrivilegs() {
        return clanPrivilegs;
    }

    public void setClanPrivilegs(int clanPrivilegs) {
        this.clanPrivilegs = clanPrivilegs;
    }

    public short getRecomLeft() {
        return recomLeft;
    }

    public void setRecomLeft(short recomLeft) {
        this.recomLeft = recomLeft;
    }

    public int getMountNpcId() {
        return mountNpcId;
    }

    public void setMountNpcId(int mountNpcId) {
        this.mountNpcId = mountNpcId;
    }

    public short getInventoryLimit() {
        return inventoryLimit;
    }

    public void setInventoryLimit(short inventoryLimit) {
        this.inventoryLimit = inventoryLimit;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public void setMaxCP(int maxCP) {
        this.maxCP = maxCP;
    }

    public int getCurrentCP() {
        return currentCP;
    }

    public void setCurrentCP(int currentCP) {
        this.currentCP = currentCP;
    }

    public TeamCircleAroundFeed getTeamCircleAroundFeed() {
        return teamCircleAroundFeed;
    }

    public void setTeamCircleAroundFeed(TeamCircleAroundFeed teamCircleAroundFeed) {
        this.teamCircleAroundFeed = teamCircleAroundFeed;
    }

    public boolean isNoble() {
        return noble;
    }

    public void setNoble(boolean noble) {
        this.noble = noble;
    }

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public boolean isFishing() {
        return fishing;
    }

    public void setFishing(boolean fishing) {
        this.fishing = fishing;
    }

    public int getNameColor() {
        return nameColor;
    }

    public void setNameColor(int nameColor) {
        this.nameColor = nameColor;
    }

    public int getPledgeClass() {
        return pledgeClass;
    }

    public void setPledgeClass(int pledgeClass) {
        this.pledgeClass = pledgeClass;
    }

    public int getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(int pledgeType) {
        this.pledgeType = pledgeType;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getCursedWeaponEquipped() {
        return cursedWeaponEquipped;
    }

    public void setCursedWeaponEquipped(int cursedWeaponEquipped) {
        this.cursedWeaponEquipped = cursedWeaponEquipped;
    }

    public int getTransformationId() {
        return transformationId;
    }

    public void setTransformationId(int transformationId) {
        this.transformationId = transformationId;
    }

    public short getAttackAttribute() {
        return attackAttribute;
    }

    public void setAttackAttribute(short attackAttribute) {
        this.attackAttribute = attackAttribute;
    }

    public Map<Elemental, Short> getDefenseElementalValue() {
        return defenseElementalValue;
    }

    public void setDefenseElementalValue(Map<Elemental, Short> newValues) {
        synchronized (this.defenseElementalValue) {
            this.defenseElementalValue.clear();
            this.defenseElementalValue.putAll(newValues);
        }
    }

    public void setDefenseElementalValue(Elemental elemental, short value) {
        this.defenseElementalValue.put(elemental, value);
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public boolean isMinimapAllowed() {
        return minimapAllowed;
    }

    public void setMinimapAllowed(boolean minimapAllowed) {
        this.minimapAllowed = minimapAllowed;
    }

    public int getVitalityPoints() {
        return vitalityPoints;
    }

    public void setVitalityPoints(int vitalityPoints) {
        this.vitalityPoints = vitalityPoints;
    }

    public int getSpecialEffect() {
        return specialEffect;
    }

    public void setSpecialEffect(int specialEffect) {
        this.specialEffect = specialEffect;
    }

    public int getXPPercents() {
        return 100;
    }

    public int getPvp() {
        return pvp;
    }

    public void setPvp(int pvp) {
        this.pvp = pvp;
    }

//    public void copyData(UserInfo userInfo){
//        ActiveChar activeChar = userInfo.getActiveChar();
//        setPoint(activeChar.getPoint());
//        setVehicleId(activeChar.getVehicleId());
//        setName(activeChar.getName());
//        setRace(activeChar.getRace());
//        setSex(activeChar.getSex());
//
//        setClassId(activeChar.getClassId());
//
//        setLvl(activeChar.getLvl());
//        setExp(activeChar.getExp());
//        setSTR(activeChar.getSTR());
//        setDEX(activeChar.getDEX());
//        setCON(activeChar.getCON());
//        setINT(activeChar.getINT());
//        setWIT(activeChar.getWIT());
//        setMEN(activeChar.getMEN());
//        setMaxHP(activeChar.getMaxHP());
//        setCurrentHP(activeChar.getCurrentHP());
//        setMaxMP(activeChar.getMaxMP());
//        setCurrentMP(activeChar.getCurrentMP());
//        setSp(activeChar.getSp());
//        setCurrentLoad(activeChar.getCurrentLoad());
//        setMaxLoad(activeChar.getMaxLoad());
//
//        setActiveWeaponItem(activeChar.isActiveWeaponItem());
//
//        setMaxTalismanCount(activeChar.getMaxTalismanCount());
//        setCloakStatus(activeChar.getCloakStatus());
//        setPAtk(activeChar.getPAtk());
//        setPAtkSpd(activeChar.getPAtkSpd());
//        setPDef(activeChar.getPDef());
//        setEvasion(activeChar.getEvasion());
//        setAccuracy(activeChar.getAccuracy());
//        setCriticalHit(activeChar.getCriticalHit());
//        setMAtk(activeChar.getMAtk());
//
//        setMAtkSpd(activeChar.getMAtkSpd());
//        setPAtkSpd(activeChar.getPAtkSpd());
//
//        setMDef(activeChar.getMDef());
//
//        setPvpFlag(activeChar.isPvpFlag());
//        setKarma(activeChar.getKarma());
//
//        setRunSpd(activeChar.getRunSpd());
//        setWalkSpd(activeChar.getWalkSpd());
//        setSwimRunSpd(activeChar.getSwimRunSpd());
//        setSwimWalkSpd(activeChar.getSwimWalkSpd());
//
//        setFlyRunSpd(activeChar.getFlyRunSpd());
//        setFlyWalkSpd(activeChar.getFlyWalkSpd());
//        setMovementSpeedMultiplier(activeChar.getMovementSpeedMultiplier());
//        setAttackSpeedMultiplier(activeChar.getAttackSpeedMultiplier());
//
//        setCollisionRadius(activeChar.getCollisionRadius());
//        setCollisionHeight(activeChar.getCollisionHeight());
//
//        setHairStyle(activeChar.getHairStyle());
//        setHairColor(activeChar.getHairColor());
//        setFaceStyle(activeChar.getFaceStyle());
//        setGM(activeChar.isGM());
//
//        setTitle(activeChar.getTitle());
//
//        setClanId(activeChar.getClanId());
//        setClanCrestId(activeChar.getClanCrestId());
//        setAllyId(activeChar.getAllyId());
//        setAllyCrestId(activeChar.getAllyCrestId());
//
//        setRelation(activeChar.getRelation());
//        setMountType(activeChar.getMountType());
//        setPrivateStoreType(activeChar.getPrivateStoreType());
//        setDwarvenCraft(activeChar.isDwarvenCraft());
//        setPk(activeChar.getPk());
//        setPvp(activeChar.getPvp());
//
//        cubics = activeChar.getCubics();
//        setPartyMatchRoom(activeChar.isPartyMatchRoom());
//
//        setAbnormalEffect(activeChar.getAbnormalEffect());
//        setFlyingMounted(activeChar.isFlyingMounted());
//
//        setClanPrivilegs(activeChar.getClanPrivilegs());
//
//        setRecomLeft(activeChar.getRecomLeft());
//        setRecomHave(activeChar.getRecomHave());
//        setMountNpcId(activeChar.getMountNpcId());
//        setInventoryLimit(activeChar.getInventoryLimit());
//
//        setClassId(activeChar.getClassId());
//        setMaxCP(activeChar.getMaxCP());
//        setCurrentCP(activeChar.getCurrentCP());
//        setEnchantEffect(activeChar.getEnchantEffect());
//        setTeamCircleAroundFeed(activeChar.getTeamCircleAroundFeed());
//
//        setClanCrestLargeId(activeChar.getClanCrestLargeId());
//        setNoble(activeChar.isNoble());
//        setHero(activeChar.isHero());
//
//        setFishing(activeChar.isFishing());
//        setFishPoint(activeChar.getFishPoint());
//        setNameColor(activeChar.getNameColor());
//
//        setRunning(activeChar.isRunning());
//
//        setPledgeClass(activeChar.getPledgeClass());
//        setPledgeType(activeChar.getPledgeType());
//
//        setTitleColor(activeChar.getTitleColor());
//
//        setCursedWeaponEquipped(activeChar.getCursedWeaponEquipped());
//        setTransformationId(activeChar.getTransformationId());
//
//        setAttackAttribute(activeChar.getAttackAttribute());
//        setDefenseElementalValue(activeChar.getDefenseElementalValue());
//
//        setAgathionId(activeChar.getAgathionId());
//
//        setFame(activeChar.getFame());
//        setMinimapAllowed(activeChar.isMinimapAllowed());
//        setVitalityPoints(activeChar.getVitalityPoints());
//        setSpecialEffect(activeChar.getSpecialEffect());
//    }

    public CharacterStatus getCharacterStatus() {
        return characterStatus;
    }
}
