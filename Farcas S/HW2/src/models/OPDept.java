package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import views.FrameStack;

public class OPDept {

	private static OPDept instance;
	
	private TreeSet<Order> orders = new TreeSet<Order>(new MyComparator());
	private BufferedReader br = null;
	private BufferedWriter bw = null;

	private OPDept() {
		pullOrdersFromFile();
	}

	public static OPDept getInstance() {
		if (instance == null) {
			instance = new OPDept();
		}
		return instance;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
		pushOrdersToFile();
	}

	public TreeSet<Order> getOrders() {
		return orders;
	}

	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}

	public void pushOrdersToFile() {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("D:\\UT\\2nd year\\PT\\Lab\\HW2\\OrderManagement\\src\\models\\Orders"));
			for (Order o : orders) {
				String orderItems = "";
				for (OrderItem oi : o.getOrderItems()) {
					orderItems = orderItems + oi.getProduct().getName() + " " + oi.getProduct().getPrice() + " "
							+ oi.getQuantity() + " ";
				}
				bw.write(o.getID() + " " + o.getStatus().getTimeReceived() + " " + o.getStatus().getTimeProcessed()
						+ " " + o.getStatus().getTimeDelivered() + " " + o.getStatus().getTimeFailed() + " "
						+ orderItems + " ");
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}

	public void pullOrdersFromFile() {
		int ID;
		long timeReceived;
		long timeProcessed;
		long timeDelivered;
		long timeFailed;
		String name;
		double price;
		int quantity;
		ArrayList<OrderItem> orderItems;
		try {
			br = new BufferedReader(
					new FileReader("D:\\UT\\2nd year\\PT\\Lab\\HW2\\OrderManagement\\src\\models\\Orders"));
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				if (st.hasMoreTokens()) {
					ID = Integer.parseInt(st.nextToken());
					timeReceived = Long.parseLong(st.nextToken());
					timeProcessed = Long.parseLong(st.nextToken());
					timeDelivered = Long.parseLong(st.nextToken());
					timeFailed = Long.parseLong(st.nextToken());
					orderItems = new ArrayList<OrderItem>();
					while (st.hasMoreTokens()) {
						name = st.nextToken();
						price = Double.parseDouble(st.nextToken());
						quantity = Integer.parseInt(st.nextToken());
						orderItems.add(new OrderItem(new Product(name, price), quantity));
					}
					orders.add(new Order(ID, new Status(timeReceived, timeProcessed, timeDelivered, timeFailed),
							orderItems));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public class MyComparator implements Comparator<Order> {

		@Override
		public int compare(Order o1, Order o2) {
			return String.valueOf(o1.getStatus().getTimeReceived())
					.compareTo(String.valueOf(o2.getStatus().getTimeReceived()));
		}
	}

}
