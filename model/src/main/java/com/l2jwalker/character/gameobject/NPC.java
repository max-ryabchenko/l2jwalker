package com.l2jwalker.character.gameobject;

import com.l2jwalker.character.etc.Cubic;
import com.l2jwalker.character.etc.FaceStyle;
import com.l2jwalker.character.etc.HairColor;
import com.l2jwalker.character.etc.HairStyle;
import com.l2jwalker.character.etc.MoveType;
import com.l2jwalker.character.etc.Point;
import com.l2jwalker.character.etc.Profession;
import com.l2jwalker.character.etc.Race;
import com.l2jwalker.character.etc.Sex;
import com.l2jwalker.character.etc.TeamCircleAroundFeed;
import com.l2jwalker.entity.NpcData;
import com.l2jwalker.packet.Version;
import org.apache.log4j.Logger;

import java.util.Map;

public class NPC extends GameObject {

    private static final Logger LOG = Logger.getLogger(NPC.class);
    private static final double HP_MOD = 10000000;
    protected Race race;
    protected Sex sex;
    protected HairStyle hairStyle;
    protected HairColor hairColor;
    protected FaceStyle faceStyle;
    protected Profession profession;
    protected boolean sitting;
    protected boolean visible = true;
    protected byte mount;//1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
    protected byte PrivateStoreType;
    protected Cubic[] cubics;
    protected boolean inPartyMatchRoom = false;
    protected boolean FlyingMounted = false;
    protected int recomHave;
    protected int MountNpcId;
    protected TeamCircleAroundFeed teamCircleAroundFeed;
    protected int clanId;
    protected int clanCrestId;
    protected int clanCrestLargeId;
    protected boolean noble = false;
    protected boolean hero = false;
    protected boolean fishing = false;
    protected Point fishPoint;
    protected int templateId = -1;
    protected NpcData data;
    protected boolean attackable = true;
    protected boolean autoAttacking = false;

    protected int mAtkSpd;
    protected int pAtkSpd;

    protected int swimRunSpd;
    protected int swimWalkSpd;
    protected int flyRunSpd;
    protected int flyWalkSpd;

    protected int currentHP = 100;
    protected int maxHP = 100;
    protected int currentMP = 100;
    protected int maxMP = 100;

    protected double attackSpeedMultiplier;
    protected double collisionRadius;
    protected double collisionHeight;
    protected int rHandWeapon;
    protected int chest;
    protected int lHandWeapon;
    protected boolean inCombat;
    protected boolean summoned;
    protected int abnormalEffect;
    protected int allyId;
    protected int allyCrestId;
    protected boolean flying;
    protected int colorEffect;
    protected boolean hideName;
    protected int specialEffect;
    protected int displayEffect;
    protected int enchantEffect;
    protected int titleColor;
    protected int nameColor;
    protected int pledgeClass;
    protected int pledgeType;
    protected boolean pvpFlag;
    protected int karma;
    protected int cursedWeaponEquippedId;
    protected int clanReputation;
    protected int transformationId;
    protected int agathionId;
    protected boolean isAlikeDead;
    protected volatile boolean moving = false;
    protected Point fromPoint;
    protected Point toPoint;
    protected Long startMovingTime;
    protected RadiusVector radiusVector;
    protected MoveType moveType = MoveType.Walk;
    protected int runSpd;
    protected int walkSpd;
    protected double movementSpeedMultiplier;
    protected boolean lastValidate = false;
    protected Integer lastAttackedId = null;
    private int targetId;
    private double movingDist;

    public NPC(long id, Version version) {
        super(id, version);
    }

    public void setNpcType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    @Override
    public boolean isUnknown() {
        return "LineageNPC.clear_npc".equals(getData().getClazz());
    }

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof NPC && ((NPC) o).id == this.id;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHPPercents() {
        return (100 * this.getCurrentHP()) / this.getMaxHP();
    }

    public int getCurrentMP() {
        return this.currentMP;
    }

    public void setCurrentMP(int currentMP) {
        this.currentMP = currentMP;
    }

