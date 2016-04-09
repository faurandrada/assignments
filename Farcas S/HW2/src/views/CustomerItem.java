package views;

import java.awt.event.ActionListener;

import javax.swing.*;

import models.ProductStock;

public class CustomerItem extends JPanel {

	private ProductStock productStock;
	private JLabel label = new JLabel();
	private JButton down = new JButton("-");
	private JButton up = new JButton("+");
	private int quantity = 0;
	private JLabel quantityLabel = new JLabel("");

	public CustomerItem(ProductStock productStock) {
		this.productStock = productStock;
		this.label = new JLabel(productStock.getProduct().getName() + " " + productStock.getProduct().getPrice() + "$ "
				+ productStock.getStock());
		this.add(label);
		this.add(down);
		this.add(up);
		this.add(quantityLabel);
	}

	public void refresh() {
		this.quantityLabel.setText(String.valueOf(quantity));
	}

	public void setDownButtonActionListener(ActionListener a) {
		down.addActionListener(a);
	}

	public void setUpButtonActionListener(ActionListener a) {
		up.addActionListener(a);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}

}
