package src.command;

import java.util.Random;

import src.Command;

public class Girlfriend extends Command {

	public Girlfriend() {
		super(60, "!getmeagirlfriend");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		Random rand = new Random();

		bot.sendMessage(
				channel,
				"How do you like, "
						+ bot.female.get(rand.nextInt(bot.female.size()))
						+ "? Nah, you have no chance!");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!getmeagirlfriend - We have no lives";
	}

}
