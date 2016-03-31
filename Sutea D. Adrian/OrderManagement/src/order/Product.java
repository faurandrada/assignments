package order;

public class Product {
	private String name = new String();
	private int quantity;
	private int price;
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int x){
		this.price=x;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		this.quantity = q;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
