import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

	public static void main(String[] args) {
		
		
		Logger frame = new Logger();
		
		
		OPDept x = new OPDept();
		Warehouse w = new Warehouse();
		Product p = new Product("DRESS","BLUE","S",1);
		Customer c=new Customer("mara","mara");
		Order o = new Order(p,c);
		w.addProduct(p);
		x.addOrder(o);
		try {

			FileOutputStream fileOut = new FileOutputStream
			("C:/Users/marat/Desktop/warehouse.ser");
			FileOutputStream file2Out =  new FileOutputStream
			("C:/Users/marat/Desktop/orders.ser");
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

		
	}


