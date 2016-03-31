package gui;

import javax.swing.*;

import order.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class AdminGui {
	private JFrame frame = new JFrame();
	private JPanel xPanel = new JPanel(new GridLayout(1, 3, 20, 20));
	private JPanel labelPanel = new JPanel(new GridLayout(3, 1, 200, 200));
	private JPanel textPanel = new JPanel(new GridLayout(3, 1, 200, 200));
	private JPanel btnPanel = new JPanel(new GridLayout(5, 1, 20, 20));
	private JLabel loginLabel = new JLabel();
	private JLabel nameLabel = new JLabel("Item name:");
	private JLabel quantityLabel = new JLabel("Quantity:");
	private JLabel priceLabel = new JLabel("Price:");
	private JTextField nameText = new JTextField();
	private JTextField quantityText = new JTextField();
	private JTextField priceText = new JTextField();
	private JButton addItem = new JButton("Add new item");
	private JButton removeItem = new JButton("Remove item");
	private JButton addToStock = new JButton("Add to stock");
	private JButton removeFromStock = new JButton("Remove from stock");
	private JButton refresh = new JButton("Refresh");
	private JTextArea items = new JTextArea();
	private JButton history = new JButton("Command history");
	File file1 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\items.txt");
	File file2 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\commands.txt");
	Scanner fileScanner;
	private Warehouse wh = new Warehouse();

	public AdminGui(String username) {
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.setText(null);
				try {
					fileScanner = new Scanner(file1);
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					System.err.println(ex);
				}
				while (fileScanner.hasNext()) {
					items.append(fileScanner.nextLine()+"\n");
				}
				fileScanner.close();
			}
		});
		addItem.addActionListener(new ActionListener() {
			String s = new String();
			int x, y;
			public void actionPerformed(ActionEvent e) {
				s = nameText.getText();
				x = Integer.parseInt(quantityText.getText());
				y = Integer.parseInt(priceText.getText());
				wh.addItem(file1, s, x, y);
			}
		});
		removeItem.addActionListener(new ActionListener() {
			String s = new String();
			public void actionPerformed(ActionEvent e) {
				s = nameText.getText();
				wh.removeItem(file1, s);
			}
		});
		addToStock.addActionListener(new ActionListener() {
			String s = new String();
			int x;
			public void actionPerformed(ActionEvent e) {
				s = nameText.getText();
				x=Integer.parseInt(quantityText.getText());
				wh.updateQuantity(s, x);
			}
		});
		removeFromStock.addActionListener(new ActionListener() {
			String s = new String();
			int x;
			public void actionPerformed(ActionEvent e) {
				s = nameText.getText();
				x=Integer.parseInt(quantityText.getText());
				wh.updateQuantity(s, -x);
			}
		});
		history.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				items.setText(null);
				try {
					fileScanner = new Scanner(file2);
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					System.err.println(ex);
				}
				while (fileScanner.hasNext()) {
					items.append(fileScanner.nextLine()+"\n");
				}
				fileScanner.close();
			}
		});
		items.setEditable(false);
		frame.setLayout(new BorderLayout());
		loginLabel.setText("You are logged in as " + username);
		loginLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(loginLabel, BorderLayout.NORTH);
		frame.add(history, BorderLayout.SOUTH);
		labelPanel.add(nameLabel);
		labelPanel.add(quantityLabel);
		labelPanel.add(priceLabel);
		frame.add(labelPanel, BorderLayout.WEST);
		textPanel.add(nameText);
		textPanel.add(quantityText);
		textPanel.add(priceText);
		btnPanel.add(addItem);
		btnPanel.add(removeItem);
		btnPanel.add(addToStock);
		btnPanel.add(removeFromStock);
		btnPanel.add(refresh);
		xPanel.add(textPanel);
		xPanel.add(btnPanel);
		xPanel.add(items);
		frame.add(xPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
