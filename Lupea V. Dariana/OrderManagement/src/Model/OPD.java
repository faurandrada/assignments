package Model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import Comparators.OrderComparator;

public class OPD implements Serializable{

	private static final long serialVersionUID = 1L;
	private Set<Order> orders = new TreeSet<Order>(new OrderComparator());;
	
	public OPD(Set<Order> orders){
		this.orders = orders;
	}
	
	public void addOrder(Order ord) {
		orders.add(ord);
	}

	public void removeOrder(Order ord) {
		orders.remove(ord);
	}
	
	public boolean existsOrder(Order ord){
		return orders.contains(ord);
	}

}
