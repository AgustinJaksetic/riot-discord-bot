package com.pium.riot.commands.profilecommand.utils;

import java.awt.*;
import java.util.Map;
import java.util.Set;

public class EmbedColor {
    public static final String IRON= "IRON";
    public static final String BRONZE = "BRONZE";
    public static final String SILVER = "SILVER";
    public static final String GOLD = "GOLD";
    public static final String PLATINUM = "PLATINUM";
    public static final String EMERALD = "EMERALD";
    public static final String DIAMOND = "DIAMOND";
    public static final String MASTER = "MASTER";
    public static final String GRANDMASTER = "GRANDMASTER";
    public static final String CHALLENGER = "CHALLENGER";

    private static final Map<String, Color> COLOR_API_VALUES;
    static {

        COLOR_API_VALUES = Map.of(
                IRON, Color.DARK_GRAY,
                BRONZE, new Color(150, 75, 0),
                SILVER, Color.GRAY,
                GOLD, Color.ORANGE,
                PLATINUM, Color.CYAN,
                EMERALD, Color.GREEN,
                DIAMOND, Color.BLUE,
                MASTER, Color.MAGENTA,
                GRANDMASTER, Color.RED,
                CHALLENGER, Color.YELLOW
                );}

    public static Color getApiValue(String colorName) {
        return COLOR_API_VALUES.get(colorName);
    }

    public static boolean isValid(String colorName) {
        return COLOR_API_VALUES.containsKey(colorName.toUpperCase());
    }

    public static Set<String> getAvailableImages() {
        return COLOR_API_VALUES.keySet();
    }

    public static Map<String, Color> getColorMap() {
        return COLOR_API_VALUES;
    }
}
