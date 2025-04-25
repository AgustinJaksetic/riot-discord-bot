package com.pium.riot.botservice.config.utils;

import lombok.Getter;


import java.util.Map;
import java.util.Set;

@Getter
public final class Region {

    private Region() {
    }

    public static final String LAS = "LAS";
    public static final String LAN = "LAN";
    public static final String BR = "BR";
    public static final String NA = "NA";
    public static final String EUW = "EUW";
    public static final String KR = "KR";
    public static final String JP = "JP";
    public static final String SEA = "SEA";
    public static final String EUROPE = "EUROPE";
    public static final String ASIA = "ASIA";
    public static final String OCEANIA = "OCEANIA";

    private static final Map<String, String> REGION_API_VALUES;
    static {

        REGION_API_VALUES = Map.of(
                LAS, "americas:la2",
                LAN, "americas:la1",
                BR, "americas:br1",
                NA, "americas:na1",
                EUW, "europe:euw1",
                KR, "asia:kr",
                JP, "asia:jp1",
                SEA, "sea:ph2");
    }

    public static String getApiValue(String regionName) {
        return REGION_API_VALUES.get(regionName.toUpperCase());
    }

    public static boolean isValid(String regionName) {
        return REGION_API_VALUES.containsKey(regionName.toUpperCase());
    }

    public static Set<String> getAvailableRegions() {
        return REGION_API_VALUES.keySet();
    }

    public static Map<String, String> getRegionMap() {
        return REGION_API_VALUES;
    }
}
