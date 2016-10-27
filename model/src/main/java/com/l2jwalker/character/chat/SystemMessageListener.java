package com.l2jwalker.character.chat;

import java.util.List;

public interface SystemMessageListener {
    void newMessage(SystemMessageId systemMessageId, List<SystemMessageParam> systemMessageParams);
}
