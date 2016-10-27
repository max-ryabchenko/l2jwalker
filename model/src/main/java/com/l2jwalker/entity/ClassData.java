package com.l2jwalker.entity;

import com.l2jwalker.character.etc.Point;
import com.l2jwalker.character.etc.Race;
import com.l2jwalker.extractor.ImportSource;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@ImportSource(name = "char_data")
@Table(name = "class")
@Cache(usage = NONSTRICT_READ_WRITE)
public class ClassData implements Identifiable<ID>, Serializable, Copyable<ClassData> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ID id;

    @ImportSource(name = "ClassName")
    @Column(name = "name")
    private String name;

    @ImportSource(name = "RaceId")
    @Column(name = "race_id", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private Race race;

    @ImportSource(name = "STR")
    @Column(name = "str")
    private Integer STR;

    @ImportSource(name = "CON")
    @Column(name = "con")
    private Integer CON;

    @ImportSource(name = "DEX")
    @Column(name = "dex")
    private Integer DEX;

    @ImportSource(name = "INT")
    @Column(name = "int")
    private Integer INT;

    @ImportSource(name = "WIT")
    @Column(name = "wit")
    private Integer WIT;

    @ImportSource(name = "MEN")
    @Column(name = "men")
    private Integer MEN;

    @ImportSource(name = "PATK")
    @Column(name = "patk")
    private Integer physAtk;

    @ImportSource(name = "PDEF")
    @Column(name = "pdef")
    private Integer physDef;

    @ImportSource(name = "MATK")
    @Column(name = "matk")
    private Integer magAtk;

    @ImportSource(name = "MDEF")
    @Column(name = "mdef")
    private Integer magDef;

    @ImportSource(name = "PSPD")
    @Column(name = "pspd")
    private Integer physSpd;

    @ImportSource(name = "MSPD")
    @Column(name = "mspd")
    private Integer magSpd;

    @ImportSource(name = "ACC")
    @Column(name = "accuracy")
    private Integer accuracy;

    @ImportSource(name = "CRITICAL")
    @Column(name = "critical")
    private Integer critical;

    @ImportSource(name = "EVASION")
    @Column(name = "evasion")
    private Integer evasion;

    @ImportSource(name = "MOVESPD")
    @Column(name = "move_spd")
    private Integer moveSpd;

    @ImportSource(name = "LOAD")
    @Column(name = "load")
    private Integer load;

    @ImportSource
    @Embedded
    private Point point;

    @ImportSource(name = "canCraft")
    @Column(name = "can_craft")
    private Boolean canCraft;

    @ImportSource(name = "MCOLR")
    @Column(name = "m_col_r")
    private Double maleCollisionRadius;

    @ImportSource(name = "MCOLH")
    @Column(name = "m_col_h")
    private Double maleCollisionHeight;

    @ImportSource(name = "FCOLR")
    @Column(name = "f_col_r")
    private Double femaleCollisionRadius;

    @ImportSource(name = "FCOLH")
    @Column(name = "f_col_h")
    private Double femaleCollisionHeight;

    public ClassData() {

    }

    public ClassData(ID id) {
        setId(id);
    }

    @Override
    public boolean equals(final Object o) {
        return
                null != o && o instanceof ClassData &&
                        ((ClassData) o).getId().equals(getId());
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
    @Transient
    public final boolean isIdSet() {
        return null != getId();
    }

    @Override
    public ClassData copy() {
        ClassData classData = new ClassData();
        copyTo(classData);
        return classData;
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

    @Override
    public void copyTo(ClassData classData) {
        classData.setId(getId());
        classData.setName(getName());
        classData.setRace(getRace());
        classData.setSTR(getSTR());
        classData.setCON(getCON());
        classData.setDEX(getDEX());
        classData.setINT(getINT());
        classData.setWIT(getWIT());
        classData.setMEN(getMEN());
        classData.setPhysAtk(getPhysAtk());
        classData.setPhysDef(getPhysDef());
        classData.setMagAtk(getMagAtk());
        classData.setMagDef(getMagDef());
        classData.setPhysSpd(getPhysSpd());
        classData.setMagSpd(getMagSpd());
        classData.setAccuracy(getAccuracy());
        classData.setCritical(getCritical());
        classData.setEvasion(getEvasion());
        classData.setMoveSpd(getMoveSpd());
        classData.setLoad(getLoad());
        classData.setPoint(getPoint());
        classData.setCanCraft(getCanCraft());
        classData.setMaleCollisionRadius(getMaleCollisionRadius());
        classData.setMaleCollisionHeight(getMaleCollisionHeight());
        classData.setFemaleCollisionRadius(getFemaleCollisionRadius());
        classData.setFemaleCollisionHeight(getFemaleCollisionHeight());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
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

    public Integer getPhysAtk() {
        return physAtk;
    }

    public void setPhysAtk(Integer physAtk) {
        this.physAtk = physAtk;
    }

    public Integer getPhysDef() {
        return physDef;
    }

    public void setPhysDef(Integer physDef) {
        this.physDef = physDef;
    }

    public Integer getMagAtk() {
        return magAtk;
    }

    public void setMagAtk(Integer magAtk) {
        this.magAtk = magAtk;
    }

    public Integer getMagDef() {
        return magDef;
    }

    public void setMagDef(Integer magDef) {
        this.magDef = magDef;
    }

    public Integer getPhysSpd() {
        return physSpd;
    }

    public void setPhysSpd(Integer physSpd) {
        this.physSpd = physSpd;
    }

    public Integer getMagSpd() {
        return magSpd;
    }

    public void setMagSpd(Integer magSpd) {
        this.magSpd = magSpd;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getEvasion() {
        return evasion;
    }

    public void setEvasion(Integer evasion) {
        this.evasion = evasion;
    }

    public Integer getMoveSpd() {
        return moveSpd;
    }

    public void setMoveSpd(Integer moveSpd) {
        this.moveSpd = moveSpd;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Boolean getCanCraft() {
        return canCraft;
    }

    public void setCanCraft(Boolean canCraft) {
        this.canCraft = canCraft;
    }

    public Double getMaleCollisionRadius() {
        return maleCollisionRadius;
    }

    public void setMaleCollisionRadius(Double maleCollisionRadius) {
        this.maleCollisionRadius = maleCollisionRadius;
    }

    public Double getMaleCollisionHeight() {
        return maleCollisionHeight;
    }

    public void setMaleCollisionHeight(Double maleCollisionHeight) {
        this.maleCollisionHeight = maleCollisionHeight;
    }

    public Double getFemaleCollisionRadius() {
        return femaleCollisionRadius;
    }

    public void setFemaleCollisionRadius(Double femaleCollisionRadius) {
        this.femaleCollisionRadius = femaleCollisionRadius;
    }

    public Double getFemaleCollisionHeight() {
        return femaleCollisionHeight;
    }

    public void setFemaleCollisionHeight(Double femaleCollisionHeight) {
        this.femaleCollisionHeight = femaleCollisionHeight;
    }
}
