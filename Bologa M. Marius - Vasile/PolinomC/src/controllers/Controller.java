package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Polynomial;
import utilities.PolynomFactory;
import utilities.Utilities;
import view.View;

public class Controller {
	private View view;
	private Polynomial a = new Polynomial(0);
	private Polynomial b = new Polynomial(0);
	private PolynomFactory factory = new PolynomFactory();
	private ManageOperations manager = new ManageOperations();

	public Controller(View view) {
		this.view = view;
		this.view.addOperationsChangeListener(new OperationsListener());
		this.view.addCoefA(new AddCoefficientForAListener());
		this.view.addCoefB(new AddCoefficientForBListener());
		this.view.addDeleteAListener(new DeleteAListener());
		this.view.addDeleteBListener(new DeleteBListener());
	}

	public class OperationsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			manager.manageOperation(view, a, b);
		}
	}

	public class AddCoefficientForAListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			a = factory.createPolynomial(view);
			view.setPolynomialA(Utilities.toString(a));

		}
	}

	public class AddCoefficientForBListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			b = factory.createPolynomial(view);
			view.setPolynomialB(Utilities.toString(b));
		}
	}

	public class DeleteAListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			a = new Polynomial(0);
			view.setPolynomialA("");
		}
	}

	public class DeleteBListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			b = new Polynomial(0);
			view.setPolynomialB("");
		}
	}
}
