package com.github.rsrulloda;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String token = "MTA3NjQwNzUzMTU0NDUzMTA1Ng.GlLsMa.pmMd-PPkmsq1Oqy4I0qMd45XgxM2hed45rmeCM";

        // Bot login
        DiscordApi api = new DiscordApiBuilder()
            .setToken(token)
            .addIntents(Intent.MESSAGE_CONTENT)
            .login().join();

        // Listener
            api.addMessageCreateListener(event -> {
                if (event.getMessageContent().equalsIgnoreCase("ron")) {
                    event.getChannel().sendMessage("Ron is a genius");
                } else {
                    System.out.println("Incorrect message");
                }
            });

        Scanner scanner = new Scanner(System.in);
        if(scanner.next().equals("dc")) {
            api.disconnect();
        }
    }
}