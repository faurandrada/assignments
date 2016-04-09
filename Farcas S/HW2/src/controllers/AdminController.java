package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.*;
import models.*;

public class AdminController extends AbstractController {

	private AdminView adminView;

	public AdminController(AdminView adminView, boolean hasBackButton) {
		super(adminView, hasBackButton);
		this.adminView = adminView;
		adminView.setAddButtonActionListener(new AddButtonActionListener());
		refresh();
	}

	public void refresh() {
		for (AdminItem ai : adminView.getItems()) {
			ai.setDownButtonActionListener(new DownButtonActionListener(ai));
			ai.setUpButtonActionListener(new UpButtonActionListener(ai));
			ai.setDeleteButtonActionListener(new DeleteButtonActionListener(ai));
		}
	}

	private class DownButtonActionListener implements ActionListener {
		private AdminItem ai;

		public DownButtonActionListener(AdminItem ai) {
			this.ai = ai;
		}

		public void actionPerformed(ActionEvent e) {
			if (ai.getProductStock().getStock() - 1 >= 0) {
				Warehouse.getInstance().increaseDecreaseStock(
						new ProductStock(ai.getProductStock().getProduct(), ai.getProductStock().getStock() - 1));
			}
			adminView.refresh();
			refresh();
		}
	}

	private class UpButtonActionListener implements ActionListener {
		private AdminItem ai;

		public UpButtonActionListener(AdminItem ai) {
			this.ai = ai;
		}

		public void actionPerformed(ActionEvent e) {
			Warehouse.getInstance().increaseDecreaseStock(
					new ProductStock(ai.getProductStock().getProduct(), ai.getProductStock().getStock() + 1));
			adminView.refresh();
			refresh();
		}
	}

	private class DeleteButtonActionListener implements ActionListener {
		private AdminItem ai;

		public DeleteButtonActionListener(AdminItem ai) {
			this.ai = ai;
		}

		public void actionPerformed(ActionEvent e) {
			Warehouse.getInstance().deleteProduct(ai.getProductStock());
			adminView.refresh();
			refresh();
		}
	}

	private class AddButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Warehouse.getInstance().addProduct(new ProductStock(
					new Product(adminView.get_Name().getText(), Double.parseDouble(adminView.getPrice().getText())),
					Integer.parseInt(adminView.getStock().getText())));
			adminView.refresh();
			refresh();
		}
	}

}
