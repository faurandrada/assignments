package controller;

import java.util.Iterator;
import java.util.TreeSet;
import models.Product;

public class Warehouse {

	public static Warehouse instance;
	private TreeSet<Product> warehouse;
	private InputOutput inOut;
	private Iterator<Product> iterator;
	private static final int MAXIMUM_STOCK = 1000;
	private static final int MINIMUM_STOCK = 100;

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

	public static int getMaximumStock() {
		return MAXIMUM_STOCK;
	}

	public static int getMinimumStock() {
		return MINIMUM_STOCK;
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
		int total = 0;
		iterator = warehouse.iterator();
		while (iterator.hasNext()) {
			total += iterator.next().getStock();
		}
		return total;
	}

	public Product[] getAllProducts() {
		int size = warehouse.size(), index = 0;
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
