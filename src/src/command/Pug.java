package src.command;

import src.Command;
import src.RFWBot;

public class Pug extends Command{

	public Pug() {
		super(20, "pug");
	}

	@Override
	public boolean handleMessage(String channel, String sender, String[] args) {
		PugHandler ph = RFWBot.instance.getPugHandler();
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("create")) {
				if(bot.ph.hasPlayer(sender)){
					bot.sendMessage(sender, "You may only have one pug chat you are in!");
					return false; 
				}
				ph.addPug(new PugObject(sender));
				bot.sendMessage(sender, "Created Pug Channel, you may join it now! Give people your username to connect to it!");
				return true;
			} else if (args[0].equalsIgnoreCase("list")) {
				if(ph.getPugs().size() == 0) {
					bot.sendMessage(sender, "No pugs to list!");
					return false;
				}
				for(PugObject p : ph.getPugs()) {
					String players = "";
					String[] pPlayers = p.getPlayers();
					for(int i = 0; i < pPlayers.length; i++) {
						if(i == pPlayers.length-1) {
							players += pPlayers[i];
						} else {
							players += pPlayers[i] +",";
						}
					}
					bot.sendMessage(sender, "-" + p.getChannel() + ": " + players);
					return false;
				}
			} else if (args[0].equalsIgnoreCase("join")) {
				if(ph.hasPlayer(sender)) {
					bot.sendMessage(sender, "You may only have one pug chat you are in!");
					return false; 
				}
				if(ph.getPugs().size() == 0){
					bot.sendMessage(sender, "No Pugs to join create one with !pug create");
					return false;
				}
				PugObject lowest = ph.getPug(0);
				for(PugObject p : ph.getPugs()) {
					if(p.getPlayerCount() < lowest.getPlayerCount()) lowest = p;
				}
				lowest.invite(sender);
				return true;
			}
		} else if (args.length == 2) {
	
			if (args[0].equalsIgnoreCase("join")) {
				for(PugObject p : ph.getPugs()) {
					for(String user : p.getPlayers()) {
						if(user.equalsIgnoreCase(args[1])){
							p.invite(sender);
							return true;
						}
					}
				}
				bot.sendMessage(sender, "No Pug with Player: " + args[1]);
				return false;
			}
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "!pug <create | join | list> [player]";
	}

}
