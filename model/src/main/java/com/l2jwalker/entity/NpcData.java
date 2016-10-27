package com.l2jwalker.entity;

import com.l2jwalker.character.etc.Sex;
import com.l2jwalker.character.gameobject.NpcType;
import com.l2jwalker.extractor.ImportSource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@ImportSource(name = "npc")
@Table(name = "npc")
@Cache(usage = NONSTRICT_READ_WRITE)
public class NpcData implements Identifiable<ID>, Serializable, Copyable<NpcData> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ID id;

    @ImportSource
    @Column(name = "name")
    private String name;

    @ImportSource
    @Column(name = "title")
    private String title;

    @ImportSource(name = "class")
    @Column(name = "class")
    private String clazz;

    @ImportSource
    @Column(name = "collision_radius")
    private Float collisionRadius;

    @ImportSource
    @Column(name = "collision_height")
    private Float collisionHeight;

    @ImportSource
    @Column(name = "level")
    private Integer level;

    @ImportSource
    @Column(name = "sex", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @ImportSource
    @Column(name = "type", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private NpcType type;

    @ImportSource(name = "attackrange")
    @Column(name = "attack_range")
    private Integer attackRange;

    @ImportSource(name = "hp")
    @Column(name = "hp")
    private Double maxHp;

    @ImportSource(name = "mp")
    @Column(name = "mp")
    private Double maxMp;

    @ImportSource(name = "hpreg")
    @Column(name = "hp_reg")
    private Double hpReg;

    @ImportSource(name = "mpreg")
    @Column(name = "mp_reg")
    private Double mpReg;

    @ImportSource(name = "str")
    @Column(name = "str")
    private Integer STR;

    @ImportSource(name = "con")
    @Column(name = "con")
    private Integer CON;

    @ImportSource(name = "dex")
    @Column(name = "dex")
    private Integer DEX;

    @ImportSource(name = "int")
    @Column(name = "int")
    private Integer INT;

    @ImportSource(name = "wit")
    @Column(name = "wit")
    private Integer WIT;

    @ImportSource(name = "men")
    @Column(name = "men")
    private Integer MEN;

    @ImportSource
    @Column(name = "exp")
    private Integer exp;

    @ImportSource
    @Column(name = "sp")
    private Integer sp;

    @ImportSource(name = "patk")
    @Column(name = "p_atk")
    private Double physAtk;

    @ImportSource(name = "pdef")
    @Column(name = "p_def")
    private Double physDef;

    @ImportSource(name = "matk")
    @Column(name = "m_atk")
    private Double magAtk;

    @ImportSource(name = "mdef")
    @Column(name = "m_def")
    private Double magDef;

    @ImportSource(name = "atkspd")
    @Column(name = "p_atk_spd")
    private Integer physAtkSpd;

    /**
     * critical times multiplier
     */
    @ImportSource
    @Column(name = "critical")
    private Integer critical;

    @ImportSource
    @Column(name = "aggro")
    private Integer aggro;

    @ImportSource(name = "matkspd")
    @Column(name = "m_atk_spd")
    private Integer magAtkSpd;

    @ImportSource(name = "rhand")
    @Column(name = "r_hand")
    private Integer rightHandWeapon;

    @ImportSource(name = "lhand")
    @Column(name = "l_hand")
    private Integer leftHandWeapon;

    @ImportSource(name = "enchant")
    @Column(name = "enchant")
    private Integer enchantEffect;

    @ImportSource(name = "walkspd")
    @Column(name = "walk_spd")
    private Double walkSpd;

    @ImportSource(name = "runspd")
    @Column(name = "run_spd")
    private Double runSpd;

    @ImportSource
    @Column(name = "targetable")
    private Boolean targetable;

    @ImportSource
    @Column(name = "show_name")
    private Boolean showName;

    @ImportSource
    @Column(name = "drop_herb_ground")
    private Boolean dropHerbGroup;

    @Transient
    private Boolean mob;

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof NpcData && ((NpcData) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

    @Override
    public final ID getId() {
        return id;
    }

    @Override
    public final void setId(ID id) {
        this.id = id;
    }

    @Override
    public final boolean isIdSet() {
        return null != this.getId();
    }

    @Override
    public NpcData copy() {
        NpcData npcData = new NpcData();
        copyTo(npcData);
        return npcData;
    }

    @Override
    public void copyTo(NpcData npcData) {
        npcData.setId(getId());
        npcData.setName(getName());
        npcData.setTitle(getTitle());
        npcData.setClazz(getClazz());
        npcData.setCollisionRadius(getCollisionRadius());
        npcData.setCollisionHeight(getCollisionHeight());
        npcData.setLevel(getLevel());
        npcData.setSex(getSex());
        npcData.setType(getType());
        npcData.setAttackRange(getAttackRange());
        npcData.setMaxHp(getMaxHp());
        npcData.setMaxMp(getMaxMp());
        npcData.setHpReg(getHpReg());
        npcData.setMpReg(getMpReg());
        npcData.setSTR(getSTR());
        npcData.setCON(getCON());
        npcData.setDEX(getDEX());
        npcData.setINT(getINT());
        npcData.setWIT(getWIT());
        npcData.setMEN(getMEN());
        npcData.setExp(getExp());
        npcData.setSp(getSp());
        npcData.setPhysAtk(getPhysAtk());
        npcData.setPhysDef(getPhysDef());
        npcData.setMagAtk(getMagAtk());
        npcData.setMagDef(getMagDef());
        npcData.setPhysAtkSpd(getPhysAtkSpd());
        npcData.setCritical(getCritical());
        npcData.setAggro(getAggro());
        npcData.setMagAtkSpd(getMagAtkSpd());
        npcData.setRightHandWeapon(getRightHandWeapon());
        npcData.setLeftHandWeapon(getLeftHandWeapon());
        npcData.setEnchantEffect(getEnchantEffect());
        npcData.setWalkSpd(getWalkSpd());
        npcData.setRunSpd(getRunSpd());
        npcData.setTargetable(getTargetable());
        npcData.setShowName(getShowName());
        npcData.setDropHerbGroup(getDropHerbGroup());
    }

    public Boolean isMOB() {
        if (null == getMob() && null != getType()) {
            setMob(StringUtils.containsIgnoreCase(getType().name(), "monster"));
//            mob = type.name().contains("Monster") || type.contains("monster");
        }
        return getMob();
    }

    public Boolean isNPC() {
        return !getMob();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Float getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(Float collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public Float getCollisionHeight() {
        return collisionHeight;
    }

    public void setCollisionHeight(Float collisionHeight) {
        this.collisionHeight = collisionHeight;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public NpcType getType() {
        return type;
    }

    public void setType(NpcType type) {
        this.type = type;
    }

    public Integer getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(Integer attackRange) {
        this.attackRange = attackRange;
    }

    public Double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Double maxHp) {
        this.maxHp = maxHp;
    }

    public Double getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(Double maxMp) {
        this.maxMp = maxMp;
    }

    public Double getHpReg() {
        return hpReg;
    }

    public void setHpReg(Double hpReg) {
        this.hpReg = hpReg;
    }

    public Double getMpReg() {
        return mpReg;
    }

    public void setMpReg(Double mpReg) {
        this.mpReg = mpReg;
    }

    public Integer getSTR() {
        return STR;
    }

    public void setSTR(Integer STR) {
        this.STR = STR;
    }

    public Integer getCON() {
        return CON;
    }

    public void setCON(Integer CON) {
        this.CON = CON;
    }

    public Integer getDEX() {
        return DEX;
    }

    public void setDEX(Integer DEX) {
        this.DEX = DEX;
    }

    public Integer getINT() {
        return INT;
    }

    public void setINT(Integer INT) {
        this.INT = INT;
    }

    public Integer getWIT() {
        return WIT;
    }

    public void setWIT(Integer WIT) {
        this.WIT = WIT;
    }

    public Integer getMEN() {
        return MEN;
    }

    public void setMEN(Integer MEN) {
        this.MEN = MEN;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

    public Double getPhysAtk() {
        return physAtk;
    }

    public void setPhysAtk(Double physAtk) {
        this.physAtk = physAtk;
    }

    public Double getPhysDef() {
        return physDef;
    }

    public void setPhysDef(Double physDef) {
        this.physDef = physDef;
    }

    public Double getMagAtk() {
        return magAtk;
    }

    public void setMagAtk(Double magAtk) {
        this.magAtk = magAtk;
    }

    public Double getMagDef() {
        return magDef;
    }

    public void setMagDef(Double magDef) {
        this.magDef = magDef;
    }

    public Integer getPhysAtkSpd() {
        return physAtkSpd;
    }

    public void setPhysAtkSpd(Integer physAtkSpd) {
        this.physAtkSpd = physAtkSpd;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getAggro() {
        return aggro;
    }

    public void setAggro(Integer aggro) {
        this.aggro = aggro;
    }

    public Integer getMagAtkSpd() {
        return magAtkSpd;
    }

    public void setMagAtkSpd(Integer magAtkSpd) {
        this.magAtkSpd = magAtkSpd;
    }

    public Integer getRightHandWeapon() {
        return rightHandWeapon;
    }

    public void setRightHandWeapon(Integer rightHandWeapon) {
        this.rightHandWeapon = rightHandWeapon;
    }

    public Integer getLeftHandWeapon() {
        return leftHandWeapon;
    }

    public void setLeftHandWeapon(Integer leftHandWeapon) {
        this.leftHandWeapon = leftHandWeapon;
    }

    public Integer getEnchantEffect() {
        return enchantEffect;
    }

    public void setEnchantEffect(Integer enchantEffect) {
        this.enchantEffect = enchantEffect;
    }

    public Double getWalkSpd() {
        return walkSpd;
    }

    public void setWalkSpd(Double walkSpd) {
        this.walkSpd = walkSpd;
    }

    public Double getRunSpd() {
        return runSpd;
    }

    public void setRunSpd(Double runSpd) {
        this.runSpd = runSpd;
    }

    public Boolean getTargetable() {
        return targetable;
    }

    public void setTargetable(Boolean targetable) {
        this.targetable = targetable;
    }

    public Boolean getShowName() {
        return showName;
    }

    public void setShowName(Boolean showName) {
        this.showName = showName;
    }

    public Boolean getDropHerbGroup() {
        return dropHerbGroup;
    }

    public void setDropHerbGroup(Boolean dropHerbGroup) {
        this.dropHerbGroup = dropHerbGroup;
    }

    public Boolean getMob() {
        return mob;
    }

    public void setMob(Boolean mob) {
        this.mob = mob;
    }
}
