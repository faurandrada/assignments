package Project;

import java.io.Serializable;
import java.util.Comparator;

/*  It contains the fields nrOrder and totalPrice. Besides these, it contains an 
	object of type Product and another one of type Customer, representing the client 
*	and the product that was bought. 
*	Thie class also implements the Serializable and Comparable interfaces.
*/
public class Order implements Comparable, Comparator<Order>, Serializable {

	private int nrOrder = 0;
	private Customer client = null;
	private Product prod = null;
	private int totalPrice = 0;

	public Order(int i, int j, Customer c, Product p) {
		nrOrder = i;
		client = c;
		prod = p;
		totalPrice = j;
	}

	public Customer getClient() {
		return client;
	}

	public Product getProduct() {
		return prod;
	}

	public int getNrOrder() {
		return nrOrder;
	}

	public int compareTo(Object o) {
		Order x = (Order) o;
		int nr = x.getNrOrder();
		if (this.nrOrder > nr)
			return 1;
		else if (this.nrOrder == nr)
			return 0;
		else
			return -1;
	}

	public int getCost() {
		return totalPrice;
	}

	public String toString() {
		String ret = "";
		ret = nrOrder + "";
		return ret;
	}

	@Override
	public int compare(Order a1, Order a2) {
		int aa1 = a1.getNrOrder();
		int aa2 = a2.getNrOrder();
		if (aa1 == aa2)
			return 0;
		else {
			if (aa1 > aa2)
				return 1;
			else
				return -1;
		}
	}
}
