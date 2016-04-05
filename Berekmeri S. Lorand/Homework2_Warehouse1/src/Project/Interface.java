package Project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeSet;

/**
 * The class Interface represents the class that creates the user interface. It
 * displays several labels and text fields on a JPanel using a GridBaglayout and
 * has a Jmenu. It extends JFrame and implements ActionListener
 */
public class Interface extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton start = new JButton("OK");
	private JLabel L1 = new JLabel("Client name");
	private JLabel L2 = new JLabel("Address");
	private JLabel L3 = new JLabel("Product");
	private JLabel L4 = new JLabel("Quantity");
	private JPanel pane = new JPanel(new GridBagLayout());;
	private TextField tt11 = new TextField(10);
	private TextField tt12 = new TextField(10);

	private TextArea info = new TextArea("", 10, 80, TextArea.SCROLLBARS_BOTH);
	private static InterfaceProducts warehouse = new InterfaceProducts();

	private boolean display1 = true;
	private JScrollPane scrollPane;
	private static JComboBox choice1 = new JComboBox();
	private TextField choice2 = new TextField(10);
	private OPDept departament = new OPDept();

	static String message = "";

	private Object[][] data2 = new Object[100][7];

	private String[] columnNames = { "Order number", "TotalPrice", "Client name", "C Addr", "Product", "amount",
			"price" };

	private JTable table2;

	/**
	 * constructor- display user interface
	 */
	public Interface() {

		departament = (OPDept) IOClass.loadInfo("dat2.dat");
		warehouse.loadWarehouse();
		if (departament == null)
			departament = new OPDept();
		if (warehouse == null)
			warehouse = new InterfaceProducts();

		this.setSize(1400, 1200);
		getContentPane().add(pane);
		GridBagConstraints c = new GridBagConstraints();
		pane.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(L1, c);
		c.gridx = 1;
		c.gridwidth = 2;
		pane.add(tt11, c);

		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		pane.add(L2, c);
		c.gridx = 1;
		c.gridwidth = 2;
		pane.add(tt12, c);

		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 1;
		pane.add(L3, c);
		c.gridx = 1;
		c.gridwidth = 2;
		choice1.addActionListener(this);
		pane.add(choice1, c);

		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 1;
		pane.add(L4, c);
		c.gridx = 1;
		c.gridwidth = 2;
		pane.add(choice2, c);

		c.gridwidth = 1;
		c.gridy = 6;
		c.gridx = 1;
		pane.add(start, c);
		start.addActionListener(this);

		c.gridwidth = 6;
		c.gridy = 7;
		c.gridx = 0;
		pane.add(info, c);

		// Create the menu bar.
		this.setJMenuBar(this.createMenuBar());

		// table 1
		c.gridwidth = 6;
		c.gridy = 8;
		c.gridx = 0;
		init();
		initializeTable();

		table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table2.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table2);
		scrollPane.setVisible(display1);
		// Add the scroll pane to this panel.
		pane.add(scrollPane, c);

		// table 2
		c.gridwidth = 6;
		c.gridy = 8;
		c.gridx = 0;

		OPDept oop = new OPDept();

	}

	public static void refreshStock() {
		init();
	}

	public static void init() {

		choice1.removeAllItems();
		TreeSet<Product> c = (TreeSet<Product>) warehouse.getProducts();
		Object[] objArray = c.toArray();
		Product aux = null;
		int i = 0;
		for (Object obj : objArray) {
			aux = (Product) obj;
			choice1.addItem(aux);
			i++;
		}
	}

	public void initializeTable() {

		TreeSet<Order> c = (TreeSet<Order>) departament.getOrders();
		Object[] objArray = c.toArray();
		Order aux;
		int i = 0;
		String s1 = "", s2, s3;
		Integer a, c1, d;
		Integer b;
		int test = 0;
		for (Object obj : objArray) {
			aux = (Order) obj;
			a = aux.getNrOrder();
			s1 = aux.getClient().getName();
			s2 = aux.getClient().getAddress();
			s3 = aux.getProduct().getName();
			b = aux.getProduct().getPrice();
			c1 = aux.getProduct().getAmount();
			d = aux.getCost();

			data2[i][0] = new Integer(a);
			data2[i][1] = new Integer(d);
			data2[i][2] = new String(s1);
			data2[i][3] = new String(s2);
			data2[i][4] = new String(s3);
			data2[i][5] = new Integer(c1);
			data2[i][6] = new Integer(b);

			i++;
		}

		table2 = new JTable(data2, columnNames);
		repaint();
		validate();
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		menuItem = new JMenuItem("Save products");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("sprod");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save orders");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("sord");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Introduce products");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menuItem.setActionCommand("admin");
		menu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menuItem.setActionCommand("exit");
		menu.add(menuItem);
		return menuBar;
	}

	private Product p = null;

	/**
	 * action performed - deal with events
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == choice1) {
			JComboBox cb = (JComboBox) event.getSource();
			p = (Product) cb.getSelectedItem();
			System.out.println("SELECTED" + p);
			start.setEnabled(!warehouse.getProducts().isEmpty());
		}

		if (source == start) {
			String s1 = tt11.getText();
			String s2 = tt12.getText();
			String s3 = choice2.getText();
			int a = 0;
			if ((s1 != null) && (s2 != null) && (s3 != null)) {
				try {
					a = Integer.parseInt(s3);

				} catch (NumberFormatException e2) {
					a = 0;
				}
				System.out.println("Sent ORDER " + s1 + s2 + a + p);

			}
			TreeSet<Product> aux2 = new TreeSet<Product>();
			aux2 = (TreeSet<Product>) departament.processOrder((TreeSet<Product>) warehouse.getProducts(), p, a, s1,
					s2);
			warehouse.setProducts((TreeSet<Product>) aux2);
			init();
			start.setEnabled(!warehouse.getProducts().isEmpty());
			info.append(message);
			initializeTable();
			repaint();
			validate();

		}
		String ss = event.getActionCommand();
		System.out.println(ss);
		
		if (ss != null) {

			if (ss.equals("sprod")) {
				warehouse.saveWarehouse();
			}
			;
			if (ss.equals("exit"))
				
				System.exit(0);
			if (ss.equals("sord"))
				//if (admin.checkUser()) {
				IOClass.saveInfo(departament, "dat2.dat");
				//} else {
					//JOptionPane.showMessageDialog(null, "Incorrect password!");
				//}
			if (ss.equals("admin")) {
				System.out.println("aici");
				AdminGUI admin = new AdminGUI();
				if (admin.checkUser()) {
					warehouse.displayInterface();
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect password!");
				}

			}

		}

	}

}
