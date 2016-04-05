package app.model;

import java.io.Serializable;

/**
 * Class used to represent an actual customer.
 * 
 * @author Bogdan
 *
 */
@SuppressWarnings("serial")
public class Customer implements Serializable {
	private String name;
	private int customerID;

	public Customer(String name, int customerID) {
		super();
		this.name = name;
		this.customerID = customerID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}
