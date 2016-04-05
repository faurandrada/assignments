package Project;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

/*Class which simulates an order processing
*it contains proccesing methods of the orders and for stock update
*/
public class OPDept implements Serializable {

	private static final long serialVersionUID = 2983402786393661267L;

	private static int clientNumber = 1;
	private static int orderNumber = 1;
	private TreeSet<Order> orders = new TreeSet<Order>();

	public OPDept() {
		orders.add(new Order(0, 0, new Customer(0, "a", ""), new Product("a", 0, 0)));
	}

	public Collection getOrders() {
		return orders;
	}

	public Collection processOrder(TreeSet<Product> x, Product y, int q, String cn, String ca) {
		TreeSet<Product> ret = new TreeSet<Product>(x);
		Customer c = new Customer(clientNumber, cn, ca);
		ret.remove(y);
		int priceB = 0;
		int price = y.getPrice();
		int amount = y.getAmount();
		int amountRemaining = amount - q;
		if (amountRemaining < 0)
			amountRemaining = 0;
		priceB = price * (amount - amountRemaining);
		if (amountRemaining > 0) {
			y.setAmount(amountRemaining);
			ret.add(y);
		}
		orderNumber = orders.last().getNrOrder();
		orderNumber++;
		Order ord = new Order(orderNumber, priceB, c, new Product(y.getName(), y.getPrice(), amount - amountRemaining));
		Interface.message = "> " + " Client " + cn + " address " + ca + " purchased " + y.getName() + " amount "
				+ (amount - amountRemaining) + " total cost " + priceB + "\n";
		orders.add(ord);
		orderNumber++;
		clientNumber++;
		return ret;
	}

}
