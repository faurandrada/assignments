package shop;

import java.io.Serializable;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1248299578882935954L;
	private int orderNumber;
	private String orderDate;
	private String user;
	private int quantity;
	private String product;
	private int price;
	
	public Order leftChild;
	public Order rightChild;

	public Order(int orderNumber,String orderDate2, String user, int quantity, String product2,int price) {
		setOrderNumber(orderNumber);
		setDate(orderDate2);
		setUser(user);
		setQuantity(quantity);
		setProduct(product2);
		setPrice(price);
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setOrderNumber(int nr) {
		this.orderNumber = nr;
	}

	public void setDate(String orderDate2) {
		this.orderDate = orderDate2;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setQuantity(int q) {
		this.quantity = q;
	}

	public void setProduct(String product2) {
		this.product = product2;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public String getDate() {
		return orderDate;
	}

	public String getUser() {
		return user;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getProduct() {
		return product;
	}
	
	public int getPrice(){
		return price;
	}
}
