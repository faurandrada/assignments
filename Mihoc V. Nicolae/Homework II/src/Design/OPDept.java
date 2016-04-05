package Design;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import Comparators.OrderCompare;

public class OPDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Order> orders;
	
	public OPDept()
	{
		orders= new TreeSet<Order>(new OrderCompare());
	}
	
	public void addOrder(Order o){
		orders.add(o);
	}
	
	public Iterator<Order> getOrdersAscending() {
		return orders.iterator();
	}
}
