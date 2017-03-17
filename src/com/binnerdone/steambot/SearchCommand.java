package com.binnerdone.steambot;

import com.github.goive.steamapi.SteamApi;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;
import com.walshydev.jba.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import com.binnerdone.steambot.SteamBot;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jack on 17/03/2017.
 */
public class SearchCommand implements Command {
    @Override
    public void onCommand(User user, TextChannel textChannel, Message message, String[] args, Member member) {
        SteamApi steamApi = new SteamApi("US");

        if(args[0].equals("title")){

            final String searcharg = new String(SteamBot.getMessage(args, 1));

            if(searcharg == ""){

                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                        .setDescription("Usage: `+search GameName` \nMake sure there aren't any spaces!")
                        .setColor(Color.red)
                        .build())
                        .queue();

            }

            try {

                String available = null;

                SteamApp search = steamApi.retrieve(searcharg);

                if(search.isAvailableForLinux()){

                    available = "Linux :white_check_mark: ";

                } else if (!search.isAvailableForLinux()){

                    available = "Linux :x: ";

                } else if(search.isAvailableForMac()){

                    available = available + "Mac :white_check_mark: ";

                } else if (!search.isAvailableForMac()){

                    available = available + "Mac :x: ";

                } else if(search.isAvailableForWindows()){

                    available = available + "Windows :white_check_mark: ";

                } else if (!search.isAvailableForWindows()){

                    available = available + "Windows :x:";

                }

                String description = search.getAboutTheGame();
                description = description.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "");
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Steam Search", null, user.getEffectiveAvatarUrl())
                        .setDescription("Game: " + search.getName() + "\n**__Info__**\nApp ID: " + search.getAppId() + "\nCost: " + search.getPriceCurrency() + " " + search.getPrice() + "\nAbout the Game: " + search.getDetailedDescription() + "\nAvailability: " + available + "\nDevelopers: " + search.getDevelopers() + "\nDiscount Amount: " + search.getPriceDiscountPercentage())
                        .addField("Game Link", "[`Click Here`](" + search.getWebsite() + ")", true)
                        .setColor(Color.green)
                        .build())
                        .queue();
            } catch (SteamApiException e) {
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to the debug chat!")
                        .setColor(Color.red)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                        .setDescription("An exception has occurred!: \n" + exceptionAsString)
                        .setColor(Color.red)
                        .build())
                        .queue();
            }

        } else if(args[0].equals("id")){
            final String searcharg = new String(args[1]);
            final int searchint = Integer.parseInt(searcharg);
            if(searcharg == ""){
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                        .setDescription("Usage: `+search GameName` \nMake sure there aren't any spaces!")
                        .setColor(Color.red)
                        .build())
                        .queue();
            }
            try {
                String available = null;
                SteamApp search = steamApi.retrieve(searchint);
                if(search.isAvailableForLinux()){
                    available = "Linux :white_check_mark: ";
                } else if (!search.isAvailableForLinux()){
                    available = "Linux :x: ";
                }  if(search.isAvailableForMac()){
                    available = available + "Mac :white_check_mark: ";
                } else if (!search.isAvailableForMac()){
                    available = available + "Mac :x: ";
                } if(search.isAvailableForWindows()){
                    available = available + "Windows :white_check_mark: ";
                } else if (!search.isAvailableForWindows()){
                    available = available + "Windows :x:";
                }
                String description = search.getAboutTheGame();
                description = description.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "");

                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Steam Search", null, user.getEffectiveAvatarUrl())
                        .setDescription("Found The Game: " + search.getName() + "\n**__Info__** \nApp ID: " + search.getAppId() + "\nCost: " + search.getPriceCurrency() + " " + search.getPrice() + "\nAbout the Game: " + search.getDetailedDescription() + "\nAvailability: " + available + "\nDevelopers: " + search.getDevelopers() + "\nDiscount Amount: " + search.getPriceDiscountPercentage())
                        .addField("Game Link", "[`Click Here`](" + search.getWebsite() + ")", true)
                        .setColor(Color.green)
                        .build())
                        .queue();
            } catch (SteamApiException e) {
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to the debug chat!")
                        .setColor(Color.red)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("An exception occurred!: \n" + exceptionAsString)
                        .setColor(Color.red)
                        .build())
                        .queue();
            }
        }
        message.delete().queue();
    }


    @Override
    public String getCommand() {
        return "search";
    }

    @Override
    public String getDescription() {
        return "Searches Steam";
    }
}