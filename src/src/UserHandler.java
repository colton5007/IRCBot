package src;

import java.util.ArrayList;
import java.util.List;

public class UserHandler {

	List<UserData> users = new ArrayList<UserData>();

	public UserHandler(List<UserData> data) {
		this.users = data;
	}

	public boolean containsUser(String user) {
		for (UserData u : users) {
			if (u.isEqual(user))
				return true;
		}
		return false;
	}

	public void addUser(UserData user) {
		this.users.add(user);
	}

	public List<UserData> getUsers() {
		return users;
	}

	public UserData getUser(String user) {
		for (UserData u : users) {
			if (u.isEqual(user))
				return u;
		}
		return null;
	}

	public List<String> getScoreboard(int num) {
		List<String> results = new ArrayList<String>();
		List<UserData> temp = users;
		for (int i = 0; i < num; i++) {
			UserData highest = users.get(0);
			for (UserData u : users) {
				if(u.getWools() > highest.getWools()) {
					highest = u;
				}
			}
			results.add((i+1) + ") " + highest.getName() + "(" + highest.getWools() + ")");
			temp.remove(highest);
		}
		return results;
	}
}
