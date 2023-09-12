package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.main.objects.MessageObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * InputAdapter Class
 *
 * Think of this class as a receptionist at a hotel. It listens for incoming messages (guests)
 * and directs them to the appropriate services (commands). Specifically, it listens for messages
 * that contain the "!e" command and then processes them.
 */
public class InputAdapter {
    // The Discord client, think of this as the hotel's communication system
    private final GatewayDiscordClient client;

    /**
     * Constructor: Initializes the Discord client
     *
     * @param client The GatewayDiscordClient object
     */
    public InputAdapter(GatewayDiscordClient client) {
        this.client = client;
    }

    /**
     * Event Listener: Listens for incoming messages
     *
     * @return A Flux stream of MessageObject
     */
    public Flux<MessageObject> prompt() {
        // Listen for message creation events, like the receptionist listening for incoming guests
        return client.on(MessageCreateEvent.class) // Listen for message create events
                .flatMap(this::handleMessageEvent);
    }
    /**
     * Handles each incoming message event
     *
     * @param event The MessageCreateEvent object
     * @return A Mono of MessageObject if the message contains "!e", otherwise an empty Mono
     */
    private Mono<MessageObject> handleMessageEvent(MessageCreateEvent event) {
        // Get the message and author information
        Message message = event.getMessage();
        String content = message.getContent();
        long authorId = message.getAuthor().map(user -> user.getId().asLong()).orElse(0L);

        // Initialize a MessageObject, like preparing a service ticket
        MessageObject msgObject = new MessageObject();

        // Check if the command is /embed
        if (content.contains("!e")) {
            // Get the channel ID where the command was invoked
            MessageChannel channel = message.getChannel().block();

            // Populate the MessageObject
            msgObject.type = "!e";
            msgObject.message = message;
            msgObject.channel = channel;
            msgObject.senderID = authorId;

            // Return the populated MessageObject, like handing over the service ticket
            return Mono.just(msgObject);

        } else {
            // For other messages, return an empty Mono, like saying "No service needed"
            return Mono.empty();
        }
    }
}
