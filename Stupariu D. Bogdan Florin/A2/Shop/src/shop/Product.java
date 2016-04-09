package shop;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7646226384725156678L;
	public int ID;
	public String name;
	public int price;
	public String category;
	public int quantity;
	
	public Product leftChild;
	public Product rightChild;
	
	public Product(int ID,String name, String category, int price, int q){
		setName(name);
		setCategory(category);
		setPrice(price);
		setID(ID);
		setQuantity(q);
	}

	public void setName(String name){
		this.name=name;
	}
	
	public void setCategory(String category){
		this.category=category;
	}
	
	public void setPrice(int price){
		this.price=price;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getCategory(){
		return category;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID=ID;
	}
	
	
	public void setQuantity(int q){
		this.quantity=q;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	

}
