package org.main;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import org.main.command.EmbedCmd;
import org.main.objects.MessageObject;

public class Main {
//    static long guildId = 1096316870761648209L;
    public static void main(String[] args) throws InterruptedException {

        /*Connect to Discord Gateway and get the client object
        * PERSONAL NOTES:
        * What is a Discord Gateway?
        * A discord gateway facilitates the flow of data
        * between client applications and discord servers. Think of it as a bridge between the two*/
        GatewayDiscordClient client = Gateway.connect();
        //Initialize the Embed Class, which handles the embed messages
        EmbedCmd embedCmd = new EmbedCmd(client);
        //Initialize the InputAdapter class, which handles the user input
        InputAdapter adapter = new InputAdapter(client);

        /*Subscribe to the prompt that is observed through the Input Adapter.
        * This listens to incoming data from the user. We are LISTENING for an event (ie the input)
        */
        adapter.prompt().subscribe(
           /*Lambda gets executed when (think of onNext as "ON NEXT RECEIVED DATA,
            new data (ie, user input) is emitted.*/
           data -> onNext(data, embedCmd, client), //on receiving data
           error -> onError(error), //on error
           () -> onCompleted() //on completion
        );

        System.out.println("Connected");

        client.onDisconnect().block();
    }
    private static void onNext(MessageObject data, EmbedCmd cmd, GatewayDiscordClient client) {
        client.getChannelById(Snowflake.of(data.getChannel().getId().asLong()))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel -> channel.createMessage(cmd.generateEmbed(data)))
                .subscribe();

    }
    private static void onError(Throwable data) {
        System.out.println(data);
    }
    private static void onCompleted() {
        System.out.println("finished.");
    }
}
