package pt.onlineShop.models;

import java.io.Serializable;

/**
 * A order should be placed by a Costumer and contains a list of products
 * Have a unique id
 * @author Chiti
 *
 */
@SuppressWarnings("rawtypes")
public class Order implements Comparable,Serializable{
private static final long serialVersionUID = -4330499231498770279L;
private int id;
private String nameOfProduct;
private int amount;

public Order(int id,String nameOfProduct,int amount){
	this.id=id;
	this.nameOfProduct=nameOfProduct;
	this.amount=amount;
}
  /**
	 * @return the nameOfProduct
	 */
	public String getNameOfProduct() {
		return nameOfProduct;
	}

	/**
	 * @param nameOfProduct the nameOfProduct to set
	 */
	public void setNameOfProduct(String nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
@Override
public int compareTo(Object o) {
	Order obj=(Order) o;
	if (this.getId()>obj.getId())
		return 1;
	if (this.getId()<obj.getId())
		return -1;
	return 0;
}
  
}
