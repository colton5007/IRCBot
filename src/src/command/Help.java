package src.command;

import src.Command;

public class Help extends Command {

	CommandHandler ch;

	public Help(CommandHandler ch) {
		super(30, "help");
		this.ch = ch;
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		for (Command c : ch.getCommands()) {
			bot.sendMessage(sender, c.getUsage());
		}
		applyCooldown(sender);
		return true;

	}

	@Override
	public String getUsage() {
		return "<!help | ! ?> Get help for help so you can help the help!";
	}

}
