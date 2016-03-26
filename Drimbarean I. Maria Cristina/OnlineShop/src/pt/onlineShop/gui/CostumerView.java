package pt.onlineShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pt.onlineShop.processing.OPDept;

public class CostumerView extends JFrame{
	private static final long serialVersionUID = -7798681967881346383L;
	private OPDept department;
	JTextField nameSearch,nameOrder,amount;
	JButton search,order,show,exit;
	public CostumerView(OPDept department){
		this.department = department;
		this.setTitle("Programming techniques-Assignment2-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(3, 3));
		
		nameSearch=new JTextField();
		search=new JButton("Search!");
		JButton b1=new JButton();
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		this.add(nameSearch);
		this.add(b1);
		this.add(search);
		
		nameOrder=new JTextField();
		amount=new JTextField();
		order=new JButton("Place Order!");
		this.add(nameOrder);
		this.add(amount);
		this.add(order);
		
		JButton b2=new JButton();
		b2.setBackground(Color.pink);
		b2.setEnabled(false);
		show=new JButton("Show History!");
		exit=new JButton("Exit and Save!");
		this.add(show);
		this.add(b2);
		this.add(exit);
		
		Handler handler = new Handler();
		search.addActionListener(handler);
		order.addActionListener(handler);
		show.addActionListener(handler);
		exit.addActionListener(handler);
		this.setVisible(true);
	}
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource()==search){
				String productName=nameSearch.getText();
				department.getCostumer1().searchProduct(department.getWarehouse(), productName);
			}else if (event.getSource()==order){
				String productName=nameOrder.getText();
				int value=Integer.parseInt(amount.getText());
				department.getCostumer1().placeOrder(department.getWarehouse(), productName, value);
			}
			else if (event.getSource()==show){
				new OrderHistory(department.getCostumer1().getOrders());
			}else if (event.getSource()==exit){
				department.closeCostumer1session();
				System.exit(0);
			}
		}
		
	}
}
