package models;

public abstract class User {

	private String username;
	private String pass;
	private UserType type;
	
	public User(String username, String pass, UserType type) {
		this.username = username;
		this.pass = pass;
		this.type = type;
	}
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUserame(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
