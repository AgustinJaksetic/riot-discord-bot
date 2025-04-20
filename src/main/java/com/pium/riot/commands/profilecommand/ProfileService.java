package com.pium.riot.commands.profilecommand;


import com.pium.riot.api.ApiRiot;
import com.pium.riot.api.LolProfile;
import com.pium.riot.commands.profilecommand.utils.embed;
import net.dv8tion.jda.api.entities.MessageEmbed;


import java.awt.*;
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
        return embed.generateEmbed(
                Profile.getQueueType(),
                null,
                null,
                Profile.getTier()+ " " + Profile.getRank(),
                "Lp: " + Profile.getLeaguePoints(),
                "Partidas: ",
                "Wins: " + Profile.getWins() + "\n" + "Losses: " + Profile.getLosses(),
                Profile.getRiotUser(),
                (Profile.getQueueType().equals("Ranked Solo Q") ? Color.BLUE : Color.MAGENTA),
                null,
                null
        );

    }

    public MessageEmbed defaultBuildEmbed(){
        return embed.generateEmbed(
                (queue.equals("Ranked Solo Q") ? "Ranked Flex" : "Ranked Solo Q"),
                null,
                null,
                "Unranked",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
