package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.IntPolynomial;
import model.NotAPolynomialException;
import model.PolyOps;
import model.RealPolynomial;
import view.MenuWithOperationsGUI;

/**
 * @author iulia
 *
 *         This is the controller part of my MVC design Controller acts on both
 *         model and view. It keeps them separate. Contains listeners for
 *         objects from the GUI
 */
public class PolyController {

	private MenuWithOperationsGUI view;
	private PolyOps model;

	
	public PolyController(PolyOps model, MenuWithOperationsGUI view) {
		this.model = model;
		this.view = view;

		this.view.addAdditionListener(new AdditionListener());
		this.view.addSubtractionListener(new SubtractionListener());
		this.view.addMultiplicationListener(new MultiplicationListener());
		this.view.addDivisionListener(new DivisionListener());
		this.view.addDifferentiationListener(new DifferentiationListener());
		this.view.addIntegrationListener(new IntegrationListener());
	}

	class AdditionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//JOptionPane.showMessageDialog(null, "You clicked add!");
			String s1 = null, s2 = null;
			s1 = view.getFirstPolynomial();
			s2 = view.getSecondPolynomial();
			System.out.println(s1);
			System.out.println(s2);

			try {
				IntPolynomial poly1 = new IntPolynomial(s1);
				IntPolynomial poly2 = new IntPolynomial(s2);
				IntPolynomial addition = new IntPolynomial();
				addition = PolyOps.add(poly1, poly2);
				view.setResult(addition.printPoly());
				// System.out.println(addition.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at addition!");
			}

		}
	}

	class SubtractionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//JOptionPane.showMessageDialog(null, "You clicked subtract!");
			String s1 = null, s2 = null;
			s1 = view.getFirstPolynomial();
			s2 = view.getSecondPolynomial();
			System.out.println(s1);
			System.out.println(s2);

			try {
				IntPolynomial poly1 = new IntPolynomial(s1);
				IntPolynomial poly2 = new IntPolynomial(s2);
				IntPolynomial subtraction = new IntPolynomial();
				subtraction = PolyOps.subtract(poly1, poly2);
				view.setResult(subtraction.printPoly());
				// System.out.println(subtraction.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at subtraction!");
			}

		}
	}

	class MultiplicationListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//JOptionPane.showMessageDialog(null, "You clicked multiply!");
			String s1 = null, s2 = null;
			s1 = view.getFirstPolynomial();
			s2 = view.getSecondPolynomial();
			System.out.println(s1);
			System.out.println(s2);

			try {
				IntPolynomial poly1 = new IntPolynomial(s1);
				IntPolynomial poly2 = new IntPolynomial(s2);
				IntPolynomial multiplication = new IntPolynomial();
				multiplication = PolyOps.multiply(poly1, poly2);
				view.setResult(multiplication.printPoly());
				// System.out.println(multiplication.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at multiplication!");
			}
		}
	}

	class DivisionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//JOptionPane.showMessageDialog(null, "You clicked division!");
			String s1 = null, s2 = null;
			s1 = view.getFirstPolynomial();
			s2 = view.getSecondPolynomial();
			System.out.println(s1);
			System.out.println(s2);

			try {
				RealPolynomial poly1 = new RealPolynomial(s1);
				RealPolynomial poly2 = new RealPolynomial(s2);
				RealPolynomial[] division = new RealPolynomial[2];
				division = PolyOps.divide(poly1, poly2);
				view.setResult("Q:" + division[0].printPoly() + "   " + "R: " + division[1].printPoly());
				// System.out.println(division.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at division!");
			}
		}
	}

	class DifferentiationListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//JOptionPane.showMessageDialog(null, "You clicked differentiation!");
			String s1 = null;
			s1 = view.getFirstPolynomial();
			System.out.println(s1);

			try {
				IntPolynomial poly1 = new IntPolynomial(s1);
				IntPolynomial differentiation = new IntPolynomial();
				differentiation = PolyOps.differentiate(poly1);
				view.setResult(differentiation.printPoly());
				System.out.println(differentiation.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at differentiation!" + e1.getMessage());
			}
		}
	}

	class IntegrationListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//JOptionPane.showMessageDialog(null, "You clicked integration!");
			String s1 = null;
			s1 = view.getFirstPolynomial();
			System.out.println(s1);

			try {
				RealPolynomial poly1 = new RealPolynomial(s1);
				RealPolynomial integration = new RealPolynomial();
				integration = PolyOps.integrate(poly1);
				view.setResult(integration.printPoly());
				// System.out.println(integration.printPoly());
			} catch (NotAPolynomialException e1) {
				view.setResult("Invalid input!");
				System.out.println("Problems at integration!" + e1.getMessage());
			}

		}
	}
}
