package me.demeng.ticketbot;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class CommandEvent extends ListenerAdapter {

  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

    switch (event.getName()) {
      case "ping":
        event.reply("Pong! \uD83C\uDFD3").setEphemeral(true).queue();
        return;

      case "panel":

        if (!isAdmin(event.getMember())) {
          event.reply("You do not have permission to use this command!").setEphemeral(true).queue();
          return;
        }

        final EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.getColor("#ff3838"));
        eb.setTitle("Ticket Creation");
        eb.setDescription("Click the button below to create a ticket.");
        event.replyEmbeds(eb.build())
            .addActionRow(Button.primary("create_ticket", "Create a Ticket"))
            .queue();

      case "close":

        if (!isAdmin(event.getMember())) {
          event.reply("You do not have permission to use this command!").setEphemeral(true).queue();
          return;
        }

        if (!event.getChannel().getName().startsWith("ticket-")) {
          event.reply("You can only use this command in a ticket channel!")
              .setEphemeral(true).queue();
          return;
        }

        event.reply("Ticket will be closed in 3 seconds! ❌").queue();
        event.getChannel().delete().queueAfter(3, TimeUnit.SECONDS);
    }
  }

  private static boolean isAdmin(Member member) {
    return member.getRoles().stream()
        .anyMatch(role -> role.getIdLong() == Main.ADMIN_ROLE);
  }
}
