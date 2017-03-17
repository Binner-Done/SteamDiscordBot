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

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Jack on 17/03/2017.
 */
public class SearchCommand implements Command {
    @Override
    public void onCommand(User user, TextChannel textChannel, Message message, String[] args, Member member) {
        SteamApi steamApi = new SteamApi("US");
        if(args[0].equals("title")){
        final String searcharg = new String(args[1]);
        if(searcharg == ""){
            textChannel.sendMessage(new EmbedBuilder()
                    .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                    .setDescription("Usage: `+search GameName` \n Make sure there aren't any spaces!")
                    .setColor(Color.RED)
                    .build())
                    .queue();
        }
            try {
            String avalable = null;
            SteamApp search = steamApi.retrieve(searcharg);
            if(search.isAvailableForLinux()){
                avalable = "Linux :white_check_mark: ";
            } else if (!search.isAvailableForLinux()){
                avalable = "Linux :x: ";
            }
            if(search.isAvailableForMac()){
                avalable = avalable + "Mac :white_check_mark: ";
            } else if (!search.isAvailableForMac()){
                avalable = avalable + "Mac :x: ";
            }
            if(search.isAvailableForWindows()){
                avalable = avalable + "Windows :white_check_mark: ";
            } else if (!search.isAvailableForWindows()){
                avalable = avalable + "Windows :x:";
            }
            String description = search.getAboutTheGame();
            description.replaceAll("<p>", "");
            description.replaceAll("</p>", "");
            description.replaceAll("<h1>", "");
            description.replaceAll("</h1>", "");
            description.replaceAll("<h3>", "");
            description.replaceAll("</h3>", "");
            description.replaceAll("<h2>", "");
            description.replaceAll("</h2>", "");
            description.replaceAll("<h4>", "");
            description.replaceAll("</h4>", "");
            description.replaceAll("<br />", "");
            description.replaceAll(".<p>", "");
            description.replaceAll(".</p>", "");
            description.replaceAll(".<h1>", "");
            description.replaceAll(".</h1>", "");
            description.replaceAll(".<h2>", "");
            description.replaceAll(".</h2>", "");
            description.replaceAll(".<h3>", "");
            description.replaceAll(".</h3>", "");
            description.replaceAll(".<h4>", "");
            description.replaceAll(".</h4>", "");
            description.replaceAll(".<br />", "");
            textChannel.sendMessage(new EmbedBuilder()
                    .setAuthor("Steam Search", null, user.getEffectiveAvatarUrl())
                    .setDescription("Found The Game: " + search.getName() + "\n Here is some information: \n App ID: " + search.getAppId() + "\n Cost: " + search.getPriceCurrency() + " " + search.getPrice() + "\n About the Game: " + search.getDetailedDescription() + "\n Availability: " + avalable + "\n Developers: " + search.getDevelopers() + "\n Discount Ammount: " + search.getPriceDiscountPercentage())
                    .addField("Game Link", "[`Click Here`](" + search.getWebsite() + ")", true)
                    .setColor(Color.GREEN)
                    .build())
                    .queue();
        } catch (SteamApiException e) {
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to Binner_Done!")
                        .setColor(Color.RED)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("Error! Here is the stacktrace: \n" + exceptionAsString)
                        .setColor(Color.RED)
                        .build())
                        .queue();
            } catch(Exception e){
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to Binner_Done!")
                        .setColor(Color.RED)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("Error! Here is the stacktrace: \n" + exceptionAsString)
                        .setColor(Color.RED)
                        .build())
                        .queue();
        }
        } else if(args[0].equals("id")){
            final String searcharg = new String(args[1]);
            final int searchint = Integer.parseInt(searcharg);
            if(searcharg == ""){
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("Usage: `+search GameName` \n Make sure there aren't any spaces!")
                        .setColor(Color.RED)
                        .build())
                        .queue();
            }
            try {
                String avalable = null;
                SteamApp search = steamApi.retrieve(searchint);
                if(search.isAvailableForLinux()){
                    avalable = "Linux :white_check_mark: ";
                } else if (!search.isAvailableForLinux()){
                    avalable = "Linux :x: ";
                }
                if(search.isAvailableForMac()){
                    avalable = avalable + "Mac :white_check_mark: ";
                } else if (!search.isAvailableForMac()){
                    avalable = avalable + "Mac :x: ";
                }
                if(search.isAvailableForWindows()){
                    avalable = avalable + "Windows :white_check_mark: ";
                } else if (!search.isAvailableForWindows()){
                    avalable = avalable + "Windows :x:";
                }
                String description = search.getAboutTheGame();
                description.replaceAll("<p>", "");
                description.replaceAll("</p>", "");
                description.replaceAll("<h1>", "");
                description.replaceAll("</h1>", "");
                description.replaceAll("<h3>", "");
                description.replaceAll("</h3>", "");
                description.replaceAll("<h2>", "");
                description.replaceAll("</h2>", "");
                description.replaceAll("<h4>", "");
                description.replaceAll("</h4>", "");
                description.replaceAll("<br />", "");
                description.replaceAll(".<p>", "");
                description.replaceAll(".</p>", "");
                description.replaceAll(".<h1>", "");
                description.replaceAll(".</h1>", "");
                description.replaceAll(".<h2>", "");
                description.replaceAll(".</h2>", "");
                description.replaceAll(".<h3>", "");
                description.replaceAll(".</h3>", "");
                description.replaceAll(".<h4>", "");
                description.replaceAll(".</h4>", "");
                description.replaceAll(".<br />", "");
                System.out.println("Debug");
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Steam Search", null, user.getEffectiveAvatarUrl())
                        .setDescription("Found The Game: " + search.getName() + "\n Here is some information: \n App ID: " + search.getAppId() + "\n Cost: " + search.getPriceCurrency() + " " + search.getPrice() + "\n About the Game: " + search.getDetailedDescription() + "\n Availability: " + avalable + "\n Developers: " + search.getDevelopers() + "\n Discount Ammount: " + search.getPriceDiscountPercentage())
                        .addField("Game Link", "[`Click Here`](" + search.getWebsite() + ")", true)
                        .setColor(Color.GREEN)
                        .build())
                        .queue();
            } catch (SteamApiException e) {
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to Binner_Done!")
                        .setColor(Color.RED)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("Error! Here is the stacktrace: \n" + exceptionAsString)
                        .setColor(Color.RED)
                        .build())
                        .queue();
            } catch(Exception e){
                e.printStackTrace();
                textChannel.sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("I have sent the error to Binner_Done!")
                        .setColor(Color.RED)
                        .build())
                        .queue();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                message.getJDA().getGuildById("283310549499117568").getTextChannelById("292395271655129088").sendMessage(new EmbedBuilder()
                        .setAuthor("Error", null, user.getEffectiveAvatarUrl())
                        .setDescription("Error! Here is the stacktrace: \n" + exceptionAsString)
                        .setColor(Color.RED)
                        .build())
                        .queue();
            }
        }
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


