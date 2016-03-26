package Serializing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

import Model.Product;
import Model.Warehouse;

public class ReadFileS {

	public Warehouse ReadFile() {

		Warehouse e = null;
		try {
			FileInputStream fileIn = new FileInputStream(
					"C:/Users/Dariana Lupea/Desktop/DariJavaSem2/OrderManagement/SerFile/dd.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Warehouse) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Product class not found");
			c.printStackTrace();
			return null;
		}

		
		return e;
	}

}
