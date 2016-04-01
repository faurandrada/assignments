package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore.Entry;
import java.util.Set;
import javax.swing.*;

import project.Admin;
import project.Customer;
import project.OPDept;
import project.Order;
import project.Product;
import project.Warehouse;

public class Gui implements ActionListener {
	// USER FRAME
	private JFrame customerFrame;
	private JFrame ordersFrame;

	private JTextField errorAdd;
	private JTextField productName;
	private JTextField productQuantity;
	private JTextField errorRemove;
	private JTextField totPrice;

	private JTextPane p1;
	private JTextPane productsList;

	private JButton add;
	private JButton remove;
	private JButton unders;
	private JButton oswerS;
	private JButton reset;
	private JButton save;
	private JButton btnOrders;
	private JButton back;

	private String pName = new String();
	private String pStock = new String();
	private int ps;

	Customer cust = new Customer();
	Warehouse war = new Warehouse();
	OPDept dept = new OPDept(war);
	int ordID = dept.getLastKey() + 1;
	Order ord = new Order(ordID);
	Set<java.util.Map.Entry<String, Product>> set1 = war.getProducts().entrySet();
	Set<java.util.Map.Entry<Integer, Order>> set2 = dept.getOrders().entrySet();

	// LOGIN FRAME
	private JFrame loginFrame;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton login;

	// ADMIN FRAME

	private JFrame adminFrmae;

	private JButton modify;
	private JButton backAdm;
	private JButton saveAdm;
	
	private JTextField productPrice;
	private JTextField productNameAdm;
	private JTextField productQuantityAdm;
	private JTextField errorModify;
	
	private JTextPane productsAdm;
	private int pp;

	Admin adm = new Admin();
	String pPrice = new String();
	public Gui() {
		// USER FRAME
		customerFrame = new JFrame();
		customerFrame.setBounds(100, 100, 518, 457);
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerFrame.getContentPane().setLayout(null);
		customerFrame.setVisible(false);

		add = new JButton("Add");
		add.setBounds(10, 162, 80, 23);
		customerFrame.getContentPane().add(add);

		errorAdd = new JTextField();
		errorAdd.setBounds(127, 163, 101, 20);
		customerFrame.getContentPane().add(errorAdd);
		errorAdd.setColumns(10);
		errorAdd.setEditable(false);

		JLabel lblName = new JLabel("Product Name");
		lblName.setBounds(10, 49, 107, 14);
		customerFrame.getContentPane().add(lblName);

		productName = new JTextField();
		productName.setBounds(127, 46, 88, 20);
		customerFrame.getContentPane().add(productName);
		productName.setColumns(10);

		JLabel lblQuantity = new JLabel("Product Quantity");
		lblQuantity.setBounds(10, 98, 101, 14);
		customerFrame.getContentPane().add(lblQuantity);

		productQuantity = new JTextField();
		productQuantity.setBounds(127, 95, 88, 20);
		customerFrame.getContentPane().add(productQuantity);
		productQuantity.setColumns(10);

		remove = new JButton("Remove");
		remove.setBounds(10, 198, 80, 23);
		customerFrame.getContentPane().add(remove);

		errorRemove = new JTextField();
		errorRemove.setColumns(10);
		errorRemove.setBounds(127, 199, 101, 20);
		customerFrame.getContentPane().add(errorRemove);
		errorRemove.setEditable(false);

		p1 = new JTextPane();
		p1.setBounds(247, 44, 224, 220);
		customerFrame.getContentPane().add(p1);
		p1.setEditable(false);

		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setBounds(10, 250, 80, 14);
		customerFrame.getContentPane().add(lblTotalPrice);

		totPrice = new JTextField();
		totPrice.setBounds(127, 244, 101, 20);
		customerFrame.getContentPane().add(totPrice);
		totPrice.setColumns(10);
		totPrice.setEditable(false);

		JLabel lblNewLabel = new JLabel("Products:");
		lblNewLabel.setBounds(10, 287, 127, 14);
		customerFrame.getContentPane().add(lblNewLabel);

		productsList = new JTextPane();
		productsList.setBounds(127, 287, 348, 52);
		customerFrame.getContentPane().add(productsList);
		productsList.setEditable(false);

		unders = new JButton("Under Stock");
		unders.setBounds(249, 10, 107, 23);
		customerFrame.getContentPane().add(unders);

		oswerS = new JButton("Over Stock");
		oswerS.setBounds(376, 10, 95, 23);
		customerFrame.getContentPane().add(oswerS);

		reset = new JButton("Reset");
		reset.setBounds(127, 12, 89, 23);
		customerFrame.getContentPane().add(reset);

		save = new JButton("Save");
		save.setBounds(33, 345, 89, 23);
		customerFrame.getContentPane().add(save);

		btnOrders = new JButton("Orders");
		btnOrders.setBounds(150, 345, 89, 23);
		customerFrame.getContentPane().add(btnOrders);

		back = new JButton("Back");
		back.setBounds(266, 345, 89, 23);
		customerFrame.getContentPane().add(back);

		add.addActionListener(this);
		remove.addActionListener(this);
		unders.addActionListener(this);
		oswerS.addActionListener(this);
		reset.addActionListener(this);
		productName.addActionListener(this);
		productQuantity.addActionListener(this);
		save.addActionListener(this);
		btnOrders.addActionListener(this);
		back.addActionListener(this);

		initWare();

		// LOGIN FRAME
		loginFrame = new JFrame();
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setVisible(true);

		userField = new JTextField();
		userField.setBounds(111, 81, 188, 20);
		loginFrame.getContentPane().add(userField);
		userField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(111, 132, 188, 20);
		loginFrame.getContentPane().add(passwordField);

		login = new JButton("Login");
		login.setBounds(169, 175, 89, 23);
		loginFrame.getContentPane().add(login);

		JLabel ID = new JLabel("USERNAME");
		ID.setBounds(10, 84, 72, 14);
		loginFrame.getContentPane().add(ID);

		JLabel pass = new JLabel("PASSWORD");
		pass.setBounds(10, 135, 72, 14);
		loginFrame.getContentPane().add(pass);

		login.addActionListener(this);

		// ADMIN FRAME
		adminFrmae = new JFrame();
		adminFrmae.setBounds(100, 100, 518, 457);
		adminFrmae.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrmae.getContentPane().setLayout(null);
		adminFrmae.setVisible(false);

		JLabel addAdm = new JLabel("Price:");
		addAdm.setBounds(10, 162, 107, 14);
		adminFrmae.getContentPane().add(addAdm);

		productPrice = new JTextField();
		productPrice.setBounds(127, 163, 101, 20);
		adminFrmae.getContentPane().add(productPrice);
		productPrice.setColumns(10);

		JLabel name = new JLabel("Product Name");
		name.setBounds(10, 49, 107, 14);
		adminFrmae.getContentPane().add(name);

		productNameAdm = new JTextField();
		productNameAdm.setBounds(127, 46, 88, 20);
		adminFrmae.getContentPane().add(productNameAdm);
		productNameAdm.setColumns(10);

		JLabel quantityAdm = new JLabel("Product Quantity");
		quantityAdm.setBounds(10, 98, 101, 14);
		adminFrmae.getContentPane().add(quantityAdm);

		productQuantityAdm = new JTextField();
		productQuantityAdm.setBounds(127, 95, 88, 20);
		adminFrmae.getContentPane().add(productQuantityAdm);
		productQuantityAdm.setColumns(10);

		modify = new JButton("Modify");
		modify.setBounds(10, 198, 80, 23);
		adminFrmae.getContentPane().add(modify);

		errorModify = new JTextField();
		errorModify.setColumns(10);
		errorModify.setBounds(127, 199, 101, 20);
		adminFrmae.getContentPane().add(errorModify);
		errorModify.setEditable(false);

		backAdm = new JButton("Back");
		backAdm.setBounds(266, 345, 89, 23);
		adminFrmae.getContentPane().add(backAdm);
		
		saveAdm = new JButton("Save");
		saveAdm.setBounds(33, 345, 89, 23);
		adminFrmae.getContentPane().add(saveAdm);

		backAdm.addActionListener(this);
		productQuantityAdm.addActionListener(this);
		modify.addActionListener(this);
		productPrice.addActionListener(this);
		saveAdm.addActionListener(this);
	}

