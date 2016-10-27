package com.l2jwalker.character.lobby;

import com.l2jwalker.character.etc.KnownObject;
import com.l2jwalker.character.etc.Point;
import com.l2jwalker.character.etc.Profession;

public class CharSelectInfoPackage implements KnownObject {

    private String name;
    private long id = 0x00030b7a;
    private String loginName;
    private int sessionId;
    private long exp = 0;
    private int sp = 0;
    private int clanId = 0;
    private int race = 0;
    private Profession profession;
    private long deleteTimer = 0L;
    private int face = 0;
    private int hairStyle = 0;
    private int hairColor = 0;
    private int sex = 0;
    private int level = 1;
    private int maxHp = 0;
    private double currentHp = 0;
    private int maxMp = 0;
    private double currentMp = 0;
    private int[] paperdoll;
    private int karma = 0;
    private int pkKills = 0;
    private int pvpKills = 0;
    private int augmentationId = 0;
    private int transformId = 0;
    //	private int x = 0;
//	private int y = 0;
//	private int z = 0;
    private Point point;
    private byte enchant = 0;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getClanId() {
        return clanId;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public long getDeleteTimer() {
        return deleteTimer;
    }

    public void setDeleteTimer(long deleteTimer) {
        this.deleteTimer = deleteTimer;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public double getCurrentMp() {
        return currentMp;
    }

    public void setCurrentMp(double currentMp) {
        this.currentMp = currentMp;
    }

    public int[] getPaperdoll() {
        return paperdoll;
    }

    public void setPaperdoll(int[] paperdoll) {
        this.paperdoll = paperdoll;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getPkKills() {
        return pkKills;
    }

    public void setPkKills(int pkKills) {
        this.pkKills = pkKills;
    }

    public int getPvpKills() {
        return pvpKills;
    }

    public void setPvpKills(int pvpKills) {
        this.pvpKills = pvpKills;
    }

    public int getAugmentationId() {
        return augmentationId;
    }

    public void setAugmentationId(int augmentationId) {
        this.augmentationId = augmentationId;
    }

    public int getTransformId() {
        return transformId;
    }

    public void setTransformId(int transformId) {
        this.transformId = transformId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public byte getEnchant() {
        return enchant;
    }

    public void setEnchant(byte enchant) {
        this.enchant = enchant;
    }
}
