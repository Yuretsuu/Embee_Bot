package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;

public class Bot {
    static long guildId = 1096316870761648209L;
    public static void main(String[] args) {

        GatewayDiscordClient client = Gateway.connect();

        ApplicationCmd applicationCmd = new ApplicationCmd(client);
        ApplicationCommandRequest greetCmdRequest = applicationCmd.createCommand();

        Gateway.connect();

        System.out.println("Connected");

        client.getRestClient().getApplicationService()
                .createGuildApplicationCommand(applicationCmd.getApplicationId(), guildId, greetCmdRequest)
                .subscribe();

        System.out.println("Succeeded.");
//
//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .map(MessageCreateEvent::getMessage)
//                .filter(message -> message.getContent().equalsIgnoreCase("/greet"))
//                .flatMap(message -> {
//                    String name = ""; // Extract the name from the message content if needed
//                    String content = message.getContent();
//                    if (content.startsWith("/greet --name")) {
//                        String[] parts = content.split("\\s+");
//                        if (parts.length >= 3) {
//                            name = parts[2];
//                        }
//                    }
//                    String response = "Hello, " + name + "! Nice to meet you."; // Customize the greeting message
//                    return message.getChannel()
//                            .flatMap(channel -> channel.createMessage(response))
//                            .doOnSuccess(sentMessage -> System.out.println("Message sent: " + sentMessage.getContent()));
//                })
//                .subscribe();

        client.on(ChatInputInteractionEvent.class, event ->
        {
            var a = event.getOption("name").get().getValue();
            String name;

            if (a.isEmpty())
                name = "empty";
            else
                name = a.get().asString();

            return event.reply("Hello, " + name + "!");
        }).subscribe();

        client.onDisconnect().block();
    }
}
