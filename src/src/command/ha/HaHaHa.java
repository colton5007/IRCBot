package src.command.ha;

import src.Command;

public class HaHaHa extends Command {

	public HaHaHa() {
		super(15, "hahaha");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.sendMessage(channel, sender
				+ " smiled and air came out of his/her nose.");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!hahaha - Now your getting it!";
	}

}
