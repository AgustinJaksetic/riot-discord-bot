package com.pium.riot.commands.profilecommand;


import com.pium.riot.api.ApiRiot;
import com.pium.riot.api.LolProfile;
import com.pium.riot.commands.utils.EmbedConfigBuilder;
import com.pium.riot.commands.utils.EmbedColor;
import com.pium.riot.commands.utils.Images;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {
    public List<MessageEmbed> embeds = new ArrayList<>();
    private final ApiRiot apiRiot;
    private String queue;

    public ProfileService(ApiRiot ap){
        apiRiot = ap;
    }

    public void profilesBuilder() throws IOException {

        for (int i = 0; i <= 1; i++) {
            LolProfile profile = apiRiot.getDatos(i);
            if (profile != null) {
                embeds.add(embedBuilder(profile));
                queue = profile.getQueueType();
            }else{
                embeds.add(defaultBuildEmbed());
            }
        }
    }

    private MessageEmbed embedBuilder(LolProfile Profile) {
        String tier = Profile.getTier();

        return EmbedConfigBuilder.builder().authorName(Profile.getQueueType()).
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
        return EmbedConfigBuilder.builder()
                .authorName((queue.equals("Ranked Solo Q") ? "Ranked Flex" : "Ranked Solo Q")).
                title("Unranked").
                build().
                buildEmbed();
    }

    public static String winrateCalculated(int wins, int losses) {
        int total = wins + losses;
        if (total == 0) return "0%";
        int winrate = (int) Math.round(((double) wins / total) * 100);
        return winrate + "%";
    }
}
