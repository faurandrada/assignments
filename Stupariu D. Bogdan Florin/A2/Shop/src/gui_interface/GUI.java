package gui_interface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import actors.logInUser;
import run.Const;
import shop.OPDept;
import shop.Order;
import shop.Product;
import shop.Warehouse;

public class GUI implements ActionListener, KeyListener {

	private Warehouse warehouse;
	private OPDept opdept;
	private waitingWindow waitingW;

	private JFrame myFrame = new JFrame("StupShop");
	private CardLayout card = new CardLayout();
	private JPanel contentPanel = new JPanel();
	private JPanel entryPanel = new JPanel();
	private JButton entrySearchButton = new JButton("Search Product");
	private JPanel userPanel = new JPanel();
	private JPanel adminPanel = new JPanel();
	private JButton backToEntry = new JButton("Back");

	private JLabel shopName = new JLabel("StupShop");
	private GridBagConstraints gbc = new GridBagConstraints();
	private JTextField userArea = new JTextField();
	private JLabel userName = new JLabel("user");
	private JLabel passwordName = new JLabel("password");
	private JButton logIn = new JButton("Log in");
	private JButton createAccount = new JButton("New User");
	private String password = new String();
	private String user = new String();
	private boolean loggedIn = false;
	private JPasswordField passwordArea = new JPasswordField();
	private JButton backToMenu = new JButton("Back");
	private String username = new String();
	private JTextField textId = new JTextField();
	private JTextField textName = new JTextField();
	private JTextField textCategory = new JTextField();
	private JTextField textPrice = new JTextField();
	private JTextField textQuant = new JTextField();

	private JTable table = new JTable();

	// create a table model and set a Column Identifiers to this model
	private Object[] columns = { "Id", "Product Name", "Category", "Price", "Quantity" };
	private DefaultTableModel model = new DefaultTableModel();
	// create JScrollPane
	private JScrollPane pane = new JScrollPane(table);

	// create JButtons
	private JButton btnAdd = new JButton("Add");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnUpdate = new JButton("Update");

	private JLabel searchProduct = new JLabel("Search product");
	private JTextField searchField = new JTextField();
	private JLabel loggedUserInfo = new JLabel();

	private JTable userTable = new JTable();
	private JTable orderTable = new JTable();

	// create a table model and set a Column Identifiers to this model
	private Object[] userColumns = { "Product Name", "Category", "Price", "Quantity" };
	private Object[] orderColumns = { "Order Date", "Product", "Quantity", "Price" };
	private DefaultTableModel userModel = new DefaultTableModel();
	private DefaultTableModel orderModel = new DefaultTableModel();
	// create JScrollPane
	private JScrollPane userPane = new JScrollPane(userTable);
	private JScrollPane orderPane = new JScrollPane(orderTable);

	private JTextField userTextName = new JTextField();
	private JTextField userTextCategory = new JTextField();
	private JTextField userTextPrice = new JTextField();
	private JTextField userTextQuant = new JTextField();

	private Object[] userRow = new Object[4];
	private Object[] orderRow = new Object[4];

	private JButton placeOrder = new JButton("Place Order");
	private JTextArea statusOrder = new JTextArea("No order placed!");

	private Object[] row = new Object[5];

