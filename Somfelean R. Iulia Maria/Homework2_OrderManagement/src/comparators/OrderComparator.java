package comparators;

import java.io.Serializable;
import java.util.Comparator;

import model.Order;

public class OrderComparator implements Serializable, Comparator<Order> {

	public int compare(Order o1, Order o2) {

		if (o1.getId() == o2.getId())
			return 0;
		else {
			if (o1.getId() < o2.getId())
				return -1;
			else
				return 1;
		}

	}

}
