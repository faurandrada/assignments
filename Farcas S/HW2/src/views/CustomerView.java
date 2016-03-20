package views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import controllers.Main;
import models.ProductStock;

public class CustomerView extends Frame {

	private ArrayList<CustomerItem> items = new ArrayList<CustomerItem>();
	private JTextField search = new JTextField();
	private JButton buy = new JButton("Buy");
	private JButton history = new JButton("History");
	private JButton searchButton = new JButton("Search");
	private JPanel menu = new JPanel();

	public CustomerView(String title) {
		super(title);
		search.setPreferredSize(new Dimension(100, 20));
		menu.add(search);
		menu.add(searchButton);
		menu.add(buy);
		menu.add(history);
		contentPanel.add(menu);
	}

	public void refresh() {
		contentPanel.removeAll();
		contentPanel.revalidate();
		this.repaint();
		contentPanel.add(menu);
		for (CustomerItem ci : items) {
			contentPanel.add(ci);
		}
		contentPanel.revalidate();
		this.repaint();
	}

	public void setBuyButtonActionListener(ActionListener a) {
		buy.addActionListener(a);
	}

	public void setHistoryButtonActionListener(ActionListener a) {
		history.addActionListener(a);
	}

	public void setSearchButtonActionListener(ActionListener a) {
		searchButton.addActionListener(a);
	}

	public ArrayList<CustomerItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CustomerItem> items) {
		this.items = items;
	}

	public JTextField getSearch() {
		return search;
	}

	public void setSearch(JTextField search) {
		this.search = search;
	}

}
