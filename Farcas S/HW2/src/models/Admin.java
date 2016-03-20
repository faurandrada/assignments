package models;

public class Admin extends User {

	public Admin (String username, String pass){
		super(username, pass, UserType.ADMIN);
	}
	
}
