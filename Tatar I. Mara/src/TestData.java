import java.util.ArrayList;
import java.util.List;

public class TestData {

	protected List<Customer> users; 
	private String admin;
	private String adminPass;
	
	
	public TestData(){
		users=new ArrayList<Customer>();
		admin="admin";
		adminPass="adminPass";
	}
	public boolean checkAdmin(String username,String pass){
		if (username.compareTo(admin)==0)
			if (pass.compareTo(adminPass)==0)
				return true;
	    return false;
	}
	public void register(String username,String pass){
	//	Order newOrder=new Order();
		Customer newCustomer=new Customer(username,pass);
		
		users.add(newCustomer);

	}
  
	public boolean login(String username,String pass){
		for (Customer p:users){
			if ((p.getUsername().compareTo(username)==0)&&(p.getPass().compareTo(pass)==0))
				return true;
			}
	    return false;
	}
	public void print(){
		for (Customer p:users){
			System.out.print("username "+p.getUsername());
		}
	}
}
