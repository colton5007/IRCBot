package src.command;

import java.util.ArrayList;
import java.util.List;

import src.RFWBot;

public class PugHandler {

	public static PugHandler instance;
	List<PugObject> pugs = new ArrayList<PugObject>();
	List<String> pugChannels = new ArrayList<String>();
	List<String> players = new ArrayList<String>();

	public PugHandler() {

		instance = this;
	}

	public void addPug(PugObject p) {
		pugs.add(p);
	}

	public void removePug(String s) {
		pugs.remove(getPug(s));
	}

	public int getNextId() {
		return pugs.size();
	}

	public PugObject getPug(String s) {
		String sid = s.replaceAll(RFWBot.PUG_PREFIX, "");
		int id = Integer.parseInt(sid);
		return pugs.get(id);
	}

	public PugObject getPug(int id) {
		return pugs.get(id);
	}

	public List<PugObject> getPugs() {
		return pugs;
	}

	public void addChannel(String s) {
		this.pugChannels.add(s);
	}

	public List<String> getChannels() {
		return pugChannels;
	}

	public void addPlayer(String s) {
		players.add(s);
	}

	public boolean hasPlayer(String s) {
		return players.contains(s);
	}
}
