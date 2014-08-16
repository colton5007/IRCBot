package src.command;

import java.util.Date;

import src.Command;

public class Time extends Command {

	public Time() {
		super(60, "time");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {

		String time = new Date().toString();
		bot.sendMessage(channel, sender + ": The time is now " + time);
		return true;

	}

	@Override
	public String getUsage() {
		return "!time -Time, or rhyme I don't know which which is which";
	}

}