    public int getMaxMP() {
        return this.maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public int getMPPercents() {
        return (100 * this.getCurrentMP()) / this.getMaxMP();
    }

    public int getPercentHP() {
        return (int) (100 * (((double) (this.getCurrentHP())) / ((double) (this.getMaxHP()))));
    }

    public int getPercentMP() {
        return (int) (100 * (((double) (this.getCurrentMP())) / ((double) (this.getMaxMP()))));
    }

    public boolean isAutoAttacking() {
        return this.autoAttacking;
    }

    public void setAutoAttacking(boolean autoAttacking) {
        this.autoAttacking = autoAttacking;
    }

    public Integer getLastAttackedId() {
        return lastAttackedId;
    }

    public void setLastAttackedId(Integer lastAttackedId) {
        this.lastAttackedId = lastAttackedId;
    }

    @Override
    public void statusUpdate(Map<Integer, Integer> stats) {
        //todo
    }

    public int getCurrentHP() {
        return this.isAlikeDead ? 0 : this.currentHP;
    }

    public void setCurrentHP(final int currentHP) {
        if (null != this.data) {
            this.currentHP = (int) ((((double) currentHP) / HP_MOD) * this.maxHP);
        } else {
            this.currentHP = currentHP;
        }
    }

    public Point getToPoint() {
        return toPoint;
    }

    public void MoveToPoint(Point fromPoint, Point toPoint) {
        this.startMovingTime = System.currentTimeMillis();
        this.fromPoint = fromPoint;
        this.point = fromPoint.clone();
        this.toPoint = toPoint;
        this.radiusVector = new RadiusVector(this.fromPoint, this.toPoint);
        this.movingDist = this.fromPoint.dist2D(this.toPoint);
        this.moving = true;
    }

    public void MoveToPoint(Point toPoint) {
        this.startMovingTime = System.currentTimeMillis();
        this.fromPoint = this.point.clone();
        this.toPoint = toPoint.clone();
        this.radiusVector = new RadiusVector(this.fromPoint, this.toPoint);
        this.movingDist = this.fromPoint.dist2D(this.toPoint);
        this.moving = true;
    }

    public void stopMoving() {
        if (!moving)
            return;
        moving = false;
    }

    public void returnMoving() {
        if (!isMoving())
            return;
        point = fromPoint;
        moving = false;
    }

    public void forceStopTo(Point p) {
        point = p;
        moving = false;
    }

    @Override
    protected boolean refreshPosition() {
        if (!isMoving() || GameObjectType.Item == this.getGameObjectType()) {
            return false;
        }
        Double movingTime = (double) (System.currentTimeMillis() - this.startMovingTime);
        double speed;
        if (MoveType.Run == this.moveType) {
            speed = this.runSpd;
        } else {
            speed = this.walkSpd;
        }
        speed *= this.movementSpeedMultiplier;
        double currentDist = movingTime / 1000 * speed;
        if (currentDist >= this.movingDist) {
            this.point = this.toPoint;
            this.moving = false;
            this.lastValidate = true;
        } else {
            this.point.setX((int) (this.fromPoint.getX() + currentDist * radiusVector.x));
            this.point.setY((int) (this.fromPoint.getY() + currentDist * radiusVector.y));
            this.point.setZ((int) (this.fromPoint.getZ() + currentDist * radiusVector.z));
        }
        return true;
    }

    @Override
    public boolean isMoving() {
        refreshPosition();
        return moving;
    }

    public boolean isAlikeDead() {
        return isAlikeDead;
    }

    public void setAlikeDead(boolean alikeDead) {
        isAlikeDead = alikeDead;
    }

    @Override
    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean isHaveTarget() {
        return 0 != this.getTargetId();
    }

    public void resetTarget() {
        setTargetId(0);
    }

    public int getEnchantEffect() {
        return enchantEffect;
    }

    public void setEnchantEffect(int enchantEffect) {
        this.enchantEffect = enchantEffect;
    }

    public int getTemplateId() {
        return templateId;
    }

    public NpcData getData() {
        return this.data;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }

    public int getMAtkSpd() {
        return mAtkSpd;
    }

    public void setMAtkSpd(int mAtkSpd) {
        this.mAtkSpd = mAtkSpd;
    }

    public int getPAtkSpd() {
        return pAtkSpd;
    }

    public void setPAtkSpd(int pAtkSpd) {
        this.pAtkSpd = pAtkSpd;
    }

    public int getRunSpd() {
        return runSpd;
    }

    public void setRunSpd(int runSpd) {
        this.runSpd = runSpd;
    }

    public int getWalkSpd() {
        return walkSpd;
    }

    public void setWalkSpd(int walkSpd) {
        this.walkSpd = walkSpd;
    }

    public int getSwimRunSpd() {
        return swimRunSpd;
    }

    public void setSwimRunSpd(int swimRunSpd) {
        this.swimRunSpd = swimRunSpd;
    }

    public int getSwimWalkSpd() {
        return swimWalkSpd;
    }

    public void setSwimWalkSpd(int swimWalkSpd) {
        this.swimWalkSpd = swimWalkSpd;
    }

    public int getFlyRunSpd() {
        return flyRunSpd;
    }

    public void setFlyRunSpd(int flyRunSpd) {
        this.flyRunSpd = flyRunSpd;
    }

    public double getMovementSpeedMultiplier() {
        return movementSpeedMultiplier;
    }

    public void setMovementSpeedMultiplier(double movementSpeedMultiplier) {
        this.movementSpeedMultiplier = movementSpeedMultiplier;
    }

    public double getAttackSpeedMultiplier() {
        return attackSpeedMultiplier;
    }

    public void setAttackSpeedMultiplier(double attackSpeedMultiplier) {
        this.attackSpeedMultiplier = attackSpeedMultiplier;
    }

    public double getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(double collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public double getCollisionHeight() {
        return collisionHeight;
    }

    public void setCollisionHeight(double collisionHeight) {
        this.collisionHeight = collisionHeight;
    }

    public int getRHandWeapon() {
        return rHandWeapon;
    }

    public void setRHandWeapon(int rHandWeapon) {
        this.rHandWeapon = rHandWeapon;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getLHandWeapon() {
        return lHandWeapon;
    }

    public void setLHandWeapon(int lHandWeapon) {
        this.lHandWeapon = lHandWeapon;
    }

    public boolean isRunning() {
        return MoveType.Run == this.moveType;
    }

    public void setRunning(boolean running) {
        this.moveType = running ? MoveType.Run : MoveType.Walk;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public boolean isSummoned() {
        return summoned;
    }

    public void setSummoned(boolean summoned) {
        this.summoned = summoned;
    }

    public void setName(String name) {
        if (null == this.name)
            this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAbnormalEffect() {
        return abnormalEffect;
    }

    public void setAbnormalEffect(int abnormalEffect) {
        this.abnormalEffect = abnormalEffect;
    }

    public int getClanId() {
        return clanId;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public int getClanCrestId() {
        return clanCrestId;
    }

    public void setClanCrestId(int clanCrestId) {
        this.clanCrestId = clanCrestId;
    }

    public int getAllyId() {
        return allyId;
    }

    public void setAllyId(int allyId) {
        this.allyId = allyId;
    }

    public int getAllyCrestId() {
        return allyCrestId;
    }

    public void setAllyCrestId(int allyCrestId) {
        this.allyCrestId = allyCrestId;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public int getColorEffect() {
        return colorEffect;
    }

    public void setColorEffect(int colorEffect) {
        this.colorEffect = colorEffect;
    }

    public boolean isHideName() {
        return hideName;
    }

    public void setHideName(boolean hideName) {
        this.hideName = hideName;
    }

    public int getSpecialEffect() {
        return specialEffect;
    }

    public void setSpecialEffect(int specialEffect) {
        this.specialEffect = specialEffect;
    }

    public int getDisplayEffect() {
        return displayEffect;
    }

    public void setDisplayEffect(int displayEffect) {
        this.displayEffect = displayEffect;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public boolean isPvpFlag() {
        return pvpFlag;
    }

    public void setPvpFlag(boolean pvpFlag) {
        this.pvpFlag = pvpFlag;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getFlyWalkSpd() {
        return flyWalkSpd;
    }

    public void setFlyWalkSpd(int flyWalkSpd) {
        this.flyWalkSpd = flyWalkSpd;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = Race.getRace((byte) race);
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = HairStyle.getHairStyle((byte) hairStyle);
    }

    public void setHairStyle(HairStyle hairStyle) {
        this.hairStyle = hairStyle;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = HairColor.getHairColor((byte) hairColor);
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public FaceStyle getFaceStyle() {
        return faceStyle;
    }

    public void setFaceStyle(int faceStyle) {
        this.faceStyle = FaceStyle.getFaceStyle((byte) faceStyle);
    }

    public void setFaceStyle(FaceStyle faceStyle) {
        this.faceStyle = faceStyle;
    }

    public boolean isSitting() {
        return sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public byte getMount() {
        return mount;
    }

    public void setMount(byte mount) {
        this.mount = mount;
    }

    public byte getPrivateStoreType() {
        return PrivateStoreType;
    }

    public void setPrivateStoreType(byte privateStoreType) {
        PrivateStoreType = privateStoreType;
    }

    public Cubic[] getCubics() {
        return cubics;
    }

    public void setCubics(Cubic[] cubics) {
        this.cubics = cubics;
    }

    public boolean isInPartyMatchRoom() {
        return this.inPartyMatchRoom;
    }

    public void setInPartyMatchRoom(boolean inPartyMatchRoom) {
        this.inPartyMatchRoom = inPartyMatchRoom;
    }

    public boolean isFlyingMounted() {
        return FlyingMounted;
    }

    public void setFlyingMounted(boolean flyingMounted) {
        FlyingMounted = flyingMounted;
    }

    public int getRecomHave() {
        return recomHave;
    }

    public void setRecomHave(int recomHave) {
        this.recomHave = recomHave;
    }

    public int getMountNpcId() {
        return MountNpcId;
    }

    public void setMountNpcId(int mountNpcId) {
        MountNpcId = mountNpcId;
    }

    public TeamCircleAroundFeed getTeamCircleAroundFeed() {
        return teamCircleAroundFeed;
    }

    public void setTeamCircleAroundFeed(TeamCircleAroundFeed teamCircleAroundFeed) {
        this.teamCircleAroundFeed = teamCircleAroundFeed;
    }

    public int getClanCrestLargeId() {
        return clanCrestLargeId;
    }

    public void setClanCrestLargeId(int clanCrestLargeId) {
        this.clanCrestLargeId = clanCrestLargeId;
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

    public Point getFishPoint() {
        return fishPoint;
    }

    public void setFishPoint(Point fishPoint) {
        this.fishPoint = fishPoint;
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

    public int getCursedWeaponEquippedId() {
        return cursedWeaponEquippedId;
    }

    public void setCursedWeaponEquippedId(int cursedWeaponEquippedId) {
        this.cursedWeaponEquippedId = cursedWeaponEquippedId;
    }

    public int getClanReputation() {
        return clanReputation;
    }

    public void setClanReputation(int clanReputation) {
        this.clanReputation = clanReputation;
    }

    public int getTransformationId() {
        return transformationId;
    }

    public void setTransformationId(int transformationId) {
        this.transformationId = transformationId;
    }

    public int getAgathionId() {
        return agathionId;
    }

    public void setAgathionId(int agathionId) {
        agathionId = agathionId;
    }

    @Override
    public void setPoint(Point point) {
        this.point = point;
    }

    protected static class RadiusVector {
        final double x;
        final double y;
        final double z;

        RadiusVector(Point fromPoint, Point toPoint) {
            final double norm = Math.sqrt(
                    (toPoint.getX() - fromPoint.getX()) * (toPoint.getX() - fromPoint.getX()) +
                            (toPoint.getY() - fromPoint.getY()) * (toPoint.getY() - fromPoint.getY()) +
                            (toPoint.getZ() - fromPoint.getZ()) * (toPoint.getZ() - fromPoint.getZ()));
            x = (toPoint.getX() - fromPoint.getX()) / norm;
            y = (toPoint.getY() - fromPoint.getY()) / norm;
            z = (toPoint.getZ() - fromPoint.getZ()) / norm;
        }
    }

}
