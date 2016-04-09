package models;
/**
 * 
 * @author Ghiurutan
 *The class of Customer
 */
public class Customer {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = "Customer";
		this.password = "Customer1";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
