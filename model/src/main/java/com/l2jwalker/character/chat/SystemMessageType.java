package com.l2jwalker.character.chat;

import com.l2jwalker.character.etc.Castle;
import com.l2jwalker.character.etc.Elemental;
import com.l2jwalker.entity.ID;
import com.l2jwalker.packet.Version;
import com.l2jwalker.service.ServiceHolder;
import org.apache.log4j.Logger;

public enum SystemMessageType {
    TYPE_SYSTEM_STRING(13),
    TYPE_PLAYER_NAME(12),
    // id 11 - unknown
    TYPE_INSTANCE_NAME(10),
    TYPE_ELEMENT_NAME(9) {
        @Override
        protected String getInnerMessage(Object o, Version version) {
            return Elemental.getElemental(((Integer) o).shortValue()).name();
        }
    },
    // id 8 - same as 3
    TYPE_ZONE_NAME(7),
    TYPE_ITEM_NUMBER(6),
    TYPE_CASTLE_NAME(5) {
        @Override
        protected String getInnerMessage(Object o, Version version) {
            return Castle.getById(((Integer) o).shortValue()).name();
        }
    },
    TYPE_SKILL_NAME(4) {
        @Override
        protected String getInnerMessage(Object o, Version version) {
            return ServiceHolder.getInstance().getSkillDataService().getById(new ID(((int[]) o)[0], version)).getName() + "[" + ((int[]) o)[1] + "]";
        }
    },
    TYPE_ITEM_NAME(3) {
        @Override
        protected String getInnerMessage(Object o, Version version) {
            return ServiceHolder.getInstance().getItemDataService().getById(new ID((Integer) o, version)).getName();
        }
    },
    TYPE_NPC_NAME(2) {
        @Override
        protected String getInnerMessage(Object o, Version version) {
            return ServiceHolder.getInstance().getNpcDataService().getById(new ID((Integer) o, version)).getName();
        }
    },
    TYPE_NUMBER(1),
    TYPE_TEXT(0);

    public final int id;
    private final Logger log = Logger.getLogger(getClass());

    SystemMessageType(final int id) {
        this.id = id;
    }

    public static SystemMessageType getType(final int id) throws NoMessageType {
        for (SystemMessageType messageType : SystemMessageType.values()) {
            if (id == messageType.id)
                return messageType;
        }
        throw new NoMessageType("No system message with id = " + id);
    }

    protected String getInnerMessage(Object o, Version version) throws ClassCastException, NullPointerException {
        return o.toString();
    }

    public final String getMessage(Object o, Version version) {
        try {
            return getInnerMessage(o, version);
        } catch (ClassCastException e) {
            log.debug("ClassCastException " + name() + " - " + e.getMessage());
            return "[???]";
        } catch (NullPointerException e) {
            log.debug("NullPointerException " + name() + " - " + e.getMessage());
            return "[???]";
        }
    }
}
