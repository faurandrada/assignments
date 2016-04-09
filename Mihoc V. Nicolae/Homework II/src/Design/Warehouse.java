package Design;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import Comparators.ProductCompare;

public class Warehouse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeSet<Product> items;

	public Warehouse() {
		items = new TreeSet<Product>(new ProductCompare());
	}

	public void addProduct(Product i) {
		items.add(i);
	}

	public void removeProduct(Product i) {
		items.remove(i);
	}
	
	public Iterator<Product> getProductsInOrder() {
		return items.iterator();
	}
	
}
