package order;

public class Order {
	private int quantity;
	private Product prod;
	
	public Order(int q, Product p){
		this.quantity=q;
		this.prod=p;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public Product getProduct(){
		return prod;
	}
}
