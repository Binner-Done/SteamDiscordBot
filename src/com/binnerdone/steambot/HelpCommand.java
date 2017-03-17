package com.binnerdone.steambot;

import com.walshydev.jba.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

/**
 * Created by Jack on 17/03/2017.
 */
public class HelpCommand implements Command {
    @Override
    public void onCommand(User user, TextChannel textChannel, Message message, String[] strings, Member member) {
        textChannel.sendMessage(new EmbedBuilder()
                .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                .setDescription("Steam Bot Commands \n `@search title {title}` - Searches for steam games by name \n `@search id {id}` - Searches for steam games by ID")
                .setColor(Color.GREEN)
                .build())
                .queue();
        message.delete().queue();
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Help";
    }
}
