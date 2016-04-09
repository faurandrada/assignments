package project;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable{

	private static final String userName = "user1";
	private static final String password = "pass1";

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

}
