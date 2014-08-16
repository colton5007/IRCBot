package src.command;

import org.jibble.pircbot.User;

import src.Command;

public class Wool extends Command {

	public Wool() {
		super(60, "wool");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		if (args.length != 1) {
			bot.sendMessage(sender, "Incorrect Syntax");
			return false;
		}
		String user = args[0];
		User[] users = bot.getUsers(channel);
		for (User u : users) {
			if (u.getNick().equalsIgnoreCase(user)
					&& !u.getNick().equalsIgnoreCase(sender)) {
				bot.sendMessage(channel, u.getNick() + ", heres a wool from "
						+ sender + "!");

				bot.uh.getUser(u.getNick()).addWool();
				return true;
			}
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "!wool <User> - Give a wool to that important person in your life ;)";
	}

}
