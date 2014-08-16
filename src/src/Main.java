package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class Main {

	public static void main(String[] args) throws NickAlreadyInUseException,
			IOException, IrcException {
		RFWBot bot = new RFWBot();
		bot.connect("irc.freenode.net");
		bot.joinChannel(RFWBot.CHANNEL);
		//bot.joinChannel("#rmct");
		ChatInteractive c = new ChatInteractive();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					bot.writeUserData();
					bot.quitServer("Because Colton is fixing me!");
					c.setDead();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		c.start();
	}

}
