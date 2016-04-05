package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import groups.Warehouse;
import subjects.ProductStock;
import tables.StockTableModel;
import tables.WarehouseTable;

public class OrderPanel extends JPanel{
	private JLabel search = new JLabel("Searh Product");
	private JTextField input = new JTextField();
	private JButton addProduct = new JButton ("Add Product to Order");
	private JButton removeProduct = new JButton("Remove Product from Order");
	private JButton viewOrder = new JButton("View Order");
	private JButton viewHistory = new JButton("View History");
	private JButton confirm = new JButton("Confirm Order");
	private JButton back = new JButton ("Back");
	private Warehouse warehouse;
	private WarehouseTable warehouseTable;
	private StockTableModel stockTableModel = new StockTableModel();
	private JPanel bottomPanel = new JPanel();
	
	public OrderPanel(JButton addProduct, JButton removeProduct,JButton viewOrder, JButton viewHistory, JButton confirm,Warehouse warehouse,JButton back){
		this.addProduct=addProduct;
		this.removeProduct=removeProduct;
		this.viewOrder=viewOrder;
		this.viewHistory=viewHistory;
		this.confirm=confirm;
		this.warehouse = warehouse;
		this.back=back;
		
		setLayout(new BorderLayout());
		
		bottomPanel.setLayout(new GridLayout(3,2));
		bottomPanel.add(addProduct);
		bottomPanel.add(removeProduct);
		bottomPanel.add(viewOrder);
		bottomPanel.add(viewHistory);
		bottomPanel.add(confirm);
		bottomPanel.add(back);
		
		add(bottomPanel,BorderLayout.SOUTH);
		
	}
}
