package app.graphic;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.data.OPDept;
import app.data.Warehouse;
import app.model.Customer;
import app.model.Product;
import app.model.tree.Node;

/**
 * Class that holds the main functionality for our application. Here we create
 * the main JFrame in which a certain panel is attached based on the result of
 * the login. We can have two possible views, one for the admin and one for the
 * customer. Also here we serialize data from our Order and Product model to the
 * disk via our WindowListener.
 * 
 * @author Bogdan
 * 
 */
public class LoginController {
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel = new DefaultListModel();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JList itemList = new JList(listModel);
	private Warehouse warehouse = new Warehouse();
	private ListSelectionModel listSelectionModel;
	private AdminPanel adminPanel;
	private CustomerPanel customerPanel;
	private OPDept opDept = new OPDept();
	private Customer customer = new Customer("David", 6);
	UserType userType;

	public LoginController() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		updateListFromWarehouse(warehouse.getAllProducts());
		UserType result = login();

		if (result.equals(UserType.ADMIN)) {
			adminPanel = new AdminPanel(this, frame);
			frame.setVisible(true);
			frame.setTitle("Admin");
			frame.add(adminPanel, BorderLayout.CENTER);
		} else if (result.equals(UserType.CUSTOMER)) {
			customerPanel = new CustomerPanel(this, frame);
			frame.setVisible(true);
			frame.setTitle("Customer");
			frame.add(customerPanel, BorderLayout.CENTER);
		}

		if (result.equals(UserType.ADMIN)) {
			ListSelectionListener listSelectionListener = new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent listSelectionEvent) {
					if (itemList.getSelectedValue() != null) {
						Product product = getProductByName((String) itemList.getSelectedValue());
						if (product != null) {
							adminPanel.getInfo().setText("Product Name: " + product.getName() + "\nStock: "
									+ product.getStock() + "\nDescription: " + product.getDescription());
						}

					} else {
						adminPanel.getInfo().setText("");
					}

				}
			};
			itemList.addListSelectionListener(listSelectionListener);
		} else if (result.equals(UserType.CUSTOMER)) {
			ListSelectionListener listSelectionListener = new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent listSelectionEvent) {
					if (itemList.getSelectedValue() != null) {
						Product product = getProductByName((String) itemList.getSelectedValue());
						if (product != null) {
							customerPanel.getInfo().setText("Product Name: " + product.getName() + "\nStock: "
									+ product.getStock() + "\nDescription: " + product.getDescription());
						}
					} else {
						customerPanel.getInfo().setText("");
					}

				}
			};
			itemList.addListSelectionListener(listSelectionListener);
		}

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveDataToDisk(warehouse.getTree(), "C:\\products.ser");
				if (result.equals(UserType.CUSTOMER)) {
					saveDataToDisk(opDept.getTree(), "C:\\orders.ser");
				}
			}
		});
	}

	protected void saveDataToDisk(Object object, String path) {
		try {

			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + path + "\n");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void updateListFromWarehouse(List<Node> products) {
		listModel.removeAllElements();
		for (Node product : products) {
			listModel.addElement(product.getNodeName());
		}
		itemList.updateUI();
	}

	private UserType login() {
		JLabel usernameLabel = new JLabel("Username");
		JTextField user = new JTextField();
		JLabel passwordLabel = new JLabel("Password");
		JPasswordField pass = new JPasswordField();
		Object[] ob = { usernameLabel, user, passwordLabel, pass };
		int result = JOptionPane.showConfirmDialog(null, ob, "Login", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (user.getText().equals("admin")
					&& (Arrays.equals(pass.getPassword(), new char[] { '1', '2', '3', '4', '5' }))) {
				return UserType.ADMIN;
			} else if (user.getText().equals("customer")
					&& (Arrays.equals(pass.getPassword(), new char[] { '1', '2', '3' }))) {
				return UserType.CUSTOMER;
			} else {
				JOptionPane.showMessageDialog(this.getItemList(), "Invalid user or password");
				return login();
			}
		}
		return UserType.NONE;
	}

	@SuppressWarnings("rawtypes")
	public DefaultListModel getListModel() {
		return listModel;
	}

	@SuppressWarnings("rawtypes")
	public JList getItemList() {
		return itemList;
	}

	@SuppressWarnings("unchecked")
	public void addNewProduct(Product newProduct) {
		warehouse.addNewProduct(newProduct);
		listModel.addElement(newProduct.getName());
	}

	public void deleteProduct(Product product) {
		warehouse.deleteProduct(product);
	}

	public Product getProductByName(String name) {
		return warehouse.getProductByName(name);
	}

	public void modifyStock(int productID, int stock) {
		warehouse.updateProduct(productID, stock);
	}

	public List<Node> getAllProducts() {
		return warehouse.getAllProducts();
	}

	public List<Node> getProductsByFilter(String filter) {
		return warehouse.getProductsByFilter(filter);
	}

	public int getProductByStock(String productNode) {
		return warehouse.getProductStockByName(productNode);
	}

	public int getMaxProductID() {
		return warehouse.getMaxProductID();
	}

	/**
	 * @return the listSelectionModel
	 */
	public ListSelectionModel getListSelectionModel() {
		return listSelectionModel;
	}

	/**
	 * @return the opDept
	 */
	public OPDept getOpDept() {
		return opDept;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	public String getCustomerName() {
		return customer.getName();
	}

}
