package com.github.rsrulloda;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;


import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Bot login to Discord
        DiscordApi api = new DiscordApiBuilder()
            .setToken("MTA3NjQwNzUzMTU0NDUzMTA1Ng.G5tBEl.27q8Np_p1kpd9y1FQYnjdFB3aBE0TPrAP7mYkg")
            .addIntents(Intent.MESSAGE_CONTENT)
            .login().join();

        // Loads database
        DictionaryManager dictionary = new DictionaryManager();

        try { // Discord listener checks for messages
            api.addMessageCreateListener(event -> {
                String msg = event.getMessageContent().toLowerCase();
                System.out.println(event.getMessage().getCreationTimestamp() + " " + msg);

                // List of commands
                if (msg.startsWith("j")) {
                    String[] commandParts = msg.split("\\s+");
                    String mainCommand = commandParts[0];
                    switch (mainCommand) {
                        case "jhelp": // Help menu
                            if (commandParts.length == 1) {
                                new MessageBuilder()
                                    .setEmbed(new EmbedBuilder()
                                    .setTitle("Jisho Commands")
                                    .setDescription("Type `jhelp` followed by a command name to see more details about" +
                                                    " that particular command.\n\n `help`, `about`, `wotd`")
                                    .setColor(Color.GRAY))
                                    .send(event.getChannel());
                            } else {
                                String subCommand = commandParts[1];
                                switch (subCommand) {
                                    case "help": // Help help
                                        new MessageBuilder()
                                            .setEmbed(new EmbedBuilder()
                                            .setTitle("Command Info: `jhelp`")
                                            .setDescription("Usage: `jhelp` *`command`*\n\n View this list of commands. " +
                                                            "Specify a command for detailed information.\n\n Optional parameters are shown in italics")
                                            .setColor(Color.GRAY))
                                            .send(event.getChannel());
                                        break;
                                    case "about": // Help about
                                        new MessageBuilder()
                                                .setEmbed(new EmbedBuilder()
                                                        .setTitle("Command Info: `jabout`")
                                                        .setDescription("Usage: `jabout`\n\n Displays information about Jisho bot.")
                                                        .setColor(Color.GRAY))
                                                .send(event.getChannel());
                                        break;
                                    case "wotd": // Help word of the day
                                        new MessageBuilder()
                                            .setEmbed(new EmbedBuilder()
                                            .setTitle("Command Info: `jwotd`")
                                            .setDescription("Usage: `jwotd`\n\n Displays current word of the day. Resets midnight UTC.")
                                            .setColor(Color.GRAY))
                                            .send(event.getChannel());
                                        break;
                                    case "quiz":
                                        new MessageBuilder()
                                                .setEmbed(new EmbedBuilder()
                                                        .setTitle("Command Info: `jquiz`")
                                                        .setDescription("Usage: `jquiz`\n\n Take a quiz to test your knowledge! Keeps track of your scores.")
                                                        .setColor(Color.GRAY))
                                                .send(event.getChannel());
                                        break;
                                    default:
                                        System.out.println("Unknown subcommand. Type 'jhelp' for help.");
                                }
                            }
                            break;


                        case "jabout": // About Jisho
                            new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                .setTitle("About Jisho")
                                .setDescription("Jisho is a Japanese dictionary bot capable of translating " +
                                                "from English to Japanese and vice versa. It also provides " +
                                                "a Japanese word of the day.\n\n Bot created by dubootie#0442\n" +
                                                "Profile picture created by kalimooni#6675")
                                .setColor(Color.GRAY))
                                .send(event.getChannel());
                            break;
                        case "jwotd": // Sends current word of the day
                            Word wotd = dictionary.getWotd();

                            new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                .setTitle("Word of the Day")
                                .setDescription("Word: " + wotd.getJapanese() +
                                                "\n Furigana: " + wotd.getFurigana() +
                                                "\n English: " + wotd.getEnglish() +
                                                "\n Part of Speech: " + wotd.getPos())
                                .setColor(Color.GRAY))
                                .send(event.getChannel());


                            System.out.println("Message Sent");
                            break;
                        case "jquiz":
                            Word word = dictionary.getRandomWord();

                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Quiz")
                                    .setDescription("Word: " + word.getJapanese() +
                                            "\n Furigana: " + word.getFurigana() +
                                            "\n English: " + word.getEnglish() +
                                            "\n Part of Speech: " + word.getPos())
                                    .setColor(Color.GRAY);

                            new MessageBuilder()
                                    .setEmbed(embed)
                                    .send(event.getChannel())
                                    .thenAcceptAsync(message -> {
                                        message.addReaction("\uD83C\uDDE6"); // A
                                        message.addReaction("\uD83C\uDDE7"); // B
                                        message.addReaction("\uD83C\uDDE8"); // C
                                        message.addReaction("\uD83C\uDDE9"); // D
                                    });


                            System.out.println("Message Sent");
                            break;
                        case "jtest":
//                            RandomIntGenerator randomID = new RandomIntGenerator();
//                            System.out.println(randomID.getRandomUnusedInt());
                        break;
                        default:
                            System.out.println("Unknown command. Type 'jhelp' for help.");
                    }
                }
            });

            // Logs bot out of Discord
            Scanner scanner = new Scanner(System.in);
            if(scanner.next().equals("dc")) {
                api.disconnect();
                dictionary.closeConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}