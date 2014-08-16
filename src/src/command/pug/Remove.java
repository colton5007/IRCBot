package src.command.pug;

import src.Command;

public class Remove extends Command {

	public Remove() {
		super(30, "remove");
		this.allowPug();
		this.needsPug();
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		bot.ph.getPug(channel).remove();
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "!remove - Removes the pug you are currently in (Only works in pug channels)";
	}

}
