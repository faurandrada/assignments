package model;

import java.io.Serializable;

public class OrderedProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product;
	private int orderedQuantity;

	public OrderedProduct(Product product, int orderedQuantity) {

		this.product = product;
		this.orderedQuantity = orderedQuantity;

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

}
