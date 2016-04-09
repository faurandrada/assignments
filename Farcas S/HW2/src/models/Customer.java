package models;

public class Customer extends User{

	public Customer(String username, String pass){
		super(username, pass, UserType.CUSTOMER);
	}
	
}
