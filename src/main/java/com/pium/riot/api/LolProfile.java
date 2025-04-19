package com.pium.riot.api;

public class LolProfile {
    String riotuser, rank, tier, queuetype;
    int leaguepoints, wins, losses;

    public LolProfile(String riotuser,String rank,String tier,int leaguepoints,int wins,int losses, String queuetype){
        this.riotuser = riotuser;
        this.rank = rank;
        this.tier = tier;
        this.leaguepoints = leaguepoints;
        this.wins = wins;
        this.losses = losses;
        this.queuetype = queuetype.equals("RANKED_SOLO_5x5") ? "Ranked Solo" : "Ranked Flex";
    }

    public String getQueuetype() {
        return queuetype;
    }

    public String getRiotuser() {
        return riotuser;
    }

    public String getRank() {
        return rank;
    }

    public String getTier() {
        return tier;
    }

    public int getLeaguePoints() {
        return leaguepoints;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}