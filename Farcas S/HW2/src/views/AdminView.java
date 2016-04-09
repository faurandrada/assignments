package views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import controllers.*;
import models.*;


public class AdminView extends Frame {

	private ArrayList<AdminItem> items = new ArrayList<AdminItem>();
	private JPanel addPanel = new JPanel();
	private JTextField name = new JTextField("name");
	private JTextField price = new JTextField("price");
	private JTextField stock = new JTextField("stock");
	private JButton add = new JButton("Add");
	
	public AdminView(String title){
		super(title);
		name.setPreferredSize(new Dimension(100, 20));
		price.setPreferredSize(new Dimension(100, 20));
		stock.setPreferredSize(new Dimension(100, 20));
		addPanel.add(name);
		addPanel.add(price);
		addPanel.add(stock);
		addPanel.add(add);
		contentPanel.add(addPanel);
		refresh();
	}
	
	public void refresh(){
		contentPanel.removeAll();
		contentPanel.revalidate();
		this.repaint();
		items = new ArrayList<AdminItem>();
		for(ProductStock ps: Warehouse.getInstance().getProductStock()){
			items.add(new AdminItem(ps));
		}
		for(AdminItem ai: items){
			contentPanel.add(ai);
		}
		contentPanel.add(addPanel);
		contentPanel.revalidate();
		this.repaint();
	}

	
	public void setAddButtonActionListener(ActionListener a) {
		add.addActionListener(a);
	}
	
	
	public ArrayList<AdminItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<AdminItem> items) {
		this.items = items;
	}

	public JTextField get_Name() {
		return name;
	}

	public void setName(JTextField name) {
		this.name = name;
	}

	public JTextField getPrice() {
		return price;
	}

	public void setPrice(JTextField price) {
		this.price = price;
	}

	public JTextField getStock() {
		return stock;
	}

	public void setStock(JTextField stock) {
		this.stock = stock;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

}
