package Control;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import UserInterface.AdminFrame;
import UserInterface.CustomerFrame;
import Users.Admin;
import Users.Customer;
import Users.User;
import Users.UsersData;

public class UserDialog {

	private JTextField username;
	private JTextField password;
	private JRadioButton adminButton;
	private JRadioButton customerButton;
	private ButtonGroup group;

	private UsersData usersData;

	public UserDialog() {
		username = new JTextField(10);
		password = new JPasswordField(10);
		adminButton = new JRadioButton("Admin");
		customerButton = new JRadioButton("Customer");

		group = new ButtonGroup();
		group.add(adminButton);
		group.add(customerButton);

		usersData = new UsersData();

		usersData.updateUsers();
		System.out.println("Initial users");
		System.out.println("******************************************");
		usersData.seeAllUsers();

		int optionExisting = JOptionPane.showConfirmDialog(null, "Are you an existing user ?");
		if (optionExisting == JOptionPane.YES_OPTION)
			authenticateExistingUser();
		else if (optionExisting == JOptionPane.NO_OPTION) {
			int optionCreate = JOptionPane.showConfirmDialog(null, "Create account ?");
			if (optionCreate == JOptionPane.YES_OPTION) {
				
				createAccount();
				
				usersData.updateUsers();
				System.out.println("Updated users");
				System.out.println("******************************************");
				usersData.seeAllUsers();
			} else
				System.exit(0);
		} else
			System.exit(0);
	}

	private void createAccount() {
		Object[] message = { "Choose Username:", username, "Choose Password:", password, "Choose user state",
				adminButton, customerButton };
		int option = JOptionPane.showConfirmDialog(null, message, "Create Account", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (adminButton.isSelected()) {
				usersData.addUser(new Admin(username.getText(), password.getText()));
				new AdminFrame(username.getText());
				

			} else if (customerButton.isSelected()) {
				usersData.addUser(new Customer(username.getText(), password.getText()));
				new CustomerFrame(username.getText());

			} else if (!(adminButton.isSelected()) && (!(customerButton.isSelected()))) {
				JOptionPane.showMessageDialog(null, "Please select User's state", "Selection ERROR",
						JOptionPane.ERROR_MESSAGE);
				createAccount();
			}
		} else
			System.exit(0);
	}

	private void authenticateExistingUser() {

		Object[] message = { "Username:", username, "Password:", password, adminButton, customerButton };
		int option = JOptionPane.showConfirmDialog(null, message, "Authenticate", JOptionPane.OK_CANCEL_OPTION);
		boolean found = false;
		if (option == JOptionPane.OK_OPTION) {
			if (!(adminButton.isSelected()) && (!(customerButton.isSelected()))) {
				JOptionPane.showMessageDialog(null, "Please authenticate as Customer or Admin!", "Selection ERROR",
						JOptionPane.ERROR_MESSAGE);
				authenticateExistingUser();
			} else if (adminButton.isSelected()) {
				for (User user : usersData.getUsers()) {
					if ((user.isAdmin()) && username.getText().equals(user.getName())
							&& (password.getText().equals(user.getPassword()))) {
						new AdminFrame(user.getName());
						found = true;
					}
				}
				if (!found) {
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password for Admin Account!",
								"Admin Authentication ERROR", JOptionPane.ERROR_MESSAGE);
						authenticateExistingUser();
					}
				}
			} else if (customerButton.isSelected()) {
				for (User user : usersData.getUsers()) {
					if ((user.isCustomer()) && username.getText().equals(user.getName())
							&& (password.getText().equals(user.getPassword()))) {
						new CustomerFrame(user.getName());
						found = true;
					}
				}
				if (!found) {
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password for Customer Account!",
								"Customer Authentication ERROR", JOptionPane.ERROR_MESSAGE);
						authenticateExistingUser();
					}
				}
			}
		} else if (option == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
	}
}
