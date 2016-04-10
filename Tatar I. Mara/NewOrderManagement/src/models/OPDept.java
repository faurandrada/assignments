package models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import comparators.IDComparator;

public class OPDept implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -3366822520497300596L;
private TreeSet<Order> orders;
	public OPDept(){
		orders  = new TreeSet<Order>(new IDComparator());
	}
	public TreeSet<Order> getOrders() {
		return orders;
	}

	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}
	public void addOrder(Order order){
		orders.add(order);
	}
	public void remove(Order order){
		orders.remove(order);
	}
	public void print(){
		for(Order order: orders){
			System.out.println(order.getCustomer().getName() + " " +order.toString()+"\n");
		}
	}
	public Order findProduct(int ID) {
	    Iterator<Order> it=orders.iterator();
	    while(it.hasNext()){
	    	Order order=it.next();
	    	if (order.getID()==ID)
	    		return order;
	    }
	    return null;
	}
}
