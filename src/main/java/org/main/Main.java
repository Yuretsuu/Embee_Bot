package org.main;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import org.main.command.EmbedCmd;
import org.main.command.GreetCmd;
import org.main.objects.MessageObject;

public class Main {
    static long guildId = 1096316870761648209L;
    public static void main(String[] args) throws InterruptedException {

        GatewayDiscordClient client = Gateway.connect();

        //if created command exists, don't execute. if it does, execute
        GreetCmd greetCmd = new GreetCmd(client);
        EmbedCmd embedCmd = new EmbedCmd(client);
        InputAdapter adapter = new InputAdapter(client);

        adapter.prompt().subscribe(
           data -> onNext(data, embedCmd, client),
           error -> onError(error),
           () -> onCompleted()
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
