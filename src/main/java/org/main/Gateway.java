package org.main;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

//This class is used to connect to the Discord gateway
public class Gateway {
    public static GatewayDiscordClient connect(){
        GatewayDiscordClient client = DiscordClientBuilder.create
                        ("MTA5MjY3ODE2MzE3NzQyMjg0OA.G6M3T6.QQI2tE1zoyG7QcC3uWOdCbjnUMd9UDFMSrMGsk")
                .build()
                .login()
                .block();
        return client;
    }

}
