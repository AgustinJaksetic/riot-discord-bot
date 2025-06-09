package com.pium.riot.api.config;

public class UrlBuilder {

    public static String buildAccountUrl(String name, String tag, String region, String apiKey) {
        name = name.replaceAll(" ", "%20");


        return String.format("https://%s.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s?api_key=%s",
                region, name, tag, apiKey);
    }

    public static String buildLolUrl(String puuid, String server, String apiKey) {
        return String.format("https://%s.api.riotgames.com/lol/league/v4/entries/by-puuid/%s?api_key=%s",
                server, puuid, apiKey);
    }
}

