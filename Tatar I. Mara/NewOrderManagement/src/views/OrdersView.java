package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import io.IOStream;
import models.OPDept;
import models.Order;
import models.Product;

public class OrdersView {
	private OPDept ordersDept;
	private IOStream io;
	private JTable table;
	private DefaultTableModel model;	
	public OrdersView() {

		JFrame frame = new JFrame();
		io = new IOStream();
		table = new JTable();
		ordersDept=new OPDept();
		ordersDept = io.deserializeOPDept();
	
		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "ID","Customer","ShoppingCart" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		Font font = new Font("", 1, 22);
		table.setModel(model);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setFont(font);
		table.setRowHeight(30);

		for (Order order :ordersDept.getOrders()) {
			  for (Product product:order.getShoppingCart()){
				Object[] row = { order.getID(),order.getCustomer().getName(),product.getItem()};
				model.addRow(row);
			}
		}

		// JTextField textId = new JTextField();



		// create JButtons

		JButton btnFinish = new JButton("Finish Order");
		JButton btnViewWarehouse = new JButton("Warehouse");

		// textId.setBounds(20, 220, 100, 25);

	
		btnFinish.setBounds(150, 220, 100, 25);
		btnViewWarehouse.setBounds(150, 280, 100, 25);
	
		// create JScrollPane

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);
		frame.setLayout(null);
		frame.add(pane);



		// add JButtons to the jframe

		frame.add(btnFinish);
		frame.add(btnViewWarehouse);
		

		// create an array of objects to set the row data

		Object[] row = new Object[3];

		// button add row

		btnFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
				row[0] = model.getValueAt(i, 0).toString();
				row[1] = model.getValueAt(i, 0).toString();
				row[2] = model.getValueAt(i, 0).toString();
				if (i >= 0) {

				  Order order=ordersDept.findProduct(Integer.parseInt(row[0].toString()));
				  ordersDept.remove(order);
				    model.removeRow(i);
				    System.out.println("ADMIN REMOVED PRODUCTS");
				    ordersDept.print();
				    io.SerializeOPdept(ordersDept);
				} else {
					System.out.println("Delete Error");
				}
				
			}
		});

		btnViewWarehouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminView();
				frame.dispose();
			}

		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
			
			}
		});
		frame.setSize(900, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
}}
