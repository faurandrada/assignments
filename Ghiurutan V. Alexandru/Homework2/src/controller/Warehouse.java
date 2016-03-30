package controller;

import java.util.Iterator;
import java.util.TreeSet;
import models.Product;

public class Warehouse {

	public static Warehouse instance;
	private TreeSet<Product> warehouse;
	private InputOutput inOut;
	private Iterator<Product> iterator;

	private Warehouse() {
		inOut = new InputOutput();
		warehouse = inOut.deserializeProducts();
	}

	public static Warehouse getInstance() {
		if (instance == null) {
			instance = new Warehouse();
		}
		return instance;
	}

	public void addProduct(Product product) {
		if (warehouse.contains(product)) {
			iterator = warehouse.iterator();
			while (iterator.hasNext()) {
				Product p = iterator.next();
				if (product.equals(p)) {
					p.setStock(p.getStock() + product.getStock());
					break;
				}
			}
		} else {
			warehouse.add(product);
		}
		inOut.serializeProducts();
	}

	public void removeProduct(Product product) {
		if (warehouse.contains(product)) {
			warehouse.remove(product);
			inOut.serializeProducts();
		}
	}

	public int getTotalNumberOfProducts() {
		return warehouse.size();
	}

	public Product[] getAllProducts() {
		int size = this.getTotalNumberOfProducts(), index = 0;
		if (size != 0) {
			Product[] products = new Product[size];
			iterator = warehouse.iterator();
			while (iterator.hasNext()) {
				Product product = iterator.next();
				products[index++] = product;
			}
			return products;
		}
		return null;
	}

	public int getProductStock(Product product) {
		if (warehouse.contains(product)) {
			iterator = warehouse.iterator();
			while (iterator.hasNext()) {
				Product p = iterator.next();
				if (product.equals(p)) {
					return p.getStock();
				}
			}
		}
		return 0;
	}

	public void sellProduct(Product product) {
		if (warehouse.contains(product)) {
			iterator = warehouse.iterator();
			while (iterator.hasNext()) {
				Product p = iterator.next();
				if (product.equals(p)) {
					p.setStock(p.getStock() - product.getStock());
					break;
				}
			}
			inOut.serializeProducts();
		}
	}

	public boolean searchForProduct(Product product) {
		return warehouse.contains(product);
	}
}
