package me.demeng.ticketbot;

import java.util.Collections;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonListener extends ListenerAdapter {

  @Override
  public void onButtonInteraction(ButtonInteractionEvent event) {

    if (event.getButton().getId().equals("create_ticket")) {

      Category category = event.getGuild().getCategoryById(Main.TICKETS_CATEGORY);

      category.createTextChannel("ticket-" + event.getUser().getName())
          .addMemberPermissionOverride(event.getUser().getIdLong(),
              Collections.singletonList(Permission.VIEW_CHANNEL), null)
          .queue(channel -> channel.sendMessage(
                  event.getUser().getAsMention() + ", welcome to your ticket!").queue());
    }
  }
}
