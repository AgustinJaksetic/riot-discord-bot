package com.pium.riot.commandservice.riotcommandmanagerservice;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;

public interface RiotBotCommand {
    void execute(SlashCommandInteractionEvent event) throws IOException;
}