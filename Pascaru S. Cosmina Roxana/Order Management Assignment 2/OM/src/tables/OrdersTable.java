package tables;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OrdersTable extends JPanel{
	
	public OrdersTable(OrdersTableModel ordersTableModel){
		JTable ordersTable = new JTable(ordersTableModel);
		JScrollPane scrollPane = new JScrollPane(ordersTable);
		add(scrollPane);
		setVisible(true);
	}

}
