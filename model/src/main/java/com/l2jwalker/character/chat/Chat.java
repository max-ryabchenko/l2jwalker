package com.l2jwalker.character.chat;

import com.l2jwalker.packet.Version;
import javolution.util.FastList;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    private static final Logger LOG = Logger.getLogger(Chat.class);

    private final List<ChatMessage> messages = new LinkedList<ChatMessage>();

    public Chat() {

    }

    private String buildUserSystemMessage(SystemMessageId systemMessageId, List<SystemMessageParam> systemMessageParams, Version version) {
        StringBuilder msg = new StringBuilder(systemMessageId.getName());
        for (int c = 0; c < msg.length(); c++) {
            if (msg.charAt(c) >= 65 && msg.charAt(c) <= 91) {// check for Capital  letter ...   ascii id of "A" is 65 ... "Z" is 91.
                msg.setCharAt(c, (char) (msg.charAt(c) + 32));     //   The character at the specified index is set to  to uppercase
            }
            if (msg.charAt(c) == '_') {
                msg.setCharAt(c, ' ');
            }
        }
        int paramNum = 0;
        if (null == systemMessageParams) {
            return msg.toString();
        }
        for (SystemMessageParam param : systemMessageParams) {//TODO npe
            paramNum++;
            String stringParam = param.getType().getMessage(param.getValue(), version);
            int paramPosition;
            paramPosition = msg.indexOf("c" + paramNum);
            if (-1 != paramPosition) {
                msg.replace(paramPosition, paramPosition + 2, stringParam);
                continue;
            }
            paramPosition = msg.indexOf("s" + paramNum);
            if (-1 != paramPosition) {
                msg.replace(paramPosition, paramPosition + 2, stringParam);
                continue;
            }
            LOG.warn("system message param position not found - " + systemMessageId.getName() +
                    ", param " + param.getType() + ", value " + param.getValue() +
                    ", stringParam " + stringParam + ", paramNum " + paramNum);
        }
        return msg.toString();
    }

    public void newSystemMessage(SystemMessageId systemMessageId, List<SystemMessageParam> systemMessageParams, Version version) {
        messages.add(new ChatMessage(0, SayType.SYSTEM, buildUserSystemMessage(systemMessageId, systemMessageParams, version), null));
    }

    public synchronized void creatureSay(SayType sayType, int objectId, String messageText, String speakerName) {
        messages.add(new ChatMessage(objectId, sayType, messageText, speakerName));
    }

    public synchronized List<ChatMessage> getMessages() {
        List<ChatMessage> result = FastList.newInstance();
        result.addAll(messages);
        return result;
    }

    public synchronized void clear() {
        getMessages().clear();
    }

}
