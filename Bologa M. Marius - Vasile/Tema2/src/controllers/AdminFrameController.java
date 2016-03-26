package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.OPDept;
import models.Order;
import models.Product;
import models.Warehouse;
import views.AdminFrame2;
import views.MessageDialogs;

public class AdminFrameController {
	private static AdminFrame2 frame = new AdminFrame2();
	private Warehouse stock = new Warehouse();
	private static Product p;
	private Order o;
	private Iterator<Product> itProduct;
	private OPDept orders = new OPDept();
	private Iterator<Order> itOrder;
	private SerializableManager manager = new SerializableManager();
	public static final int UNDERSTOCK = 100;
	public static final int OVERSTOCK = 1000;
	private int total;

	public AdminFrameController() {
		frame.setAddActionListener(new AddButtonActionListener());
		frame.setDelButtonActionListener(new DeleteButtonActionListener());
		frame.setSearchButtonActionListener(new SearchButtonActionListener());
		frame.setSeeActionListener(new SeeButtonActionListener());
		frame.setSearch1ButtonActionListener(new Search1ButtonActionListener());
		frame.setStockButtonActionListener(new StockButtonActionListener());
		this.stock = manager.deserializeWharehouse();
		this.orders = manager.deserializeOrders();
		this.total = getTotalNumberOfProducts(stock);
	}

	public static void printTheProduct(Product p) {
		Object[] row = { p.getID(), p.getName(), p.getQuantity() };
		((DefaultTableModel) AdminFrame2.getTable().getModel()).addRow(row);
	}

	public class AddButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			System.out.println(total);
			if (checkForOverStock()) {
				JOptionPane.showMessageDialog(null, "The overstock limit has been exceeded!", "Error",
						JOptionPane.WARNING_MESSAGE);
				manager.serializaWarehouse(stock);
			} else {
				String[] p = MessageDialogs.addProductPanel();
				Product product = new Product(Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2]));
				stock.addProduct(product);
				printTheProduct(product);
				manager.serializaWarehouse(stock);
			}
		}
	}

	public class DeleteButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (checkForOverStock()) {
				JOptionPane.showMessageDialog(null, "The number of products is under limit!", "Error",
						JOptionPane.WARNING_MESSAGE);
				manager.serializaWarehouse(stock);
			} else {
				int viewIndex = AdminFrame2.getTable().getSelectedRow();
				if (viewIndex != -1) {
					int id = Integer.parseInt(AdminFrame2.getTable().getValueAt(viewIndex, 0).toString());
					try {
						itProduct = stock.getProductsInAscendingOrder();
						while (itProduct.hasNext()) {
							p = itProduct.next();
							if (p.getID() == id) {
								stock.removeProduct(p);
								break;
							}
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Introduceti datele corect si completati toate campurile!",
								"Eroare", JOptionPane.WARNING_MESSAGE);
					}
					int modelIndex = AdminFrame2.getTable().convertRowIndexToModel(viewIndex); // model
					DefaultTableModel model = (DefaultTableModel) AdminFrame2.getTable().getModel();
					model.removeRow(modelIndex);
				}
				manager.serializaWarehouse(stock);
			}
		}

	}

	public class SearchButtonActionListener implements ActionListener {
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

	public class SeeButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			try {
				itOrder = orders.getOrdersInAscendingOrder();
				while (itOrder.hasNext()) {
					o = itOrder.next();
					Object[] row = { o.getID(), o.getProduct().getName(), o.getQuantity(), o.getCustomer().getName() };
					((DefaultTableModel) AdminFrame2.getTable().getModel()).addRow(row);
				}
			} catch (NumberFormatException e1) {
			}
		}
	}

	public class Search1ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			itOrder = orders.getOrdersInAscendingOrder();
			String s = "";
			String id = JOptionPane.showInputDialog("Give the name of the order to be searched:");
			boolean este = false;
			try {
				while (itOrder.hasNext()) {
					o = itOrder.next();

					if (o.getID() == Integer.parseInt(id)) {
						s = s + "ID:" + o.getID() + "  Customer:" + o.getCustomer().getName() + "  Quantity:"
								+ o.getQuantity() + "\n";
						este = true;
					}
				}

				if (este == false) {
					JOptionPane.showMessageDialog(null, "There is no order with id " + id + "!", "Error!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, s, "Order Found", JOptionPane.DEFAULT_OPTION);
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Introduce the number of the order:", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public class StockButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			String[] p = MessageDialogs.addProductPanel();
			itProduct = stock.getProductsInAscendingOrder();
			try {

				while (itProduct.hasNext()) {
					Product prod = itProduct.next();

					if (prod.getName().equals(p[1]) && prod.getID() == Integer.parseInt(p[0])) {
						int row = MessageDialogs.getRowByValue(AdminFrame2.getTable().getModel(), p[1]);
						System.out.println(row);
						AdminFrame2.getTable().getModel()
								.setValueAt(new Integer(prod.getQuantity() + Integer.parseInt(p[2])), row, 2);
						prod.setQuantity(prod.getQuantity() + Integer.parseInt(p[2]));
						prod.getQuantity();
					}
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "There is no product with this id", "Error!",
						JOptionPane.WARNING_MESSAGE);
			}
			manager.serializaWarehouse(stock);
		}
	}

	public Warehouse getStock() {
		return stock;
	}

	public void setStock(Warehouse stock) {
		this.stock = stock;
	}

	public static AdminFrame2 getFrame() {
		return frame;
	}

	public static void setFrame(AdminFrame2 frame) {
		AdminFrameController.frame = frame;
	}

	public boolean checkForUnderStock() {
		if (total < UNDERSTOCK) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkForOverStock() {
		if (total > OVERSTOCK) {
			return true;
		} else {
			return false;
		}
	}

	public int getTotalNumberOfProducts(Warehouse w) {
		Iterator<Product> it = w.getProductsInAscendingOrder();
		int total = 0;
		while (it.hasNext()) {
			Product p = it.next();
			total = total + p.getQuantity();
		}

		return total;

	}

}
