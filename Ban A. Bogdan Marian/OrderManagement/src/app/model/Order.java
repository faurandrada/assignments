package app.model;

import java.io.Serializable;
/**
 * Represents an Order. For each we need a customer,a product and the quantity.
 * 
 * @author Bogdan
 *
 */
@SuppressWarnings("serial")
public class Order implements Serializable {
	private int orderID;
	private Customer customer;
	private Product product;
	private int quantity;

	/**
	 * @param customer
	 * @param product
	 * @param quantity
	 */
	public Order(int orderID, Customer customer, Product product, int quantity) {
		this.orderID = orderID;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID
	 *            the orderID to set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
