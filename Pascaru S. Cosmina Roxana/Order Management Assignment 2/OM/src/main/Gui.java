package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import groups.OPDept;
import groups.ShopCustomers;
import groups.Warehouse;
import panels.AdminPanel;
import panels.CustomerPanel;
import panels.LogInPanel;
import panels.OrderPanel;
import subjects.Admin;
import subjects.Customer;
import subjects.Order;
import subjects.Person;
import subjects.Product;
import subjects.ProductStock;
import tables.CustomersTable;
import tables.CustomersTableModel;
import tables.OrdersTable;
import tables.OrdersTableModel;
import tables.StockTableModel;
import tables.WarehouseTable;

public class Gui implements ActionListener {
	/* LOG IN PANEL */
	private LogInPanel logInPanel;
	private JButton login = new JButton("login");

	/* CUSTOMER PANEL */
	private CustomerPanel customerPanel;
	private JButton back1 = new JButton("back");
	private JButton save = new JButton("save");
	private JButton back6 = new JButton("back");
	private JTextField addressText = new JTextField();
	private JTextField emailText = new JTextField();
	private JTextField phoneText = new JTextField();

	/* ORDER PANEL */
	private Warehouse warehouse = new Warehouse();
	private OrderPanel orderPanel;
	private JButton addProduct = new JButton("Add Product to Order");
	private JButton removeProduct = new JButton("Remove Product from Order");
	private JButton viewOrder = new JButton("View Order");
	private JButton viewHistory = new JButton("View History");
	private JButton confirm = new JButton("Confirm Order");
	private Order newOrder;
	private int i = 5;

	/* ADMIN PANEL */
	private AdminPanel adminPanel;
	private JButton viewCustomers = new JButton("View Customers");
	private JButton viewWarehouse = new JButton("View Warehouse");
	private JButton viewOrders = new JButton("View Orders");
	private JButton back2 = new JButton("Back");
	// Customers
	private CustomersTable customersTable;
	private CustomersTableModel customersTableModel = new CustomersTableModel();
	private JPanel customersTablePanel = new JPanel();
	private JButton back3 = new JButton("Back");
	// Products
	private StockTableModel stockTableModel = new StockTableModel();
	private WarehouseTable warehouseTable;
	private JPanel warehouseTablePanel = new JPanel();
	private JButton back4 = new JButton("Back");
	// Orders
	private OrdersTableModel ordersTableModel = new OrdersTableModel();
	private OrdersTable ordersTable;
	private JPanel ordersTablePanel = new JPanel();
	private JButton back5 = new JButton("Back");

	/* FRAME */
	private JFrame frame = new JFrame("Order Management");

	/* CUSTOMER */
	private Customer customer;
	private ShopCustomers shopCustomers = new ShopCustomers();

	/* ORDER DEPARTMENT */
	private OPDept opdept = new OPDept();

	/* ADMIN */
	private Person admin = new Admin("admin", "a");
	private JButton addStock = new JButton("Add Product");
	private JButton removeStock = new JButton("Remove Product");
	private JButton updateStock = new JButton("Update Stock");

	/* FILTER */
	private Filter filter = new Filter();

