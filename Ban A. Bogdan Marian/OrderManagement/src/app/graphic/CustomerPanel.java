package app.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.model.Order;
import app.model.Product;
import app.model.tree.Node;
import app.model.tree.OrderNode;

/**
 * Class that is used for creating the panel visible by the customer user.
 * 
 * @author Bogdan
 * 
 */
@SuppressWarnings("serial")
public class CustomerPanel extends JPanel {
	private JButton search = new JButton("Search");
	private JButton order = new JButton("Order");
	private JButton history = new JButton("History");
	private JLabel greetLabel = new JLabel("Welcome Customer");
	private JLabel label = new JLabel("Current available items");
	private JTextArea historyInfo = new JTextArea();
	private JTextArea info = new JTextArea();
	private JScrollPane scroll = new JScrollPane(historyInfo);
	private JButton exit = new JButton("Log out");
	private JButton all = new JButton("Clear Filter");

	public CustomerPanel(LoginController loginController, JFrame frame) {
		this.setLayout(new BorderLayout());
		add(this.greetLabel, BorderLayout.PAGE_START);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.GRAY);

		label.setForeground(Color.WHITE);
		label.setAlignmentX(0f);
		JScrollPane sp = new JScrollPane(loginController.getItemList());
		sp.setPreferredSize(new Dimension(300, 200));
		sp.setViewportView(loginController.getItemList());
		panel.add(label, BorderLayout.NORTH);
		info.setEditable(false);
		panel.add(sp, BorderLayout.CENTER);
		panel.add(this.info, BorderLayout.SOUTH);
		add(panel, BorderLayout.LINE_START);

		// buttons
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(5, 1));
		bPanel.setBackground(Color.GRAY);

		bPanel.add(this.search);
		addSearch(loginController, frame);

		bPanel.add(this.order);
		addOrder(loginController, frame);

		bPanel.add(this.history);
		addHistory(loginController, frame);

		bPanel.add(this.all);
		addAll(loginController, frame);

		bPanel.add(this.exit);
		addExit(frame);

		add(bPanel, BorderLayout.CENTER);

		historyInfo.setEditable(false);
		scroll.setPreferredSize(new Dimension(150, 110));
		scroll.setViewportView(historyInfo);
		add(scroll, BorderLayout.PAGE_END);

	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

	private void addExit(JFrame frame) {
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void addSearch(LoginController loginController, JFrame frame) {
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel searchLabel = new JLabel("Search item: ");
				JTextField search = new JTextField();
				Object[] ob = { searchLabel, search };
				int result = JOptionPane.showConfirmDialog(null, ob, "Searching", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					loginController.updateListFromWarehouse(loginController.getProductsByFilter(search.getText()));
				}
			}
		});
	}

	private void addOrder(LoginController loginController, JFrame frame) {
		order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedProduct = (String) loginController.getItemList().getSelectedValue();
				if (selectedProduct != null) {
					JLabel questionLabel = new JLabel("How many items? ");
					JTextField number = new JTextField();
					Object[] ob = { questionLabel, number };
					int result = JOptionPane.showConfirmDialog(null, ob, "Confirm order", JOptionPane.OK_CANCEL_OPTION);
					int orderID = 1;
					Product product = loginController
							.getProductByName((String) loginController.getItemList().getSelectedValue());
					if (result == JOptionPane.OK_OPTION) {
						try {

							int intNumber = Integer.parseInt(number.getText());
							if (product.getStock() >= intNumber) {
								Order order = new Order(orderID, loginController.getCustomer(), product, intNumber);
								loginController.getOpDept().addNewOrder(order);
								if (order != null) {
									JOptionPane.showMessageDialog(frame, "You ordered the product " + product.getName()
											+ " with the quantity of " + intNumber);
									product.setStock(product.getStock() - intNumber);
								}
							} else {
								JOptionPane.showMessageDialog(frame, "No available items in the stock");
							}
						} catch (NumberFormatException ex) {
							System.out.println("Invalid number entered");
						}

					}
					orderID++;
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from the list.");
				}
			}
		});
	}

	private void addHistory(LoginController loginController, JFrame frame) {
		history.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				historyInfo.setText("");
				historyInfo.append("Your history: \n");
				int i = 1;
				for (Node order : loginController.getOpDept().getAllOrders()) {
					historyInfo.append("Order nr. " + i + " : "
							+ loginController.getOpDept().getOrderName((OrderNode) order) + " with the quantity of "
							+ loginController.getOpDept().getOrderQuantity((OrderNode) order) + "\n");
					i++;
				}
			}
		});
	}

	private void addAll(LoginController loginController, JFrame frame) {
		all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginController.updateListFromWarehouse(loginController.getAllProducts());
			}
		});
	}

	/**
	 * @return the info
	 */
	public JTextArea getInfo() {
		return info;
	}
}
