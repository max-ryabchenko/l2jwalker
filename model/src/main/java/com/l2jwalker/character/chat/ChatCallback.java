package com.l2jwalker.character.chat;

public interface ChatCallback {
    void sendChatMessage(String message, SayType chatType, String receiverPlayerName);
}
