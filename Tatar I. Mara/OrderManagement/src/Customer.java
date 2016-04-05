import java.util.Random;

public class Customer {
	private int ID;
	private String username;
	private String pass;
	
	public Customer(String username,String pass){
		
		this.username=username;
		this.pass=pass;
		Random randomGenerator=new Random();
		ID=randomGenerator.nextInt(10000);
	}
	public String getUsername(){
		return username;
	}
	public String getPass(){
		return pass;
	}

	public int getID(){
		return ID;
	}
}
