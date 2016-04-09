package Project;

import java.io.Serializable;

/*
* Class which containt all the details abou a product (entity of the product)
* It implements the Serializable interface and the Comparable interface 
* overriding the compareTo method
*/
public class Product implements Comparable, Serializable {

	private int id = 0;
	private String name = "";
	private int price = 0;
	private int amount = 0;

	public Product() {

	}

	public Product(String s, int b, int c) {
		id = 0;
		name = s;
		price = b;
		amount = c;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int x) {
		amount = x;
	}

	@Override
	public int compareTo(Object arg0) {
		String s = this.name;
		Product x = null;
		x = (Product) arg0;
		String s2 = x.getName();
		return s.compareTo(s2);
	}

	public String toString() {
		String ret = "";
		ret = name + " price " + price + " amount " + amount + "\n";
		return ret;
	}
}
