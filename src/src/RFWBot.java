package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import src.command.CommandHandler;
import src.command.PugHandler;

public class RFWBot extends PircBot {

	public static final String PUG_PREFIX = "#RFWPug_";
	public static final String CHANNEL = "#colton5007";
	public static final String BOT_NAME = "ColtonBot";
	public static final String OWNER = "colton5007";
	public static final boolean VERBOOSE = false;

	public static RFWBot instance;
	File f = new File(System.getProperty("user.dir") + "/RFWBot.info");
	public List<String> female;
	public List<String> male;
	public PugHandler ph = new PugHandler();
	public UserHandler uh;
	public HashMap<String, Double> timeZones = new HashMap<String, Double>();
	PrintWriter ircLog;

	CommandHandler ch;

	public RFWBot() throws FileNotFoundException {
		this.setName(BOT_NAME);
		this.setVerbose(VERBOOSE);
		female = getCelebs("female");
		male = getCelebs("male");
		this.setLogin(BOT_NAME);
		this.timeZones = readTimeZones();
		instance = this;
		ch = new CommandHandler(30);
		try {
			File f = new File(System.getProperty("user.dir")
					+ "/IRC.log");
			if (!f.exists())
				f.createNewFile();
			if (!this.f.exists())
				this.f.createNewFile();
			ircLog = new PrintWriter(f);
			List<UserData> data = readUserData();
			this.uh = new UserHandler(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		ch.processCommand(channel, sender, login, hostname, message);
		long time = System.currentTimeMillis();
		for (String p : ph.getChannels()) {
			ph.getPug(p).timeout();
		}
		Date d = new Date(time);
		logLine("[" + channel + "-" + d.toString() + "]" + "<" + sender + ">"
				+ " " + message);
	}

	@Override
	protected void onJoin(String channel, String sender, String login,
			String hostname) {
		logLine("[" + channel + "-" + new Date(System.currentTimeMillis()).toString() + "]" + "<" + sender + ">"
				+ " Join Channel");
		if (channel.contains(PUG_PREFIX)) {
			String id = channel.replaceAll(PUG_PREFIX, "");
			ph.getPug(Integer.parseInt(id)).addUser(sender);
		}
		if (!uh.containsUser(sender)) {
			UserData user = new UserData(sender);
			uh.addUser(user);
			sendLoginMessage(user, channel);
		} else {
			UserData user = uh.getUser(sender);
			if (user.getLogin() + (1000 * 60 * 60 * 24) <= System
					.currentTimeMillis()) {
				sendLoginMessage(user, channel);
				user.setLogin(System.currentTimeMillis());
			}
		}
	}

	public void sendLoginMessage(UserData sender, String channel) {

		String message = sender.getLoginMessage();
		message = message.replace("%n", sender.getName())
				.replace("%c", channel);
		sendMessage(channel, message);
	}

	@Override
	protected void onPart(String channel, String sender, String login,
			String hostname) {
		if (ph.getChannels().contains(channel)) {
			ph.getPug(channel).checkRemove(sender);
		}
	}

	public void logLine(String s) {
		ircLog.println(s);
		System.out.println(s);
		ircLog.flush();
	}

	public List<String> getCelebs(String ext) {
		List<String> celebs = new ArrayList<String>();
		Scanner sc = null;
		sc = new Scanner(getClass().getResourceAsStream(ext + ".txt"));
		while (sc.hasNext()) {
			celebs.add(sc.nextLine());
		}
		sc.close();
		return celebs;
	}

	public User getUser(String s, String channel) {
		for (User u : this.getUsers(channel)) {
			if (u.getNick().equalsIgnoreCase(s)) {
				return u;
			}
		}
		return null;
	}

	public PugHandler getPugHandler() {
		return ph;
	}

	public List<UserData> readUserData() throws FileNotFoundException {
		List<UserData> users = new ArrayList<UserData>();
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] data = line.split("`");
			users.add(UserData
					.parseAndRoute(data[0], data[1], data[2], data[3]));
		}
		sc.close();
		return users;
	}

	public void writeUserData() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(f);
		for (UserData user : this.uh.getUsers()) {
			String line = user.name + "`" + user.wools + "`" + user.login + "`"
					+ user.loginMessage;
			pw.println(line);
		}
		pw.flush();
		pw.close();
	}

	public HashMap<String, Double> readTimeZones() throws FileNotFoundException {
		HashMap<String, Double> result = new HashMap<String, Double>();
		Scanner sc = new Scanner(getClass().getResourceAsStream("time.txt"));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.split(" ");
			result.put(split[0], Double.parseDouble(split[1]));
		}
		sc.close();
		return result;
	}
}
