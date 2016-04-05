package groups;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeSet;

import subjects.Customer;

public class ShopCustomers implements Serializable {
	private TreeSet<Customer> customers;

	public ShopCustomers() {
		customers = new TreeSet<Customer>();
	}

	public void addCustomer(Customer c) {
		customers.add(c);
	}

	public void removeCustomer(Customer c) {
		customers.remove(c);
	}

	public TreeSet<Customer> getCustomers() {
		return customers;
	}

	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("customers.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(customers);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data saved in customers.txt\n");

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("");
		}
	}

	public TreeSet<Customer> deserialize() {
		TreeSet<Customer> customers = null;
		try {
			FileInputStream fileIn = new FileInputStream("customers.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			customers = (TreeSet<Customer>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Product class not found");
			e.printStackTrace();
		}
		return customers;
	}

}
