package Orders;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order implements Serializable, Comparable<Order> {

	private static final long serialVersionUID = 3156675637257235803L;

	private String ID;
	private String time;
	private String customerName;
	private String productID;
	private String status;
	private int quantity;

	public Order(String productID, int quantity, String customerName) {
		this.time = new SimpleDateFormat("dd-MM-yyyy || HH:mm:ss").format(Calendar.getInstance().getTime());
		this.customerName = customerName;
		this.productID = productID;
		this.quantity = quantity;
		this.ID = this.customerName + time;
	}

	public void setID(String id) {
		this.ID = id;
	}

	public String getID() {
		return ID;
	}

	public String getTime() {
		return time;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getProductID() {
		return productID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return ("\n-----Order-----" + "\nID: " + ID + "\nCustomer Name: " + customerName + "\nProduct ID: "
				+ productID + "\nQuantity "+ quantity + "\nTime: " + time + "\nStatus: " + status + "\n---------------------------");
	}

	@Override
	public int compareTo(Order o) {
		return this.ID.compareTo(o.getID());
	}
}
