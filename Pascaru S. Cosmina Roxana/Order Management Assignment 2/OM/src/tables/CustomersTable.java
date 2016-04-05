package tables;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomersTable extends JPanel{

	public CustomersTable(CustomersTableModel customersTableModel){
		JTable customersTable = new JTable(customersTableModel);
		JScrollPane scrollPane = new JScrollPane(customersTable);
		add(scrollPane);
		setVisible(true);
	}
}
