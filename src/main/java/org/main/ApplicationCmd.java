package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;

public class ApplicationCmd {
    //Member variables

    long guildId = 1096316870761648209L;

    private final GatewayDiscordClient client;

    //Constructors
    public ApplicationCmd(GatewayDiscordClient client) {
        this.client = client;
    }

    public ApplicationCommandRequest createGreetCommand(){
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
    public ApplicationCommandRequest createMessageCommand(){
        return ApplicationCommandRequest.builder()
                    .name("createMessage")
                    .description("Creates a message")
                    .addOption(ApplicationCommandOptionData.builder()
                            .name("message")
                            .description("Your name")
                            .type(ApplicationCommandOption.Type.STRING.getValue())
                            .required(true)
                            .build()
                    ).build();
    }

    //Getters
    public long getApplicationId() {

        return client.getSelfId().asLong();
    }
}
