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
        switch () {
            case "!greet":
                greetCmd.execute();
                break;
            case "!embed":
                embedCmd.execute();
                break;
        }


        System.out.println("Connected");

        client.onDisconnect().block();
    }
}
