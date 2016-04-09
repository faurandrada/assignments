package controller;
/**
 * The class that helps serialization
 */
import java.io.*;
import java.util.TreeSet;

import models.Order;
import models.Product;

public class InputOutput {
	private Product[] products;
	private FileOutputStream fileOut;
	private ObjectOutputStream out;
	private FileInputStream fileIn;
	private ObjectInputStream in;
	private Warehouse warehouse;
	private OPDept orderDept;
	private Order[] orders;

	public InputOutput() {
	}

	public void serializeProducts() {
		try {
			warehouse = Warehouse.getInstance();
			fileOut = new FileOutputStream("D:\\Java workspace\\Homework2\\Products.ser");
			out = new ObjectOutputStream(fileOut);
			products = warehouse.getAllProducts();
			for (Product product : products) {
				out.writeObject(product);
			}
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public TreeSet<Product> deserializeProducts() {
		TreeSet<Product> readProducts = new TreeSet<Product>();
		Product product;
		try {
			fileIn = new FileInputStream("D:\\Java workspace\\Homework2\\Products.ser");
			in = new ObjectInputStream(fileIn);
			while (true) {
				product = (Product) in.readObject();
				if (product == null) {
					break;
				}
				readProducts.add(product);
			}
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		return readProducts;
	}

	public void serializeOrders() {
		try {
			orderDept = OPDept.getInstance();
			fileOut = new FileOutputStream("D:\\Java workspace\\Homework2\\Orders.ser");
			out = new ObjectOutputStream(fileOut);
			orders = orderDept.getAllOrders();
			for (Order order : orders) {
				out.writeObject(order);
			}
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public TreeSet<Order> deserializeOrders() {
		TreeSet<Order> readOrders = new TreeSet<Order>();
		Order order;
		try {
			fileIn = new FileInputStream("D:\\Java workspace\\Homework2\\Orders.ser");
			in = new ObjectInputStream(fileIn);
			while (true) {
				order = (Order) in.readObject();
				if (order == null) {
					break;
				}
				readOrders.add(order);
			}
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		return readOrders;
	}
}
