package Stock;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {

	private static final long serialVersionUID = 137756559387840888L;

	private String ID;
	private String type;
	private String make;
	private String model;
	private double price;
	private int quantity;

	public Product(String ID, String type, String make, String model, double price, int quantity) {
		this.ID = ID;
		this.type = type;
		this.make = make;
		this.model = model;
		this.price = price;
		this.quantity = quantity;
	}

	public String getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return ("\nProduct" + "\nID: " + ID + "\nType: " + type + "\nMake: " + make + "\nModel: " + model + 
				"\nPrice: " + price + "\nQuantity: " + quantity + "\n----------------------------");
	}

	@Override
	public int compareTo(Product prod) {
		return this.ID.compareTo(prod.getID());
	}
}
