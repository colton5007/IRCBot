package src;

import java.util.Scanner;

public class ChatInteractive extends Thread {

	public volatile boolean bool = true;

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(bool) {
			String line = sc.nextLine();
			if(line.equalsIgnoreCase("stop")) {
				setDead();
			} else if(line.equalsIgnoreCase("exit")) {
				System.exit(0);
			}else {
				RFWBot.instance.sendMessage(RFWBot.CHANNEL, line);
			}
		}
		sc.close();
	}

	public void setDead() {
		this.bool = false;
	}

}
