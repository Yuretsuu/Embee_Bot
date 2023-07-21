package org.main.command;

import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.time.Instant;

public class EmbedBuilder {
    private final EmbedCreateSpec.Builder embedBuilder;

    public EmbedBuilder() {
        this.embedBuilder = EmbedCreateSpec.builder();
    }

    public EmbedBuilder setColor(Color color) {
        embedBuilder.color(color);
        return this;
    }

    public EmbedBuilder setTitle(String title) {
        embedBuilder.title(title);
        return this;
    }

    public EmbedBuilder setUrl(String url) {
        embedBuilder.url(url);
        return this;
    }

    public EmbedBuilder setAuthor(String name, String url, String iconUrl) {
        embedBuilder.author(name, url, iconUrl);
        return this;
    }

    public EmbedBuilder setDescription(String description) {
        embedBuilder.description(description);
        return this;
    }

    public EmbedBuilder setThumbnail(String thumbnailUrl) {
        embedBuilder.thumbnail(thumbnailUrl);
        return this;
    }

    public EmbedBuilder addField(String name, String value, boolean inline) {
        embedBuilder.addField(name, value, inline);
        return this;
    }

    public EmbedBuilder setImage(String imageUrl) {
        embedBuilder.image(imageUrl);
        return this;
    }

    public EmbedBuilder setTimestamp(Instant timestamp) {
        embedBuilder.timestamp(timestamp);
        return this;
    }

    public EmbedBuilder setFooter(String text, String iconUrl) {
        embedBuilder.footer(text, iconUrl);
        return this;
    }

    public EmbedCreateSpec build() {
        return embedBuilder.build();
    }
}
