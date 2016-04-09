package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import comparators.OrderedProductComparator;
import utilities.OrderStatus;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idCustomer;
	private TreeSet<OrderedProduct> orderedProducts;
	private OrderStatus status;

	public Order(int id, int idCustomer) {

		orderedProducts = new TreeSet<OrderedProduct>(new OrderedProductComparator());
		setId(id);
		setIdCustomer(idCustomer);
		setStatus(OrderStatus.PENDING);
	}

	public Order(int id, int idCustomer, TreeSet<OrderedProduct> orderedProducts) {

		this(id, idCustomer);
		setOrderedProducts(orderedProducts);
		setStatus(OrderStatus.PENDING);
	}

	public void addOrderedProduct(OrderedProduct op) {

		this.orderedProducts.add(op);
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getIdCustomer() {

		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {

		this.idCustomer = idCustomer;
	}

	public OrderStatus getStatus() {

		return status;
	}

	public void setStatus(OrderStatus status) {

		this.status = status;
	}

	public TreeSet<OrderedProduct> getOrderedProducts() {

		return this.orderedProducts;
	}

	public void setOrderedProducts(TreeSet<OrderedProduct> orderedProducts) {

		this.orderedProducts = orderedProducts;
	}

	public double getValue() {

		double value = 0;
		Iterator<OrderedProduct> itr = this.orderedProducts.iterator();

		while (itr.hasNext()) {
			OrderedProduct op = itr.next();
			value += op.getProduct().getPrice() * op.getOrderedQuantity();
		}

		return value;
	}

	public void processOrder() {

		Iterator<OrderedProduct> itr = this.orderedProducts.iterator();

		while (itr.hasNext()) {

			OrderedProduct op = itr.next();

			setStatus(OrderStatus.PROCESSING);
			if (op.getProduct().getQuantity() < op.getOrderedQuantity()) {

				setStatus(OrderStatus.FAILED);
			}

		}
	}

	public void printOrder() {

		System.out.print(this.getStatus() + " ");
		Iterator<OrderedProduct> itr = this.orderedProducts.iterator();

		while (itr.hasNext()) {
			OrderedProduct op = itr.next();
			System.out.println("Order" + op.getProduct().getTitle() + " " + op.getOrderedQuantity() + " ");
		}
	}
}
