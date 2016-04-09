package Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Collection;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.html.HTMLDocument.Iterator;
/**
 * This class implements the 2nd Jpanel, where only the admin can
 * modify the products. (load, save, initialize, display)
 */
public class InterfaceProducts extends JFrame implements ActionListener, TableModelListener, Serializable {

	private static final long serialVersionUID = -2512026283231375452L;

	private JButton button1 = new JButton("OK");
	JButton admin;
	private JLabel l1 = new JLabel("Product name");
	private JLabel l2 = new JLabel("Quantity");
	private JLabel l3 = new JLabel("Price");

	private TextField name = new TextField(30);
	private TextField quant = new TextField(30);
	private TextField price = new TextField(30);

	private JPanel pane = new JPanel(new GridBagLayout());;

	private JScrollPane scrollPane;

	private String[] columnNames2 = { "Name", "Price", "Quantity" };

	private Warehouse warehouse = new Warehouse();

	private Object[][] data2 = new Object[100][3];

	private JTable table;

	private GridBagConstraints c = new GridBagConstraints();

	public InterfaceProducts() {
		this.setSize(1400, 1200);
		getContentPane().add(pane);

		pane.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(l1, c);
		c.gridx = 1;
		pane.add(name, c);

		c.gridx = 0;
		c.gridy = 1;
		pane.add(l2, c);
		c.gridx = 1;
		pane.add(quant, c);

		c.gridx = 0;
		c.gridy = 2;
		pane.add(l3, c);
		c.gridx = 1;
		pane.add(price, c);

		c.gridx = 0;
		c.gridy = 3;
		pane.add(button1, c);
		button1.addActionListener(this);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;

		initializeTable();

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		if (table.getRowCount() != 0) {
			scrollPane = new JScrollPane(table);
			scrollPane.setVisible(true);
			// Add the scroll pane to this panel.
			pane.add(scrollPane, c);
		}
		this.add(pane);
		
	}

	public void saveWarehouse() {
		IOClass.saveInfo(warehouse, "dat3.dat");
	}

	public void loadWarehouse() {
		warehouse = (Warehouse) IOClass.loadInfo("dat3.dat");
		if (warehouse == null)
			warehouse = new Warehouse();
	}

	/**
	 * initialize table with elements
	 */
	public void initializeTable() {

		TreeSet<Product> c = (TreeSet<Product>) warehouse.getProducts();
		Object[] objArray = c.toArray();
		Product aux;
		int i = 0;
		String s1 = "";
		Integer a;
		Integer b;
		for (Object obj : objArray) {
			aux = (Product) obj;
			s1 = aux.getName();
			a = aux.getPrice();
			b = aux.getAmount();

			if (obj != null) {
				// System.out.println(obj);
				data2[i][0] = new String(s1);
				data2[i][1] = new Integer(a);
				data2[i][2] = new Integer(b);
			}
			i++;

		}
		table = new JTable(data2, columnNames2);
		repaint();
		validate();
	}

	public void displayInterface() {
		System.out.println("aici2");
		this.setSize(1000, 1000);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Interface.refreshStock();
			}
		});
		this.setTitle("Introduce products");
		pane.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		this.pack();
		this.setVisible(true);
		initializeTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == button1) {

			String s1 = name.getText();
			String s2 = quant.getText();
			String s3 = price.getText();
			name.setText("");
			if ((s1 != "") && (s1 != null) && (name.getText() != "")) {
				int a = 0;
				int b = 0;
				try {
					a = Integer.parseInt(s3);
					b = Integer.parseInt(s2);
				} catch (NumberFormatException e2) {
					a = 0;
					b = 0;
				}
				if (a * b != 0) {
					Product x = new Product(s1, a, b);
					warehouse.addProduct(x);
					initializeTable();
				}

			}

		}
	}

	public Collection getProducts() {
		return warehouse.getProducts();
	}

	public void setProducts(TreeSet<Product> x) {
		warehouse.setProducts(x);
	}

	public void tableChanged(TableModelEvent e) {
		int firstRow = e.getFirstRow();
		int lastRow = e.getLastRow();
		int index = e.getColumn();
		System.out.println("Event on table!");
		switch (e.getType()) {
		case TableModelEvent.INSERT:
			for (int i = firstRow; i <= lastRow; i++) {
				System.out.println(i);
			}
			break;
		case TableModelEvent.UPDATE:
			if (firstRow == TableModelEvent.HEADER_ROW) {
				if (index == TableModelEvent.ALL_COLUMNS) {
					System.out.println("A column was added");
				} else {
					System.out.println(index + "in header changed");
				}
			} else {
				for (int i = firstRow; i <= lastRow; i++) {
					if (index == TableModelEvent.ALL_COLUMNS) {
						System.out.println("All columns have changed");
					} else {
						System.out.println(index);
					}
				}
			}
			break;
		case TableModelEvent.DELETE:
			for (int i = firstRow; i <= lastRow; i++) {
				System.out.println(i);
			}
			break;
		}
	}
}
