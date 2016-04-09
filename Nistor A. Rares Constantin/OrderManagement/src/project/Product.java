package project;

import java.io.Serializable;

public class Product implements Serializable{
	private String name;
	private double price;
	private int stock;

	public Product(String name, double price, int stock) {

		this.price = price;
		this.name = name;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
}
