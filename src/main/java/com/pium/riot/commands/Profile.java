package com.pium.riot.commands;

import com.pium.riot.api.ApiRiot;
import com.pium.riot.api.LolProfile;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;

public class Profile implements RiotBotCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {

        String summonerName = event.getOption("nick").getAsString();
        String tag = event.getOption("tag").getAsString();
        String region = event.getOption("region").getAsString();
        ApiRiot apiRiot;

        try {
            apiRiot = new ApiRiot(summonerName, tag, region);
        } catch (IOException e) {
            event.reply("No existe la cuenta").setEphemeral(true).queue();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            LolProfile lolprofile = apiRiot.getDatos(i);
            if (lolprofile != null) {
                event.reply("Ranked: " + lolprofile.getQueuetype() + "\n" +
                        "RiotUser: " + lolprofile.getRiotuser() + "\n" +
                        "elo: " + lolprofile.getTier() + " " + lolprofile.getRank() + "\n" +
                        "LP: " + lolprofile.getLeaguePoints() + "\n" +
                        "Wins: " + lolprofile.getWins() + "\n" +
                        "Losses: " + lolprofile.getLosses()).queue();
            } else if (i == 0) {
                event.reply("Unranked").queue();
            }
        }
    }
}