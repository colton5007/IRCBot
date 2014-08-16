package src.command.ha;

import src.Command;

public class HaHa extends Command {

	public HaHa() {
		super(15, "haha");

	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.sendMessage(channel, sender + " smiled.");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!haha - Better than the last one";
	}

}
