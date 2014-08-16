package src.command;

import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.User;

import src.RFWBot;

public class PugObject {

	int id;
	List<String> users = new ArrayList<String>();
	RFWBot bot = RFWBot.instance;
	String initial;
	String channel;
	long created;
	
	public PugObject(String initial) {
		this.id = PugHandler.instance.getNextId();
		this.channel = RFWBot.PUG_PREFIX + id;
		this.created = System.currentTimeMillis();
		bot.joinChannel(channel);
		bot.ph.addChannel(channel);
		bot.setTopic(channel, "Pug Chat Will Close When All Players Leave");
		this.initial = initial;
		invite(initial);
	}
	
	public void invite(String s) {
		bot.sendInvite(s, channel);
		bot.sendMessage(s, "You've been invited to " + initial + "'s pug!");
	}
	
	public void addUser(String user) {
		users.add(user);
		bot.ph.addPlayer(user);
	}
	
	public String getCreator() {
		return initial;
	}
	
	public String[] getPlayers() {
		User[] users = bot.getUsers(channel);
		String[] players = new String[users.length];
		for(int i = 0; i < users.length; i++) {
			players[i] = users[i].getNick();
		}
		return players;
	}

	public String getChannel() {
		return channel;
	}
	
	public int getPlayerCount() {
		return users.size();
	}
	
	public void checkRemove(String s) {
		System.out.println(getPlayers().length);
		users.remove(s);
		if(getPlayers().length < 2) {
			bot.ph.removePug(channel);
			bot.partChannel(channel);
		}
	}
	
	public int getId() {
		return id;
	}

	public void remove() {
		for(String p : getPlayers()) {
			if(!p.equalsIgnoreCase(RFWBot.BOT_NAME)) {
				bot.kick(channel, p, "Player used remove command / Channel Timeouted");
			}
		}
		bot.partChannel(channel);
	}

	public void timeout() {
		if(System.currentTimeMillis() >= created + (1000 * 60 * 60 * 3)) remove();
	}
}
