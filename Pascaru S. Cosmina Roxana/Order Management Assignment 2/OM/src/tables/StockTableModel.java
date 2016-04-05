package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import subjects.ProductStock;

public class StockTableModel extends AbstractTableModel {

	private String[] columnNames = { "Name", "Price", "Quantity (kg)" };
	private ArrayList<ProductStock> products;

	public StockTableModel() {
		products = new ArrayList<ProductStock>();
	}

	public StockTableModel(ArrayList<ProductStock> products) {
		this.products = products;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return products.size();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProductStock product = getProduct(row);

		switch (column) {
		case 0:
			return product.getProductName();
		case 1:
			return product.getProductPrice();
		case 2:
			return product.getQuantity();
		}
		return product;
	}

	public ProductStock getProduct(int row) {
		return products.get(row);
	}

	public void addProduct(ProductStock product) {
		insertProduct(getRowCount(), product);
	}

	public void insertProduct(int row, ProductStock product) {
		products.add(row, product);
		fireTableRowsInserted(row, row);
	}

	public void removeProduct(int row) {
		products.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
