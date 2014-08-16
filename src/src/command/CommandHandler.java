package src.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.Command;
import src.RFWBot;
import src.command.ha.Acknowledge;
import src.command.ha.Ha;
import src.command.ha.HaHa;
import src.command.ha.HaHaHa;
import src.command.ha.HaHaHaHa;
import src.command.pug.Remove;
import src.command.time.TimeConv;

public class CommandHandler {

	List<Command> commands = new ArrayList<Command>();
	List<Command> disabled = new ArrayList<Command>();
	HashMap<String, Long> globalCooldowns = new HashMap<String, Long>();
	int globalCooldown;
	TimeConv conv = new TimeConv();
	public static CommandHandler instance;

	public CommandHandler(int globalCooldown) {
		registerCommand(new Boyfriend());
		registerCommand(new Girlfriend());
		registerCommand(new Scoreboard());
		registerCommand(new Slap());
		registerCommand(new Stillthere());
		registerCommand(new Time());
		registerCommand(new Wools());
		registerCommand(new Wool());
		registerCommand(new Acknowledge());
		registerCommand(new Ha());
		registerCommand(new HaHa());
		registerCommand(new HaHaHa());
		registerCommand(new HaHaHaHa());
		registerCommand(new Enable());
		registerCommand(new Disable());
		registerCommand(new Pug());
		registerCommand(new Remove());
		registerCommand(new LoginMessage());
		registerCommand(new Help(this));
		this.globalCooldown = globalCooldown;
		CommandHandler.instance = this;
	}

	public void registerCommand(Command c) {
		commands.add(c);
	}

	public void registerUser(String user) {
		for (Command c : commands) {
			c.registerUser(user);
		}
	}

	public void disableCommand(String name) {
		for (Command c : commands) {
			if (c.getName().equals(name)) {
				commands.remove(c);
				disabled.add(c);
				return;
			}
		}
	}

	public void enableCommand(String name) {
		for (Command c : disabled) {
			if (c.getName().equals(name)) {
				disabled.remove(c);
				commands.add(c);
				return;
			}
		}
	}

	public boolean hasUser(String user) {
		return commands.get(0).hasUser(user);
	}

	public void processCommand(String channel, String sender, String login,
			String hostname, String message) {
		String[] msg = message.split(" ");
		String command = msg[0];
		String[] args = new String[msg.length - 1];
		for (int i = 0; i < args.length; i++) {
			args[i] = msg[i + 1];
		}
		for (Command c : commands) {
			if (command.equalsIgnoreCase("!" + c.getName())) {
				if (getAllowed(sender, channel, c)) {
					if (c.getAllowed(sender)) {
						boolean cooldown = c.handleMessage(channel, sender,
								args);
						if (cooldown)
							applyCooldown(sender);
						c.applyCooldown(sender);
					}
				}
			}
		}
		for (Command c : disabled) {
			if (command.equalsIgnoreCase("!" + c.getName())) {
				RFWBot.instance.sendMessage(sender, c.getName()
						+ " is a disabled command");
			}
		}
		if (msg.length > 2 && msg.length < 5 && message.contains(":"))
			conv.handleMessage(channel, sender, message);
	}

	public List<Command> getCommands() {
		return commands;
	}

	public boolean getAllowed(String user, String channel, Command c) {
		if (!globalCooldowns.containsKey(user))
			globalCooldowns.put(user, (long) 0);
		if (c.needsPug())
			return c.getPug();
		if (RFWBot.instance.ph.getChannels().contains(channel))
			return c.getPug();
		boolean allowed = globalCooldowns.get(user) <= System
				.currentTimeMillis();
		if (!allowed) {
			boolean op = RFWBot.instance.getUser(user, RFWBot.CHANNEL).isOp();
			if (!op)
				RFWBot.instance.sendMessage(user,
						"All commands have a minute for cooldown!");
			return op;
		}
		if (user.equals(RFWBot.OWNER))
			return true;
		return allowed;
	}

	public void applyCooldown(String user) {
		globalCooldowns.replace(user, System.currentTimeMillis()
				+ (globalCooldown * 1000));
	}
}
