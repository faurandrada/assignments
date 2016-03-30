package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import controller.Warehouse;
import models.Product;

public class UserView extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7882841254291370031L;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private DecimalFormat decimalFormat;
	private String[] columns = { "Name", "Company name", "Price", "Stock" };
	private Object[][] rows;
	private String name, company, price, stock;
	private String ERROR = "ERROR";
	private Warehouse warehouse;
	private Product product;
	private JPanel southSection, operations, fields, field1, field2, field3, field4;
	private JTextArea nameText, companyText, priceText, numberText;
	private JTextField nameField, companyField, priceField, numberField;
	private JButton add, remove, viewOrders, search, logOut;

	public UserView() {
		this.setTitle("Admin");
		decimalFormat = new DecimalFormat();
		warehouse = Warehouse.getInstance();
		initializeNorthSection();
		initializeSouthSection();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void fillTable() {
		if (warehouse.getTotalNumberOfProducts() != 0) {
			Product[] products = warehouse.getAllProducts();
			for (Product product : products) {
				name = product.getName();
				company = product.getCompany();
				price = decimalFormat.format(product.getPrice());
				stock = String.valueOf(warehouse.getProductStock(product));
				Object[] row = { name, company, price, stock };
				tableModel.addRow(row);
			}
		}
	}

	private boolean checkFields() {
		double price;
		int number;
		if (nameText.getText().equals("") || companyText.getText().equals("") || priceText.getText().equals("")
				|| numberText.equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't complete all the fields in the search bar.", ERROR,
					JOptionPane.ERROR_MESSAGE);
		} else {
			price = Double.valueOf(priceText.getText());
			if (price <= 0) {
				JOptionPane.showMessageDialog(this, "You didn't enter a valid price.", ERROR,
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			number = Integer.valueOf(numberText.getText());
			if (number <= 0) {
				JOptionPane.showMessageDialog(this, "You didn't enter a valid number.", ERROR,
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	private void initializeSouthSection() {
		southSection = new JPanel();
		southSection.setLayout(new BoxLayout(southSection, BoxLayout.Y_AXIS));
		fields = new JPanel();
		fields.setLayout(new FlowLayout());
		nameField = new JTextField("Poduct name:");
		nameField.setEditable(false);
		companyField = new JTextField("Company:");
		companyField.setEditable(false);
		priceField = new JTextField("Price:");
		priceField.setEditable(false);
		numberField = new JTextField("Number:");
		numberField.setEditable(false);
		nameText = new JTextArea(1, 10);
		companyText = new JTextArea(1, 10);
		priceText = new JTextArea(1, 10);
		numberText = new JTextArea(1, 10);
		field1 = new JPanel(new FlowLayout());
		field1.add(nameField);
		field1.add(nameText);
		field2 = new JPanel(new FlowLayout());
		field2.add(companyField);
		field2.add(companyText);
		field3 = new JPanel(new FlowLayout());
		field3.add(priceField);
		field3.add(priceText);
		field4 = new JPanel(new FlowLayout());
		field4.add(numberField);
		field4.add(numberText);
		fields.add(field1);
		fields.add(field2);
		fields.add(field3);
		fields.add(field4);
		operations = new JPanel();
		operations.setLayout(new FlowLayout());
		add = new JButton("Add");
		add.addActionListener(this);
		remove = new JButton("Remove");
		remove.addActionListener(this);
		search = new JButton("Search");
		search.addActionListener(this);
		viewOrders = new JButton("View orders");
		viewOrders.addActionListener(this);
		logOut = new JButton("Log out");
		logOut.addActionListener(this);
		operations.add(add);
		operations.add(remove);
		operations.add(search);
		operations.add(viewOrders);
		operations.add(logOut);
		southSection.add(fields);
		southSection.add(operations);
		this.add(southSection, BorderLayout.SOUTH);
	}

	private void initializeNorthSection() {
		tableModel = new DefaultTableModel(rows, columns);
		table = new JTable();
		table.setEnabled(false);
		table.setModel(tableModel);
		fillTable();
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	private void updateTable() {
		tableModel = new DefaultTableModel(rows, columns);
		tableModel.setRowCount(0);
		table.setModel(tableModel);
		Product[] products = warehouse.getAllProducts();
		for (Product product : products) {
			name = product.getName();
			company = product.getCompany();
			price = decimalFormat.format(product.getPrice());
			stock = String.valueOf(warehouse.getProductStock(product));
			Object[] row = { name, company, price, stock };
			tableModel.addRow(row);
		}
	}

	private boolean checkForSearch() {
		if (nameText.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the name of product.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (companyText.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the company name of product.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (priceText.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't enter the price of product.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == logOut) {
			this.dispose();
			new Gui();
		} else if (event.getSource() == add) {
			if (checkFields()) {
				name = nameText.getText();
				company = companyText.getText();
				price = priceText.getText();
				stock = numberText.getText();
				product = new Product(name, company, Double.valueOf(price), Integer.valueOf(stock));
				warehouse.addProduct(product);
				updateTable();
			}
		} else if (event.getSource() == remove) {
			if (checkForSearch()) {
				name = nameText.getText();
				company = companyText.getText();
				price = priceText.getText();
				product = new Product(name, company, Double.valueOf(price), 0);
				warehouse.removeProduct(product);
				updateTable();
			}
		} else if (event.getSource() == viewOrders) {
			new OrdersView();
		} else if (event.getSource() == search) {
			if (checkForSearch()) {
				product = new Product(nameText.getText(), companyText.getText(), Double.valueOf(priceText.getText()),
						0);
				if (warehouse.searchForProduct(product)) {
					JOptionPane.showMessageDialog(this, "The product exists in the warehouse.", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "The product doesn't exist in the warehouse.", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
