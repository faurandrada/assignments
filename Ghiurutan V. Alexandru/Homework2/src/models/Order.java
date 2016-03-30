package models;

import models.Product;
import java.io.Serializable;

public class Order implements Serializable, Comparable<Order> {
	private static final long serialVersionUID = -2328908218015604885L;
	private Status commandStatus;
	private Product product;
	private Customer customer;
	private String name;
	private String customerName;

	public Order(String name, Product product, Customer customer) {
		this(name, product, customer.getLastName() + " " + customer.getFirstName(), Status.PENDING);
	}

	public Order(String name, Product product, String customerName, Status commandStatus) {
		this.name = name;
		this.product = product;
		this.customerName = customerName;
		setStatus(commandStatus);
	}

	public void setStatus(Status commandStatus) {
		this.commandStatus = commandStatus;
	}

	public Status getCommandStatus() {
		return this.commandStatus;
	}

	public Product getProcuct() {
		return product;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getName() {
		return name;
	}
	@Override
	public int compareTo(Order order) {
		if (this.equals(order)) {
			return 0;
		} else if (this.hashCode() > order.hashCode()) {
			return 1;
		} else {
			return -1;
		}
	}

}
