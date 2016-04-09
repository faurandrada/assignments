package model;

import java.io.Serializable;

import utilities.Constants;
import utilities.ProductStatus;

/**
 * @author iulia
 *
 *         This class defines a product along it's characteristics, such as
 *         title, author, etc.
 * 
 *         Has as fields the quantity and price/unit corresponding to the
 *         product.
 */
public class Product implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idProduct;
	private String title;
	private String author;
	private String publisher;
	private double price;
	private int quantity;
	private int maxQuantity; // the maximum quantity that can exist in stock
	private ProductStatus status;

	public Product(int idProduct, String title, String author, String publisher, double price, int quantity) {

		setIdProduct(idProduct);
		setTitle(title);
		setAuthor(author);
		setPublisher(publisher);
		setPrice(price);
		setQuantity(quantity);
		this.maxQuantity = Constants.MAX_QUANTITY;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price <= 0)
			this.price = 0;
		else
			this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		if (quantity <= 0) {
			this.quantity = 0;
			this.setStatus(ProductStatus.UNAVAILABLE);
		}

		else {
			if (quantity <= Constants.MAX_LIMITED_QUANTITY) {
				this.setStatus(ProductStatus.LIMITED);
			} else
				this.setStatus(ProductStatus.AVAILABLE);
			this.quantity = quantity;

		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public void printProduct() {
		System.out.println("Id: " + this.idProduct);
		System.out.println("Title: " + this.title);
		System.out.println("Author: " + this.author);
		System.out.println("Publisher: " + this.publisher);
		System.out.println("Price: " + this.price);
		System.out.println("Quantity: " + this.quantity);
		System.out.println("Status: " + this.status);
		System.out.println();

	}

}
