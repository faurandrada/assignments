import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order extends Node<Order> {
	private int ID;
	private Product product;
	private Customer customer;
	
	public Order(Product product,Customer customer){
		super();
		Random rand = new Random();
		ID= rand.nextInt(1000) + 1;
		this.product=product;
		this.customer=customer;

	//	this.customer=customer;
	}

	//public void addProducts(Product product){
	//	products.add(product);
	//}
	
	//public void removeProduct(Product product){
	//	int index=-1;
	//	for (Product p:products){
		//	if (p.getItem()==product.getItem())
		///		if (p.getSize()==product.getSize())
		//			if (p.getColor()==product.getColor())
		//				index=products.indexOf(p);
						
	//	}	
	//	if (index>=0)
	//		products.remove(index);
	//	
	//}
	//public List<Product> getList(){
//		return products;
	//}
	public Customer getCustomer(){
		return customer;
	}
	public Product getProduct(){
		return product;
	}
	@Override
	public int getId() {
		return ID;
	}
	
	
}
