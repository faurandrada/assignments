package Control;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Orders.OPDept;
import Orders.Order;
import Orders.OrdersTable;
import Stock.Product;
import Stock.ProductsTable;
import Stock.Warehouse;
import UserInterface.OutputFrame;
import Users.CustomersTable;
import Users.User;
import Users.UsersData;

public class AdminActions {

	private UsersData usersData = new UsersData();
	private Warehouse warehouse = new Warehouse();
	private OPDept opdept = new OPDept();
	private OutputFrame outputFrame;
	private String nameOfTheAdmin;

	public AdminActions(String nameOfTheAdmin) {
		this.nameOfTheAdmin = nameOfTheAdmin;
	}

	public void customerRemoval() {
		JTextField customerName = new JTextField(10);
		Object[] message = { "Input the name of the customer that you want to remove", customerName };
		int option = JOptionPane.showConfirmDialog(null, message, "Remove Customer", JOptionPane.OK_CANCEL_OPTION);
		boolean customerFound = false;

		usersData.updateUsers();

		if (option == JOptionPane.OK_OPTION) {
			for (User user : usersData.getUsers()) {
				if (customerName.getText().equals(user.getName())) {
					customerFound = true;
					if (user.isCustomer()) {
						usersData.removeUser(user);
						JOptionPane.showMessageDialog(null, "Customer successfully deleted", "Success",
								JOptionPane.INFORMATION_MESSAGE);

						outputFrame.getOutputFrame().hide();

						System.out.println("Updated users");
						System.out.println("******************************************");
						usersData.seeAllUsers();

						break;
					} else {
						JOptionPane.showMessageDialog(null, "Cannot remove this user because it's an ADMIN",
								"ERROR removing User", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			if (!customerFound)
				JOptionPane.showMessageDialog(null, "This user does not exist!", "Inexisting user",
						JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addProductToStock() {

		JTextField IDField = new JTextField(10);
		JTextField typeField = new JTextField(10);
		JTextField makeField = new JTextField(10);
		JTextField modelField = new JTextField(10);
		JTextField priceField = new JTextField(10);
		JTextField quantityField = new JTextField(10);

		boolean validProduct = true;

		Object[] message = { "Product ID: ", IDField, "Product type: ", typeField, "Product make: ", makeField,
				"Product model: ", modelField, "Product price: ", priceField, "Product quantity: ", quantityField };

		int option = JOptionPane.showConfirmDialog(null, message, "New Product", JOptionPane.OK_CANCEL_OPTION);

		warehouse.updateStock();

		if (option == JOptionPane.OK_OPTION) {

			double price = 0.0;
			try {
				price = Double.parseDouble(priceField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid numeric format for field Price !", "ERROR at Price",
						JOptionPane.ERROR_MESSAGE);
				validProduct = false;
			}
			int quantity = 0;
			try {
				quantity = Integer.parseInt(quantityField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid numeric format for field Quantity !", "ERROR at Quantity",
						JOptionPane.ERROR_MESSAGE);
				validProduct = false;
			}

			for (Product product : warehouse.getStock()) {
				if (IDField.getText().equals(product.getID())) {
					validProduct = false;
					JOptionPane.showMessageDialog(null, "This ID already exists for " + product + "!!!", "Invalid ID",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}

			if (validProduct) {
				Product productCreated = new Product(IDField.getText(), typeField.getText(), makeField.getText(),
						modelField.getText(), price, quantity);
				warehouse.addProduct(productCreated);

				JOptionPane.showMessageDialog(null, "Product successfully added", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				outputFrame.getOutputFrame().hide();

				System.out.println("Updated stock");
				System.out.println("******************************************");
				warehouse.seeStock();
			}
		}

	}

	public void removeProductFromStock() {

		JTextField field = new JTextField(10);
		boolean foundID = false;
		Object[] message = { "Input the ID of the product you want to remove", field };
		int option = JOptionPane.showConfirmDialog(null, message, "Remove Product", JOptionPane.OK_CANCEL_OPTION);

		warehouse.updateStock();

		if (option == JOptionPane.OK_OPTION) {
			for (Product product : warehouse.getStock()) {
				if (field.getText().equals(product.getID())) {
					foundID = true;
					warehouse.removeProduct(product);
					JOptionPane.showMessageDialog(null, "Product successfully removed", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					outputFrame.getOutputFrame().hide();

					System.out.println("Updated stock");
					System.out.println("******************************************");
					warehouse.seeStock();
					break;
				}
			}

			if (!foundID) {
				JOptionPane.showMessageDialog(null, "Product ID does not exist in the warehouse", "ERROR invalid ID",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void showWarehouse() {
		ProductsTable pt = new ProductsTable();
		outputFrame = new OutputFrame(pt.getTable(), "Products");
	}

	public void modifyStock() {
		JTextField fieldForID = new JTextField(10);
		boolean foundID = false;
		Object[] message = { "Input the ID of the product you want to modify", fieldForID };
		int option = JOptionPane.showConfirmDialog(null, message, "Modify Stock", JOptionPane.OK_CANCEL_OPTION);

		warehouse.updateStock();

		if (option == JOptionPane.OK_OPTION) {
			for (Product product : warehouse.getStock()) {
				if (fieldForID.getText().equals(product.getID())) {
					foundID = true;
					JTextField fieldForQuantity = new JTextField(10);
					Object[] message2 = { "Current items in the stock: ", product.getQuantity(),
							"New number of items: ", fieldForQuantity };
					int option2 = JOptionPane.showConfirmDialog(null, message2, "Configure Stock",
							JOptionPane.OK_CANCEL_OPTION);

					if (option2 == JOptionPane.OK_OPTION) {
						int newQuantity = 0;
						boolean validQuantity = true;
						try {
							newQuantity = Integer.parseInt(fieldForQuantity.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Invalid numeric format for Quantity !",
									"ERROR numeric format", JOptionPane.ERROR_MESSAGE);
							validQuantity = false;
						}
						if (newQuantity < 0) {
							validQuantity = false;
							JOptionPane.showMessageDialog(null, "Stock must be a positive integer number !",
									"ERROR invalid quantity", JOptionPane.ERROR_MESSAGE);
						}
						if (validQuantity) {
							product.setQuantity(newQuantity);
							warehouse.modifyCurrentStock();
							JOptionPane.showMessageDialog(null, "Stock successfully modified", "Success",
									JOptionPane.INFORMATION_MESSAGE);

							outputFrame.getOutputFrame().hide();

							System.out.println("Updated stock");
							System.out.println("******************************************");
							warehouse.seeStock();
						}
					}

				}
			}
			if (!foundID) {
				JOptionPane.showMessageDialog(null, "No product found with this ID", "Invalid ID",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void seeOrders() {
		OrdersTable ot = new OrdersTable();
		outputFrame = new OutputFrame(ot.getTable(), "Orders");
	}

	public void seeCustomers() {
		CustomersTable ct = new CustomersTable();
		outputFrame = new OutputFrame(ct.getTable(), "Customers");
	}

	public void manageOrders() {
		warehouse.updateStock();
		opdept.updateOrders();
		for (Order order : opdept.getOrders()) {
			if (order.getStatus().equals("Pending")) {
				order.setStatus("Delivered");
				for(Product product : warehouse.getStock()){
					if(product.getID().equals(order.getProductID())){
						product.setQuantity(product.getQuantity() - order.getQuantity());
					}
				}
			}
			
		}
		JOptionPane.showMessageDialog(null, "Pending orders have been successfully delivered !", "Orders Managed",
				JOptionPane.INFORMATION_MESSAGE);
		outputFrame.getOutputFrame().hide();
		
		warehouse.modifyCurrentStock();
		opdept.modifyCurrentOrders();
	}
}
