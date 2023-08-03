package com.github.rsrulloda;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) throws IOException {
        // Bot login
        DiscordApi api = new DiscordApiBuilder()
            .setToken("MTA3NjQwNzUzMTU0NDUzMTA1Ng.GlLsMa.pmMd-PPkmsq1Oqy4I0qMd45XgxM2hed45rmeCM")
            .addIntents(Intent.MESSAGE_CONTENT)
            .login().join();

        // Listens for a message from discord
        api.addMessageCreateListener(event -> {
            String msg = event.getMessageContent().toLowerCase();
            System.out.println(event.getMessage().getCreationTimestamp() + " " + msg);

        // List of Commands
        switch(msg) {
            case "jhelp": // Help menu
                new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                        .setTitle("Jisho Commands")
                        .setDescription("Type `jhelp` followed by a command name to see more details about" +
                                        " that particular command.\n\n `about`, `help`, `wotd`")
                        .setColor(Color.GRAY))
                        .send(event.getChannel());
                    break;

            case "jhelp about": // Help about
                new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                        .setTitle("Command Info: `jabout`")
                        .setDescription("Usage: `jabout`\n\n Displays information about Jisho bot.")
                        .setColor(Color.GRAY))
                        .send(event.getChannel());
                        break;

            case "jhelp help": // Help help
                new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                        .setTitle("Command Info: `jhelp`")
                        .setDescription("Usage: `jhelp` *`command`*\n\n View this list of commands. " +
                                        "Specify a command for detailed information.\n\n Optional parameters are shown in italics")
                        .setColor(Color.GRAY))
                        .send(event.getChannel());
                    break;

            case "jhelp wotd": // Help word of the day
                new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                        .setTitle("Command Info: `jwotd`")
                        .setDescription("Usage: `jwotd`\n\n Displays current word of the day. Resets midnight UTC.")
                        .setColor(Color.GRAY))
                        .send(event.getChannel());
                    break;

            case "jabout":
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

            case "jwotd": // Word of the Day
                SQLiteManager database = new SQLiteManager();
                String s3 = database.readQuery2("SELECT * FROM Dictionary");
                event.getChannel().sendMessage(s3);
                database.closeConnection();

                break;
        }
});
        Scanner scanner = new Scanner(System.in);
        if(scanner.next().equals("dc")) {
        api.disconnect();
        }
    }
}