	public GUI(int HEIGHT, int WIDTH) {

		warehouse = new Warehouse();
		opdept = new OPDept();

		warehouse.read();
		opdept.read();

		// TODO Auto-generated constructor stub
		buildFrame(HEIGHT, WIDTH);

		contentPanel.setLayout(card);

		shopName.setFont(Const.myTitleFont);
		shopName.setForeground(Color.BLACK);

		entrySearchButton.setPreferredSize(new Dimension(400, 50));
		entrySearchButton.setBackground(Color.WHITE);
		entrySearchButton.setFont(Const.myButtonFont);
		entrySearchButton.setFocusPainted(false);
		entrySearchButton.addActionListener(this);
		entryPanel.setLayout(new GridBagLayout());

		gbc.insets = new Insets(10, 250, 100, 10);
		entryPanel.setBackground(Color.darkGray);
		gbc.gridx = 1;
		gbc.gridy = 1;
		entryPanel.add(shopName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		entryPanel.add(entrySearchButton, gbc);

		gbc.insets = new Insets(10, 670, 10, 0);
		gbc.gridx = 1;
		gbc.gridy = 3;
		userName.setForeground(Color.WHITE);
		userName.setPreferredSize(new Dimension(70, 30));
		entryPanel.add(userName, gbc);
		gbc.insets = new Insets(10, 0, 10, 10);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 3;
		userArea.setPreferredSize(new Dimension(150, 25));
		userArea.addKeyListener(this);
		entryPanel.add(userArea, gbc);
		gbc.insets = new Insets(10, 670, 10, 0);
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		passwordName.setForeground(Color.WHITE);
		passwordName.setPreferredSize(new Dimension(70, 30));
		entryPanel.add(passwordName, gbc);
		gbc.insets = new Insets(10, 0, 10, 10);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 4;
		passwordArea.setPreferredSize(new Dimension(150, 25));
		passwordArea.addKeyListener(this);
		entryPanel.add(passwordArea, gbc);
		gbc.insets = new Insets(5, 0, 10, 10);
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridy = 5;
		logIn.setBackground(Color.BLACK);
		logIn.setForeground(Color.WHITE);
		logIn.setFocusPainted(false);
		logIn.addActionListener(this);
		logIn.addKeyListener(this);
		entryPanel.add(logIn, gbc);
		gbc.insets = new Insets(5, 0, 10, 10);
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbc.gridy = 5;
		createAccount.setBackground(Color.BLACK);
		createAccount.setForeground(Color.WHITE);
		createAccount.setFocusPainted(false);
		createAccount.addActionListener(this);
		entryPanel.add(createAccount, gbc);

		buildAdminPanel();
		buildUserPanel();

		contentPanel.add(entryPanel, "entry");
		contentPanel.add(userPanel, "userView");
		contentPanel.add(adminPanel, "admin");
		card.show(contentPanel, "entry");

		myFrame.add(contentPanel);

		myFrame.setVisible(true);
	}
	
	JLabel nrOrdersLabel = new JLabel();
	JLabel totalCostLabel = new JLabel();
	JLabel nrOrdersText = new JLabel();
	JLabel totalCostText = new JLabel();

	private void buildUserPanel() {

		// TODO Auto-generated method stub
		userPanel.setLayout(null);
		userPanel.setBackground(Color.DARK_GRAY);

		searchProduct.setForeground(Color.WHITE);
		searchProduct.setBounds(10, 10, 100, 30);
		userPanel.add(searchProduct);

		searchField.setBackground(Color.WHITE);
		searchField.setBounds(110, 10, 300, 30);
		userPanel.add(searchField);

		loggedUserInfo.setForeground(Color.WHITE);
		loggedUserInfo.setBounds(Const.MAIN_WIDTH - 300, 10, 300, 30);
		userPanel.add(loggedUserInfo);

		backToEntry.setBounds(Const.MAIN_WIDTH - 120, Const.MAIN_HEIGHT - 80, 100, 40);
		backToEntry.addActionListener(this);
		backToEntry.setBackground(Color.WHITE);
		userPanel.add(backToEntry);

		placeOrder.setBackground(Color.RED);
		placeOrder.setForeground(Color.WHITE);
		placeOrder.setBounds(50, 420, 200, 50);
		placeOrder.setFocusPainted(false);
		placeOrder.addActionListener(this);
		userPanel.add(placeOrder);

		statusOrder.setBackground(Color.DARK_GRAY);
		statusOrder.setForeground(Color.WHITE);
		statusOrder.setBounds(300, 420, 300, 100);
		statusOrder.setFocusable(false);
		userPanel.add(statusOrder);
		
		nrOrdersLabel.setForeground(Color.LIGHT_GRAY);
		nrOrdersLabel.setText("Total number of orders: ");
		nrOrdersLabel.setBounds(650, 370, 150, 30);
		userPanel.add(nrOrdersLabel);
		
		totalCostLabel.setForeground(Color.LIGHT_GRAY);
		totalCostLabel.setText("Total cost: ");
		totalCostLabel.setBounds(650, 410, 150, 30);
		userPanel.add(totalCostLabel);
		
		nrOrdersText.setForeground(Color.LIGHT_GRAY);
		nrOrdersText.setBounds(800, 370, 100, 30);
		userPanel.add(nrOrdersText);
		
		totalCostText.setForeground(Color.LIGHT_GRAY);
		totalCostText.setBounds(800, 410, 100, 30);
	    userPanel.add(totalCostText);
		
		userModel.setColumnIdentifiers(userColumns);
		orderModel.setColumnIdentifiers(orderColumns);

		// set the model to the table
		userTable.setModel(userModel);
		orderTable.setModel(orderModel);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		userTable.setBackground(Color.LIGHT_GRAY);
		userTable.setForeground(Color.black);
		userTable.setFont(Const.tableFont);
		userTable.setRowHeight(30);
		//userTable.setDefaultEditor(Object.class, null);

		orderTable.setBackground(Color.LIGHT_GRAY);
		orderTable.setForeground(Color.BLACK);
		orderTable.setFont(Const.orderFont);
		orderTable.setRowHeight(30);
		//orderTable.setDefaultEditor(Object.class, null);
		
		userPane.setBounds(0, 50, 600, 300);
		orderPane.setBounds(650, 50, 300, 300);

		userTextName.setBounds(0, 370, 140, 30);
		userPanel.add(userTextName);
		userTextCategory.setBounds(150, 370, 140, 30);
		userPanel.add(userTextCategory);
		userTextPrice.setBounds(300, 370, 140, 30);
		userPanel.add(userTextPrice);
		userTextQuant.setBounds(450, 370, 150, 30);
		userPanel.add(userTextQuant);

		userPanel.add(orderPane);
		userPanel.add(userPane);

		// create an array of objects to set the row data

		// add existing products
		for (int j = 0; j < warehouse.toArray().size(); j++) {
			userRow[0] = warehouse.toArray().get(j).getName();
			userRow[1] = warehouse.toArray().get(j).getCategory();
			userRow[2] = warehouse.toArray().get(j).getPrice();
			userRow[3] = warehouse.toArray().get(j).getQuantity();
			userModel.addRow(userRow);
		}

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(userTable.getModel());
		userTable.setRowSorter(rowSorter);

		searchField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = searchField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = searchField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});

