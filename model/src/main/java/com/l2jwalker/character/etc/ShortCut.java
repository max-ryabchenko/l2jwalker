package com.l2jwalker.character.etc;

public class ShortCut {
    public final static int TYPE_ITEM = 1;
    public final static int TYPE_SKILL = 2;
    public final static int TYPE_ACTION = 3;
    public final static int TYPE_MACRO = 4;
    public final static int TYPE_RECIPE = 5;
    public final static int TYPE_TPBOOKMARK = 6;

    private final int _slot;
    private final int _page;
    private final int _type;
    private final int _id;
    private final int _level;
    private final int _characterType;
    private int _sharedReuseGroup = -1;

    public ShortCut(int slotId, int pageId, int shortcutType,
                    int shortcutId, int shortcutLevel, int characterType) {
        _slot = slotId;
        _page = pageId;
        _type = shortcutType;
        _id = shortcutId;
        _level = shortcutLevel;
        _characterType = characterType;
    }

    public int getId() {
        return _id;
    }

    public int getLevel() {
        return _level;
    }

    public int getPage() {
        return _page;
    }

    public int getSlot() {
        return _slot;
    }

    public int getType() {
        return _type;
    }

    public int getCharacterType() {
        return _characterType;
    }

    public int getSharedReuseGroup() {
        return _sharedReuseGroup;
    }

    public void setSharedReuseGroup(int g) {
        _sharedReuseGroup = g;
    }
}
