package src.command.time;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import src.RFWBot;

public class TimeOperations {

	public static String convert(String sender, String msg) {
		RFWBot bot = RFWBot.instance;
		String result = "";
		// HH:MM <AM|PM> <UTC> <EST>
		String[] args = msg.split(" ");
		int hrs = 0;
		int min = 0;
		int extraHrs = 0;
		try {
			if (args[0].contains(":")) {
				String[] split = args[0].split(":");
				if (split.length != 2) {
					bot.sendMessage(sender, "Incorrect Time Format HH:MM");
					return "";
				}
				hrs = Integer.parseInt(split[0]);
				min = Integer.parseInt(split[1]);
			} else {
				hrs = Integer.parseInt(args[0]);
				min = 0;
			}

		} catch (Exception e) {
			bot.sendMessage(sender, "Hours or Minutes aren't real nums");
			return "";
		}
		boolean noAM = false;
		if (args[1].equals("PM")) {
			extraHrs += 12;
		} else if (args[1].equals("AM")) {
			extraHrs += 0;
		}else {
			noAM = true;
		}
		double originalTimeOffset = 0;
		int i = 2;
		if(noAM) i = 1;
		if (!bot.timeZones.containsKey(args[i])) {
			bot.sendMessage(sender, "Invalid Timezone, Default UTC");
		} else {
			originalTimeOffset = bot.timeZones.get(args[i]);
		}

		int j = 3;
		if(noAM) j = 2;
		double newTimeOffset = 0;
		if (!bot.timeZones.containsKey(args[j])) {
			bot.sendMessage(sender, "Invalid Timezone, Default UTC");
		} else {
			newTimeOffset = bot.timeZones.get(args[j]);
		}
	
		Calendar c = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		c.set(Calendar.HOUR, hrs + extraHrs);
		c.set(Calendar.MINUTE, min);
		c.setTimeInMillis((long) (c.getTimeInMillis()
				+ ((long) 1000 * 60 * 60 * originalTimeOffset) - ((long) 1000 * 60 * 60 * newTimeOffset)));
		String minutes = Integer.toString(c.get(Calendar.MINUTE));
		String hour = Integer.toString(c.get(Calendar.HOUR));
		if (minutes.equals("0")) minutes += "0";
		if (hour.equals("0")) hour = "12";
		result = "| " + args[j] +" " + hour + ":" + minutes + " " + getAM_PM(c) + " |";
		return result;
	}

	public static String getAM_PM(Calendar c) {
		if (c.get(Calendar.AM_PM) == 1) {
			return "AM";
		} else {
			return "PM";
		}
	}
}
