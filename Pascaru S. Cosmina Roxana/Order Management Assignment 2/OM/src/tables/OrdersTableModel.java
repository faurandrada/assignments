package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import subjects.Customer;
import subjects.Order;

public class OrdersTableModel extends AbstractTableModel {

	private String[] columnNames = { "ID", "Customer" };
	private ArrayList<Order> orders;

	public OrdersTableModel() {
		orders = new ArrayList<Order>();
	}

	public OrdersTableModel(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return orders.size();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Order order = getOrder(row);
		switch (column) {
		case 0:
			return order.getID();
		case 1:
			return order.getCustomer().getName();

		}
		return order;
	}

	public Order getOrder(int row) {
		return orders.get(row);
	}

	public void addOrder(Order order) {
		insertOrder(getRowCount(), order);
	}

	public void insertOrder(int row, Order order) {
		orders.add(row, order);
		fireTableRowsInserted(row, row);
	}

	public void removeOrder(int row) {
		orders.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
