package comparators;

import java.io.Serializable;
import java.util.Comparator;

import model.OrderedProduct;
import model.Product;

public class OrderedProductComparator  implements Serializable, Comparator<OrderedProduct>{

	public int compare(OrderedProduct o1, OrderedProduct o2) {
	
			if( o1.getOrderedQuantity() == o2.getOrderedQuantity())
				return 0;
			else
			{
				if(o1.getOrderedQuantity() < o2.getOrderedQuantity())
					return -1;
				else
					return 1;
			}
			
		}
	

}
