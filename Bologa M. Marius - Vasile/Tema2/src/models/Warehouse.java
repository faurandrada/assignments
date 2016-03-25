package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import models.comparators.ProductComparator;

public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
	private TreeSet<Product> stock;

	public Warehouse() {
		stock = new TreeSet<Product>(new ProductComparator());
	}

	public TreeSet<Product> getStock() {
		return stock;
	}

	public void setStock(TreeSet<Product> stock) {
		this.stock = stock;
	}

	public void addProduct(Product product) {
		stock.add(product);
	}

	public void removeProduct(Product product) {
		stock.remove(product);
	}

	public int getSize() {
		return stock.size();
	}

	public Iterator<Product> getProductsInAscendingOrder() {
		return stock.iterator();
	}
}
