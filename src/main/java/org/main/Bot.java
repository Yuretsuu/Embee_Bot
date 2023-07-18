package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;

public class Bot {
    static long guildId = 1096316870761648209L;
    public static void main(String[] args) {

        GatewayDiscordClient client = Gateway.connect();

        ApplicationCmd applicationCmd = new ApplicationCmd(client);
        ApplicationCommandRequest greetCmdRequest = applicationCmd.createGreetCommand();

        Gateway.connect();

        System.out.println("Connected");

        //creating a guild specific command
        client.getRestClient().getApplicationService()
                .createGuildApplicationCommand(applicationCmd.getApplicationId(), guildId, greetCmdRequest)
                .subscribe();

        //Event handler for chat input interaction events
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
