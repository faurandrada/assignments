package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import comparators.ProductComparator;

public class Warehouse implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeSet<Product> products;

	// creates an empty warehouse
	public Warehouse() {

		this.products = new TreeSet<Product>(new ProductComparator());
	}

	// creates a warehouse with given products
	public Warehouse(TreeSet<Product> products) {

		setProducts(products);
	}

	public void addProduct(Product p) {

		this.products.add(p);
	}

	public void removeProduct(int idProduct) {

		Iterator<Product> itr = this.products.iterator();

		while (itr.hasNext()) {

			Product p = itr.next();

			if (p.getIdProduct() == idProduct)
				itr.remove();

		}
	}

	public void modifyProduct(int idProduct, int index, String modified) {

		System.out.println("Modified:" + idProduct + " " + index + " " + modified);

		Iterator<Product> itr = this.products.iterator();

		while (itr.hasNext()) {

			Product p = itr.next();

			if (p.getIdProduct() == idProduct) {

				switch (index) {

				case 1:
					p.setTitle(modified);
					break;
				case 2:
					p.setAuthor(modified);
					break;
				case 3:
					p.setPublisher(modified);
					break;
				case 4:
					p.setPrice(Double.parseDouble(modified));
					break;
				case 5:
					p.setQuantity(Integer.parseInt(modified));
					break;
				default:
					break;

				}

			}
		}
	}

	public void view() {

		Iterator<Product> itr = this.products.iterator();

		while (itr.hasNext()) {

			Product p = itr.next();
			p.printProduct();

		}

	}

	public TreeSet<Product> getProducts() {

		return products;

	}

	public void setProducts(TreeSet<Product> products) {

		this.products = products;

	}

	public Product getProduct(int idProduct) {

		Iterator<Product> itr = this.products.iterator();

		while (itr.hasNext()) {

			Product p = itr.next();
			if (p.getIdProduct() == idProduct) {

				return p;
			}

		}
		return null;
	}

	public TreeSet<Product> searchWarehouse(String search) {

		TreeSet<Product> searchResults = new TreeSet<Product>(new ProductComparator());

		Iterator<Product> itr = this.products.iterator();

		while (itr.hasNext()) {

			Product p = itr.next();
			if (p.getTitle().equals(search) || p.getAuthor().equals(search) || p.getPublisher().equals(search)) {
				searchResults.add(p);
			}
		}

		return searchResults;
	}
}
