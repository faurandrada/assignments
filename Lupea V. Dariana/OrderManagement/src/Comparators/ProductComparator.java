package Comparators;

import java.io.Serializable;
import java.util.Comparator;

import Model.Product;

public class ProductComparator implements Comparator<Product>, Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Product p1, Product p2) {
		
		return p1.getId().compareTo(p2.getId());
	}

}
