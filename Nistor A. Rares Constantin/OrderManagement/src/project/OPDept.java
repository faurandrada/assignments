package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class OPDept {
	private TreeMap<Integer, Order> orders = new TreeMap<Integer, Order>();

	Warehouse war;
	private int key=0;
	
	public OPDept(Warehouse war) {
		this.war = war;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("data1.bin"));
			while(true){
			Order o1=(Order) is.readObject();
			key++;
			addOrder(o1);
			System.out.println(o1.getOrderID()+" "+o1.getTotalPrice());
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

	public void addOrder(Order ord) {
		orders.put(ord.getOrderID(), ord);
	}
	
	public boolean checkProductStock(Product p) {
		if (war.getProducts().get(p.getName()).getStock() - p.getStock() >= 0) {
			int newStock = war.getProducts().get(p.getName()).getStock() - p.getStock();
			war.getProducts().get(p.getName()).setStock(newStock);
			return true;
		}
		return false;
	}

	public boolean checkProductExists(String s) {
		if (war.getProducts().get(s) == null)
			return false;
		return true;
	}

	public TreeMap<Integer, Order> getOrders() {
		return orders;
	}

	public int getLastKey(){
		return key;
	}
	
	
	public static void main(String[] args) {

		String fileName = "data.bin";
		Product pro1 = new Product("Apples", 2, 30);
		Product pro2 = new Product("Bananas", 1, 24);
		Product pro3 = new Product("Kiwi", 1.5, 20);
		Product pro4 = new Product("Strawberrys", 1, 20);
		Order ord = new Order(1);
		ord.addProduct(pro1);
		ord.addProduct(pro2);
		Order ord1 = new Order(2);
		ord1.addProduct(pro2);
		ord1.addProduct(pro3);
		Order ord2 = new Order(3);
		ord2.addProduct(pro3);
		ord2.addProduct(pro4);
		
		
		Warehouse war = new Warehouse();
		Set<java.util.Map.Entry<String, Product>> set1 = war.getProducts().entrySet();
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.bin"));
			os.writeObject(pro1);
			os.writeObject(pro2);
			os.writeObject(pro3);
			os.writeObject(pro4);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data1.bin"));
			os.writeObject(ord);
			os.writeObject(ord1);
			os.writeObject(ord2);
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OPDept dept=new OPDept(war);
	}
}