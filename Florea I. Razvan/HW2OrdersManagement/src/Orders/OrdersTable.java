package Orders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTable;

public class OrdersTable {

	private Set<Order> orders;
	private OPDept opdept;
	private String[] header = { "Order ID", "Customer Name", "Product ID", "Quantity", "Time", "Order Status" };
	private Object[][] data;
	private JTable table;

	public OrdersTable() {
		opdept = new OPDept();
		opdept.updateOrders();

		orders = new TreeSet<Order>();
		orders = opdept.getOrders();

		data = new Object[orders.size()][6];
		int i = 0;
		for (Order order : orders) {
			data[i][0] = order.getID();
			data[i][1] = order.getCustomerName();
			data[i][2] = order.getProductID();
			data[i][3] = order.getQuantity();
			data[i][4] = order.getTime();
			data[i][5] = order.getStatus();
			i++;
		}

		table = new JTable(data, header);

		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setForeground(Color.decode("0x121212"));
		table.setBackground(Color.decode("0xc9c9c9"));
		table.setGridColor(Color.WHITE);
		table.setRowHeight(25);
	}
	
	public JTable getTable(){
		return table;
	}
}
