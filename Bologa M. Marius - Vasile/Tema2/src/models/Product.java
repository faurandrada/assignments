package models;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private int quantity;

	public Product(int id, String name, int quantity) {
		this.ID = id;
		this.name = name;
		this.quantity = quantity;
	}

	public int getID() {
		return ID;
	}


	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
