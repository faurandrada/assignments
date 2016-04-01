package Stock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTable;

public class ProductsTable{

	private Set<Product> setOfProducts;
	private Warehouse warehouse;
	private String[] header = { "ID", "Type", "Make", "Model", "Price", "Quantity" };
	private Object[][] data;
	private JTable table;

	public ProductsTable() {
		
		warehouse = new Warehouse();
		warehouse.updateStock();
		
		setOfProducts = new TreeSet<Product>();
		setOfProducts = warehouse.getStock();

		data = new Object[setOfProducts.size()][6];
		int i = 0;
		for (Product product : setOfProducts) {
			data[i][0] = product.getID();
			data[i][1] = product.getType();
			data[i][2] = product.getMake();
			data[i][3] = product.getModel();
			data[i][4] = product.getPrice();
			data[i][5] = product.getQuantity();
			i++;
		}

		table = new JTable(data, header);
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setFont(new Font("SansSerif", Font.BOLD, 16));
		table.setForeground(Color.decode("0x121212"));
		table.setBackground(Color.decode("0xc9c9c9"));
		table.setGridColor(Color.WHITE);
		table.setRowHeight(25);
	}
	
	public JTable getTable(){
		return table;
	}

}
