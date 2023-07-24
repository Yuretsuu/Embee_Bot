package org.main.command;

import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.time.Instant;

public class EmbedFormatter {
    public static EmbedCreateSpec format(String text) {
        EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder();

        String[] lines = text.split("\n");

        for (String line : lines) {
            String[] parts = line.split(" ", 2);
            if (parts.length < 2) {
                continue;
            }

            String tag = parts[0].toLowerCase();
            String value = parts[1].trim();

            switch (tag) {
                case "<title>":
                    embedBuilder.title(value);
                    break;
                case "<description>":
                    embedBuilder.description(value);
                    break;
                case "<image>":
                    embedBuilder.image(value);
                    break;
                // Add more cases for other tags like <author>, <field>, <footer>, etc.
                // Handle them accordingly based on your desired format
                default:
                    break;
            }
        }

        embedBuilder.color(Color.PINK);
        embedBuilder.timestamp(Instant.now());

        return embedBuilder.build();
    }
}
