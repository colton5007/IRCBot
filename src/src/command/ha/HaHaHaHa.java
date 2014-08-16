package src.command.ha;

import src.Command;

public class HaHaHaHa extends Command {

	public HaHaHaHa() {
		super(15, "hahahaha");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.sendMessage(channel, sender + " smiled and actually made a noise!");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!hahahaha - Excuse me while I laugh my head off";
	}

}
