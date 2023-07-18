package org.main;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.util.Color;
import java.time.Instant;

public class Bot {
    static long guildId = 1096316870761648209L;
    public static void main(String[] args) {
        //Added comment
        
        GatewayDiscordClient client = Gateway.connect();

        ApplicationCmd applicationCmd = new ApplicationCmd(client);
        ApplicationCommandRequest greetCmdRequest = applicationCmd.createCommand();

        Gateway.connect();

        System.out.println("Connected");

        client.getRestClient().getApplicationService()
                .createGuildApplicationCommand(applicationCmd.getApplicationId(), guildId, greetCmdRequest)
                .subscribe();

        System.out.println("Succeeded.");

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
        
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
            .color(Color.PINK)
            .title("Title")
            .url("https://discord4j.com")
            .author("Some Name", "https://discord4j.com", "https://i.imgur.com/F9BhEoz.png")
            .description("a description")
            .thumbnail("https://i.imgur.com/F9BhEoz.png")
            .addField("field title", "value", false)
            .addField("\u200B", "\u200B", false)
            .addField("inline field", "value", true)
            .addField("inline field", "value", true)
            .addField("inline field", "value", true)
            .image("https://cdn.discordapp.com/attachments/987407171773923389/1130487094654017586/image0.jpg")
            .timestamp(Instant.now())
            .footer("footer", "https://i.imgur.com/F9BhEoz.png")
            .build();
        
        client.getChannelById(Snowflake.of("1096318842181001296"))
            .ofType(GuildMessageChannel.class)
            .flatMap(channel -> channel.createMessage(embed))
            .subscribe();
        
        client.onDisconnect().block();
    }
}
