package src.command;

import src.Command;

public class LoginMessage extends Command{

	public LoginMessage() {
		super(30, "login");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		
			if(args.length == 0) {
				return false;
			}
			
			if(args.length == 1) {
				if(!bot.uh.containsUser(args[0])) {
					bot.sendMessage(sender, "No User With Name: " + args[0]);
					return false;
				}
				bot.sendMessage(sender, args[0] + ": " + bot.uh.getUser(args[0]).getLoginMessage());
				return true;
			}
			String message = "";
			for(int i = 1; i < args.length; i++) {
				message += args[i] + " ";
			}
			bot.uh.getUser(args[0]).setLoginMessage(message);
			bot.sendMessage(sender, "Custom Message Set!");
			return true;
	}

	@Override
	public String getUsage() {
		return "!login <User> [New Message]";
	}

	
}
