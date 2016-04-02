package Design;

public class Administrator {
	private String user;
	private String pass;
	
	public Administrator(String user, String pass){
		this.user=user;
		this.pass=pass;
	}
	
	public String getUser(){
		return user;
	}
	
	public String getPass(){
		return pass;
	}
}