	public Gui() {

		/* TEST */
		Customer c1 = new Customer("ana", "ana", "cluj", "ana@mail.com", "0765456789");
		Customer c2 = new Customer("maria", "maria", "cluj", "maria@mail.com", "0765479789");
		Customer c3 = new Customer("dan", "dan", "cluj", "dan@mail.com", "0755479789");
		Customer c4 = new Customer("calin", "calin", "cluj", "calin@mail.com", "0765471189");

		shopCustomers.addCustomer(c1);
		shopCustomers.addCustomer(c2);
		shopCustomers.addCustomer(c3);
		shopCustomers.addCustomer(c4);

		Product p1 = new Product("curmale", 3);
		Product p2 = new Product("lapte cocos", 9);
		Product p3 = new Product("mere", 3);
		Product p4 = new Product("banane", 5);
		Product p5 = new Product("salata", 2);
		Product p6 = new Product("migdale", 9);
		Product p7 = new Product("nuca", 15);

		ProductStock sp1 = new ProductStock(p1, 3);
		ProductStock sp2 = new ProductStock(p2, 2);
		ProductStock sp3 = new ProductStock(p3, 1);
		ProductStock sp4 = new ProductStock(p4, 2);
		ProductStock sp5 = new ProductStock(p5, 3);
		ProductStock sp6 = new ProductStock(p6, 4);
		ProductStock sp7 = new ProductStock(p7, 2);

		warehouse.addProduct(sp7);
		warehouse.addProduct(sp6);
		warehouse.addProduct(sp5);
		warehouse.addProduct(sp4);
		warehouse.addProduct(sp3);
		warehouse.addProduct(sp2);
		warehouse.addProduct(sp1);

		Order o1 = new Order(1, c1);
		o1.addProduct(p1);
		o1.addProduct(p2);
		o1.addProduct(p3);

		Order o2 = new Order(2, c2);
		o2.addProduct(p7);
		o2.addProduct(p6);

		Order o3 = new Order(3, c3);
		o3.addProduct(p4);
		o3.addProduct(p5);

		Order o4 = new Order(4, c4);
		o4.addProduct(p3);
		o4.addProduct(p7);
		o4.addProduct(p4);

		opdept.addOrder(o1);
		opdept.addOrder(o2);
		opdept.addOrder(o3);
		opdept.addOrder(o4);

		/* LOGIN PANEL */
		login.setActionCommand("login");
		login.addActionListener(this);
		logInPanel = new LogInPanel(login);

		/* CUSTOMER PANEL */
		customerPanel = new CustomerPanel(back1, save, addressText, emailText, phoneText);
		save.setActionCommand("save");
		save.addActionListener(this);
		back1.setActionCommand("back1");
		back1.addActionListener(this);

		/* ORDER PANEL */
		orderPanel = new OrderPanel(addProduct, removeProduct, viewOrder, viewHistory, confirm, warehouse, back6);
		addProduct.addActionListener(this);
		addProduct.setActionCommand("add");

		removeProduct.addActionListener(this);
		removeProduct.setActionCommand("removeProduct");
		viewOrder.addActionListener(this);
		viewOrder.setActionCommand("viewOrder");
		viewHistory.addActionListener(this);
		viewHistory.setActionCommand("viewHistory");
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);

		/* ADMIN PANEL */
		adminPanel = new AdminPanel(viewCustomers, viewWarehouse, viewOrders, addStock, removeStock, updateStock,
				back2);

		viewCustomers.addActionListener(this);
		viewCustomers.setActionCommand("viewCust");
		viewWarehouse.addActionListener(this);
		viewWarehouse.setActionCommand("viewWare");
		viewOrders.addActionListener(this);
		viewOrders.setActionCommand("viewOrders");
		addStock.setActionCommand("addStock");
		addStock.addActionListener(this);
		removeStock.setActionCommand("removeStock");
		removeStock.addActionListener(this);
		updateStock.setActionCommand("updateStock");
		updateStock.addActionListener(this);

		// Display products
		 //warehouse.serialize();

		// Display customers
		 //shopCustomers.serialize();

		// Display orders
		 //opdept.serialize();

		back1.setActionCommand("back1");
		back1.addActionListener(this);
		back2.addActionListener(this);
		back2.setActionCommand("back2");
		back3.setActionCommand("back3");
		back3.addActionListener(this);
		back4.setActionCommand("back4");
		back4.addActionListener(this);
		back5.setActionCommand("back5");
		back5.addActionListener(this);
		back6.setActionCommand("back6");
		back6.addActionListener(this);

