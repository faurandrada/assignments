package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import models.comparators.OrderComparator;

public class OPDept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeSet<Order> orders;

	public OPDept() {
		orders = new TreeSet<Order>(new OrderComparator());
	}

	public TreeSet<Order> getOrders() {
		return orders;
	}

	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public int getSize() {
		return orders.size();
	}

	public Iterator<Order> getOrdersInAscendingOrder() {
		return orders.iterator();
	}
}
