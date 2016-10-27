package com.l2jwalker.character.gameobject;

import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;

import java.util.Map;

public class DroppedItem extends GameObject {

    protected int whoDroppedId;
    protected boolean isStackable;
    protected long count;
    protected int templateId;
    protected ItemData data;

    public DroppedItem(int id, Version version) {
        super(id, version);
        this.gameObjectType = GameObjectType.Item;
    }

    @Override
    public boolean isUnknown() {
        return null == this.name || this.name.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof DroppedItem && ((DroppedItem) o).id == this.id;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public void statusUpdate(Map<Integer, Integer> map) {
    }

    @Override
    protected boolean refreshPosition() {
        return false;
    }

    public int getWhoDroppedId() {
        return whoDroppedId;
    }

    public void setWhoDroppedId(int whoDroppedId) {
        this.whoDroppedId = whoDroppedId;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public void setStackable(boolean stackable) {
        isStackable = stackable;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        if (this.isStackable()) {
            this.name += "(" + this.count + ")";
        }
    }

    public int getItemId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
        this.data = ServiceHolder.getInstance().getItemDataService().getById(new ID(this.templateId, getVersion()));
        this.name = (null != this.data) ? this.data.getName() : "[" + this.templateId + "]";
        if (this.name.isEmpty()) {
            this.name = "[" + Integer.toString(this.templateId) + "][" + Long.toString(this.id) + "]";
        }
    }

    public int getCurrentHP() {
        return 100;
    }

    public int getMaxHP() {
        return 100;
    }

    public int getCurrentMP() {
        return 100;
    }

    public int getMaxMP() {
        return 100;
    }
}
