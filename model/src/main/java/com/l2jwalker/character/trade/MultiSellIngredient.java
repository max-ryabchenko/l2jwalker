package com.l2jwalker.character.trade;

import com.l2jwalker.character.item.BaseItem;
import com.l2jwalker.character.item.ItemInfo;
import com.l2jwalker.packet.Version;

public class MultiSellIngredient extends BaseItem {

    //    private boolean isTaxIngredient;
//    private boolean maintainIngredient;
    private ItemInfo itemInfo = new ItemInfo();

    public MultiSellIngredient(long itemId, long ount, Version version
//            , boolean isTaxIngredient, boolean maintainIngredient
    ) {
        super(itemId, ount, version);
//        this.isTaxIngredient = isTaxIngredient;
//        this.maintainIngredient = maintainIngredient;

    }

    public MultiSellIngredient copy() {
        return new MultiSellIngredient(getItemId(), getCount(), getVersion()
//                , this.isTaxIngredient, this.isMaintainIngredient()
        );
    }

//    public boolean isTaxIngredient() {
//        return isTaxIngredient;
//    }
//
//    public boolean isMaintainIngredient() {
//        return maintainIngredient;
//    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }
}
