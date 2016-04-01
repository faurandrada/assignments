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

import app.model.Product;
import app.model.tree.Node;
import app.model.tree.OrderNode;

/**
 * Class that is used for creating the panel visible by the admin user.
 * 
 * @author Bogdan
 * 
 */
@SuppressWarnings("serial")
public class AdminPanel extends JPanel {
	private JButton view = new JButton("Orders");
	private JButton add = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private JButton search = new JButton("Search");
	private JButton update = new JButton("Update");
	private JButton increase = new JButton("Increase");
	private JButton decrease = new JButton("Decrease");
	private JButton overStock = new JButton("Over-stock");
	private JButton underStock = new JButton("Und-stock");
	private JLabel label = new JLabel("Current available items");
	private JTextArea info = new JTextArea();
	private JScrollPane scroll = new JScrollPane(info);
	private JLabel greetLabel = new JLabel("Welcome admin");
	private JButton all = new JButton("Clear Filter");

	public AdminPanel(LoginController loginController, JFrame frame) {

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
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(5,1));
		bPanel.setBackground(Color.GRAY);

		bPanel.add(this.search);
		addSearch(loginController, frame);

		bPanel.add(this.all);
		addAll(loginController, frame);

		bPanel.add(this.add);
		add(loginController);

		bPanel.add(this.delete);
		addDelete(loginController, frame);

		bPanel.add(this.update);
		addUpdate(loginController, frame);

		bPanel.add(this.increase);
		addIncrease(loginController, frame);

		bPanel.add(this.decrease);
		addDecrease(loginController, frame);
	
		bPanel.add(this.overStock);
		addOverStock(loginController, frame);

		bPanel.add(this.underStock);
		addUnderStock(loginController, frame);
		
		bPanel.add(this.view);
		addExit(loginController, frame);
		
		add(bPanel,BorderLayout.EAST);

		info.setEditable(false);
		add(this.info);
		scroll.setPreferredSize(new Dimension(150, 110));
		scroll.setViewportView(info);
		add(scroll, BorderLayout.PAGE_END);
	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

	private void addExit(LoginController loginController, JFrame frame) {
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				info.setText("");
				info.append("From customer " + loginController.getCustomerName() + "\n");
				int i = 1;
				for (Node order : loginController.getOpDept().getAllOrders()) {
					info.append("Order nr. " + i + " : " + loginController.getOpDept().getOrderName((OrderNode) order)
							+ " with the quantity of " + loginController.getOpDept().getOrderQuantity((OrderNode) order) + "\n");
					i++;
				}
			
			}
		});
	}

	private void addDelete(LoginController loginController, JFrame frame) {
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = loginController.getItemList().getSelectedIndex();
				if (selectedIndex != -1) {
					loginController.deleteProduct(loginController
							.getProductByName((String) loginController.getItemList().getSelectedValue()));
					loginController.getListModel().remove(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from the list.");
				}
			}
		});
	}

	private void add(LoginController loginController) {
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel nameLabel = new JLabel("Name of the item: ");
				JTextField name = new JTextField();
				JLabel descriptionLabel = new JLabel("Description of the item: ");
				JTextField description = new JTextField();
				JLabel numberLabel = new JLabel("Number of items added: ");
				JTextField number = new JTextField();
				Object[] ob = { nameLabel, name, descriptionLabel, description, numberLabel, number };
				int result = JOptionPane.showConfirmDialog(null, ob, "Adding", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						Product newProduct = new Product(loginController.getMaxProductID() + 1, name.getText(),
								description.getText(), (Integer.parseInt(number.getText())));
						loginController.addNewProduct(newProduct);
					} catch (NumberFormatException ex) {
						System.out.println("Invalid number entered");
					}

				}
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


	private void addOverStock(LoginController loginController, JFrame frame) {
		overStock.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				loginController.getListModel().removeAllElements();
				for (Node product : loginController.getAllProducts()) {
					if (loginController.getProductByStock(product.getNodeName()) > 10) {
						loginController.getListModel().addElement(product.getNodeName());
					}
				}

			}
		});
	}

	private void addUnderStock(LoginController loginController, JFrame frame) {
		underStock.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				loginController.getListModel().removeAllElements();
				for (Node product : loginController.getAllProducts()) {
					if (loginController.getProductByStock(product.getNodeName()) == 0) {
						loginController.getListModel().addElement(product.getNodeName());
					}
				}

			}
		});
	}

	private void addUpdate(LoginController loginController, JFrame frame) {
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginController
						.getProductByName((String) loginController.getItemList().getSelectedValue()) != null) {
					JLabel updateLabel = new JLabel("Update stock: ");
					JTextField update = new JTextField();
					Object[] ob = { updateLabel, update };
					int result = JOptionPane.showConfirmDialog(null, ob, "Updating", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Product product = loginController
								.getProductByName((String) loginController.getItemList().getSelectedValue());
						product.setStock(Integer.parseInt(update.getText()));
						getInfo().setText("Product Name: " + product.getName() + "\nStock: " + product.getStock() + "\nDescription: " + product.getDescription());
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from the list.");
				}
			}
		});
	}

	private void addIncrease(LoginController loginController, JFrame frame) {
		increase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Product product = loginController
						.getProductByName((String) loginController.getItemList().getSelectedValue());
				if (product != null) {
					int increase = product.getStock() + 1;
					product.setStock(increase);
					getInfo().setText("Product Name: " + product.getName() + "\nStock: " + product.getStock() +  "\nDescription: " + product.getDescription());
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from the list.");
				}
			}
		});
	}

	private void addDecrease(LoginController loginController, JFrame frame) {
		decrease.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Product product = loginController
						.getProductByName((String) loginController.getItemList().getSelectedValue());
				if (product != null) {
					if (product.getStock() > 0) {
						int decrease = product.getStock() - 1;
						product.setStock(decrease);
					}
					if (product.getStock() == 0) {
						JOptionPane.showMessageDialog(frame, "The stock is empty");
					}
					getInfo().setText("Product Name: " + product.getName() + "\nStock: " + product.getStock() +  "\nDescription: " + product.getDescription());
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from the list.");
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

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(JTextArea info) {
		this.info = info;
	}

}
