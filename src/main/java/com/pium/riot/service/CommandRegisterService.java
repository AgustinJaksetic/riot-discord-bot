package com.pium.riot.service;

import net.dv8tion.jda.api.JDA;

public class CommandRegisterService {
    private final JDA api;

    public CommandRegisterService(JDA api) {
        this.api = api;
    }

    public void registerCommands() {
//        api.updateCommands().addCommands(
//                Commands.slash("profile", "Muestra tu perfil de League of Legends")
//                        .addOption(OptionType.STRING, "nick", "Ingresa tu RiotUser", true)
//                        .addOption(OptionType.STRING, "tag", "Ingresa tu tag sin el #", true)
//
//                        //TODO:esta pateando los comandos. cada que se reinicia.
//                        .addOptions(RegionConfig.getRegionOptions())
//        ).queue();
    }
}
