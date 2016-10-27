package com.l2jwalker.character.chat;

public class ChatMessage {

    private final int objectId;
    private final SayType sayType;
    private final String messageText;
    private final String speakerName;

    public ChatMessage(int objectId, SayType sayType, String messageText, String speakerName) {
        this.objectId = objectId;
        this.sayType = sayType;
        this.messageText = messageText;
        this.speakerName = speakerName;
    }

    public int getObjectId() {
        return objectId;
    }

    public SayType getSayType() {
        return sayType;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSpeakerName() {
        return speakerName;
    }
}
