package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.*;

public class Warehouse {
	private TreeMap<String, Product> wareHouse = new TreeMap<String, Product>();

	public Warehouse(){
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("data.bin"));
			while(true){
			Product p1=(Product) is.readObject();
			addProduct(p1);
			System.out.println(p1.getName()+" "+p1.getPrice()+" "+p1.getStock());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addProduct(Product p) {
		if (checkProductExists(p.getName()) == false) {
			wareHouse.put(p.getName(), p);
		} else {
			wareHouse.get(p.getName()).setStock(wareHouse.get(p.getName()).getStock() + p.getStock());
		}

	}
	
	
	public void addProductAdmin(Product p) {
		if (checkProductExists(p.getName()) == false) {
			wareHouse.put(p.getName(), p);
		} else {
			wareHouse.remove(p.getName());
			wareHouse.put(p.getName(), p);
		}
	}

	public TreeMap<String, Product> getProducts() {
		return wareHouse;
	}

	public void removeProduct(Product p) {
		wareHouse.remove(p.getName());
	}

	public boolean checkProductExists(String s) {
		if (wareHouse.get(s) == null)
			return false;
		return true;
	}

	
	public Product getProd(String s){
		return wareHouse.get(s);
	}
}
