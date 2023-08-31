package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.main.objects.MessageObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

// TODO WRITE COMMENTS
public class InputAdapter {
    private final GatewayDiscordClient client;
    private final Map<Long, String> userInputMap = new HashMap<>();

    //Constructor
    public InputAdapter(GatewayDiscordClient client) {
        this.client = client;
    }
    //Event Listener
    public Flux<MessageObject> prompt() {
        return client.on(MessageCreateEvent.class) // Listen for message create events
                .flatMap(this::handleMessageEvent);
    }
    private Mono<MessageObject> handleMessageEvent(MessageCreateEvent event) {
        // Get the message and author information
        Message message = event.getMessage();
        String content = message.getContent();
        long authorId = message.getAuthor().map(user -> user.getId().asLong()).orElse(0L);

        MessageObject msgObject = new MessageObject();

        // Check if the command is /embed
        if (content.contains("!e")) {
            // Get the channel ID where the command was invoked
            MessageChannel channel = message.getChannel().block();

            msgObject.type = "!e";
            msgObject.message = message;

            msgObject.channel = channel;
            msgObject.senderID = authorId;

            return Mono.just(msgObject);

        } else {
            // For other commands or messages, return an empty Mono
            return Mono.empty();
        }
    }

    public Mono<String> getUserInput(MessageCreateEvent event, long authorId) {
        System.out.println("MessageObject received");
        return client.getEventDispatcher().on(MessageCreateEvent.class)
                .filter(e -> e.getMessage().getAuthor().map(user -> user.getId().asLong()).orElse(0L) == authorId)
                .next()
                .map(MessageCreateEvent::getMessage)
                .map(Message::getContent)
                .doOnNext(userInput -> userInputMap.put(authorId, userInput));

    }
}
