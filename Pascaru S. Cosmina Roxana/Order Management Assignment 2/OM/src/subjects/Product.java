package subjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Product implements Serializable,Comparable<Product>{

	private String name;
	private int price;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void displayProduct() {
		System.out.printf("%s %d \n", name, price);
	}

	@Override
	public int compareTo(Product p) {
		return name.compareTo(p.getName());
	}

}
