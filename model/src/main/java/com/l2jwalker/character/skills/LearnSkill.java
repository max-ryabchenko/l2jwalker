package com.l2jwalker.character.skills;


import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import com.l2jwalker.entity.SkillData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LearnSkill {

    private static final Logger LOG = Logger.getLogger(LearnSkill.class);

    private final long id;
    private final Version version;
    private final SkillData data;
    private final int nextLevel;
    private final int maxLevel;
    private final int spCost;
    private final int requirements;
    private final SkillType skillType;
    private final List<Req> reqList = new ArrayList<Req>(8);

    public LearnSkill(int id, int nextLevel, int maxLevel, int spCost, int requirements, SkillType skillType, Version version) {
        this.id = id;
        this.version = version;
        this.nextLevel = nextLevel;
        this.maxLevel = maxLevel;
        this.spCost = spCost;
        this.requirements = requirements;
        this.skillType = skillType;
        this.data = ServiceHolder.getInstance().getSkillDataService().getById(new ID(getId(), getVersion()));
    }

    public LearnSkill(int id, int level, int spCost, int mode, Version version) {
        this.id = id;
        this.version = version;
        this.nextLevel = level;
        this.spCost = spCost;
        this.skillType = SkillType.getById(mode);

        this.maxLevel = 0;
        this.requirements = 0;
        this.data = ServiceHolder.getInstance().getSkillDataService().getById(new ID(getId(), getVersion()));
    }

    public List<Req> getReqList() {
        return reqList;
    }

    public Version getVersion() {
        return version;
    }

    public SkillData getData() {
        return data;
    }

    public void addReq(Req req) {
        synchronized (this.reqList) {
            this.reqList.add(req);
        }
    }

    public long getId() {
        return id;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getSpCost() {
        return spCost;
    }

    public int getRequirements() {
        return requirements;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public String toString() {
        if (null != getData()) {
            return getData().getName();
        } else {
            return "skill[id=" + this.id + "]";
        }
    }

    public static class Req {

        private final long itemId;
        private final Version version;
        private final long count;
        private final int type;
        private final int unk;

        private final ItemData itemData;

        public Req(int pType, int pItemId, long pCount, int pUnk, Version version) {
            this.itemId = pItemId;
            this.version = version;
            this.type = pType;
            this.count = pCount;
            this.unk = pUnk;

            this.itemData = ServiceHolder.getInstance().getItemDataService().getById(new ID(getItemId(), getVersion()));
        }

        public long getItemId() {
            return itemId;
        }

        public long getCount() {
            return count;
        }

        public int getType() {
            return type;
        }

        public int getUnk() {
            return unk;
        }

        public Version getVersion() {
            return version;
        }

        @Override
        public String toString() {
            if (null != getItemData()) {
                return getItemData().getName();
            }
            return "item[id=" + getItemId() + "]";
        }

        public ItemData getItemData() {
            return itemData;
        }
    }

}
