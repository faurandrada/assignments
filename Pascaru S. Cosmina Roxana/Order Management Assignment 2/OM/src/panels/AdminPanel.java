package panels;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import groups.ShopCustomers;

public class AdminPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton viewCustomers = new JButton("View Customers");
	private JButton viewWarehouse = new JButton("View Warehouse");
	private JButton viewOrders = new JButton("View Orders");
	private JButton addProduct = new JButton("Add Product");
	private JButton removeProduct = new JButton("Remove Product");
	private JButton updateStock = new JButton("Update Stock");
	private JButton back = new JButton("Back");

	public AdminPanel(JButton viewCustomers, JButton viewWarehouse, JButton viewOrders, JButton addProduct,
			JButton removeProduct, JButton updateStock, JButton back) {
		this.viewCustomers = viewCustomers;
		this.viewWarehouse = viewWarehouse;
		this.viewOrders = viewOrders;
		this.addProduct = addProduct;
		this.removeProduct = removeProduct;
		this.updateStock = updateStock;
		setLayout(new GridLayout(4, 2));

		add(viewCustomers);
		add(viewWarehouse);
		add(viewOrders);
		add(addProduct);
		add(removeProduct);
		add(updateStock);
		add(back);
		setVisible(true);
	}

}
