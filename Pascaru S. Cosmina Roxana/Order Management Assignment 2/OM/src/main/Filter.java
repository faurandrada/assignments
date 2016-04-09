package main;

import java.util.TreeSet;

import javax.swing.JOptionPane;

import groups.Warehouse;
import subjects.Order;
import subjects.Product;
import subjects.ProductStock;

public class Filter {
	private ProductStock productStock;
	private Product product;
	private boolean found;

	public boolean filterProductStock(Warehouse warehouse, String name) {
		found = false;
		for (ProductStock p : warehouse.deserialize()) {
			if (p.getProductName().equals(name)) {
				productStock = p;
				found = true;
			}
		}
		return found;
	}

	public boolean filterOrder(Order newOrder, String productName) {
		found = false;
		for (Product p : newOrder.getProductList()) {
			if (p.getName().equals(productName)) {
				product = p;
				found = true;
			}
		}
		return found;
	}

	public ProductStock getFoundProductStock() {
		return productStock;
	}

	public Product getFoundProduct() {
		return product;
	}

}
