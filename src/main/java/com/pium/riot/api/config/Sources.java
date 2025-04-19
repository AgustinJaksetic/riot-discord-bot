package com.pium.riot.api.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sources {
    USER_URL(".api.riotgames.com/riot/account/v1/accounts/by-riot-id/"),
    PUUID_URL(".api.riotgames.com/lol/league/v4/entries/by-puuid/");

    private final String link;
}
