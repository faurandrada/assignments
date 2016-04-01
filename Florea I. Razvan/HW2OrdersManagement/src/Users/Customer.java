package Users;

public class Customer extends User {

	private static final long serialVersionUID = -382889313286829218L;

	public Customer(String name, String password) {
		super(name, password);
		stateAdmin = false;
		stateCustomer = true;
	}

	@Override
	public String toString() {
		return ("Customer\nname: " + name + "\npassword: " + password + "\n-------------------------");
	}

}
