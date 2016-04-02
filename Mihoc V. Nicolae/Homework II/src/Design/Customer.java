package Design;

public class Customer {
	private String username;
	private String pass;
	
	public Customer(String username, String pass)
	{
		this.username=username;
		this.pass=pass;
	}
	
	public String getUsername(){
		return username;
	}
	public String getPass(){
		return pass;
	}
}
