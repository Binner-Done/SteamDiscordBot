package com.binnerdone.steambot;

import com.walshydev.jba.JBA;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Jack on 12/03/2017.
 */
public class SteamBot extends JBA {
    public static void main(String[] args){
        new SteamBot().init(new JDABuilder(AccountType.BOT)
                .setToken(Secret.secret)
                .setGame(Game.of("@help | Steam Bot"))
                , "@");
    }

    @Override
    public void run() {
       registerCommand(new SearchCommand());
       LOGGER.info("Started");
    }
    public static String getMessage(String[] args, int min) {
        return Arrays.stream(args).skip(min).collect(Collectors.joining(" ")).trim();
    }

    public static String getMessage(String[] args, int min, int max) {
        String message = "";
        for(int index = min; index < max; index++){
            message += args[index] + " ";
        }
        return message.trim();
    }
}
