package subjects;

import java.io.Serializable;

public class ProductStock implements Serializable,Comparable<ProductStock>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6169336779908568538L;
	public Product product;
	private int quantity;
	
	public ProductStock(Product product, int quantity){
		this.product=product;
		this.quantity = quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public String getProductName(){
		return product.getName();
	}
	
	public int getProductPrice(){
		return product.getPrice();
	}

	public void displayProductStock(){
		System.out.printf("%s %d %d\n",product.getName(),product.getPrice(),quantity);
	}
	@Override
	public int compareTo(ProductStock p) {
		return product.getName().compareTo(p.product.getName());
	}
	
}
