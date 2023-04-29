//package org.main;
//
//import discord4j.core.DiscordClient;
//import discord4j.core.DiscordClientBuilder;
//import discord4j.core.GatewayDiscordClient;
//import discord4j.core.event.domain.lifecycle.ReadyEvent;
//import discord4j.core.event.domain.message.MessageCreateEvent;
//import discord4j.core.object.entity.Message;
//import discord4j.core.object.entity.User;
//import reactor.core.publisher.Mono;
//
//public class BotHelper {
//    //Member variables
//    private Message message;
//    //Pass discord bot token
////    DiscordClient client = DiscordClient.create(token);
//    GatewayDiscordClient client = DiscordClientBuilder.create("MTA5MjY3ODE2MzE3NzQyMjg0OA.GT_PO9.0pJLNIXAH97JPevIO-c0Cf8t55ee4brPKBuNd8")
//            .build().login().block();
//
//    //constructor
//    public BotHelper(){
//    }
//
//    public void connect(){
//        client.getEventDispatcher().on(ReadyEvent.class)
//                .subscribe(event -> {
//                    User self = event.getSelf();
//                    System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
//                });
//    }
//
//    public Mono<Void> handleMessages() {
//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .map(MessageCreateEvent::getMessage)
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
//                .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
//                .flatMap(Message::getChannel)
//                .flatMap(channel -> channel.createMessage("Pong!"))
//                .subscribe();
//
//        client.onDisconnect().block();
//
//        return Mono.empty();
//    }
//}
