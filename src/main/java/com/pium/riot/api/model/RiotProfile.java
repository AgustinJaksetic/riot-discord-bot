package com.pium.riot.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RiotProfile {

    String riotUser;
    String puuid;
    String region;
    String server;

    RiotProfile(String riotUser, String puuid, String region, String server) {
        this.riotUser = riotUser;
        this.puuid = puuid;
        this.region = region;
        this.server = server;
    }

}
