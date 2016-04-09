package pt.onlineShop.models;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class Product implements Comparable, Serializable {
	private static final long serialVersionUID = -1138094293613352489L;
	private String name;
	private int stock;
	private int price;

	/**
	 * Constructor only with name to be used when new elements interact with the
	 * warehouse For objects not stored
	 * 
	 * @param name
	 */
	public Product(String name) {
		this.name = name;
		this.stock = 1;
	}

	/**
	 * A real needed object constuctor
	 * 
	 * @param name
	 * @param stock
	 */
	public Product(String name, int stock, int price) {
		this.stock = stock;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + " stock=" + stock + " price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void incrementStock(int value) {
		this.stock = this.stock + value;
	}

	public void decrementStock(int value) {
		this.stock = this.stock - value;
	}

	/**
	 * to see if to products are equal
	 */
	@Override
	public int compareTo(Object arg0) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		Product product = (Product) arg0;
		if ((this.name).equals(product.name))
			return EQUAL;
		if ((this.name).compareTo(product.name) == -1)
			return BEFORE;
		return AFTER;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * to put it in a sorted tree set
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
