package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import models.OPDept;
import models.Order;
import models.Product;
import models.Warehouse;
import views.AdminFrame2;
import views.LoginFrame;
import views.UserFrame;

public class SerializableManager {
	public void serializaWarehouse(Warehouse wh) {
		try {
			FileOutputStream fileOut = new FileOutputStream("stock.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(wh);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in stock.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public Warehouse deserializeWharehouse() {
		Warehouse e = null;
		try {
			FileInputStream fileIn = new FileInputStream("stock.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Warehouse) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Warehouse class not found");
			c.printStackTrace();
		}
		Iterator<Product> it = e.getProductsInAscendingOrder();

		while (it.hasNext()) {
			Product p = it.next();
			Object[] row = { p.getID(), p.getName(), p.getQuantity() };
			if (LoginFrame.getUserButton().isSelected()) {
				((DefaultTableModel) UserFrame.getTable().getModel()).addRow(row);
			} else {
				((DefaultTableModel) AdminFrame2.getTable().getModel()).addRow(row);
			}
		}

		return e;

	}

	public void serializaOrders(OPDept ord) {
		try {
			FileOutputStream fileOut = new FileOutputStream("orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(ord);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in orders.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public OPDept deserializeOrders() {
		OPDept e = null;
		try {
			FileInputStream fileIn = new FileInputStream("orders.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (OPDept) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("OPDept class not found");
			c.printStackTrace();
		}
		Iterator<Order> it = e.getOrdersInAscendingOrder();

		while (it.hasNext()) {
			Order o = it.next();
			Object[] row = { o.getID() + 1, o.getCustomer().getName(), o.getProduct().getName(), o.getQuantity(),
					"Computed" };
			if (LoginFrame.getUserButton().isSelected()) {
				if (o.getCustomer().getName().equals(LoginFrame.getUserName())) {
					((DefaultTableModel) UserFrame.getOrderTable().getModel()).addRow(row);
				}
			} else {
				((DefaultTableModel) AdminFrame2.getTableOrder().getModel()).addRow(row);
			}
		}

		return e;

	}

}
