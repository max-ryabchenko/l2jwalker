package com.l2jwalker.entity;

import com.l2jwalker.extractor.ImportSource;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@ImportSource(name = "skill_trees")
@Table(name = "skill")
@Cache(usage = NONSTRICT_READ_WRITE)
public class SkillData implements Identifiable<ID>, Serializable, Copyable<SkillData> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ID id;

    @ImportSource
    @Column(name = "name")
    private String name;

    @ImportSource
    @Column(name = "learned_by_npc", columnDefinition = "bool")
    private Boolean learnedByNpc;

    @ImportSource
    @Column(name = "is_transfer", columnDefinition = "bool")
    private Boolean transfer;

    @ImportSource
    @Column(name = "is_autoget", columnDefinition = "bool")
    private Boolean autoget;

    @Override
    public boolean equals(final Object o) {
        return null != o && o instanceof SkillData && ((SkillData) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return getId() != null;
    }

    @Override
    public SkillData copy() {
        SkillData skillData = new SkillData();
        copyTo(skillData);
        return skillData;
    }

    @Override
    public void copyTo(SkillData skillData) {
        skillData.setId(getId());
        skillData.setName(getName());
        skillData.setLearnedByNpc(isLearnByNpc());
        skillData.setTransfer(isTransfer());
        skillData.setAutoget(isAutoget());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isLearnByNpc() {
        return learnedByNpc;
    }

    public void setLearnedByNpc(Boolean learnByNpc) {
        this.learnedByNpc = learnByNpc;
    }

    public Boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(Boolean transfer) {
        this.transfer = transfer;
    }

    public Boolean isAutoget() {
        return autoget;
    }

    public void setAutoget(Boolean autoget) {
        this.autoget = autoget;
    }
}
