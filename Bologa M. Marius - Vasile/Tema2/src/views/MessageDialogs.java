package views;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MessageDialogs {

	public static String[] addProductPanel() {
		String[] result1 = new String[3];
		JTextField id = new JTextField(7);
		JTextField name = new JTextField(7);
		JTextField quantity = new JTextField(7);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Id:"));
		myPanel.add(id);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Name:"));
		myPanel.add(name);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Quantity:"));
		myPanel.add(quantity);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = id.getText();
			result1[1] = name.getText();
			result1[2] = quantity.getText();
		}
		return result1;
	}

	public static String[] addOrderWindow() {
		String[] result1 = new String[4];
		JTextField name = new JTextField(7);
		JTextField quantity = new JTextField(7);
		JTextField product = new JTextField(7);
		JTextField id = new JTextField(4);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("ID:"));
		myPanel.add(id);
		myPanel.add(Box.createVerticalStrut(5));
		myPanel.add(new JLabel("Customer:"));
		myPanel.add(name);
		name.setText(LoginFrame.getUserName());
		myPanel.add(Box.createVerticalStrut(5)); // a spacer
		myPanel.add(new JLabel("Product:"));
		myPanel.add(product);
		myPanel.add(Box.createVerticalStrut(5)); // a spacer
		myPanel.add(new JLabel("Quantity:"));
		myPanel.add(quantity);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter proper values in all the fields",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			result1[0] = LoginFrame.getUserName();
			result1[1] = product.getText();
			result1[2] = quantity.getText();
			result1[3] = id.getText();
		}
		return result1;
	}

	public static int getRowByValue(TableModel model, Object value) {
		int row = 0;
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			for (int j = model.getColumnCount() - 1; j >= 0; --j) {
				if (model.getValueAt(i, j).equals(value)) {
					// what if value is not unique?
					row = i;
				}
			}
		}
		return row;
	}
}
