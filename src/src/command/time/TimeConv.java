package src.command.time;

import java.util.HashMap;

import src.RFWBot;

public class TimeConv{

	int cooldown = 5;
	HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	public TimeConv() {
	}

	public boolean handleMessage(String channel, String sender, String message) {
		String msg = TimeOperations.convert(sender, message);
		if(msg.equals("")) {
			return false;
		}
		RFWBot.instance.sendMessage(channel, msg);
		return true;
	}
	
	public boolean getAllowed(String sender) {
		if(sender.equals(RFWBot.OWNER)) return true;
		if(cooldowns.containsKey(sender)) {
			return cooldowns.get(sender) >= System.currentTimeMillis();
		} else {
			cooldowns.put(sender, (long)0);
			return true;
		}
	}
	
	public void applyCooldown(String sender) {
		cooldowns.replace(sender, ((long) cooldown * 1000 * 60));
	}

}
