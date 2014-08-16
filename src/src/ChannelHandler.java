package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelHandler {

	HashMap<String, Boolean> channels = new HashMap<String, Boolean>();
	List<String> channelData = new ArrayList<String>();
	
	public ChannelHandler() {
	
	}
	
	public void addChannel(String s, boolean pug) {
		channels.put(s, pug);
		channelData.add(s);
	}
	public void addChannel(String s) {
		channels.put(s, false);
		channelData.add(s);
	}
	
	public boolean isPug(String s) {
		return channels.get(s);
	}
	
	public List<String> getChannels() {
		return channelData;
	}
}
