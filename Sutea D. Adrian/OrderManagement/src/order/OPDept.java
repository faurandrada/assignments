package order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gui.Gui;

public class OPDept {
	private Customer customer = new Customer();
	private Warehouse wh = new Warehouse();
	private Product prod = new Product();
	private int quantity;
	File file = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\commands.txt");
	
	public void placeCommand(Order ord){
		customer.setName(Gui.getUsername());
		prod=ord.getProduct();
		quantity=ord.getQuantity();
		wh.updateQuantity(prod.getName(), -quantity);
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(customer.getName()+ " ");
			fw.write(prod.getName() + " ");
			fw.write(quantity + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		
	}
	
	public Product checkForProduct(String s){
		return wh.isProduct(s);
	}
	
	public boolean checkStock(Product p, int quantity){
		if(p.getQuantity()<quantity)
			return false;
		return true;
	}
}
