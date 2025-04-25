package com.pium.riot.commandservice.commands.profilecommand;


import com.pium.riot.api.apiconfig.ApiRiot;
import com.pium.riot.api.model.LolProfile;
import com.pium.riot.commandservice.commands.utils.EmbedConfigBuilder;
import com.pium.riot.commandservice.commands.utils.EmbedColor;
import com.pium.riot.commandservice.commands.utils.Images;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LolProfileService {
    public List<MessageEmbed> embeds = new ArrayList<>();
    private final ApiRiot apiRiot;
    private String queue;

    public LolProfileService(ApiRiot ap){
        apiRiot = ap;
    }

    public void profilesBuilder() throws IOException {

        for (int i = 0; i <= 1; i++) {
            LolProfile profile = apiRiot.getLolProfile(i);
            if (profile != null) {
                queue = profile.getQueueType().equals("RANKED_SOLO_5x5") ?
                        "Ranked Solo Q" : "Ranked Flex";
                embeds.add(embedBuilder(profile));
            }else{
                embeds.add(defaultBuildEmbed());
            }
        }
    }

    private MessageEmbed embedBuilder(LolProfile Profile) {
        String tier = Profile.getTier();

        return EmbedConfigBuilder.builder().
                authorName(queue).
                title(tier + " " + Profile.getRank()).
                description("Lp: " + Profile.getLeaguePoints()).
                field("Games: ").
                inField("Wins: " + Profile.getWins() + "\n" + "Losses: " + Profile.getLosses()).
                secondFiel("Winrate: ").
                secondInField(winrateCalculated(Profile.getWins(), Profile.getLosses())).
                footer(Profile.getRiotUser()).
                color(EmbedColor.getApiValue(tier)).
                thumbnailUrlImage(Images.getApiValue(tier)).
                build().
                buildEmbed();
    }

    public MessageEmbed defaultBuildEmbed(){
        String a = queue == null ? "Server Wrong" :
                queue.equals("Ranked Solo Q") ? "Ranked Flex" : "Ranked Solo Q";

        return EmbedConfigBuilder.builder()
                .authorName(a)
                .title("Unranked")
                .color(Color.BLACK)
                .build()
                .buildEmbed();
    }

    public static String winrateCalculated(int wins, int losses) {
        int total = wins + losses;
        if (total == 0) return "0%";
        int winrate = (int) Math.round(((double) wins / total) * 100);
        return winrate + "%";
    }

    public static boolean isErrorEmbed(MessageEmbed embed) {
        return "Server Wrong".equals(
                embed.getAuthor() != null ? embed.getAuthor().getName() : null
        );
    }

}
