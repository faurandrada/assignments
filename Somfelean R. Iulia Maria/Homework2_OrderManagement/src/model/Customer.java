package model;

import java.io.Serializable;
import java.util.TreeSet;

import comparators.OrderComparator;
import utilities.UserType;

/**
 * @author iulia
 *
 *         This class describes the attributes of a customer.
 * 
 *         The "isAdmin" field defines a flag which signals if the user/customer
 *         has administrative rights over the system.
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idCustomer;
	private UserType userType;
	private String name, username, password;
	private String cnp, email;
	private TreeSet<Order> orders = new TreeSet<Order>(new OrderComparator());

	public Customer(int idCustomer, UserType userType, String name, String username, String password, String cnp,
			String email) {

		setIdCustomer(idCustomer);
		setUserType(userType);
		setName(name);
		setUsername(username);
		setPassword(password);
		setCnp(cnp);
		setEmail(email);
		//setOrders(orders);

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public TreeSet<Order> getOrders() {
		return orders;
	}

	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}
}
