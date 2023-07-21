package org.main.command;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.util.Color;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class EmbedCmd implements Command {
    private final GatewayDiscordClient client;
    private static long guildId = 1096316870761648209L;

    //Constructor
    public EmbedCmd(GatewayDiscordClient client) {
        this.client = client;
    }

    //Creates and registers the command in Discord
    public ApplicationCommandRequest embedCommand(){
        return ApplicationCommandRequest.builder()
                .name("embed")
                .description("Create a new embed")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("embed")
                        .description("Embed Details")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(false)
                        .build()
                ).build();
    }
    @Override
    public void execute() {
        // Event handler for chat input interaction events
        client.getEventDispatcher().on(ChatInputInteractionEvent.class)
                .flatMap(event -> {
                    // Prompt the user for input and return the response
                    event.reply("Please enter the details of your embed.");

                    // Store the user's input in a stateful variable for later processing
                    // The state will be associated with the user's ID (author ID) to track their response
                    Map<Long, String> userInputMap = new HashMap<>();

                    client.getEventDispatcher().on(MessageCreateEvent.class);
                    String userInput = userInputMap.get(event.getInteraction().getUser().getId().asString());


                })
                .subscribe(); // This ensures the entire chain is executed when the user responds
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
