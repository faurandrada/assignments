package Users;

public class Admin extends User {

	private static final long serialVersionUID = 6654424125554665647L;

	public Admin(String name, String password) {
		super(name, password);
		stateAdmin = true;
		stateCustomer = false;
	}
	
	@Override
	public String toString(){
		return ("Admin\nname: " + name + "\npassword: " + password + "\n-------------------------");	
	}
}
