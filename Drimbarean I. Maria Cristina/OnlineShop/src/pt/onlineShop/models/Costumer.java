package pt.onlineShop.models;

import java.io.Serializable;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class Costumer implements Serializable{
	private static final long serialVersionUID = 8180534432116034441L;
	private String username;
	private String passwoard;
	private TreeSet<Order> orders=new TreeSet<Order>();
	/**
	 * @return the order
	 */
	public TreeSet<Order> getOrders() {
		return orders;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username  the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
 * A costumer can search the product in the existing warehouse
 * @param warehouse
 * @param productName
 */
	public void searchProduct(Warehouse warehouse, String productName) {
		Product product = warehouse.search(productName);
		if (product != null)
			infoBox(product.getName()+"| price="+product.getPrice()+"| stock="+product.getStock(),"Search Error");
		else {
            infoBox("Product not found! Try again!","Search Error");
			Product filter = new Product(productName.substring(0, 1));
			for (Product p : warehouse.getWarehouse()) {
				Product aux=new Product(p.getName().substring(0, 1));
				if (aux.compareTo(filter)==0)
					System.out.println(p);
			}
		}
	}
	public void placeOrder(Warehouse warehouse,String productName,int amount){
		Product inStock=warehouse.search(productName);
		if (inStock==null)
		{ infoBox("Not an available product!","Processing order issue!");}
		else{
			if (amount>inStock.getStock())
				infoBox("Not so many products available!","Processing order issue!");
			else {
				warehouse.decrementStockOnProduct(productName, amount);
				orders.add(new Order(orders.size()+1,productName,amount));
				infoBox("Order placed succesfully!","Succes message");
			}
		}
	}
	/**
	 * @return the passwoard
	 */
	public String getPasswoard() {
		return passwoard;
	}

	/**
	 * @param passwoard the passwoard to set
	 */
	public void setPasswoard(String passwoard) {
		this.passwoard = passwoard;
	}
	/**
	 * used to tell the user what happend
	 * @param infoMessage
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
