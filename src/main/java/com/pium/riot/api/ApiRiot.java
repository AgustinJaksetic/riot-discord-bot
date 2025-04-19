package com.pium.riot.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ApiRiot {
    private final String apiKey;
    private String riotuserurl;
    private String riotuser;
    private Dotenv dotenv = Dotenv.load();
    private String puuidurl;
    private String routingregion;
    private String platformregion;

    public ApiRiot(String nick, String tag, String region) throws IOException {
        this.apiKey = dotenv.get("riot_api_key");
        this.riotuser = nick + "#" + tag.replace("#", "");
        String tagencoded = tag.replace(" ", "%20").replace("#", "");
        String nickencoded = nick.replace(" ", "%20");
        String[] regiones = region.split("\\|");
        this.routingregion = regiones[0];
        this.platformregion = regiones[1];
        this.riotuserurl = "https://" + routingregion + ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + nickencoded + "/" + tagencoded + "?api_key=" + apiKey;
        getPuuid();
    }

    private void getPuuid() throws IOException {

        URL url = URI.create(riotuserurl).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        if(connection.getResponseCode() != 200) {
            throw new IOException("Error: " + connection.getResponseCode());
        }
        connection.connect();

        StringBuilder response = new StringBuilder();
        Scanner sc = new Scanner(connection.getInputStream());
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();
        JSONObject datos = new JSONObject(response.toString());
        String puuid = datos.getString("puuid");

        this.puuidurl = "https://" + platformregion + ".api.riotgames.com/lol/league/v4/entries/by-puuid/"
                + puuid + "?api_key=" + apiKey;
    }

    public LolProfile getDatos(int cola) throws IOException {
        URL url = URI.create(puuidurl).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        StringBuilder response = new StringBuilder();
        Scanner sc = new Scanner(connection.getInputStream());
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();

        JSONArray datos = new JSONArray(response.toString());

        if (cola >= datos.length()) {
            return null;
        }
        JSONObject rank = datos.getJSONObject(cola);
        String queuetype = rank.getString("queueType");
        String tier = rank.getString("tier");
        String elo = rank.getString("rank");
        int leaguePoints = rank.getInt("leaguePoints");
        int wins = rank.getInt("wins");
        int losses = rank.getInt("losses");

        return new LolProfile(riotuser, elo, tier, leaguePoints, wins, losses, queuetype);
    }
}