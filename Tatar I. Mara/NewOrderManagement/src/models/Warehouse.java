package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import comparators.IDComparator;

public class Warehouse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3311117613467207081L;
	private TreeSet<Product> products;

	public Warehouse() {
		products = new TreeSet<Product>(new IDComparator());
	}

	public TreeSet<Product> getProducts() {
		return products;
	}
	
	public void add(Product product) {
		products.add(product);

	}

	public void remove(Product product) {
		products.remove(product);
	}

	public double getTotals() {
		double total = 0;
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product p = it.next();
			total += p.getPrice();
		}
		return total;
	}

	public void processOrder(List<Product> shoppingCart) {
		for (Product p : shoppingCart) {
			if (p.getStock() >= 1)
				p.setStock(p.getStock() - 1);
			if (p.getStock() == 0)
				remove(p);
		}
	}

	public Product findProduct(int ID) {
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			if (product.getID() == ID)
				return product;
		}
		return null;

	}

	public void print() {
		for (Product p : products) {
			System.out.println(p.getItem()+" " + p.getSize()+" "+p.getColor()+" "+ p.getStock() + "\n");
		}
	}

	public Product findProductAfterItem(String item,String size,String color) {
		for (Product p : products) {
			if ((item.compareTo(p.getItem())==0)&&(size.compareTo(p.getSize())==0)&&(color.compareTo(p.getColor())==0)){
				return p;
			}
		}
		return null;
	}
}
