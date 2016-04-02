package Read_Write;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import Design.OPDept;
import Design.Order;
import Design.Product;
import Design.Warehouse;
import Frames.AdminPanel;
import Frames.LogInFrame;
import Frames.UserPanel;

public class InputOutput {
	
	public void serWarehouse(Warehouse w) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/warehouse.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(w);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Warehouse deserWarehouse() {
		Warehouse x = null;
		try {
			FileInputStream fileIN = new FileInputStream(
					"D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/warehouse.ser");
			ObjectInputStream in = new ObjectInputStream(fileIN);
			x = (Warehouse) in.readObject();
			in.close();
			fileIN.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		Iterator<Product> it = x.getProductsInOrder();
		while (it.hasNext()) {
			Product p = it.next();
			if (LogInFrame.getTake().isSelected()) {
				Object[] row1 = { p.getName(), p.getQuantity(), p.getPrice() };
				((DefaultTableModel) UserPanel.getModel()).addRow(row1);
			} else {
				Object[] row = { p.getID(), p.getName(), p.getQuantity(), p.getPrice() };
				((DefaultTableModel) AdminPanel.getModel()).addRow(row);
			}
		}
		return x;
	}

	public void serOrders(OPDept order) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(order);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OPDept deserOrders() {
		OPDept v = null;
		try {
			FileInputStream fileIn = new FileInputStream(
					"D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/orders.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			v = (OPDept) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		Iterator<Order> ot = v.getOrdersAscending();
		// Iterator<Product> it = k.getProductsInOrder();
		while (ot.hasNext()) {
			Order o = ot.next();
			// {
			// p=it.next();
			Object[] row = { o.getID(), o.getName(), o.getQuantity(), o.getPrice() };
			((DefaultTableModel) UserPanel.getModel2()).addRow(row);

		}
		return v;
	}
}
