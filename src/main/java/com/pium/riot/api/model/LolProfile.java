package com.pium.riot.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LolProfile{
    private String riotUser;
    private String rank;
    private String tier;
    private String queueType;
    private String region;
    private String server;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;

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