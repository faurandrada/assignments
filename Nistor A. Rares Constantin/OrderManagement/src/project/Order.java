package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

public class Order implements Serializable{

	
	private int totalPrice;
	private ArrayList<Product> products = new ArrayList<Product>();
	private final int orderID;
	
	private Customer cust = new Customer();
	
	public Order(int orderID){
		this.orderID=orderID;
		
	}
	
	public void addProduct(Product p) {
		int ok = 0;
		for (int i = 0; i < products.size(); i++)
			if (products.get(i).getName().equals(p.getName())) {
				products.get(i).setStock(products.get(i).getStock() + p.getStock());
				ok = 1;
			}
		if (ok == 0)
			products.add(p);
		totalPrice += p.getPrice() * p.getStock();
	}
	
	public void removeProduct(Product p,int i){
		products.remove(i);
		totalPrice-=p.getPrice()*p.getStock();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public int getSize() {
		return products.size();
	}

	public int getOrderID() {
		return orderID;
	}

	public Customer getCust() {
		return cust;
	}

}
