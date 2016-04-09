package views;

import java.awt.event.*;

import javax.swing.*;

import models.*;

public class AdminItem extends JPanel {

	private ProductStock productStock;
	private JLabel label = new JLabel();
	private JButton down = new JButton("-");
	private JButton up = new JButton("+");
	private JButton delete = new JButton("x");

	public AdminItem(ProductStock productStock) {
		this.productStock = productStock;
		this.label = new JLabel(productStock.getProduct().getName() + " " + productStock.getProduct().getPrice() + "$ "
				+ productStock.getStock());
		this.add(label);
		this.add(down);
		this.add(up);
		this.add(delete);
	}

	public void setDownButtonActionListener(ActionListener a){
		down.addActionListener(a);
	}
	
	public void setUpButtonActionListener(ActionListener a){
		up.addActionListener(a);
	}
	
	public void setDeleteButtonActionListener(ActionListener a){
		delete.addActionListener(a);
	}

	public ProductStock getProductStock() {
		return productStock;
	}
	
}
