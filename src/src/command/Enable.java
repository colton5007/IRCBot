package src.command;

import src.Command;

public class Enable extends Command{

	public Enable() {
		super(0, "enable");
		this.requireOp();
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		if(args.length != 1) return false;
		CommandHandler.instance.enableCommand(args[0]);
		bot.sendMessage(sender, "Enabled " + args[0]);
		return true;
	}

	@Override
	public String getUsage() {
		return "!enable <CommandName> - Enables A Disabled Command";
	}

}
