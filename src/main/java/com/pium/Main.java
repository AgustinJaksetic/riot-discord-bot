package com.pium;

import com.pium.riot.RiotCommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String token = dotenv.get("token");

        JDA api = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new RiotCommandManager())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        OptionData regionOption = new OptionData(OptionType.STRING, "region", "Selecciona tu region", true)
                .addChoices(
                        new Command.Choice("Las", "americas|la2"),
                        new Command.Choice("Europa", "europe|euw1")
                );
        api.updateCommands().addCommands(

                    Commands.slash("profile", "Muestra tu perfil de LeagueOfLegends")
                            .addOption(OptionType.STRING, "nick", "Ingresa tu RiotUser", true)
                            .addOption(OptionType.STRING, "tag", "Ingresa tu tag sin el #", true)
                            .addOptions(regionOption)
        ).queue();

    }
}