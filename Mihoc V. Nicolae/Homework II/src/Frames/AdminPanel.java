package Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Design.Product;
import Design.Warehouse;
import Read_Write.InputOutput;

public class AdminPanel {
	private JFrame admin;
	private JLabel productlabel;
	private JTextField productbox;
	private JLabel pricelabel;
	private JTextField pricebox;
	private JLabel quantitylabel;
	private JTextField quantitybox;
	private JButton addprod;
	private JButton removeprod;
	private JTable table;
	private static DefaultTableModel tableModel;
	private JLabel idlabel;
	private JTextField idbox;
	private JButton search;
	private JButton stock;

	private Warehouse items;
	private InputOutput x;
	private Product p;
	private Iterator<Product> it;

	public AdminPanel() {
		admin = new JFrame("Admin Panel");
		admin.setSize(500, 500);
		admin.setLayout(null);
		admin.setVisible(true);
		admin.setLocationRelativeTo(null);
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		idlabel = new JLabel("Product ID: ");
		idlabel.setBounds(5, 10, 120, 20);
		admin.add(idlabel);

		idbox = new JTextField();
		idbox.setBounds(70, 10, 150, 20);
		admin.add(idbox);

		productlabel = new JLabel("Product: ");
		productlabel.setBounds(5, 30, 120, 20);
		admin.add(productlabel);

		productbox = new JTextField();
		productbox.setBounds(70, 30, 150, 20);
		admin.add(productbox);

		quantitylabel = new JLabel("Quantity: ");
		quantitylabel.setBounds(5, 50, 120, 20);
		admin.add(quantitylabel);

		quantitybox = new JTextField();
		quantitybox.setBounds(70, 50, 150, 20);
		admin.add(quantitybox);

		pricelabel = new JLabel("Price: ");
		pricelabel.setBounds(5, 70, 180, 20);
		admin.add(pricelabel);

		pricebox = new JTextField();
		pricebox.setBounds(70, 70, 150, 20);
		admin.add(pricebox);

		addprod = new JButton("Add Product");
		addprod.setBounds(250, 10, 150, 25);
		admin.add(addprod);

		removeprod = new JButton("Remove Product");
		removeprod.setBounds(250, 35, 150, 25);
		admin.add(removeprod);

		search = new JButton("Search");
		search.setBounds(250, 60, 150, 25);
		admin.add(search);

		stock = new JButton("Change Stock");
		stock.setBounds(250, 85, 150, 25);
		admin.add(stock);

		JScrollPane pane = new JScrollPane();
		table = new JTable();
		tableModel = new DefaultTableModel(
				new Object[] { "Product ID", "Product Name", "Product Quantity", "Product Price" }, 0);
		table.setModel(tableModel);
		pane.setViewportView(table);
		pane.setBounds(50, 130, 400, 180);
		admin.add(pane);

		items = new Warehouse();
		x = new InputOutput();
		items = x.deserWarehouse();

		addprod.addActionListener(new AddActionListener());
		removeprod.addActionListener(new RemoveActionListener());
		search.addActionListener(new SearchBActionListener());
		stock.addActionListener(new StockBActionListener());
	}

	public String[] memoprod() {
		String[] res = new String[4];
		res[0] = idbox.getText();
		res[1] = productbox.getText();
		res[2] = quantitybox.getText();
		res[3] = pricebox.getText();
		return res;
	}

	private void printProduct(Product p) {
		Object[] row = { p.getID(), p.getName(), p.getQuantity(), p.getPrice() };
		((DefaultTableModel) table.getModel()).addRow(row);
	}

	class AddActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] r = memoprod();
			p = new Product(Integer.parseInt(r[0]), r[1], Integer.parseInt(r[2]), Integer.parseInt(r[3]));
			items.addProduct(p);
			printProduct(p);
			x.serWarehouse(items);
		}
	}

	class RemoveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int selRow = table.getSelectedRow();
			if (selRow != -1) {
				try {
					it = items.getProductsInOrder();
					while (it.hasNext()) {
						p = it.next();
						if (p.getID() == Integer.parseInt(idbox.getText())) {
							items.removeProduct(p);
							// printProduct(p);
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Eroare!");
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(selRow);
			}

			x.serWarehouse(items);
		}

	}

	class SearchBActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			it = items.getProductsInOrder();
			boolean y = false;
			try {
				while (it.hasNext()) {
					p = it.next();
					if (p.getName().equals(productbox.getText())) {
						y = true;
					}
				}
				if (y == false) {
					JOptionPane.showMessageDialog(null, "There is no product");
				} else {
					JOptionPane.showMessageDialog(null, "Product found");
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Try Again");
			}
		}
	}

	static int getRow(TableModel model, Object o) {
		int row = 0;
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			for (int j = model.getColumnCount() - 1; j >= 0; j--) {
				if (model.getValueAt(i, j).equals(o)) {
					row = i;
				}
			}
		}
		return row;
	}

	class StockBActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] r = memoprod();
			it = items.getProductsInOrder();
			try {
				while (it.hasNext()) {
					Product prod = it.next();

					if (prod.getID() == Integer.parseInt(r[0]) && prod.getName().equals(r[1])) {
						int row = getRow(getModel(), r[1]);
						// System.out.println(row);
						table.getModel().setValueAt(new Integer(prod.getQuantity() + Integer.parseInt(r[2])), row, 2);
						prod.setQuantity(new Integer(prod.getQuantity()) + Integer.parseInt(r[2]));

					}
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "There is no product with this ID!");
			}
			x.serWarehouse(items);
		}
	}

	public static DefaultTableModel getModel() {
		return tableModel;
	}

}
