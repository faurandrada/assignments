package Users;

import java.util.Set;
import java.util.TreeSet;

import Control.Helper;

public class UsersData {

	private Set<User> users;
	private Helper helper;
	
	public void updateUsers() {
		users = new TreeSet<User>();
		helper = new Helper();
		helper.readFromSerFile(users, "users.bin");
	}

	public Set<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		users.add(user);
		helper.updateSerFile(users, "users.bin");
	}

	public void removeUser(User user) {
		users.remove(user);
		helper.updateSerFile(users, "users.bin");
	}

	public void seeAllUsers() {
		for (User user : users) {
			System.out.println(user);
		}
	}
}