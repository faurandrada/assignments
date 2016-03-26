package Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderId;
	private String orderDate;
	private String orderStatus;
	private Set<Product> orderedProducts;
	//private Customer customer;

	public Order(int orderId, String orderDate, String orderStatus) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		orderedProducts = new HashSet<Product>();;
		//this.customer = customer;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	//public Customer getCustomer() {
	//	return customer;
	//}

	//public void setCustomer(Customer customer) {
	//	this.customer = customer;
	//}

	public Set<Product> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(Set<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}
