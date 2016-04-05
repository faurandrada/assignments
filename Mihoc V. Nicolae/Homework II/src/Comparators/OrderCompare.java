package Comparators;
import java.io.Serializable;
import java.util.Comparator;

import Design.Order;

public class OrderCompare implements Comparator<Order>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Order arg0, Order arg1) {
		int comp = 0;
		if (arg0.getID() < arg1.getID()) {
			comp = -1;
		} else if (arg0.getID() == arg1.getID()) {
			comp = 0;
		} else if (arg0.getID() > arg1.getID()) {
			comp = 1;
		}
		return comp;
		
	}

}
