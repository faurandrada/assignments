package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.lang.model.type.NullType;
import javax.swing.JOptionPane;

import comparators.OrderComparator;
import controllers.SerializationController;
import utilities.OrderStatus;

/**
 * @author iulia
 * 
 * Class that deals with order processing. 
 *
 */
public class OPDept implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeSet<Order> orders;

	public OPDept() {

		this.orders = new TreeSet<Order>(new OrderComparator());

	}

	public OPDept(TreeSet<Order> orders) {

		setOrders(orders);
	}

	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}

	public TreeSet<Order> getOrders() {
		return orders;
	}

	public int getNextOrderId() {

		// JOptionPane.showMessageDialog(null, orders.first().getId());
		// JOptionPane.showMessageDialog(null, orders.last().getId());

		return orders.last().getId() + 1;
	}

	public void addOrders(Order o) {

		// JOptionPane.showMessageDialog(null, "Added" + o.getId());
		this.orders.add(o);
	}

	public void processOrders() {

		int oldQuantity;

		Iterator<Order> itr = this.orders.iterator();

		while (itr.hasNext()) {

			Order o = itr.next();

			System.out.println("Order: ");
			// JOptionPane.showMessageDialog(null, "Order" + o.getId() + " " +
			// o.getStatus());
			if (o.getStatus() == OrderStatus.PENDING) {

				o.processOrder();
				System.out.println("enter PENDING");
			}

			if (o.getStatus() == OrderStatus.FAILED) {
				// JOptionPane.showMessageDialog(null, "Order FAILED!");
			} else {

				if (o.getStatus() == OrderStatus.PROCESSING) {
					
					System.out.println("enter PROCESSING");
					
					TreeSet<OrderedProduct> ordP = o.getOrderedProducts();
					Iterator<OrderedProduct> itrOP = ordP.iterator();
					
					while (itrOP.hasNext()) {

						OrderedProduct op = itrOP.next();

						System.out.println("OPDEPT:" + op.getProduct().getTitle() + " " + op.getOrderedQuantity());

						oldQuantity = op.getProduct().getQuantity();
						
						op.getProduct().setQuantity(oldQuantity - op.getOrderedQuantity());
						
						System.out.print("NEW:");
						
						op.getProduct().printProduct();

					}
					o.setStatus(OrderStatus.DELIVERED);
				}
			}

		}

	}
}
