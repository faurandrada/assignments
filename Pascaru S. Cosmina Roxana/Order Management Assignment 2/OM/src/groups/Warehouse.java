package groups;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeSet;

import subjects.ProductStock;

public class Warehouse implements Serializable {

	private static final long serialVersionUID = 2589713771123559474L;
	private TreeSet<ProductStock> products;

	public Warehouse() {
		products = new TreeSet<ProductStock>();
	}

	public void addProduct(ProductStock s) {
		products.add(s);
	}

	public void removeProduct(ProductStock s) {
		products.remove(s);
	}

	public TreeSet<ProductStock> getProducts() {
		return products;
	}

	public void replaceProduct(ProductStock p1, ProductStock p2) {
		removeProduct(p1);
		addProduct(p2);
	}

	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("warehouse.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(products);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data saved in warehouse.txt\n");

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("");
		}

	}

	@SuppressWarnings("unchecked")
	public TreeSet<ProductStock> deserialize() {
		TreeSet<ProductStock> products = null;
		try {
			FileInputStream fileIn = new FileInputStream("warehouse.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			products = (TreeSet<ProductStock>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Product class not found");
			e.printStackTrace();
		}
		return products;
	}

}
