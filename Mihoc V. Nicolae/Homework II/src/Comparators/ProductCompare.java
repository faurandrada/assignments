package Comparators;
import java.io.Serializable;
import java.util.Comparator;

import Design.Product;

public class ProductCompare implements Comparator<Product>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Product product1, Product product2) {
		int comp = 0;
		if (product1.getID() < product2.getID()) {
			comp = -1;
		} else if (product1.getID() == product2.getID()) {
			comp = 0;
		} else if (product1.getID() > product2.getID()) {
			comp = 1;
		}
		return comp;
	}

}
