package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class UserFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel topPanel;
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel btnPanel;
	private JButton addOrder, searchButton;
	private static JTable table;
	private JScrollPane scrollPane;
	String header[] = { "ID", "Name", "Stock" };
	private String header1[] = { "ID", "Customer", "Product", "Quantity", "Status" };
	private static JTable orderTable;

	public UserFrame() {
		setTitle("OrderManagement");

		getContentPane().add(jtp);

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();

		jtp.addTab("Stock", jp1);
		jtp.addTab("OrderHistory", jp2);
		setSize(500, 550);
		setBackground(Color.gray);
		topPanel = new JPanel();
		btnPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		jp1.add(topPanel, BorderLayout.CENTER);
		jp1.add(btnPanel, BorderLayout.SOUTH);
		table = new JTable(0, 3);
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header[i]);
		}
		scrollPane = new JScrollPane(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		addOrder = new JButton("Add Order");
		searchButton = new JButton("Search");
		btnPanel.add(addOrder);
		btnPanel.add(searchButton);
		////
		JPanel topPanel1 = new JPanel();
		JPanel btnPanel1 = new JPanel();
		topPanel1.setLayout(new BorderLayout());
		jp2.add(topPanel1, BorderLayout.CENTER);
		jp2.add(btnPanel1, BorderLayout.SOUTH);
		orderTable = new JTable(0, 5);
		for (int i = 0; i < orderTable.getColumnCount(); i++) {
			TableColumn column1 = orderTable.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header1[i]);
		}
		scrollPane = new JScrollPane(orderTable);
		topPanel1.add(scrollPane, BorderLayout.CENTER);
		setVisible(true);

	}

	public static JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		UserFrame.table = table;
	}

	public final void setAddOrderButtonActionListener(final ActionListener a) {
		addOrder.addActionListener(a);
	}

	public final void setSearchButtonActionListener(final ActionListener a) {
		searchButton.addActionListener(a);

	}

	public static JTable getOrderTable() {
		return orderTable;
	}

	public static void setOrderTable(JTable orderTable) {
		UserFrame.orderTable = orderTable;
	}

}
