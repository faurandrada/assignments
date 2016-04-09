package Control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Orders.OPDept;
import Orders.Order;
import Stock.Product;
import Stock.ProductsTable;
import Stock.Warehouse;
import UserInterface.OutputFrame;

public class CustomerActions {

	private OutputFrame outputFrame;
	private Warehouse warehouse;
	private OPDept opdept;
	private Order order;
	private String nameOfTheCustomer;

	public CustomerActions(String nameOfTheCustomer) {
		this.nameOfTheCustomer = nameOfTheCustomer;
	}

	public void seeProducts() {
		ProductsTable pt = new ProductsTable();
		outputFrame = new OutputFrame(pt.getTable(), "Products");
	}

	public void orderProduct() {
		warehouse = new Warehouse();
		opdept = new OPDept();
		opdept.updateOrders();
		warehouse.updateStock();

		boolean foundID = false;
		boolean validQuantity = true;
		JTextField idField = new JTextField(10);
		JTextField quantityField = new JTextField(10);
		Object[] message = { "Input the ID of the product", idField, "Input the number of items", quantityField };

		int quantity = 0;

		int option = JOptionPane.showConfirmDialog(null, message, "Order Product", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			try {
				quantity = Integer.parseInt(quantityField.getText());
			} catch (NumberFormatException e) {
				validQuantity = false;
				JOptionPane.showMessageDialog(null, "Invalid numeric format for Quantity !", "ERROR quantity field",
						JOptionPane.ERROR_MESSAGE);
			}

			for (Product product : warehouse.getStock()) {
				if (idField.getText().equals(product.getID())) {
					foundID = true;
					if (product.getQuantity() < quantity) {
						validQuantity = false;
						JOptionPane.showMessageDialog(null, "Quantity ordered is too large", "Invalid Quantity",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (validQuantity) {
							order = new Order(product.getID(), quantity, nameOfTheCustomer);
							order.setStatus("Pending");
							opdept.addOrder(order);
							opdept.updateOrders();
							JOptionPane.showMessageDialog(null, "Order successfully placed !", "Thnaks for buying",
									JOptionPane.INFORMATION_MESSAGE);
							outputFrame.getOutputFrame().hide();
						}
					}

				}
			}

			if (!foundID) {
				JOptionPane.showMessageDialog(null, "No product has this ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void filterProducts() {
		warehouse = new Warehouse();
		warehouse.updateStock();

		JTextField makeField = new JTextField(10);
		JTextField typeField = new JTextField(10);
		JTextField priceField = new JTextField(10);

		Object[] message = { "Make", makeField, "Type", typeField, "Price", priceField };
		int option = JOptionPane.showConfirmDialog(null, message, "Filter Products", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {

			String[] header = { "ID", "Type", "Make", "Model", "Price", "Quantity" };
			String make = makeField.getText();
			String type = typeField.getText();
			double price = 0.0;
			boolean validPrice = true;

			boolean less = false;
			boolean greater = false;
			boolean validSign = true;

			if (!priceField.getText().isEmpty()) {

				less = false;
				greater = false;
				validSign = true;

				if (priceField.getText().substring(0, 1).equals("<")) {
					less = true;
				} else if (priceField.getText().substring(0, 1).equals(">")) {
					greater = true;
				} else
					validSign = false;

				try {
					price = Double.parseDouble(priceField.getText().substring(1));
				} catch (NumberFormatException e) {
					validPrice = false;
				}

				if (price < 0.0)
					validPrice = false;
				if (!validPrice)
					JOptionPane.showMessageDialog(null, "Invalid price !", "ERROR at price", JOptionPane.ERROR_MESSAGE);
			}
			Object[][] data = new Object[warehouse.getStock().size()][6];
			int i = 0;
			for (Product product : warehouse.getStock()) {
				if (!make.isEmpty()) {
					if (product.getMake().equals(make)) {
						data[i][0] = product.getID();
						data[i][1] = product.getType();
						data[i][2] = product.getMake();
						data[i][3] = product.getModel();
						data[i][4] = product.getPrice();
						data[i][5] = product.getQuantity();
						i++;
					}
				} else if (!type.isEmpty()) {
					if (product.getType().equals(type)) {
						data[i][0] = product.getID();
						data[i][1] = product.getType();
						data[i][2] = product.getMake();
						data[i][3] = product.getModel();
						data[i][4] = product.getPrice();
						data[i][5] = product.getQuantity();
						i++;
					}
				} else if (price != 0.0) {
					if (!validSign) {
						JOptionPane.showMessageDialog(null, "Invalid sign. Valid signs are (> or <)", "Invalid sign",
								JOptionPane.ERROR_MESSAGE);
						break;
					} else {
						if (less && validSign)
							if (product.getPrice() < price) {
								data[i][0] = product.getID();
								data[i][1] = product.getType();
								data[i][2] = product.getMake();
								data[i][3] = product.getModel();
								data[i][4] = product.getPrice();
								data[i][5] = product.getQuantity();
								i++;
							}
						if (greater && validSign)
							if (product.getPrice() > price) {
								data[i][0] = product.getID();
								data[i][1] = product.getType();
								data[i][2] = product.getMake();
								data[i][3] = product.getModel();
								data[i][4] = product.getPrice();
								data[i][5] = product.getQuantity();
								i++;
							}
					}
				}
			}

			JTable filteredTable = new JTable(data, header);

			filteredTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
			filteredTable.setFillsViewportHeight(true);
			filteredTable.setEnabled(false);
			filteredTable.setFont(new Font("SansSerif", Font.BOLD, 16));
			filteredTable.setForeground(Color.decode("0x121212"));
			filteredTable.setBackground(Color.decode("0xc9c9c9"));
			filteredTable.setGridColor(Color.WHITE);
			filteredTable.setRowHeight(25);
			outputFrame.getOutputFrame().hide();
			
			outputFrame = new OutputFrame(filteredTable, "Filtered Products");
		}
	}

	public void seeHistory() {
		opdept = new OPDept();
		opdept.updateOrders();

		String[] header = { "Order ID", "Customer Name", "Product ID", "Quantity", "Time", "Order Status" };
		Object[][] data = new Object[opdept.getOrders().size()][6];
		int i = 0;

		for (Order order : opdept.getOrders()) {
			if (order.getCustomerName().equals(nameOfTheCustomer)) {
				data[i][0] = order.getID();
				data[i][1] = order.getCustomerName();
				data[i][2] = order.getProductID();
				data[i][3] = order.getQuantity();
				data[i][4] = order.getTime();
				data[i][5] = order.getStatus();
				i++;
			}
		}
		
		JTable table = new JTable(data, header);

		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setForeground(Color.decode("0x121212"));
		table.setBackground(Color.decode("0xc9c9c9"));
		table.setGridColor(Color.WHITE);
		table.setRowHeight(25);
		
		outputFrame = new OutputFrame(table, "Orders History for customer " + nameOfTheCustomer);

	}
}
