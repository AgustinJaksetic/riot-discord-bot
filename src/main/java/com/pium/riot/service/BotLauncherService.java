package com.pium.riot.service;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotLauncherService {
    public void start() {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("token");

        JDA api = JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new RiotCommandManagerService())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        new CommandRegisterService(api).registerCommands();
    }
}
