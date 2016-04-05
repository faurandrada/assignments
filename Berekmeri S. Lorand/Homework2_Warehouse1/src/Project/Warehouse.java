package Project;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

/* 
*   Class which contains a TreeSet of products and implements Serializable
*	for saving the products in a binary file.
*/
public class Warehouse implements Serializable {

	private static final long serialVersionUID = -6566493106154063473L;
	private TreeSet<Product> products = new TreeSet<Product>();

	public Warehouse() {
		products.add(new Product("a", 0, 0));

	}

	public Warehouse(TreeSet<Product> x) {
		products = new TreeSet<Product>(x);
	}

	public void setProducts(TreeSet<Product> x) {
		products = new TreeSet<Product>(x);
	}

	public void addProduct(Product p) {
		// System.out.println("Introducing new product");
		boolean ok = products.add(p);
		if (ok) {
			System.out.println("New product is introduced");

		} else {
			System.out.println("Product already exists");
			Object[] objArray = products.toArray();
			Product aux;
			int y = 0;
			for (Object obj : objArray) {
				aux = (Product) obj;
				if (aux.compareTo(p) == 0) {
					y = aux.getAmount();
				}
			}
			products.remove(p);
			p.setAmount(p.getAmount() + y);
			products.add(p);

		}
	}

	public Collection getProducts() {
		return products;

	}
}
