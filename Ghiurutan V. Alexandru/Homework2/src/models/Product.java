package models;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {
	/**
	 * Used at serialization type saving.
	 */
	private static final long serialVersionUID = 1916860049980206685L;
	private String name;
	private String company;
	private double price;
	private int stock;

	public Product(String name, String company, double price, int stock) {
		this.name = name;
		this.company = company;
		setPrice(price);
		setStock(stock);
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public int compareTo(Product p2) {
		if (this.equals(p2)) {
			return 0;
		} else if (this.hashCode() > p2.hashCode()) {
			return 1;
		}
		return -1;
	}

}
