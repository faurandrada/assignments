package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import controller.OPDept;
import models.Order;
import models.Product;
import models.Status;

/**
 * 
 * @author Ghiurutan .The class in which the Orders are displayed in a JTable.
 */
public class OrdersView extends JFrame {
	private static final long serialVersionUID = -5369998738754443367L;
	private OPDept orderProcessing;
	private Object[][] rows;
	private Object[] columns = { "Command name", "Product name", "Product company", "Product price", "number",
			"Customer name", "Command status" };
	private String commandName, productName, productCompany, customerName;
	private double price;
	private int number;
	private boolean isRunning;
	private Product product;
	private DecimalFormat decimalFormat;
	private JTable table;
	private Timer timer;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public OrdersView() {
		this.setTitle("Orders history");
		decimalFormat = new DecimalFormat();
		table = new JTable();
		tableModel = new DefaultTableModel(rows, columns);
		table.setModel(tableModel);
		orderProcessing = OPDept.getInstance();
		fillTable();
		scrollPane = new JScrollPane(table);
		refreshStatus();
		this.add(scrollPane, BorderLayout.NORTH);
		this.setSize(650, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void fillTable() {
		if (orderProcessing.getNrOfOrders() != 0) {
			Order[] orders = orderProcessing.getAllOrders();
			rows = new Object[orders.length][7];
			for (Order order : orders) {
				product = order.getProcuct();
				commandName = order.getName();
				productName = product.getName();
				productCompany = product.getCompany();
				price = product.getPrice();
				number = product.getStock();
				customerName = order.getCustomerName();
				Status status = order.getCommandStatus();
				Object[] row = { commandName, productName, productCompany, decimalFormat.format(price), number,
						customerName, status };
				tableModel.addRow(row);
			}
		}
	}

	private void refreshStatus() {
		isRunning = true;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updateTable();
				if (!isRunning) {
					timer.cancel();
				}
				isRunning = false;
			}
		}, 1000, 1000);
	}

	public void stopTimer() {
		timer.cancel();
	}

	public void updateTable() {
		tableModel = new DefaultTableModel(rows, columns);
		tableModel.setRowCount(0);
		table.setModel(tableModel);
		Order[] orders = orderProcessing.getAllOrders();
		for (Order order : orders) {
			product = order.getProcuct();
			commandName = order.getName();
			productName = product.getName();
			productCompany = product.getCompany();
			price = product.getPrice();
			customerName = order.getCustomerName();
			number = product.getStock();
			Status status = order.getCommandStatus();
			if (status != Status.SENT) {
				isRunning = true;
			}
			Object[] row = { commandName, productName, productCompany, price, number, customerName, status };
			tableModel.addRow(row);
		}
	}
}
