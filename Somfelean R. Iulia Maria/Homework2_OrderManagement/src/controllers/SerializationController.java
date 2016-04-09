package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import model.OPDept;
import model.Product;
import model.Warehouse;

public class SerializationController {

	public void serializeWarehouse(Warehouse warehouse) {
		try {
			FileOutputStream fileOut = new FileOutputStream("warehouse.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(warehouse);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in warehouse.ser");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Warehouse deserializeWarehouse() {
		Warehouse w = null;
		try {
			FileInputStream fileIn = new FileInputStream("warehouse.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			w = (Warehouse) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Warehouse class not found!");
			System.out.println(e.getMessage());

		}
		
		return w;

	}

	public void serializeOrders(OPDept orders) {
		try {
			FileOutputStream fileOut = new FileOutputStream("orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(orders);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in orders.ser");
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public OPDept deserializeOrders() {
		OPDept o = null;
		try {
			FileInputStream fileIn = new FileInputStream("orders.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = (OPDept) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());

		} catch (ClassNotFoundException e) {
			System.out.println("OPDept class not found");
			System.out.println(e.getMessage());

		}
	

		return o;

	}
}
