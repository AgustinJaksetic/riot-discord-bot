package com.pium.riot.botservice;

import com.pium.riot.botservice.config.RegionConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class CommandRegisterService {
    private final JDA api;

    public CommandRegisterService(JDA api) {
        this.api = api;
    }

    public void registerCommands() {
//       api.updateCommands().addCommands(
//                Commands.slash("profile", "Muestra tu perfil de League of Legends")
//                        .addOption(OptionType.STRING, "nick", "Ingresa tu RiotUser", true)
//                        .addOption(OptionType.STRING, "tag", "Ingresa tu tag sin el #", true)
//
//                        //TODO:esta pateando los comandos. cada que se reinicia.
//                        .addOptions(RegionConfig.getRegionOptions())
//        ).queue();
    }
}
