package com.pium.riot;

import com.pium.riot.commands.Profile;
import com.pium.riot.commands.RiotBotCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RiotCommandManager extends ListenerAdapter {
    private final Map<String, RiotBotCommand> commands = new HashMap<>();

    public RiotCommandManager() {
        addCommand("profile", new Profile());
    }

    private void addCommand(String name, RiotBotCommand command) {
        commands.put(name, command);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String commandname = event.getName();
        RiotBotCommand command = commands.get(commandname);
        try {
            command.execute(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}