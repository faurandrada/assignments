package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.*;
import views.*;

public class LogInController extends AbstractController {

	private LogIn logIn;

	public LogInController(LogIn frame, boolean hasBackButton) {
		super(frame, hasBackButton);
		frame.setSubmitButtonActionListener(new SubmitButtonActionListener());
		logIn = frame;
	}

	private class SubmitButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (logIn.getUserName().getText().equals(UserRepository.getInstance().getAdmin().getUsername())
					&& logIn.getPass().getText().equals(UserRepository.getInstance().getAdmin().getPass()))
				new AdminController(new AdminView("Admin"), true);
			else if (logIn.getUserName().getText().equals(UserRepository.getInstance().getCustomer().getUsername())
					&& logIn.getPass().getText().equals(UserRepository.getInstance().getCustomer().getPass()))
				new CustomerController(new CustomerView("Customer"), true);
			else {
				logIn.getUserName().setText("Invalid log");
				logIn.getPass().setText("Invalid log");
			}
		}
	}
}
