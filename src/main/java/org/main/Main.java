package org.main;

import discord4j.core.GatewayDiscordClient;
import org.main.command.EmbedCmd;
import org.main.command.GreetCmd;

public class Main {
    static long guildId = 1096316870761648209L;
    public static void main(String[] args) {

        GatewayDiscordClient client = Gateway.connect();

        //if created command exists, don't execute. if it does, execute
        GreetCmd greetCmd = new GreetCmd(client);
        EmbedCmd embedCmd = new EmbedCmd(client);
        InputAdapter adapter = new InputAdapter(client);

        //Switch case to execute based on user input
//        greetCmd.execute();

        switch () {
            case "greet":
                greetCmd.execute();
                break;
            case "embed":
                embedCmd.execute();
                break;
        }
        embedCmd.execute();

//      ApplicationCommandRequest greetCmdRequest = greetCmd.createGreetCommand();
//      ApplicationCommandRequest embedCmdRequest = embedCmd.embedCommand();

        System.out.println("Connected");

        //creating a guild specific command. needed for updates and registration in Discords API
//        client.getRestClient().getApplicationService()
//                .createGuildApplicationCommand(greetCmd.getApplicationId(), guildId, greetCmdRequest)
//                .subscribe();

//        client.getRestClient().getApplicationService()
//                .createGuildApplicationCommand(embedCmd.getApplicationId(), guildId, embedCmdRequest)
//                .subscribe();

//        EmbedCreateSpec embed = EmbedCreateSpec.builder()
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
//
//        client.getChannelById(Snowflake.of("1096318842181001296"))
//                .ofType(GuildMessageChannel.class)
//                .flatMap(channel -> channel.createMessage(embed))
//                .subscribe();

        client.onDisconnect().block();
    }
}
