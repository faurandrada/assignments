package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import utilities.Constants;
import view.AdminView;
import view.LogInView;
import view.UserView;

public class LoginController {

	private LogInView login;
	private UserView uw;
	private AdminView aw;
	public LoginController(LogInView login, AdminView aw, UserView uw) {

		this.login = login;
		this.login.loginActionListener(new LoginListener());
		this.uw = uw;
		this.aw = aw;
	}

	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String username = null, password = null;
			username = login.getUserText();
			password = login.getPasswordText();

			System.out.println(username + " " + password);
			if (((!username.equals(Constants.user1) && !password.equals(Constants.pass1))
					&& (!username.equals(Constants.admin) && !password.equals(Constants.adminPass)))) {

				JOptionPane.showMessageDialog(null, "Authentication failed!");

			} else {
				if (username.equals(Constants.user1) && password.equals(Constants.pass1)) {
					 uw.setVisible(true);

				} else {
					if (username.equals(Constants.admin) && password.equals(Constants.adminPass)) {
						aw.setVisible(true);
					}
				}

			}
		}

	}
}
