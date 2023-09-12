package org.main.command;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
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

        String title = "";
        String description = "";
        String thumbnail = "";
        String ftitle = "";
        String fvalue = "";
        String image = "";
        String url = "";
        String footerTxt = "";
        String footerURL = "";
        String msgcolor = "FFFFFF"; // Default to white if not set

        for (String line : msg) {
            if (line.startsWith("<title>")) {
                title = line.split("<title>")[1];
            }
            if (line.startsWith("<desc>")) {
                description = line.split("<desc>")[1];
            }
            if (line.startsWith("<thumbnail>")) {
                thumbnail = line.split("<thumbnail>")[1];
            }
            if (line.startsWith("<ftitle>")) {
                ftitle = line.split("<ftitle>")[1];
            }
            if (line.startsWith("<fvalue>")) {
                fvalue = line.split("<fvalue>")[1];
            }
            if (line.startsWith("<image>")) {
                image = line.split("<image>")[1];
            }
            if (line.startsWith("<url>")) {
                url = line.split("<url>")[1];
            }
            if (line.startsWith("<footer>")) {

                /*The .split(",", 2) method will split the input string into an array based on the comma separator.
                *The number 2 indicates that you want to split the string into at most 2 parts.

                *When you check footerParts.length > 0, you are essentially verifying the number of splits.
                *If footerParts.length > 1, you verify that a second part exists (i.e., the string actually had a comma and text after it).

                *footerParts[0] will contain the text before the comma (if any).
                *footerParts[1] will contain the text after the comma (if any).*/

               String footerInput = line.split("<footer>")[1];

               String footerParts[] = footerInput.split(",",2);
                if (footerParts.length > 0){
                    footerTxt = footerParts[0].trim();
                }
                if (footerParts.length > 1){
                    footerURL = footerParts[1].trim();
                }

            }
            if (line.startsWith("<color>")) {
                msgcolor = line.split("<color>")[1];
            }
        }

        // Build your embed
        if (!title.isEmpty() && !description.isEmpty()) {
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                    .title(title)
                    .description(description)
                    .thumbnail(thumbnail)
                    .addField(ftitle, fvalue, false)
                    .url(url)
                    .image(image)
                    .footer(footerTxt, footerURL)
                    .color(Color.of(Integer.parseInt(msgcolor, 16)))
                    .build();
            return embed;
        } else {
            return null; // Return null or a default embed
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
