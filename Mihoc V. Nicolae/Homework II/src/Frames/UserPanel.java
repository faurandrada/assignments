package Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Design.OPDept;
import Design.Order;
import Design.Product;
import Design.Warehouse;
import Read_Write.InputOutput;

public class UserPanel {
	private JFrame user;
	private JLabel prod;
	private JTextField prodbox;
	private JLabel amount;
	private JTextField amountbox;
	private JLabel status;
	private JTextField statusbox;
	private JButton placeorder;
	private JTable table;
	private JLabel orders;
	private JLabel existprod;
	private static DefaultTableModel tablemodel;
	private static DefaultTableModel tablemodel2;
	private JButton search;
	private JLabel idlabel;
	private JTextField idbox;
	JTable table2 = new JTable();

	private Iterator<Product> it;
	private Warehouse w;
	private Product p;
	private InputOutput x;
	private OPDept y;

	public UserPanel() {
		user = new JFrame("Customer Panel");
		user.setSize(500, 500);
		user.setVisible(true);
		user.setLocationRelativeTo(null);
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user.setLayout(null);

		prod = new JLabel("Product to Order: ");
		prodbox = new JTextField();
		amount = new JLabel("Quantity: ");
		amountbox = new JTextField();
		status = new JLabel("Status: ");
		statusbox = new JTextField();
		placeorder = new JButton("Place Order");
		orders = new JLabel("Orders: ");
		existprod = new JLabel("Existing Products: ");
		idlabel = new JLabel("ID: ");
		idbox = new JTextField();

		idlabel.setBounds(30, 10, 80, 30);
		user.add(idlabel);
		idbox.setBounds(20, 40, 30, 20);
		user.add(idbox);

		prod.setBounds(80, 10, 120, 30);
		user.add(prod);
		prodbox.setBounds(80, 40, 100, 20);
		user.add(prodbox);

		amount.setBounds(200, 10, 100, 30);
		user.add(amount);
		amountbox.setBounds(200, 40, 50, 20);
		user.add(amountbox);

		placeorder = new JButton("Place Order");
		placeorder.setBounds(350, 30, 120, 30);
		user.add(placeorder);

		search = new JButton("Search");
		search.setBounds(270, 30, 80, 30);
		user.add(search);

		status.setBounds(20, 220, 100, 30);
		user.add(status);
		statusbox.setBounds(70, 225, 300, 20);
		statusbox.setEditable(false);
		user.add(statusbox);

		existprod.setBounds(20, 260, 150, 30);
		user.add(existprod);

		orders.setBounds(20, 90, 100, 30);
		user.add(orders);

		JScrollPane pane = new JScrollPane();
		JScrollPane pane2 = new JScrollPane();

		pane2.setViewportView(table2);
		setTable(new JTable());
		tablemodel = new DefaultTableModel(new Object[] { "Product Name", "Quantity", "Price" }, 0);
		tablemodel2 = new DefaultTableModel(new Object[] { "Order ID", "Product Name", "Quantity", "Price" }, 0);
		getTable().setModel(tablemodel);
		table2.setModel(tablemodel2);
		pane.setViewportView(getTable());
		pane.setBounds(20, 300, 250, 150);
		pane2.setBounds(20, 110, 450, 100);
		user.add(pane);
		user.add(pane2);

		w = new Warehouse();
		x = new InputOutput();
		y = new OPDept();
		w = x.deserWarehouse();
		y = x.deserOrders();

		search.addActionListener(new SearchBActionListener());
		placeorder.addActionListener(new PlaceOrderActionListener());

	}

	private void printOrder(Order o) {
		Object[] row = { o.getID(), o.getName(), o.getQuantity(), p.getPrice() };
		((DefaultTableModel) getModel2()).addRow(row);
	}

	class SearchBActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			it = w.getProductsInOrder();
			boolean y = false;
			try {
				while (it.hasNext()) {
					p = it.next();
					if (p.getName().equals(prodbox.getText())) {
						y = true;
					}
				}
				if (y == false) {
					JOptionPane.showMessageDialog(null, "There is no product!");
				} else {
					JOptionPane.showMessageDialog(null, "Product found!");
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Try Again!");
			}
		}
	}

	class PlaceOrderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			it = w.getProductsInOrder();
			int row = AdminPanel.getRow(getTable().getModel(), prodbox.getText());
			int row2= AdminPanel.getRow(getModel2(),amountbox.getText());
			Integer quant = (Integer) table.getModel().getValueAt(row, 1);
			Integer aux= (Integer) table2.getModel().getValueAt(row, 2);
			while(it.hasNext())
			{
				p = it.next();
				if (p.getName().equals(prodbox.getText()) && Integer.parseInt(amountbox.getText())<=p.getQuantity()) {
					Order o = new Order(Integer.parseInt(idbox.getText()), prodbox.getText(),
							Integer.parseInt(amountbox.getText()),
							p.getPrice() * Integer.parseInt(amountbox.getText()));
					y.addOrder(o);
					table.getModel().setValueAt(new Integer(quant - Integer.parseInt(amountbox.getText())), row, 1);
					p.setQuantity(new Integer(p.getQuantity() - Integer.parseInt(amountbox.getText())));
					table2.getModel().setValueAt(new Integer(aux * Integer.parseInt(amountbox.getText())), row2,3);
					o.setPrice(p.getPrice() * Integer.parseInt(amountbox.getText()));
					printOrder(o);
					statusbox.setText("Order success!");
				} else {
					statusbox.setText("Not enough products in stock!");
				}
			}
			x.serWarehouse(w);
			x.serOrders(y);
		}
	}

	public static Object getModel() {
		return tablemodel;
	}

	public static TableModel getModel2() {
		return tablemodel2;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
}
