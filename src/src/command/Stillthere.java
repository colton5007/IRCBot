package src.command;

import src.Command;

public class Stillthere extends Command {

	public Stillthere() {
		super(20, "areyoustillthere");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		bot.sendMessage(channel, "I'm still here!");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!areyoustillthere - How can I be here if my eyes aren't real?";
	}

}
