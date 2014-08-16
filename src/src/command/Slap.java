package src.command;

import org.jibble.pircbot.User;

import src.Command;

public class Slap extends Command {

	public Slap() {
		super(45, "slap");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		if (args.length > 1) {
			bot.sendMessage(sender, "Incorrect Syntax");
			return false;
		}
		if (args.length == 0) {
			bot.sendMessage(channel, "Slap Who? " + sender + "? GOTCHA!");
			return true;
		}

		User[] users = bot.getUsers(channel);
		for (User u : users) {
			if (u.getNick().toLowerCase().equalsIgnoreCase(args[0])) {
				bot.sendMessage(channel, "Slap Who? " + u.getNick()
						+ "? GOTCHA!");
				return true;
			}
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "!slap [User] - Slap lax, I mean someone";
	}

}
