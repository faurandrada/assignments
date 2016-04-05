package Project;

import java.io.Serializable;
/**
 * Class which deals with the details about a client
 * The client has a name, address and id.
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String name = "";
	private String address = "";
	/**
	 * constructor - id, name, address
	 */
	public Customer(int i, String s1, String s2) {
		id = i;
		name = s1;
		address = s2;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getId() {
		return id;
	}

}
