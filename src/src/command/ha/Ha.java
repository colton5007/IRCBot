package src.command.ha;

import src.Command;

public class Ha extends Command {

	public Ha() {
		super(15, "ha");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.sendMessage(channel, sender + " appreciates the joke.");
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!ha - That joke was barely even funny.";
	}

}
