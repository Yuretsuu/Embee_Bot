package org.main;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;

public class ApplicationCmd {
    //Member variables

    long guildId = 1096316870761648209L;
    long applicationId = Gateway.client.getRestClient().getApplicationId().block();

    private final GatewayDiscordClient client;

    //Constructors
    public ApplicationCmd(GatewayDiscordClient client) {
        this.client = client;
    }

    public ApplicationCommandRequest createCommand(){
    ApplicationCommandRequest greetCmdRequest = ApplicationCommandRequest.builder()
            .name("greet")
            .description("Greets You")
            .addOption(ApplicationCommandOptionData.builder()
                    .name("name")
                    .description("Your name")
                    .type(ApplicationCommandOption.Type.STRING.getValue())
                    .required(true)
                    .build()
            ).build();
        return greetCmdRequest;
    }

//    public void createGuildApplicationCommand(String applicationId, long guildId, ApplicationCommandRequest greetCmdRequest) {
//        Gateway.client.getRestClient().getApplicationService()
//                .createGuildApplicationCommand(
//                        Long.parseLong(applicationId),
//                        guildId,
//                        greetCmdRequest)
//                .subscribe();
//    }
    //Getters
    public long getApplicationId() {
        return client.getSelfId().asLong();
    }
//    Gateway.client.getRestClient().getApplicationService()
}
