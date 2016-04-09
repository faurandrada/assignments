package models;

import java.util.ArrayList;

public class Order {
	
	private int ID;
	private Status status;
	private ArrayList<OrderItem> orderItems;
	
	public Order(int ID, Status status, ArrayList<OrderItem> orderItems){
		this.ID = ID;
		this.status = status;
		this.orderItems = orderItems;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
