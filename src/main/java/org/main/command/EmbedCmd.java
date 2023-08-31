package org.main.command;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.spec.EmbedCreateSpec;
import org.main.InputAdapter;
import org.main.objects.MessageObject;

public class EmbedCmd implements Command {
    //Not initializing because client is already pre-defined in @Gateway.java.
    private final GatewayDiscordClient client;
    //Instance of EmbedFormatter is used to format the output of the command
    private EmbedFormatter embedFormatter = new EmbedFormatter();

    InputAdapter inputAdapter;
    private static long guildId = 1096316870761648209L;

    /*
    Constructor
    EmbedCmd takes in a GatewayDiscordClient as a parameter.
    EmbedCmd class is delegating or utilizing functionality provided by the InputAdapter class.
    */
    public EmbedCmd(GatewayDiscordClient client) {
        this.client = client;
        this.inputAdapter = new InputAdapter(client);
    }

    public EmbedCreateSpec generateEmbed(MessageObject input) {
        String[] msg = input.message.getContent().split("\n");
        int isValid = 0;
        String title = "";
        String description = "";
        String ftitle = "";
        String fvalue = "";
        String footer = "";
        String url = "";
        String color = "";

        for (String line : msg) {
            if (line.startsWith("<title>")) {
                title = line.split("<title>")[1];
                isValid += 1;
            }
            if (line.startsWith("<description>") || line.startsWith("<desc>")) {
                description = line.split("<description>")[1];
                isValid += 1;
            }
            if (line.startsWith("<ftitle>")) {
                ftitle = line.split("<ftitle>")[1];

            }
            if (line.startsWith("<fvalue>")) {
                fvalue = line.split("<fvalue>")[1];

            }
            if (line.startsWith("<footer>")) {
                footer = line.split("<footer>")[1];

            }
            if (line.startsWith("<url>")) {
                url = line.split("<url>")[1];

            }
            if (line.startsWith("<color>")) {
                color = line.split("<color>")[1];

            }
        }

        if (isValid == 2) {
            if (fvalue != null && ftitle == null) {
                return null;
            } else {
                EmbedCreateSpec embed = EmbedCreateSpec.builder()
                        .title(title)
                        .description(description)
                        .build();

                return embed;

            }
        } else {
            return null;
        }
    }

    @Override
    public void execute() {
        System.out.println("execute prompt");
        inputAdapter.prompt();
    }


    @Override
    public long getApplicationId() {
        return client.getSelfId().asLong();
    }

}
