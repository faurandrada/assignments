package Design;
import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	private int ID;
	private int price;
	
	public Order(int ID,String name, int quantity, int price)
	{
		this.ID=ID;
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
	
	public String getName(){
		return name;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getPrice(){
		return price;
	}

	public void setPrice(int price) {
		 this.price=price;
		
	}
}
