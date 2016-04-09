package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.OPDept;
import model.Order;
import model.OrderedProduct;
import model.Product;
import model.Warehouse;
import view.AdminView;

public class AdminController {

	private AdminView aw;
	private Warehouse warehouse;
	private OPDept opdept;
	private SerializationController sc;

	public AdminController(AdminView aw, SerializationController sc) {

		this.aw = aw;
		this.sc = sc;
		this.warehouse = sc.deserializeWarehouse();
		this.opdept = sc.deserializeOrders();

		this.aw.viewOrdersListener(new ViewOrdersListener());
		this.aw.viewChangeListener(new ViewChangeListener());
		this.aw.addTableActionListener(new TableActionListener());
		this.aw.addAddButtonListener(new AddButtonActionListener());
		this.aw.addRemoveButtonListener(new RemoveButtonActionListener());

		this.aw.getPanelAdd().setVisible(true);
		this.aw.getPanelView().setVisible(true);
		this.aw.getPanelOrders().setVisible(false);
		
		this.aw.getViewAndChange().setSelected(true);
		
		
	}

	class RemoveButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (aw.getTable().getSelectedRow() != -1) {

				int idToBeRemoved = (int) (aw.getModel().getValueAt(aw.getTable().getSelectedRow(), 0));

				warehouse.removeProduct(idToBeRemoved);
				sc.serializeWarehouse(warehouse);

				
				aw.getModel().removeRow(aw.getTable().getSelectedRow());
				
				warehouse = sc.deserializeWarehouse();
				

			}
		}

	}

	class AddButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Product p = new Product(Integer.parseInt(aw.getId()), aw.getBook(), aw.getAuthor(), aw.getPublisher(),
					Double.parseDouble(aw.getPrice()), Integer.parseInt(aw.getQuantity()));
			p.printProduct();

			warehouse.addProduct(p);
			sc.serializeWarehouse(warehouse);

			aw.getModel().setRowCount(0);

			warehouse = sc.deserializeWarehouse();
			TreeSet<Product> products = warehouse.getProducts();
			aw.paintTable(products);

		}

	}

	class ViewOrdersListener implements MenuListener {

		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void menuDeselected(MenuEvent arg0) {
			// TODO Auto-generated method stub
			aw.getViewAndChange().setSelected(false);
			aw.getPanelOrders().setVisible(true);
			aw.getPanelAdd().setVisible(false);
			aw.getPanelView().setVisible(false);

			opdept = sc.deserializeOrders();
			Iterator<Order> itr = opdept.getOrders().iterator();

			while (itr.hasNext()) {

				Order o = itr.next();
				
				aw.getListData().addElement(o.getId());
				aw.getListData().addElement("Status: " + o.getStatus() );
				aw.getListData().addElement("Total value: " + o.getValue());
				
				Iterator<OrderedProduct> itrop = o.getOrderedProducts().iterator();
				
				while (itrop.hasNext()) {

					OrderedProduct op = itrop.next();

					aw.getListData().addElement(op.getProduct().getTitle() + "-> Ordered quantity: " + op.getOrderedQuantity());
				}
				
				aw.getListData().addElement(" ");
				
				aw.getOrderList().setListData(aw.getListData());

			}

			
		}

		@Override
		public void menuSelected(MenuEvent arg0) {

			aw.getViewAndChange().setSelected(false);
			aw.getPanelOrders().setVisible(true);
			aw.getPanelAdd().setVisible(false);
			aw.getPanelView().setVisible(false);

			opdept = sc.deserializeOrders();
			Iterator<Order> itr = opdept.getOrders().iterator();

			while (itr.hasNext()) {

				Order o = itr.next();
				
				aw.getListData().addElement(o.getId());
				aw.getListData().addElement("Status: " + o.getStatus() );
				aw.getListData().addElement("Total value: " + o.getValue());
				
				Iterator<OrderedProduct> itrop = o.getOrderedProducts().iterator();
				
				while (itrop.hasNext()) {

					OrderedProduct op = itrop.next();

					aw.getListData().addElement(op.getProduct().getTitle() + "-> Ordered quantity: " + op.getOrderedQuantity());
				}
				
				aw.getListData().addElement(" ");
				
				aw.getOrderList().setListData(aw.getListData());

			}

			
			
		}

	}

	class ViewChangeListener implements MenuListener {

		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void menuDeselected(MenuEvent arg0) {
			aw.getPanelAdd().setVisible(true);
			aw.getPanelView().setVisible(true);
			aw.getPanelOrders().setVisible(false);

			aw.getModel().setRowCount(0);

			warehouse = sc.deserializeWarehouse();

			TreeSet<Product> products = warehouse.getProducts();
			aw.paintTable(products);

			sc.serializeWarehouse(warehouse);

		}

		@Override
		public void menuSelected(MenuEvent arg0) {

			
			aw.getPanelAdd().setVisible(true);
			aw.getPanelView().setVisible(true);
			aw.getPanelOrders().setVisible(false);

			aw.getModel().setRowCount(0);

			warehouse = sc.deserializeWarehouse();

			TreeSet<Product> products = warehouse.getProducts();
			aw.paintTable(products);

			sc.serializeWarehouse(warehouse);
		}

	}

	class TableActionListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent tme) {

			if (tme.getColumn() != -1) {
				// we have an update, not a row insert
				warehouse.modifyProduct(tme.getFirstRow() + 1, tme.getColumn(),
						aw.getModel().getValueAt(tme.getFirstRow(), tme.getColumn()).toString());

				sc.serializeWarehouse(warehouse);
				
			}

		}

	}

}
