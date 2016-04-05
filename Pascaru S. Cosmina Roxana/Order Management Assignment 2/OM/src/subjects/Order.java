package subjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Order implements Serializable,Comparable<Order>{

	private int ID;
	private Customer customer;
	private TreeSet<Product> products;

	public Order(int ID, Customer customer) {
		this.ID = ID;
		this.customer = customer;
		products = new TreeSet<Product>();
	}

	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void removeProduct(Product p){
		products.remove(p);
	}

	public TreeSet<Product> getProductList() {
		return products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getID() {
		return ID;
	}

	public void displayOrder() {
		for (Product p : products) {
			p.displayProduct();
			System.out.println("");
		}
	}

	@Override
	public int compareTo(Order o) {
		if(this.ID < o.ID){
			return -1;
		}else 
			return 1;
	}

}
