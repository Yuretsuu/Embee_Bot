package org.main;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

public class Gateway {
    static GatewayDiscordClient client;

    public static GatewayDiscordClient connect(){
        client = DiscordClientBuilder.create
                        ("MTA5MjY3ODE2MzE3NzQyMjg0OA.GT_PO9.0pJLNIXAH97JPevIO-c0Cf8t55ee4brPKBuNd8")
                .build()
                .login()
                .block();
        return client;
    }

}
