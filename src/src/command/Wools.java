package src.command;

import org.jibble.pircbot.User;

import src.Command;

public class Wools extends Command {

	public Wools() {
		super(10, "wools");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		if (args.length > 1) {
			bot.sendMessage(sender, "Incorrect Syntax");
			return false;
		}
		if (args.length == 0) {
			int woolCount = bot.uh.getUser(sender).getWools();
			bot.sendMessage(channel, sender + ", you have " + woolCount
					+ " wools!");

			return true;
		}

		User[] users = bot.getUsers(channel);
		for (User u : users) {
			if (u.getNick().toLowerCase().equalsIgnoreCase(args[0])) {
				int woolCount = bot.uh.getUser(u.getNick()).getWools();
				bot.sendMessage(channel, u.getNick() + ", has " + woolCount
						+ " wools!");

				return true;
			}

		}
		return false;

	}

	@Override
	public String getUsage() {
		return "!wools [user] - Gets the amount of money a user has, RMCT style!";
	}

}
