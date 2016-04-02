package Design;
import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private int quantity;
	private int price;
	
	public Product(int ID, String name, int quantity, int price){
		this.ID=ID;
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return name;
	}
	public int getQuantity(){
		return quantity;
	}
	public int getPrice(){
		return price;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
}
