package org.main.command;

import discord4j.core.spec.EmbedCreateSpec;

public class EmbedFormatter {
    public static EmbedCreateSpec format(String text) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
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
                    embedBuilder.setTitle(value);
                    break;
                case "<description>":
                    embedBuilder.setDescription(value);
                    break;
                case "<image>":
                    embedBuilder.setImage(value);
                    break;
                // Add more cases for other tags like <author>, <field>, <footer>, etc.
                // Handle them accordingly based on your desired format
                default:
                    break;
            }
        }

        return embedBuilder.build();
    }
}
