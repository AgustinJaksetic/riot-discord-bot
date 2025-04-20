package com.pium.riot.commands.profilecommand.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class embed {

    public static MessageEmbed generateEmbed(String authorName, String authorLink, String authorIcon, String title, String description, String field , String inField , String footer, Color color, String thumbnailUrlImage, String urlImage) {

        EmbedBuilder eb = new EmbedBuilder();

        if (authorName != null) eb.setAuthor(authorName, authorLink, authorIcon);
        if (title != null) eb.setTitle(title);
        if (description != null) eb.setDescription(description);
        if (field != null && inField != null) eb.addField(field, inField, true);
        if (footer != null) eb.setFooter(footer);
        if (color != null) eb.setColor(color);
        if (thumbnailUrlImage != null) eb.setThumbnail(thumbnailUrlImage);
        if (urlImage != null) eb.setImage(urlImage);

        return eb.build();
    }
}