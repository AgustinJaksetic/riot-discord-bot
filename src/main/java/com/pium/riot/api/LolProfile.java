package com.pium.riot.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LolProfile {
    private String riotUser;
    private String rank;
    private String tier;
    private String queueType;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;

    public LolProfile(String riotUser, String rank, String tier, Integer leaguePoints,
                      Integer wins, Integer losses, String queueType){
        this.riotUser = riotUser;
        this.rank = rank;
        this.tier = tier;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.queueType = queueType
                .equals("RANKED_SOLO_5x5") ? "Ranked Solo Q" : "Ranked Flex";
    }

    public String showUserStats() {
        return String.format(
                "Ranked: %s\nRiotUser: %s\nElo: %s %s\nLP: %d\nWins: %d\nLosses: %d",
                getQueueType(),
                getRiotUser(),
                getTier(),
                getRank(),
                getLeaguePoints(),
                getWins(),
                getLosses()
        );
    }
}