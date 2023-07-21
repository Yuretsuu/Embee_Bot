package org.main.command;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;

//This class is used to create guild application commands
public class GreetCmd implements Command{
    //Member variables
    long guildId = 1096316870761648209L;
    private final GatewayDiscordClient client;

    //Constructors
    public GreetCmd(GatewayDiscordClient client) {
        this.client = client;
    }
    //Greet command
    public ApplicationCommandRequest greetCommand(){
        return ApplicationCommandRequest.builder()
                .name("greet")
                .description("Greets You")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("name")
                        .description("Your name")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build()
                ).build();
    }

    @Override
    public void execute() {
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

    }
    //Getters
    public long getApplicationId() {
        return client.getSelfId().asLong();
    }
}
