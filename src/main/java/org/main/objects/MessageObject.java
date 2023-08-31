package org.main.objects;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class MessageObject {
    public String type;
    public Message message;
    public long senderID;
    public MessageChannel channel;

    public long getSenderID() {
        return senderID;
    }

    public void setSenderID(long senderID) {
        this.senderID = senderID;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
