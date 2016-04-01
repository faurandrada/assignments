package Users;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTable;

public class CustomersTable {

	private Set<User> setOfCustomers;
	private UsersData usersData;
	private String[] header = { "Customer Name", "Customer Password" };
	private Object[][] data;
	private JTable table;

	public CustomersTable() {

		usersData = new UsersData();
		usersData.updateUsers();

		setOfCustomers = new TreeSet<User>();
		setOfCustomers = usersData.getUsers();

		data = new Object[setOfCustomers.size()][2];
		int i = 0;
		for (User user : setOfCustomers) {
			if (user.isCustomer()) {
				data[i][0] = user.getName();
				data[i][1] = user.getPassword();
				i++;
			}
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

	public JTable getTable() {
		return table;
	}

}
