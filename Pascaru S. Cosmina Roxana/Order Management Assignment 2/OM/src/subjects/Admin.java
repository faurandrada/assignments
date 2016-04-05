package subjects;

public class Admin extends Person{
	private String name;
	private String password;

	public Admin(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}


}
