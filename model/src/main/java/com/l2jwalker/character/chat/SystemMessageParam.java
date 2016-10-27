package com.l2jwalker.character.chat;

public final class SystemMessageParam {
    private final SystemMessageType type;
    private final Object value;

    public SystemMessageParam(final SystemMessageType type, final Object value) {
        this.type = type;
        this.value = value;
    }

    public final SystemMessageType getType() {
        return type;
    }

    public final Object getValue() {
        return value;
    }

    public final String getStringValue() {
        return (String) value;
    }

    public final int getIntValue() {
        return ((Integer) value).intValue();
    }

    public final long getLongValue() {
        return ((Long) value).longValue();
    }

    public final int[] getIntArrayValue() {
        return (int[]) value;
    }
}
