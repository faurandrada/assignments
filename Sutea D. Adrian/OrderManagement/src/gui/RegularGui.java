package gui;

import order.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class RegularGui {
	private JFrame frame = new JFrame();
	private JPanel comPanel = new JPanel(new BorderLayout());
	private JPanel searchPanel = new JPanel(new GridLayout(2, 2, 50, 50));
	private JPanel labelPanel = new JPanel(new GridLayout(2, 1, 20, 20));
	private JPanel textPanel = new JPanel(new GridLayout(2, 1, 20, 20));
	private JPanel centerPanel = new JPanel(new BorderLayout(0, 20));
	private JLabel loginLabel = new JLabel();
	private JTextField searchText = new JTextField();
	private JButton searchBtn = new JButton("Search by name");
	private JTextField searchPrice = new JTextField();
	private JButton searchPriceBtn = new JButton("Search by price");
	private JLabel itemLabel = new JLabel("Item name:");
	private JLabel quantity = new JLabel("Quantity:");
	private JTextField itemText = new JTextField();
	private JTextField quantityText = new JTextField();
	private JButton command = new JButton("Place command");
	private JButton refresh = new JButton("Refresh");
	private JButton history = new JButton("My command history");
	private JTextArea items = new JTextArea(10, 20);
	private JPanel itemsPanel = new JPanel(new BorderLayout());
	File file1 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\prices.txt");
	File file2 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\commands.txt");
	Scanner fileScanner;
	String temp = new String();
	private Warehouse wh = new Warehouse();
	private Order order;
	private OPDept opd = new OPDept();
	private Product prod= new Product();

	public RegularGui(String username) {
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
		searchBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e){
				items.setText(null);
				temp=null;
				temp=wh.searchName(searchText.getText());
				items.setText(temp);
			}
		});
		searchPriceBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				items.setText(null);
				temp=null;
				int x = Integer.parseInt(searchPrice.getText());
				temp=wh.searchPrice(x);
				items.setText(temp);
			}
		});
		command.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = itemText.getText();
				int x = Integer.parseInt(quantityText.getText());
				if(opd.checkForProduct(s)!=null){
					prod=opd.checkForProduct(s);
					order=new Order(x, prod);
					if(opd.checkStock(prod, x)==true){
						opd.placeCommand(order);
					}
					else{
						items.setText("The requested quantity exceeds the stock!");
					}
				}
				else{
					items.setText("The requested product wasn't found in the warehouse!");
				}
			}
		});
		history.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String username = new String();
				username=Gui.getUsername();
				items.setText(null);
				try {
					BufferedReader br = new BufferedReader(new FileReader(file2));
					String line = null;
					while ((line = br.readLine()) != null) {
						String[] tokens = line.split(" ");
						if(Objects.equals(tokens[0], username)){
							items.append(line+"\n");
						}
					}
					br.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		items.setEditable(false);
		frame.setLayout(new BorderLayout(20, 0));
		itemsPanel.add(items, BorderLayout.CENTER);
		itemsPanel.add(refresh, BorderLayout.SOUTH);
		itemsPanel.add(history, BorderLayout.NORTH);
		loginLabel.setText("You are logged in as " + username);
		loginLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(loginLabel, BorderLayout.NORTH);
		searchPanel.add(searchText);
		searchPanel.add(searchBtn);
		searchPanel.add(searchPrice);
		searchPanel.add(searchPriceBtn);
		labelPanel.add(itemLabel);
		labelPanel.add(quantity);
		textPanel.add(itemText);
		textPanel.add(quantityText);
		comPanel.add(labelPanel, BorderLayout.WEST);
		comPanel.add(textPanel, BorderLayout.CENTER);
		centerPanel.add(searchPanel, BorderLayout.NORTH);
		centerPanel.add(comPanel, BorderLayout.CENTER);
		centerPanel.add(command, BorderLayout.SOUTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(itemsPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
