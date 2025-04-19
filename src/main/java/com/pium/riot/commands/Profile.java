package com.pium.riot.commands;

import com.pium.riot.api.ApiRiot;
import com.pium.riot.api.LolProfile;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;

public class Profile implements RiotBotCommand {
    private ApiRiot apiRiot;
    private static final String[] REGIONS = {"br", "na", "eune", "eu", "jp", "kr", "lan", "las", "oce", "tr"};
    private static final String[] TAGS = {"br", "na", "eune", "eu", "jp", "kr", "lan", "las", "oce", "tr"};

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {
        String summonerName = getOptionValue(event, "nick");
        String tag = getOptionValue(event, "tag");
        String region = getOptionValue(event, "region");

        if (summonerName == null || tag == null || region == null) {
            event.reply("Faltan argumentos. ").setEphemeral(true).queue();
            return;
        }

        try {
            apiRiot = new ApiRiot(summonerName, tag, region);
        } catch (IOException e) {
            event.reply("No existe la cuenta").setEphemeral(true).queue();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            LolProfile profile = apiRiot.getDatos(i);
            if (profile != null) {
                event.reply(profile.showUserStats()).queue();
                return;
            }
        }

        event.reply("Unranked").queue();
    }

    private String getOptionValue(SlashCommandInteractionEvent event, String optionName) {
        return event.getOption(optionName) != null ? event.getOption(optionName).getAsString() : null;
    }

}