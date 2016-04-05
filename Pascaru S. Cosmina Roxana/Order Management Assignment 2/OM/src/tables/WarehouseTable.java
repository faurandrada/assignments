package tables;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WarehouseTable extends JPanel{
	
	public WarehouseTable(StockTableModel stockTableModel){
		JTable warehouseTable = new JTable(stockTableModel);
		JScrollPane scrollPane = new JScrollPane(warehouseTable);
		add(scrollPane);
		setVisible(true);
	}

}
