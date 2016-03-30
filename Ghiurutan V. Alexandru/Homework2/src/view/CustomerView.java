package view;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.OPDept;
import controller.Warehouse;
import models.Customer;
import models.Product;

public class CustomerView extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6930765159653365919L;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private DecimalFormat decimalFormat;
	private String[] columns = { "Name", "Company name", "Price", "Stock" };
	private Object[][] rows;
	private String name, company, price, stock;
	private static String ERROR = "Error";
	private Customer customer;
	private Product product;
	private Warehouse warehouse;
	private OPDept orderProcessing;
	private JPanel southSection, operations, fields;
	private JTextArea nameText, companyText, priceText, numberText;
	private JTextField nameField, companyField, numberField, priceField;
	private JButton buy, viewOrders, search, logOut;

	public CustomerView() {
		this.setTitle("Customer");
		this.setLayout(new BorderLayout());
		customer = new Customer("Customer", "1");
		warehouse = Warehouse.getInstance();
		orderProcessing = OPDept.getInstance();
		decimalFormat = new DecimalFormat();
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
			rows = new Object[products.length][4];
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

	private void initializeNorthSection() {
		tableModel = new DefaultTableModel(rows, columns);
		table = new JTable();
		table.setModel(tableModel);
		table.setEnabled(false);
		fillTable();
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
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
		fields.add(nameField);
		fields.add(nameText);
		fields.add(companyField);
		fields.add(companyText);
		fields.add(priceField);
		fields.add(priceText);
		fields.add(numberField);
		fields.add(numberText);
		southSection.add(fields);
		operations = new JPanel();
		buy = new JButton("Buy");
		buy.addActionListener(this);
		viewOrders = new JButton("View orders");
		viewOrders.addActionListener(this);
		search = new JButton("Search");
		search.addActionListener(this);
		logOut = new JButton("Log out");
		logOut.addActionListener(this);
		operations.add(buy);
		operations.add(viewOrders);
		operations.add(search);
		operations.add(logOut);
		southSection.add(operations);
		this.add(southSection, BorderLayout.SOUTH);
	}

	private boolean checkFields() {
		double price;
		int number;
		if (nameText.getText().equals("") || companyText.getText().equals("") || priceText.getText().equals("")
				|| numberText.equals("")) {
			JOptionPane.showMessageDialog(this, "You didn't complete all the fields in the search bar.", ERROR,
					JOptionPane.ERROR_MESSAGE);
			return false;
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
		} else if (event.getSource() == buy) {
			if (checkFields()) {
				int number, newStock;
				number = Integer.valueOf(numberText.getText());
				product = new Product(nameText.getText(), companyText.getText(), Double.valueOf(priceText.getText()),
						number);
				newStock = warehouse.getTotalNumberOfProducts() - number;
				if (newStock < Warehouse.getMinimumStock()) {
					JOptionPane.showMessageDialog(this,
							"The warehouse is going under the stock: " + Warehouse.getMinimumStock()
									+ " ,after selling " + number + " ,products.",
							"UNDERSTOCK", JOptionPane.INFORMATION_MESSAGE);
				} else if ((number > 0) && (number <= warehouse.getProductStock(product))) {
					warehouse.sellProduct(product);
					orderProcessing.addOrder(product, customer);
					updateTable();
				} else {
					JOptionPane.showMessageDialog(this, "You didn't enter valid fields.", ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
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
		} else if (event.getSource() == viewOrders) {
			new OrdersView();
		}
	}
}
