package me.demeng.ticketbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

  private static final String TOKEN = "YOUR_TOKEN_HERE";
  public static final long TICKETS_CATEGORY = 0L;
  public static final long ADMIN_ROLE = 0L;

  public static void main(String[] args) throws InterruptedException {

    JDA jda = JDABuilder.createDefault(TOKEN)
        .addEventListeners(new CommandEvent(), new ButtonListener())
        .build().awaitReady();

    jda.upsertCommand("ping", "Pings the bot.").queue();
    jda.upsertCommand("panel", "Creates a new ticket creation panel.").queue();
    jda.upsertCommand("close", "Closes a ticket.").queue();
  }
}
