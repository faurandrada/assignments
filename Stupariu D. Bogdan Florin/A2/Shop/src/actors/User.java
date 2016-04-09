package actors;

public class User {
	
	private String name;
	private String password;
	private String country;
	private String address;
	private String age;
	private int privillege;

	public User(String name, String password, String country, String address, String age,int privillege) {
		// TODO Auto-generated constructor stub
		setName(name);
		setPassword(password);
		setCountry(country);
		setAddress(address);
		setAge(age);
		setPrivillege(privillege);
	}

	private void setName(String name2) {
		// TODO Auto-generated method stub
		this.name = name2;
	}
	
	String getName(){
		return name;
	}

	private void setPassword(String password2) {
		// TODO Auto-generated method stub
		this.password = password2;
	}
	
	String getPassword(){
		return password;
	}

	private void setCountry(String country2) {
		// TODO Auto-generated method stub
		this.country = country2;
	}
	
	String getCountry(){
		return country;
	}

	private void setAddress(String address2) {
		// TODO Auto-generated method stub
		this.address = address2;
	}
	
	String getAddress(){
		return address;
	}

	private void setAge(String age2) {
		// TODO Auto-generated method stub
		this.age = age2;
	}
	
	String getAge(){
		return age;
	}
	
	private void setPrivillege(int privillege)
	{
		this.privillege = privillege;
	}

	int getPrivillege() {
		return privillege;
	}

}
