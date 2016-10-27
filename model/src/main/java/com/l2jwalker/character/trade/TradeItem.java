package com.l2jwalker.character.trade;

import com.l2jwalker.character.item.BaseItem;
import com.l2jwalker.packet.Version;

public class TradeItem extends BaseItem {

    private final long price;

    public TradeItem(long itemId, long count, long price, Version version) {
        super(itemId, count, version);
        this.price = price;
    }

    public TradeItem copy() {
        TradeItem newItem = new TradeItem(getItemId(), getCount(), getPrice(), getVersion());
        newItem.setObjectId(this.getObjectId());
        return newItem;
    }

    public long getPrice() {
        return price;
    }
}
