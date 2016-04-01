package Orders;

import java.util.Set;
import java.util.TreeSet;

import Control.Helper;

public class OPDept {
	
	private Set<Order> orders;
	private Helper helper;
	
	public void updateOrders(){
		orders = new TreeSet<Order>();
		helper = new Helper();
		helper.readFromSerFile(orders, "orders.bin");
	}
	
	public Set<Order> getOrders(){
		return orders;
	}
	
	public void modifyCurrentOrders(){
		helper.updateSerFile(orders, "orders.bin");
	}
	
	public void addOrder(Order order){
		orders.add(order);
		helper.updateSerFile(orders, "orders.bin");
	}
	
	public void removeOrder(Order order){
		orders.remove(order);
		helper.updateSerFile(orders, "orders.bin");
	}
	
	public void seeAllOrders(){
		for(Order order : orders){
			System.out.println(order);
		}
	}
}