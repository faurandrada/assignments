package models;

import java.io.Serializable;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private Product product;
	private Customer customer;
	private int quantity;
	private transient String status;

	public Order(int id, Product prod, Customer cust, int quant) {
		this.ID = id;
		this.product = prod;
		this.customer = cust;
		this.quantity = quant;
	}

	public int getID() {
		return ID;
	}

	public Product getProduct() {
		return product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
