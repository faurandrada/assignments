package pt.onlineShop.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pt.onlineShop.processing.OPDept;

public class AdminView extends JFrame {
	private static final long serialVersionUID = -448050665942574284L;
	private OPDept department;
	private JButton addProduct, deleteProduct, updateStock, updatePrice;
	private JTextField nameAdd, priceAdd, cantAdd;
	private JTextField nameDelete;
	private JTextField nameUpdateStock, stock;
	private JTextField nameUpdatePrice, price;
	private JButton show,exit;

	public AdminView(OPDept department) {
		this.department = department;
		this.setTitle("Programming techniques-Assignment2-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(6, 4));
		
		nameAdd=new JTextField();
		priceAdd=new JTextField();
		cantAdd=new JTextField();
		addProduct=new JButton("Add!");
		this.add(nameAdd);
		this.add(priceAdd);
		this.add(cantAdd);
		this.add(addProduct);

		nameDelete=new JTextField();
		JButton b1=new JButton();
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		JButton b2=new JButton();
		b2.setBackground(Color.pink);
		b2.setEnabled(false);
		deleteProduct=new JButton("Delete");
		this.add(nameDelete);
		this.add(b1);
		this.add(b2);
		this.add(deleteProduct);
		
		nameUpdateStock=new JTextField();
		stock=new JTextField();
		JButton b3=new JButton();
		b3.setBackground(Color.pink);
		b3.setEnabled(false);
		updateStock=new JButton("Update!");
		this.add(nameUpdateStock);
		this.add(stock);
		this.add(b3);
		this.add(updateStock);
		
		nameUpdatePrice=new JTextField();
		price=new JTextField();
		JButton b4=new JButton();
		b4.setBackground(Color.pink);
		b4.setEnabled(false);
		updatePrice=new JButton("Change price!");
		this.add(nameUpdatePrice);
		this.add(price);
		this.add(b4);
		this.add(updatePrice);
		
		JButton b5=new JButton();
		b5.setBackground(Color.pink);
		b5.setEnabled(false);
		JButton b6=new JButton();
		b6.setBackground(Color.pink);
		b6.setEnabled(false);
		show=new JButton("Show stock!");
		exit=new JButton("Exit and Save!");
		this.add(b5);
		this.add(show);
		this.add(b6);
		this.add(exit);
		
		Handler handler = new Handler();
		
		addProduct.addActionListener(handler);
		deleteProduct.addActionListener(handler);
		updateStock.addActionListener(handler);
		updatePrice.addActionListener(handler);
		show.addActionListener(handler);
		exit.addActionListener(handler);
		
		this.setVisible(true);
	}
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource()==addProduct){
				String name=nameAdd.getText();
				int stoc=Integer.parseInt(cantAdd.getText());
				int pric=Integer.parseInt(priceAdd.getText());
				department.getAdministrator().addProductInWarehouse(department.getWarehouse(), name, stoc, pric);
			}else if (event.getSource()==deleteProduct){
				String name=nameDelete.getText();
				department.getAdministrator().deleteProductInWarehouse(department.getWarehouse(), name);
			}else if (event.getSource()==updateStock){
				String name=nameUpdateStock.getText();
				int stoc=Integer.parseInt(stock.getText());
				department.getAdministrator().incrementStockOfproduct(department.getWarehouse(), name, stoc);
			}else if (event.getSource()==updatePrice){
				String name=nameUpdatePrice.getText();
				int pric=Integer.parseInt(price.getText());
				department.getAdministrator().updatePriceOnProduct(department.getWarehouse(), name, pric);
			}else if (event.getSource()==show){
				new Stock(department.getWarehouse());
			}else if (event.getSource()==exit){
				department.closeAdministratorSession();
				System.exit(0);
			}
		}
		
	}
}

