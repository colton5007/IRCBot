package src;

import java.util.HashMap;

public abstract class Command {
	
	int cooldown;
	String name;
	int args;
	public RFWBot bot = RFWBot.instance;
	boolean needsOp = false;
	boolean allowPug = false;
	boolean needsPug = false;
	
	HashMap<String, Long> cooldowns = new HashMap<String, Long>();
		
	public Command(int cooldown, String name) {
		this.cooldown = cooldown;
		this.name = name;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public abstract boolean handleMessage(String channel, String sender, String[] args);
	
	public void applyCooldown(String sender) {
		cooldowns.replace(sender, System.currentTimeMillis() + (cooldown * 60 * 1000));
	}
	
	public boolean getAllowed(String sender) {
		long now = System.currentTimeMillis();
		if(!cooldowns.containsKey(sender)) cooldowns.put(sender, (long)0);
		boolean allowed = now >= cooldowns.get(sender);
		boolean userOp = bot.getUser(sender, RFWBot.CHANNEL).isOp();
		if(needsOp) {
			if(userOp) {
				if(allowed) return true;
			} else {
				return false;
			}
		}
		if(sender.equals("colton5007")) return true;
		if(userOp) return true;
		if(!allowed) bot.sendMessage(sender, "Please wait " + this.cooldown + " minutes for cooldown!");
		return allowed;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String getUsage();
	
	public void registerUser(String s) {
		cooldowns.put(s, (long)0);
	}
	
	public boolean hasUser(String s) {
		return cooldowns.containsKey(s);
	}
	
	public Command requireOp() {
		this.needsOp = true;
		return this;
	}

	public boolean getPug() {
		return allowPug;
	}
	
	public Command allowPug() {
		this.allowPug = true;
		return this;
	}
	
	public void forcePug() {
		this.needsPug = true;
	}
	
	public boolean needsPug() {
		return needsPug;
	}
}
