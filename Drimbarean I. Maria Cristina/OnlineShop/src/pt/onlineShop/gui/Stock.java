package pt.onlineShop.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;

import pt.onlineShop.models.Product;
import pt.onlineShop.models.Warehouse;

public class Stock extends JFrame{
private static final long serialVersionUID = 8962275130608012998L;

public Stock(Warehouse warehouse){
	  this.setTitle("Programming techniques-Assignment2-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);
		
		JTable table;
		String[] columnNames = {"Product Name","Stock","Price/Unity"};
		int size=warehouse.getWarehouse().size();
		Object[][] data=new Object[size][3];
		int i=0;
		for(Product p: warehouse.getWarehouse() ){
			data[i][0]=p.getName();
			data[i][1]=p.getStock();
			data[i][2]=p.getPrice();
			i++;
		}
		table = new JTable(data, columnNames);
		this.add(table);
		
		this.setVisible(true);
  }
}
