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

public class AdminFrame2 extends JFrame {
	JTabbedPane jtp = new JTabbedPane();
	private JScrollPane scrollPane;
	private static JTable tableStock;
	private static JTable tableOrder;
	JButton addButton, delButton, searchButton, seeButton, searchButton1,stock;
	String header1[] = { "ID", "Name", "Stock" };
	String header2[] = { "ID","Customer", "Product", "Quantity" };
	String header3[] = { "ID", "Name", "IDOrder" };
	private static final long serialVersionUID = 1L;

	public AdminFrame2() {
		setTitle("Order Management");
		getContentPane().add(jtp);

		JPanel jp1 = new JPanel();

		JPanel jp2 = new JPanel();

		jtp.addTab("Stock", jp1);

		jtp.addTab("Orders", jp2);
		setSize(500, 550);
		setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		jp1.add(topPanel, BorderLayout.CENTER);
		jp1.add(btnPanel, BorderLayout.SOUTH);
		tableStock = new JTable(0, 3);
		for (int i = 0; i < tableStock.getColumnCount(); i++) {
			TableColumn column1 = tableStock.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header1[i]);
		}
		scrollPane = new JScrollPane(tableStock);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		addButton = new JButton("Add Product");
		delButton = new JButton("Delete Product");
		searchButton = new JButton("Search");
		stock= new JButton("Change Stock");	
		btnPanel.add(addButton);
		btnPanel.add(delButton);
		btnPanel.add(searchButton);
		btnPanel.add(stock);
		/////////////////////////////////////////
		JPanel topPanel1 = new JPanel();
		JPanel btnPanel1 = new JPanel();
		topPanel1.setLayout(new BorderLayout());
		jp2.add(topPanel1, BorderLayout.CENTER);
		jp2.add(btnPanel1, BorderLayout.SOUTH);
		tableOrder = new JTable(0, 4);
		for (int i = 0; i < tableOrder.getColumnCount(); i++) {
			TableColumn column1 = tableOrder.getTableHeader().getColumnModel().getColumn(i);

			column1.setHeaderValue(header2[i]);
		}
		scrollPane = new JScrollPane(tableOrder);
		topPanel1.add(scrollPane, BorderLayout.CENTER);
		seeButton = new JButton("See Order");
		searchButton1 = new JButton("Search");
		btnPanel1.add(seeButton);
		btnPanel1.add(searchButton1);
		setVisible(true);

	}

	public static JTable getTable() {
		return tableStock;
	}

	public void setTable(JTable tableStock) {
		AdminFrame2.tableStock = tableStock;
	}

	public final void setAddActionListener(final ActionListener a) {
		addButton.addActionListener(a);

	}

	public final void setDelButtonActionListener(final ActionListener a) {
		delButton.addActionListener(a);

	}

	public final void setSearchButtonActionListener(final ActionListener a) {
		searchButton.addActionListener(a);

	}
	public final void setStockButtonActionListener(final ActionListener a) {
		stock.addActionListener(a);

	}

	///
	public final void setSeeActionListener(final ActionListener a) {
		seeButton.addActionListener(a);

	}

	public final void setSearch1ButtonActionListener(final ActionListener a) {
		searchButton1.addActionListener(a);

	}
	public static JTable getTableOrder() {
		return tableOrder;
	}

	public void setTableOrder(JTable tableOrder) {
		AdminFrame2.tableOrder = tableOrder;
	}


}