		// get selected row data From table to textfields
		userTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				// i = the index of the selected row
				int i = userTable.getSelectedRow();

				userTextName.setText(userModel.getValueAt(i, 0).toString());
				userTextCategory.setText(userModel.getValueAt(i, 1).toString());
				userTextPrice.setText(userModel.getValueAt(i, 2).toString());
				userTextQuant.setText(userModel.getValueAt(i, 3).toString());

			}
		});
	}

	private void buildAdminPanel() {

		System.out.println(warehouse.toArray().size());

		adminPanel.setBackground(Color.DARK_GRAY);
		adminPanel.setLayout(null);

		backToMenu.setBackground(Color.WHITE);
		backToMenu.setFocusPainted(false);
		backToMenu.addActionListener(this);

		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setFont(Const.orderFont);
		table.setRowHeight(30);

		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(this);

		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(this);

		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(this);

		textId.setBounds(20, 420, 130, 25);
		textName.setBounds(200, 420, 130, 25);
		textCategory.setBounds(380, 420, 130, 25);
		textPrice.setBounds(560, 420, 130, 25);
		textQuant.setBounds(740, 420, 130, 25);
		
		userTextName.setEditable(false);
		userTextCategory.setEditable(false);
		userTextPrice.setEditable(false);

		btnAdd.setBounds(100, 510, 150, 45);
		btnUpdate.setBounds(300, 510, 150, 45);
		btnDelete.setBounds(500, 510, 150, 45);
		backToMenu.setBounds(700, 510, 150, 45);

		pane.setBounds(0, 0, 960, 400);

		adminPanel.add(pane);

		// add JTextFields to the jframe
		adminPanel.add(textId);
		adminPanel.add(textName);
		adminPanel.add(textCategory);
		adminPanel.add(textPrice);
		adminPanel.add(textQuant);

		// add JButtons to the jframe
		adminPanel.add(btnAdd);
		adminPanel.add(btnDelete);
		adminPanel.add(btnUpdate);
		adminPanel.add(backToMenu);

		// add existing products
		for (int j = 0; j < warehouse.toArray().size(); j++) {
			row[0] = warehouse.toArray().get(j).getID();
			row[1] = warehouse.toArray().get(j).getName();
			row[2] = warehouse.toArray().get(j).getCategory();
			row[3] = warehouse.toArray().get(j).getPrice();
			row[4] = warehouse.toArray().get(j).getQuantity();
			model.addRow(row);
		}

		// get selected row data From table to textfields
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();

				textId.setText(model.getValueAt(i, 0).toString());
				textName.setText(model.getValueAt(i, 1).toString());
				textCategory.setText(model.getValueAt(i, 2).toString());
				textPrice.setText(model.getValueAt(i, 3).toString());
				textQuant.setText(model.getValueAt(i, 4).toString());

			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == entrySearchButton) {
			if (loggedIn) {
				loggedUserInfo.setText("You are logged as " + getUsername());
				for (int j = 0; j < opdept.toArray().size(); j++) {
					if (opdept.toArray().get(j).getUser().equalsIgnoreCase(getUsername())) {
						boolean exists = false;
						for (int i = 0; i < orderTable.getRowCount(); i++) {
							String s = null;
							s = orderTable.getValueAt(i, 0).toString();
							if (opdept.toArray().get(j).getDate().equalsIgnoreCase(s)) {
								exists = true;
							}
						}
						if (!exists) {
							orderRow[0] = opdept.toArray().get(j).getDate();
							orderRow[1] = opdept.toArray().get(j).getProduct();
							orderRow[2] = opdept.toArray().get(j).getQuantity();
							orderRow[3] = opdept.toArray().get(j).getPrice();
							orderModel.addRow(orderRow);
						}
					}
				}
				int totalOrders = orderTable.getRowCount();
				nrOrdersText.setText(Integer.toString(totalOrders));
				
				int total = 0;
			    for (int i = 0; i < orderTable.getRowCount(); i++){
			        int amount = Integer.parseInt(orderTable.getValueAt(i, 3).toString());
			        total += amount;
			    }
				totalCostText.setText(Integer.toString(total));
				
				card.show(contentPanel, "userView");
			} else {
				JOptionPane.showMessageDialog(entryPanel,
						"Please log in first. If you do not have an account, please register", "Log in first",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == backToEntry) {
			int rowCount = orderTable.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
			    orderModel.removeRow(i);
			}
			card.show(contentPanel, "entry");
		}
		if (e.getSource() == logIn) {
			loggedIn = false;
			user = userArea.getText();
			password = new String(passwordArea.getPassword());

			logInUser log = new logInUser();
			try {
				loggedIn = log.checkLogInUser(user, password);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (user.contentEquals("admin") && password.contentEquals("admin")) {
				loggedIn = true;
				card.show(contentPanel, "admin");
				setUsername("admin");
			} else if (loggedIn) {
				userArea.setText(user);
				passwordArea.setText("*********");
				JOptionPane.showMessageDialog(entryPanel, "Welcome again Sir! Now you can search for products",
						"Logged in", JOptionPane.INFORMATION_MESSAGE);
				username = user;
				System.out.println(username);
			} else {
				JOptionPane.showMessageDialog(entryPanel,
						"Log in failed. If you do not have an account, register first", "Log in failed",
						JOptionPane.ERROR_MESSAGE);
				passwordArea.setText(null);
				userArea.setText(null);
			}

		}
		if (e.getSource() == createAccount) {
			new createUser();
		}

		if (e.getSource() == backToMenu) {
			
			card.show(contentPanel, "entry");
		}

		if (e.getSource() == btnUpdate) {
			// i = the index of the selected row
			int i = table.getSelectedRow();

			if (i >= 0) {

				model.setValueAt(textId.getText(), i, 0);
				model.setValueAt(textName.getText(), i, 1);
				model.setValueAt(textCategory.getText(), i, 2);
				model.setValueAt(textPrice.getText(), i, 3);
				model.setValueAt(textQuant.getText(), i, 4);

				String nameValue = (String) table.getValueAt(i, 1);
				Product prod = warehouse.findNode(nameValue, warehouse.getRoot());
				prod.setID(Integer.parseInt(textId.getText().toString()));
				prod.setName(textName.getText());
				prod.setCategory(textCategory.getText());
				prod.setPrice(Integer.parseInt(textPrice.getText()));
				prod.setQuantity(Integer.parseInt(textQuant.getText()));

				System.out.println(prod.getID() + " " + prod.getName() + " " + prod.getCategory() + " "
						+ prod.getPrice() + " " + prod.getQuantity());

				warehouse.write();

			} else {
				System.out.println("Update Error");
			}
		}

		if (e.getSource() == btnDelete) {
			// i = the index of the selected row
			int i = table.getSelectedRow();
			if (i >= 0) {
				// remove a row from jtable
				int keyvalue = Integer.parseInt(table.getValueAt(i, 0).toString());
				warehouse.remove(keyvalue);
				model.removeRow(i);

			} else {
				System.out.println("Delete Error");
			}
			warehouse.write();
		}

		if (e.getSource() == btnAdd) {

			row[0] = textId.getText();
			row[1] = textName.getText();
			row[2] = textCategory.getText();
			row[3] = textPrice.getText();
			row[4] = textQuant.getText();

			// add row to the model
			boolean existsInTable = false;
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getValueAt(i, 1).equals(row[1])) {
					existsInTable = true;
				}
			}

			if (existsInTable) {
				JOptionPane.showMessageDialog(adminPanel, "Dupplicate product", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {

				model.addRow(row);
				int ID = Integer.parseInt(textId.getText());
				int price = Integer.parseInt(textPrice.getText());
				int quantity = Integer.parseInt(textQuant.getText());
				Product p = new Product(ID, textName.getText(), textCategory.getText(), price, quantity);
				System.out.println(p.getID() + " " + p.getName() + " " + p.getCategory() + " " + p.getPrice() + " "
						+ p.getQuantity());
				warehouse.addNode(p);
				warehouse.write();
			}
		}

		if (e.getSource() == placeOrder) {

			waitingW = new waitingWindow();

			
			SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {

				@Override
				protected Void doInBackground() throws Exception {
					
					int Min = 2;
					int Max = 4;
					int y = Min + (int)(Math.random() * ((Max - Min) + 1));
					
					Thread.sleep(y*1000);

					waitingW.close();

					Random ran = new Random();
					int x = ran.nextInt(6) + 5;

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();

					int orderNumber = x;
					String orderDate = dateFormat.format(date);
					String user = getUsername();
					int quantityOrdered = Integer.parseInt(userTextQuant.getText());
					String product = userTextName.getText();
					int price = Integer.parseInt(userTextPrice.getText())*quantityOrdered;

					Order order = new Order(orderNumber, orderDate, user, quantityOrdered, product, price);

					orderRow[0] = order.getDate();
					orderRow[1] = order.getProduct();
					orderRow[2] = order.getQuantity();
					orderRow[3] = order.getPrice();

					int index = 0;
					for (int i = 0; i < userTable.getRowCount(); i++) {
						if (userTable.getValueAt(i, 0).toString().equalsIgnoreCase(order.getProduct())) {
							index = i;
						}
					}
					int quantTable = Integer.parseInt(userTable.getValueAt(index, 3).toString());

					if (quantTable < quantityOrdered) {
						statusOrder.setText("Order failed.");
						JOptionPane.showMessageDialog(userPanel, "Not enaugh products in warehouse", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						statusOrder.setText("Order processed. Thank you!");
						order.setQuantity(quantityOrdered);
						opdept.addNode(order);
						orderModel.addRow(orderRow);
						userTable.setValueAt(quantTable - quantityOrdered, index, 3);

						String nameValue = order.getProduct();
						System.out.println(nameValue);
						Product p = warehouse.findNode(nameValue, warehouse.getRoot());
						System.out.println(p.getName());
						p.setQuantity(p.getQuantity() - quantityOrdered);
						opdept.write();
						warehouse.write();
					}

					return null;
				}

			};

			worker.execute();

			opdept.write();
			warehouse.write();
		}
	}

	private void buildFrame(int HEIGHT, int WIDTH) {
		// TODO Auto-generated method stub
		myFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(myFrame, "Are you sure to close this window?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					warehouse.write();
					System.exit(0);
				}
			}
		});
	}

	private void setUsername(String user) {
		this.username = user;
	}

	public String getUsername() {
		return username;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			loggedIn = false;
			user = userArea.getText();
			password = new String(passwordArea.getPassword());

			logInUser log = new logInUser();
			try {
				loggedIn = log.checkLogInUser(user, password);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (user.contentEquals("admin") && password.contentEquals("admin")) {
				loggedIn = true;
				card.show(contentPanel, "admin");
				setUsername("admin");
			} else if (loggedIn) {
				userArea.setText(user);
				passwordArea.setText("*********");
				JOptionPane.showMessageDialog(entryPanel, "Welcomae again sir! Now you can search for products",
						"Logged In", JOptionPane.INFORMATION_MESSAGE);
				setUsername(user);
			} else {
				JOptionPane.showMessageDialog(entryPanel,
						"Log in failed. If you do not have an account, register first", "Log in failed",
						JOptionPane.ERROR_MESSAGE);
				passwordArea.setText(null);
				userArea.setText(null);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
