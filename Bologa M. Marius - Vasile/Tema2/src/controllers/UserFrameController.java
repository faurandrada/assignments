package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.Customer;
import models.OPDept;
import models.Order;
import models.Product;
import models.Warehouse;
import views.LoginFrame;
import views.MessageDialogs;
import views.UserFrame;

public class UserFrameController {
	private static UserFrame frame = new UserFrame();
	private Warehouse stock = new Warehouse();
	private Product p;
	private Iterator<Product> itProduct;
	private OPDept order = new OPDept();
	private SerializableManager manager = new SerializableManager();

	public UserFrameController() {
		frame.setAddOrderButtonActionListener(new AddOrderButtonActionListener());
		frame.setSearchButtonActionListener(new SearchProductButtonActionListener());
		this.stock = manager.deserializeWharehouse();
		this.order = manager.deserializeOrders();
	}

	public Warehouse getStock() {
		return stock;
	}

	public void setStock(Warehouse stock) {
		this.stock = stock;
	}

	public class AddOrderButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String order1[] = MessageDialogs.addOrderWindow();
			int row1 = MessageDialogs.getRowByValue(UserFrame.getTable().getModel(), order1[1]);
			Integer quant=(Integer) UserFrame.getTable().getModel().getValueAt(row1, 2);
			if(order1[2].equals("0")){
				JOptionPane.showMessageDialog(null, "You have no product selected!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			else if(Integer.parseInt(order1[2])>quant){
				JOptionPane.showMessageDialog(null, "Not enough products on the stock!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}else{
			Customer cust = new Customer(order1[0]);
			Product p = new Product(Integer.parseInt(order1[3]), order1[1], Integer.parseInt(order1[2]));
			Order ord = new Order(order.getSize(), p, cust, Integer.parseInt(order1[2]));
			UserFrame.getTable().getModel().setValueAt(new Integer(quant - Integer.parseInt(order1[2])),
					row1, 2);
			order.addOrder(ord);
			Object[] row = { order.getSize(), LoginFrame.getUserName(), order1[1], order1[2], "Processing..." };
			((DefaultTableModel) UserFrame.getOrderTable().getModel()).addRow(row);
			itProduct = stock.getProductsInAscendingOrder();
			while (itProduct.hasNext()) {
				Product product = itProduct.next();
				if (product.getID() == p.getID()) {	
					product.setQuantity(quant - Integer.parseInt(order1[2]));
				}
			}
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					((DefaultTableModel) UserFrame.getOrderTable().getModel()).setValueAt(new String("Computed"),
							order.getSize()-2, 4);
				}
			}, 5000);
			manager.serializaOrders(order);
			manager.serializaWarehouse(stock);
		}
		}
	}

	public class SearchProductButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			itProduct = stock.getProductsInAscendingOrder();
			String s = "";
			String name = JOptionPane.showInputDialog("Give the name of the object to be searched:");
			boolean este = false;
			try {
				while (itProduct.hasNext()) {
					p = itProduct.next();

					if (p.getName().equals(name)) {
						s = s + "ID:" + p.getID() + "  Name:" + p.getName() + "  Quantity:" + p.getQuantity() + "\n";
						este = true;
					}
				}

				if (este == false) {
					JOptionPane.showMessageDialog(null, "There is no product called " + name + "!", "Error!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, s, "Product Found", JOptionPane.DEFAULT_OPTION);
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Introduce the name of the product:", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
