package models.comparators;

import java.io.Serializable;
import java.util.Comparator;

import models.Order;

public class OrderComparator implements Comparator<Order>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Order order1, Order order2) {
		int comp = 0;
		if (order1.getID() < order2.getID()) {
			comp = -1;
		} else if (order1.getID() == order2.getID()) {
			comp = 0;
		} else if (order1.getID() > order2.getID()) {
			comp = 1;
		}
		return comp;
	}

}