		frame.setSize(480, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.add(logInPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {
			if (logInPanel.getName().equals(admin.getName()) && logInPanel.getPassword().equals(admin.getPassword())) {
				logInPanel.setVisible(false);
				adminPanel.setVisible(true);
				frame.add(adminPanel);
			} else {
				boolean found = false;
				for (Customer c : shopCustomers.getCustomers()) {
					if (c.getName().equals(logInPanel.getName()) && c.getPassword().equals(logInPanel.getPassword())) {
						found = true;
						customer = c;
					}
				}
				if (found) {
					newOrder = new Order(opdept.getOrders().size()+1, (Customer) customer);
					updateWarehouse();
					orderPanel.add(warehouseTable, BorderLayout.CENTER);
					logInPanel.setVisible(false);
					orderPanel.setVisible(true);
					frame.add(orderPanel);
				} else {
					logInPanel.setVisible(false);
					customerPanel.setVisible(true);
					frame.add(customerPanel);
				}
			}
		} else if (e.getActionCommand().equals("back1")) {
			customerPanel.setVisible(false);
			logInPanel.setVisible(true);
			frame.add(logInPanel);
		} else if (e.getActionCommand().equals("save")) {
			customer = new Customer(logInPanel.getName(), logInPanel.getPassword(), addressText.getText(),
					emailText.getText(), phoneText.getText());
			shopCustomers.addCustomer((Customer) customer);
			shopCustomers.serialize();
			newOrder = new Order(opdept.getOrders().size()+1, (Customer) customer);
			customerPanel.setVisible(false);
			updateWarehouse();
			orderPanel.add(warehouseTable, BorderLayout.CENTER);
			shopCustomers.serialize();
			orderPanel.setVisible(true);
			frame.add(orderPanel);
		} else if (e.getActionCommand().equals("add")) {
			TreeSet<ProductStock> products;
			products = warehouse.deserialize();
			String product = JOptionPane.showInputDialog("Enter Product");
			boolean found = filter.filterProductStock(warehouse, product);
			if (found && filter.getFoundProductStock().getQuantity() != 0)
				newOrder.addProduct(filter.getFoundProductStock().product);
			else {
				JOptionPane.showMessageDialog(null, "Product not in stock at the moment");
			}
		} else if (e.getActionCommand().equals("removeProduct")) {
			String product = JOptionPane.showInputDialog("Remove Product");
			boolean found = filter.filterOrder(newOrder, product);
			if (found) {
				newOrder.removeProduct(filter.getFoundProduct());
			} else {
				JOptionPane.showMessageDialog(null, "You haven't ordered that");
			}
		} else if (e.getActionCommand().equals("viewOrder")) {
			String order = "";
			int price = 0;
			for (Product p : newOrder.getProductList()) {
				order += p.getName() + "\n";
				price += p.getPrice();
			}
			order += "Total " + price;
			System.out.println(order);
			JOptionPane.showMessageDialog(null, order);
		} else if (e.getActionCommand().equals("viewHistory")) {
			String history = "";
			int price = 0;
			for (Order o : opdept.deserialize()) {
				if (o.getCustomer().getName().equals(logInPanel.getName())
						&& o.getCustomer().getPassword().equals(logInPanel.getPassword())) {
					for (Product p : o.getProductList()) {
						history += p.getName() + "\n";
						price += p.getPrice();
					}
				}
			}
			history += "Total " + price;
			JOptionPane.showMessageDialog(null, history);
		} else if (e.getActionCommand().equals("confirm")) {
			for (Product p1 : newOrder.getProductList()) {
				boolean found = filter.filterProductStock(warehouse, p1.getName());
				if (found) {
					int quantity = filter.getFoundProductStock().getQuantity();
					ProductStock newStock = new ProductStock(p1, quantity - 1);
					warehouse.replaceProduct(filter.getFoundProductStock(), newStock);
					warehouse.serialize();
				}
			}
			opdept.addOrder(newOrder);
			opdept.serialize();
			JOptionPane.showMessageDialog(null, "Order confirmed");
		} else if (e.getActionCommand().equals("back2")) {
			adminPanel.setVisible(false);
			logInPanel.setVisible(true);
			frame.add(logInPanel);
		} else if (e.getActionCommand().equals("viewCust")) {
			updateCustomers();
			customersTablePanel.add(customersTable, BorderLayout.CENTER);
			customersTablePanel.add(back3, BorderLayout.SOUTH);
			adminPanel.setVisible(false);
			customersTablePanel.setVisible(true);
			frame.add(customersTablePanel);
		} else if (e.getActionCommand().equals("back3")) {
			customersTablePanel.setVisible(false);
			adminPanel.setVisible(true);
			frame.add(adminPanel);
		} else if (e.getActionCommand().equals("viewWare")) {
			updateWarehouse();
			warehouseTablePanel.add(warehouseTable, BorderLayout.CENTER);
			warehouseTablePanel.add(back4, BorderLayout.SOUTH);
			adminPanel.setVisible(false);
			warehouseTablePanel.setVisible(true);
			frame.add(warehouseTablePanel);
		} else if (e.getActionCommand().equals("back4")) {
			warehouseTablePanel.setVisible(false);
			adminPanel.setVisible(true);
			frame.add(adminPanel);
		} else if (e.getActionCommand().equals("viewOrders")) {
			updateOrders();
			ordersTablePanel.add(ordersTable, BorderLayout.CENTER);
			ordersTablePanel.add(back5, BorderLayout.SOUTH);
			ordersTablePanel.setVisible(true);
			adminPanel.setVisible(false);
			frame.add(ordersTablePanel);
		} else if (e.getActionCommand().equals("back5")) {
			ordersTablePanel.setVisible(false);
			adminPanel.setVisible(true);
			frame.add(adminPanel);
		} else if (e.getActionCommand().equals("back6")) {
			orderPanel.setVisible(false);
			logInPanel.setVisible(true);
			frame.add(logInPanel);
		} else if (e.getActionCommand().equals("addStock")) {
			boolean found = false;
			String name = JOptionPane.showInputDialog("Enter product name");
			int price;
			int quantity;
			found = filter.filterProductStock(warehouse, name);
			if (found) {
				JOptionPane.showMessageDialog(null, "Products already exists in stock");
			} else {
				price = Integer.parseInt(JOptionPane.showInputDialog("Enter price"));
				quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity"));
				Product newProduct = new Product(name, price);
				ProductStock newStock = new ProductStock(newProduct, quantity);
				warehouse.addProduct(newStock);
				warehouse.serialize();
			}
		} else if (e.getActionCommand().equals("removeStock")) {
			String name = JOptionPane.showInputDialog("Enter product name");
			ProductStock product = null;
			boolean found = filter.filterProductStock(warehouse, name);
			if (found) {
				product = filter.getFoundProductStock();
				warehouse.removeProduct(product);
				warehouse.serialize();
			} else
				JOptionPane.showMessageDialog(null, "Product not found in stock");

		} else if (e.getActionCommand().equals("updateStock")) {
			String name = JOptionPane.showInputDialog("Enter product name");
			ProductStock product = null;
			boolean found = filter.filterProductStock(warehouse, name);
			if (found) {
				int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter updated stock"));
				product = filter.getFoundProductStock();
				product.displayProductStock();
				product.setQuantity(quantity);
				product.displayProductStock();
				warehouse.replaceProduct(filter.getFoundProductStock(), product);
				warehouse.serialize();
			} else
				JOptionPane.showMessageDialog(null, "Product not found in stock");
		}
	}

	public void updateWarehouse() {
		for (int j = stockTableModel.getRowCount() - 1; j >= 0; j--) {
			stockTableModel.removeProduct(j);
		}
		// warehouse.getProducts().clear();
		TreeSet<ProductStock> products;
		products = warehouse.deserialize();
		for (ProductStock p : products) {
			p.displayProductStock();
			stockTableModel.addProduct(p);
		}
		warehouseTable = new WarehouseTable(stockTableModel);
		warehouseTablePanel.setLayout(new BorderLayout());
	}

	public void updateCustomers() {
		for (int j = customersTableModel.getRowCount() - 1; j >= 0; j--) {
			customersTableModel.removeCustomer(j);
		}
		// shopCustomers.getCustomers().clear();
		TreeSet<Customer> customers;
		customers = shopCustomers.deserialize();
		for (Customer c : customers) {
			customersTableModel.addCustomer(c);
		}
		customersTable = new CustomersTable(customersTableModel);
		customersTablePanel.setLayout(new BorderLayout());
	}

	public void updateOrders() {
		for (int j = ordersTableModel.getRowCount() - 1; j >= 0; j--) {
			ordersTableModel.removeOrder(j);
		}
		// opdept.getOrders().clear();
		TreeSet<Order> orders;
		orders = opdept.deserialize();
		for (Order o : orders) {
			ordersTableModel.addOrder(o);
		}
		ordersTable = new OrdersTable(ordersTableModel);
		ordersTablePanel.setLayout(new BorderLayout());
	}
}
