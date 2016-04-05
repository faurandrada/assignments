package Design;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import Frames.LogInFrame;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new LogInFrame();
/*
		OPDept x = new OPDept();
		Warehouse w = new Warehouse();
		Product p = new Product(1, "cartof", 10, 10);
		Order o = new Order(1, "cartof", 5,10);
		w.addProduct(p);
		x.addOrder(o);
		try {

			FileOutputStream fileOut = new FileOutputStream
			("D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/warehouse.ser");
			FileOutputStream file2Out =  new FileOutputStream
			("D:/Facultate/Anul II/Sem II/Programming Techniques/Workspace/Management/orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			ObjectOutputStream out1 = new ObjectOutputStream(file2Out);
			out.writeObject(w);
			out1.writeObject(x);
			out.close();
			fileOut.close();
			file2Out.close();
			System.out.printf("Serialized data is saved in warehouse.ser");
			System.out.printf("Serialized data is saved in orders.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	*/
	}

}
