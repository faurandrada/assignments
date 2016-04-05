package Users;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 2672652849697013883L;
	
	protected String name;
	protected String password;
	protected boolean stateAdmin;
	protected boolean stateCustomer;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isAdmin(){
		return stateAdmin;
	}
	
	public boolean isCustomer(){
		return stateCustomer;
	}

	@Override
	public int compareTo(User u) {
		return this.name.compareTo(u.getName());
	}

}
