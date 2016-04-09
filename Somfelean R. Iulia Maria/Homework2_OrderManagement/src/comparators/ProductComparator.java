package comparators;

import java.io.Serializable;
import java.util.Comparator;

import model.Product;

public class ProductComparator implements  Serializable, Comparator<Product> {

	public int compare(Product arg0, Product arg1) {
		
		
		if( arg0.getIdProduct() == arg1.getIdProduct())
			return 0;
		else
		{
			if(arg0.getIdProduct() < arg1.getIdProduct())
				return -1;
			else
				return 1;
		}
		
	}

	
}
