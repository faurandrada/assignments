package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import comparators.ProductComparator;

import model.Customer;
import model.OPDept;
import model.Order;
import model.OrderedProduct;
import model.Warehouse;
import utilities.OrderStatus;
import view.UserView;
import model.Product;

public class UserController {

	private UserView uw;
	private Warehouse warehouse;
	private Order order;

	private Customer customer;
	private OPDept opdept;
	private SerializationController sc;
	static int indexIdOrders;

	public UserController(UserView uw, Customer customer, SerializationController sc) {

		this.uw = uw;
		this.customer = customer;
		this.sc = sc;

		this.order = new Order(0, this.customer.getIdCustomer());
		setOrder();

		this.warehouse = sc.deserializeWarehouse();
		this.opdept = sc.deserializeOrders();

		this.uw.addSearchButtonActionListener(new SearchListener());
		this.uw.addToListActionListener(new AddToListActionListener());
		this.uw.paintTable(warehouse.getProducts());
		this.uw.addEmptyListActionListener(new EmptyListActionListener());
		this.uw.addBuyActionListener(new BuyActionListener());
		this.uw.orderHistoryActionListener(new HistoryActionListener());
	}

	class HistoryActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			
			uw.getPanelOrders().setVisible(true);
			
			opdept = sc.deserializeOrders();
			Iterator<Order> itr = opdept.getOrders().iterator();

			while (itr.hasNext()) {

				Order o = itr.next();
				
				uw.getListDataO().addElement(o.getId());
				uw.getListDataO().addElement("Status: " + o.getStatus() );
				uw.getListDataO().addElement("Total value: " + o.getValue());
				
				Iterator<OrderedProduct> itrop = o.getOrderedProducts().iterator();

				while (itrop.hasNext()) {

					OrderedProduct op = itrop.next();

					uw.getListDataO()
							.addElement(op.getProduct().getTitle() + "-> Ordered quantity: " + op.getOrderedQuantity());
				}

				uw.getListDataO().addElement(" ");

				uw.getOrderList().setListData(uw.getListDataO());

			}

		}
	}

	class SearchListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			String search = uw.getSearchText();

			if (!search.isEmpty()) {

				TreeSet<Product> searchResults = warehouse.searchWarehouse(search);

				if (searchResults.isEmpty())
					JOptionPane.showMessageDialog(null, "Item not found!");
				else
					uw.paintTable(searchResults);
			} else {
				uw.paintTable(sc.deserializeWarehouse().getProducts());
			}

		}

	}

	public void setOrder() {

	
		opdept = sc.deserializeOrders();
		this.order = new Order(opdept.getNextOrderId(), this.customer.getIdCustomer());
		sc.serializeOrders(opdept);

	}

	class EmptyListActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			uw.getListData().removeAllElements();
			uw.getShoppingList().repaint();

		}

	}

	class BuyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			opdept = sc.deserializeOrders();

			opdept.addOrders(order);
			opdept.processOrders();

			sc.serializeOrders(opdept);
			sc.serializeWarehouse(warehouse);

			uw.getListData().removeAllElements();
			uw.getShoppingList().repaint();

			warehouse = sc.deserializeWarehouse();

			if (uw.getSearchText().isEmpty())
				uw.paintTable(warehouse.getProducts());
			else
				uw.paintTable(warehouse.searchWarehouse(uw.getSearchText()));

			indexIdOrders++;
			setOrder(); // prepare next order

		}

	}

	class AddToListActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (uw.getTable().getSelectedRow() != -1) {

				int idToAdded = (int) (uw.getModel().getValueAt(uw.getTable().getSelectedRow(), 0));

				System.out.println("ORDERED: " + idToAdded);

				int qtyToAdded = Integer
						.parseInt(JOptionPane.showInputDialog("Introduce the quantity you want to buy!"));

				System.out.println("QtytoBEadded: " + qtyToAdded);

				warehouse = sc.deserializeWarehouse();

				if (qtyToAdded > warehouse.getProduct(idToAdded).getQuantity()) {
					JOptionPane.showMessageDialog(null, "UNDERSTOCK!");
				} else {
					OrderedProduct op = new OrderedProduct(warehouse.getProduct(idToAdded), qtyToAdded);
					order.addOrderedProduct(op);

					uw.getListData().addElement(op.getProduct().getTitle() + "  |  " + op.getOrderedQuantity());
					uw.getShoppingList().setListData(uw.getListData());

					sc.serializeWarehouse(warehouse);
				}

			}

		}

	}

}
