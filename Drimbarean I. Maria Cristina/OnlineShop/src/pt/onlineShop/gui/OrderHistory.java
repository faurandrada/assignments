package pt.onlineShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JTable;

import pt.onlineShop.models.Order;

public class OrderHistory extends JFrame{

private static final long serialVersionUID = 6567041303795300724L;
private TreeSet<Order> orders=new TreeSet<Order>();
  public OrderHistory(TreeSet<Order> orders){
	  this.orders=orders;
	  this.setTitle("Programming techniques-Assignment2-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);
		
		JTable table;
		String[] columnNames = {"Order id","Product name","Amount"};
		int size=orders.size();
		Object[][] data=new Object[size][3];
		int i=0;
		for(Order p: this.orders ){
			data[i][0]=p.getId();
			data[i][1]=p.getNameOfProduct();
			data[i][2]=p.getAmount();
			i++;
		}
		table = new JTable(data, columnNames);
		this.add(table);
		
		this.setVisible(true);
  }
}
