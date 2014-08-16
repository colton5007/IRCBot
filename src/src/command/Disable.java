package src.command;

import src.Command;

public class Disable extends Command{

	public Disable() {
		super(0, "disable");
		this.requireOp();
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		if(args.length != 1) return false;
		CommandHandler.instance.disableCommand(args[0]);
		bot.sendMessage(sender, "Disabled " + args[0]);
		return true;
	}

	@Override
	public String getUsage() {
		return "!disable <Command Name> - Disables A Enabled Command";
	}

}
