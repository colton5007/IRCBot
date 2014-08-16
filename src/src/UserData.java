package src;

public class UserData {

	String name;
	int wools;
	long login;
	String loginMessage;
	
	public UserData(String name) {
		this.name = name;
		this.login = System.currentTimeMillis();
		this.loginMessage = "Welcome, %n to the %c IRC Channel!";
		this.wools = 0;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getLogin() {
		return login;
	}


	public void setLogin(long login) {
		this.login = login;
	}


	public String getLoginMessage() {
		return loginMessage;
	}


	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}


	public void setWools(int wools) {
		this.wools = wools;
	}
	
	public int getWools() {
		return wools;
	}
	
	public void addWool() {
		this.wools++;
	}
	
	public static UserData parseAndRoute(String sender, String wools, String login, String loginMessage) {
		UserData user = new UserData(sender);
		user.wools = Integer.parseInt(wools);
		user.login = Long.parseLong(login);
		user.loginMessage = loginMessage;
		return user;
	}
	
	public boolean isEqual(String sender) {
		return (sender.equalsIgnoreCase(name));
	}
}
