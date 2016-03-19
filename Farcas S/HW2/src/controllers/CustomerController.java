package controllers;

import java.awt.event.*;
import java.util.*;

import javax.swing.event.*;

import models.*;
import views.*;

public class CustomerController extends AbstractController {

	private CustomerView customerView;

	public CustomerController(CustomerView customerView, boolean hasBackButton) {
		super(customerView, hasBackButton);
		this.customerView = customerView;
		customerView.setSearchButtonActionListener(new SearchButtonActionListener());
		customerView.setBuyButtonActionListener(new BuyButtonActionListener());
		customerView.setHistoryButtonActionListener(new HistoryButtonActionListener());
		refresh();
	}

	public void refresh() {
		for (CustomerItem ci : customerView.getItems()) {
			ci.setDownButtonActionListener(new DownButtonActionListener(ci));
			ci.setUpButtonActionListener(new UpButtonActionListener(ci));
		}
	}

	private class DownButtonActionListener implements ActionListener {
		private CustomerItem ci;

		public DownButtonActionListener(CustomerItem ci) {
			this.ci = ci;
		}

		public void actionPerformed(ActionEvent e) {
			if (ci.getQuantity() - 1 >= 0) {
				ci.setQuantity(ci.getQuantity() - 1);
				ci.refresh();
			}
		}
	}

	private class UpButtonActionListener implements ActionListener {
		private CustomerItem ci;

		public UpButtonActionListener(CustomerItem ci) {
			this.ci = ci;
		}

		public void actionPerformed(ActionEvent e) {
			if (ci.getProductStock().getStock() - ci.getQuantity() - 1 >= 0) {
				ci.setQuantity(ci.getQuantity() + 1);
				ci.refresh();
			}
		}
	}

	private class SearchButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			ArrayList<CustomerItem> items = new ArrayList<CustomerItem>();
			for (ProductStock ps : Warehouse.getInstance().filter(customerView.getSearch().getText())) {
				items.add(new CustomerItem(ps));
			}
			customerView.setItems(items);
			customerView.refresh();
			refresh();
		}
	}

	private class BuyButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean someProductsWereBought = false;
			int ID = (int) (Math.random() * 100);
			Status status = new Status(System.currentTimeMillis(), 0, 0, 0);
			ArrayList<OrderItem> items = new ArrayList<OrderItem>();
			for (CustomerItem ci : customerView.getItems()) {
				if (ci.getQuantity() != 0) {
					someProductsWereBought = true;
					items.add(new OrderItem(ci.getProductStock().getProduct(), ci.getQuantity()));
					Warehouse.getInstance().increaseDecreaseStock(new ProductStock(ci.getProductStock().getProduct(),
							(ci.getProductStock().getStock() - ci.getQuantity())));
				}
			}
			if (someProductsWereBought != false)
				OPDept.getInstance().addOrder(new Order(ID, status, items));
			ArrayList<CustomerItem> customerItems = new ArrayList<CustomerItem>();
			for (ProductStock ps : Warehouse.getInstance().getProductStock()) {
				customerItems.add(new CustomerItem(ps));
			}
			customerView.setItems(customerItems);
			customerView.refresh();
			refresh();
		}

	}

	private class HistoryButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new HistoryController(new HistoryView("History"), true);
		}

	}

}