package com.pium.riot.commands.profilecommand;

import com.pium.riot.api.ApiRiot;
import com.pium.riot.commands.RiotBotCommand;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.io.IOException;

public class Profile implements RiotBotCommand {
    public ProfileService service;

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {
        String summonerName = getOptionValue(event, "nick");
        String tag = getOptionValue(event, "tag");
        String region = getOptionValue(event, "region");


        if (summonerName == null || tag == null || region == null) {
            event.reply("Faltan argumentos. ").setEphemeral(true).queue();
            return;
        }

        try {
            service = new ProfileService(new ApiRiot(summonerName, tag, region));
        } catch (IOException e) {
            event.reply("No existe la cuenta").setEphemeral(true).queue();
            return;
        }

        if(!service.embeds.isEmpty()) service.embeds.clear();

        event.deferReply().queue();
        service.profilesBuilder();

        event.getHook().sendMessageEmbeds(service.embeds.getLast(), service.embeds.getFirst()).addActionRow(
            Button.link("https://github.com/AgustinJak/riot-discord-bot", "GitHub").
            withEmoji(Emoji.fromCustom("GitIcon", 1363525155573338212L, false))
            ).queue();
    }

    private String getOptionValue(SlashCommandInteractionEvent event, String optionName) {
        return event.getOption(optionName) != null ? event.getOption(optionName).getAsString() : null;
    }


}



