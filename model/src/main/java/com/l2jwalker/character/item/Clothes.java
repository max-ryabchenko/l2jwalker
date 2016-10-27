package com.l2jwalker.character.item;

import java.util.Collections;
import java.util.HashMap;

public class Clothes {

    protected java.util.Map<PaperDoll, WearItem> paperDollObject = Collections.synchronizedMap(new HashMap<PaperDoll, WearItem>());

    public WearItem setPaperDollObject(PaperDoll paperDoll, int wearId) {
        WearItem result = paperDollObject.get(paperDoll);
        if (null == result) {
            result = new WearItem();
            this.paperDollObject.put(paperDoll, result);
        }
        result.setObjectId(wearId);
        return result;
    }

    public WearItem setPaperDollItem(PaperDoll paperDoll, int wearId) {
        WearItem result = paperDollObject.get(paperDoll);
        if (null == result) {
            result = new WearItem();
            this.paperDollObject.put(paperDoll, result);
        }
        result.setItemId(wearId);
        return result;
    }

    public WearItem setPaperDollAugmentation(PaperDoll paperDoll, int wearId) {
        WearItem result = paperDollObject.get(paperDoll);
        if (null == result) {
            result = new WearItem();
            this.paperDollObject.put(paperDoll, result);
        }
        result.setAugmentId(wearId);
        return result;
    }

    public WearItem getWearItem(final PaperDoll paperDoll) {
        return this.paperDollObject.get(paperDoll);
    }

    public void copyData(final Clothes inventory) {
        for (PaperDoll paperDoll : PaperDoll.values()) {
            WearItem newWearItem = inventory.getWearItem(paperDoll);
            if (null == newWearItem || (-1 == newWearItem.getObjectId() && -1 == newWearItem.getItemId() && -1 == newWearItem.getAugmentId())) {
                continue;
            }
            WearItem oldWearItem = this.paperDollObject.get(paperDoll);
            if (null == oldWearItem) {
                oldWearItem = new WearItem();
                this.paperDollObject.put(paperDoll, oldWearItem);
            }
            if (-1 != newWearItem.getObjectId()) {
                oldWearItem.setObjectId(newWearItem.getObjectId());
            }
            if (-1 != newWearItem.getItemId()) {
                oldWearItem.setItemId(newWearItem.getItemId());
            }
            if (-1 != newWearItem.getAugmentId()) {
                oldWearItem.setAugmentId(newWearItem.getAugmentId());
            }
        }
    }

    public enum PaperDoll {
        PAPERDOLL_UNDER(0),
        PAPERDOLL_HEAD(1),
        PAPERDOLL_HAIR(2),
        PAPERDOLL_HAIR2(3),
        PAPERDOLL_NECK(4),
        PAPERDOLL_RHAND(5),
        PAPERDOLL_CHEST(6),
        PAPERDOLL_LHAND(7),
        PAPERDOLL_REAR(8),
        PAPERDOLL_LEAR(9),
        PAPERDOLL_GLOVES(10),
        PAPERDOLL_LEGS(11),
        PAPERDOLL_FEET(12),
        PAPERDOLL_RFINGER(13),
        PAPERDOLL_LFINGER(14),
        PAPERDOLL_LBRACELET(15),
        PAPERDOLL_RBRACELET(16),
        PAPERDOLL_DECO1(17),
        PAPERDOLL_DECO2(18),
        PAPERDOLL_DECO3(19),
        PAPERDOLL_DECO4(20),
        PAPERDOLL_DECO5(21),
        PAPERDOLL_DECO6(22),
        PAPERDOLL_CLOAK(23),
        PAPERDOLL_BELT(24);
        public final int id;

        PaperDoll(final int id) {
            this.id = id;
        }

        public int PAPERDOLL_TOTALSLOTS() {
            return PaperDoll.values().length;
        }
    }

    public static class WearItem {
        private int objectId = -1;
        private int itemId = -1;
        private int augmentId = -1;

        public int getAugmentId() {
            return augmentId;
        }

        public void setAugmentId(int augmentId) {
            this.augmentId = augmentId;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }
    }

}
