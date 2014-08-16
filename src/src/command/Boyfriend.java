package src.command;

import java.util.Random;

import src.Command;

public class Boyfriend extends Command {

	public Boyfriend() {
		super(60, "gemeaboyfriend");

	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		Random rand = new Random();
		bot.sendMessage(
				channel,
				"I heard you have no chance with "
						+ bot.male.get(rand.nextInt(bot.male.size())) + "!");

		return true;
	}

	@Override
	public String getUsage() {
		return "!getmeaboyfriend - We really have no lives";
	}

}
