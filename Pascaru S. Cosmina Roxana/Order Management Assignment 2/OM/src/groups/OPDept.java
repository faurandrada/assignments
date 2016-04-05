package groups;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.TreeSet;

import subjects.Order;

public class OPDept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3080050094681027519L;
	private TreeSet<Order> orders;

	public OPDept() {
		orders = new TreeSet<Order>();
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public void removeOrder(Order o) {
		orders.remove(o);
	}

	public void displayOrders() {
		for (Order o : orders) {
			o.displayOrder();
		}
	}

	public TreeSet<Order> getOrders() {
		return orders;
	}

	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("orders.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(orders);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data saved in orders.txt\n");

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("");
		}
	}

	public TreeSet<Order> deserialize() {
		TreeSet<Order> orders = null;
		try {
			FileInputStream fileIn = new FileInputStream("orders.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			orders = (TreeSet<Order>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Customer class not found");
			e.printStackTrace();
		}
		return orders;
	}
}
