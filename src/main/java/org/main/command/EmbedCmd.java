package org.main.command;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.main.InputAdapter;

import java.time.Instant;

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
    @Override
    public void execute() {
        System.out.println("execute prompt");
        inputAdapter.prompt();
    }


    @Override
    public long getApplicationId() {
        return client.getSelfId().asLong();
    }

    private EmbedCreateSpec createUserEmbed(String userInput) {
        EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder();

        // Split the user input by new lines to process each line separately
        String[] lines = userInput.split("\n");

        // Iterate through each line of user input
        for (String line : lines) {
            // Check if the line contains "<title>"
            if (line.startsWith("<title>") && line.endsWith("<title>")) {
                // Get the title text between "<title>" tags
                String title = line.substring("<title>".length(), line.length() - "<title>".length()).trim();
                embedBuilder.title(title);
            }
            // Check if the line contains "<text>"
            else if (line.startsWith("<text>") && line.endsWith("<text>")) {
                // Get the text between "<text>" tags
                String text = line.substring("<text>".length(), line.length() - "<text>".length()).trim();
                embedBuilder.description(text);
            }
        }
        embedBuilder.color(Color.PINK);
        embedBuilder.timestamp(Instant.now());

        return embedBuilder.build();

//    private EmbedCreateSpec createMarinEmbed() {
//        return EmbedCreateSpec.builder()
//                .color(Color.PINK)
//                .title("Title")
//                .url("https://discord4j.com")
//                .author("Some Name", "https://discord4j.com", "https://i.imgur.com/F9BhEoz.png")
//                .description("a description")
//                .thumbnail("https://i.imgur.com/F9BhEoz.png")
//                .addField("field title", "value", false)
//                .addField("\u200B", "\u200B", false)
//                .addField("inline field", "value", true)
//                .addField("inline field", "value", true)
//                .addField("inline field", "value", true)
//                .image("https://cdn.discordapp.com/attachments/987407171773923389/1130487094654017586/image0.jpg")
//                .timestamp(Instant.now())
//                .footer("footer", "https://i.imgur.com/F9BhEoz.png")
//                .build();
   }

}
