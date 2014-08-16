package src.command;

import src.Command;

public class Scoreboard extends Command {

	public Scoreboard() {
		super(60, "scoreboard");

	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		if (args.length > 1) {
			bot.sendMessage(sender, "Incorrect Syntax");
			return false;
		}
		int iter = 5;
		if (args.length == 1)
			try {
				iter = Integer.parseInt(args[0]);
			} catch (Exception e) {
				bot.sendMessage(sender, "I");
			}
		if (iter > 30 || iter < 1) {
			bot.sendMessage(sender, "Number Too Large<30> or Small<1>!");
			return false;
		}
		for (String s : bot.uh.getScoreboard(iter)) {
			bot.sendMessage(sender, s);
		}
		return true;
	}

	@Override
	public String getUsage() {
		return "!scoreboard - Display those top stats!";
	}

}
