package controller;

import models.Customer;
import models.Order;
import models.Product;
import models.Status;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

public class OPDept {

	private static OPDept instance;
	private static int commandNumber;
	private Iterator<Order> iterator;
	private TreeSet<Order> orderProcessing;
	private InputOutput inOut;
	private Timer timer;

	private OPDept() {
		inOut = new InputOutput();
		orderProcessing = inOut.deserializeOrders();
	}

	public static OPDept getInstance() {
		if (instance == null) {
			instance = new OPDept();
		}
		return instance;
	}

	public Order[] getAllOrders() {
		Order[] orders = new Order[orderProcessing.size()];
		int i = 0;
		iterator = orderProcessing.iterator();
		while (iterator.hasNext()) {
			orders[i++] = iterator.next();
		}
		return orders;
	}

	public int getNrOfOrders() {
		return orderProcessing.size();
	}

	private Status getNextStatus(Order order) {
		Status status = order.getCommandStatus();
		if (status == Status.PENDING) {
			status = Status.PROCESSING;
		} else if (status == Status.PROCESSING) {
			status = Status.SENT;
		}
		return status;
	}

	public void addOrder(Product product, Customer customer) {
		Order order = new Order("Command " + (++commandNumber), product, customer);
		orderProcessing.add(order);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				order.setStatus(getNextStatus(order));
				inOut.serializeOrders();
			}
		}, 5000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				order.setStatus(getNextStatus(order));
				inOut.serializeOrders();
			}
		}, 10000);
	}
}