	public void initWare() {
		String s = new String();
		for (java.util.Map.Entry<String, Product> asd : set1)
			s = s + asd.getValue().getName() + "\t" + asd.getValue().getPrice() + "\t" + asd.getValue().getStock()
					+ "\n";
		p1.setText(s);
	}

	public void underStock() {
		String s = new String();
		for (java.util.Map.Entry<String, Product> asd : set1)
			if (asd.getValue().getStock() <= 20)
				s = s + asd.getValue().getName() + "\t" + asd.getValue().getPrice() + "\t" + asd.getValue().getStock()
						+ "\n";
		p1.setText(s);
	}

	public void owerStock() {
		String s = new String();
		for (java.util.Map.Entry<String, Product> asd : set1)
			if (asd.getValue().getStock() > 20)
				s = s + asd.getValue().getName() + "\t" + asd.getValue().getPrice() + "\t" + asd.getValue().getStock()
						+ "\n";
		p1.setText(s);
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == unders) {
			underStock();
		}
		if (e.getSource() == oswerS) {
			owerStock();
		}
		if (e.getSource() == reset) {
			initWare();
		}
		if (e.getSource() == productName) {
			pName = productName.getText();
		}
		if (e.getSource() == productQuantity) {
			pStock = productQuantity.getText();
			ps = -1;
			if (isInteger(pStock) == true)
				ps = Integer.parseInt(pStock);
			else
				ps = -1;

		}
		if (e.getSource() == add) {

			if (war.checkProductExists(pName) == true) {
				if (ps > 0) {
					Product p2 = new Product(pName, war.getProd(pName).getPrice(), ps);
					errorAdd.setText("");
					if (dept.checkProductStock(p2) != false) {
						ord.addProduct(p2);
						productsList.setText(" ");
						for (int i = 0; i < ord.getSize(); i++)
							productsList.setText(productsList.getText() + ord.getProducts().get(i).getName() + " "
									+ ord.getProducts().get(i).getStock() + ",");
						String s = new String();
						s = s + ord.getTotalPrice() + "$";
						totPrice.setText(s);
						if (war.getProd(pName).getStock() == 0)
							war.removeProduct(p2);
						initWare();
					}
				} else {
					errorAdd.setText("incorect quantity");
				}
			} else {
				errorAdd.setText("incorect product");
			}
		}
		if (e.getSource() == remove) {
			int ok = 0;
			for (int i = 0; i < ord.getSize(); i++) {
				if (pName.equals(ord.getProducts().get(i).getName())) {
					Product p2 = new Product(pName, ord.getProducts().get(i).getPrice(),
							ord.getProducts().get(i).getStock());
					ord.removeProduct(p2, i);
					war.addProduct(p2);
					initWare();
					ok = 1;
				}
			}
			if (ok == 1) {
				String s = new String();
				s = s + ord.getTotalPrice();
				totPrice.setText(s);
				productsList.setText(" ");
				for (int i = 0; i < ord.getSize(); i++)
					productsList.setText(productsList.getText() + ord.getProducts().get(i).getName() + " "
							+ ord.getProducts().get(i).getStock() + ",");
			} else {
				errorRemove.setText("invalid product");
			}
		}
		if (e.getSource() == save) {
			dept.addOrder(ord);
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.bin"));
				for (java.util.Map.Entry<String, Product> produces : set1) {
					os.writeObject(produces.getValue());
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream("data1.bin"));
				for (java.util.Map.Entry<Integer, Order> orders : set2) {
					os1.writeObject(orders.getValue());
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ordID++;
			ord = new Order(ordID);
			totPrice.setText("");
			productsList.setText(" ");
		}
		if (e.getSource() == btnOrders) {

			ordersFrame = new JFrame();
			ordersFrame.setBounds(200, 200, 535, 439);
			ordersFrame.getContentPane().setLayout(null);
			ordersFrame.setVisible(true);

			JTextPane textPane = new JTextPane();
			textPane.setBounds(10, 11, 499, 378);
			ordersFrame.getContentPane().add(textPane);
			textPane.setEditable(false);

			for (java.util.Map.Entry<Integer, Order> orders : set2) {
				String s = new String();

				textPane.setText(textPane.getText() + orders.getValue().getOrderID() + "\n");
				for (int i = 0; i < orders.getValue().getSize(); i++) {
					s = orders.getValue().getProducts().get(i).getName() + " "
							+ orders.getValue().getProducts().get(i).getPrice() + "$ "
							+ orders.getValue().getProducts().get(i).getStock() + " ";
					textPane.setText(textPane.getText() + s);
				}
				textPane.setText(textPane.getText() + "\nTotal Price:" + orders.getValue().getTotalPrice() + "\n");
			}

		}
		if (e.getSource() == back) {
			loginFrame.setVisible(true);
			customerFrame.setVisible(false);
		}
		if (e.getSource() == backAdm) {
			loginFrame.setVisible(true);
			adminFrmae.setVisible(false);
		}
		if (e.getSource() == login) {
			String pass = new String(passwordField.getPassword());

			if (cust.getUserName().equals(userField.getText()) && cust.getPassword().equals(pass)) {
				loginFrame.setVisible(false);
				customerFrame.setVisible(true);
				customerFrame.getContentPane().add(p1);
			} else if (adm.getUserName().equals(userField.getText()) && adm.getPassword().equals(pass)) {
				loginFrame.setVisible(false);
				adminFrmae.setVisible(true);
				adminFrmae.getContentPane().add(p1);
			}
		}
		if (e.getSource() == productQuantityAdm) {
			pStock = productQuantityAdm.getText();
			ps = -1;
			if (isInteger(pStock) == true)
				ps = Integer.parseInt(pStock);
			else
				ps = -1;

		}
		
		if (e.getSource() == productPrice) {
			pPrice = productPrice.getText();
			pp = -1;
			if (isInteger(pPrice) == true)
				pp = Integer.parseInt(pPrice);
			else
				pp = -1;

		}
		
		if (e.getSource() == modify) {
			if (ps > -1) {
				if (ps == 0) {
					Product p = new Product(productNameAdm.getText(), pp, ps);
					war.removeProduct(p);
					initWare();
					errorModify.setText("");

				} else if (ps > 0) {
					if (pp > 0) {
						Product p = new Product(productNameAdm.getText(), pp, ps);
						war.addProductAdmin(p);
						initWare();
						errorModify.setText("");
					}
					else{
						errorModify.setText("Invalid price");
					}
				}
			}else{
				errorModify.setText("Invalid quantity");
			}
		}
		if(e.getSource()==saveAdm){
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.bin"));
				for (java.util.Map.Entry<String, Product> produces : set1) {
					os.writeObject(produces.getValue());
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new Gui();

	}

}
