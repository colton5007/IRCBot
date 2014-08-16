package src.command.ha;

import src.Command;

public class Acknowledge extends Command {

	public Acknowledge() {
		super(15, "!acknowledge joke");

	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.sendMessage(
				channel,
				sender
						+ " understands that there was an attempt at a joke, and feels it is best that we move on.");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!acknowledge joke - Didn't your parents tell you not to acknowledge strangers? Especially ones bad at jokes.";
	}

}
