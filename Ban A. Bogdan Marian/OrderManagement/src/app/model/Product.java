package app.model;

import java.io.Serializable;

/**
 * Represents a Product. For each we need a name, description and initial stock.
 * 
 * @author Bogdan
 *
 */
@SuppressWarnings("serial")
public class Product implements Serializable {
	private String name;
	private String description;
	private int stock;
	private int productID;

	public Product(int productID, String name, String description, int stock) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.productID = productID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID
	 *            the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

}
