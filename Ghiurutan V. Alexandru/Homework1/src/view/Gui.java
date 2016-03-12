package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Operations;
import controller.PolynomialProcess;
import javax.swing.BoxLayout;
import java.awt.event.*;
import models.DoublePolynomial;
import models.IntPolynomial;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
/**
 * The class that contains the graphical user interface.
 * @author Ghiurutan
 *
 */
public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 211431534849779406L;
	private static String ERROR = "Error";
	private static String ERROR_POLYNOMIAL = "You didn't enter the polynomial.";
	private static String ERROR_POLYNOMIALS = "You didn't enter both polynomials.";
	private JPanel north, north1, north2, center, south, evaluatePanel, exponentiationPanel;
	private JButton addition, subtraction, multiplication, division, evaluate, exponentiation, findRoot, derivation,
			integration, areEqual, drawGraph;
	private BoxLayout layout;
	private JTextArea pol1, pol2, rezult;
	private JTextField eval, exp, polynomial1, polynomial2;
	private Operations operations;
	private DecimalFormat decimalFormat;
	private Graph polynomialGraph;

	public Gui() {
		super("Polynomial");
		operations = new Operations();
		decimalFormat = new DecimalFormat();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initializeNorthSection();
		initializeCenterSection();
		initializeSouthSection();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(400, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this,
				"Please insert polynomials in their mathematical form.For example: x^3-2x^2+x+3", "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void initializeNorthSection() {
		north = new JPanel();
		layout = new BoxLayout(north, BoxLayout.Y_AXIS);
		north.setLayout(layout);
		north1 = new JPanel();
		polynomial1 = new JTextField("Polynomial 1:");
		polynomial1.setEditable(false);
		pol1 = new JTextArea(1, 10);
		north1.add(polynomial1);
		north1.add(pol1);
		north2 = new JPanel();
		polynomial2 = new JTextField("Polynomial 2:");
		polynomial2.setEditable(false);
		pol2 = new JTextArea(1, 10);
		north2.add(polynomial2);
		north2.add(pol2);
		north.add(north1);
		north.add(north2);
		this.add(north);
	}

	private void initializeCenterSection() {
		center = new JPanel();
		addition = new JButton("+");
		addition.addActionListener(this);
		subtraction = new JButton("-");
		subtraction.addActionListener(this);
		multiplication = new JButton("*");
		multiplication.addActionListener(this);
		division = new JButton("/");
		division.addActionListener(this);

		evaluatePanel = new JPanel();
		eval = new JTextField(5);
		evaluate = new JButton("Evaluate");
		evaluate.addActionListener(this);

		evaluatePanel.add(eval);
		evaluatePanel.add(evaluate);

		exponentiationPanel = new JPanel();
		exp = new JTextField(5);
		exponentiation = new JButton("f(x)^n");
		exponentiation.addActionListener(this);
		exponentiationPanel.add(exp);
		exponentiationPanel.add(exponentiation);

		derivation = new JButton("f '(x)");
		derivation.addActionListener(this);

		integration = new JButton("S f(x) dx");
		integration.addActionListener(this);

		areEqual = new JButton("Are equal?");
		areEqual.addActionListener(this);
		findRoot = new JButton("Find root");
		findRoot.addActionListener(this);
		drawGraph = new JButton("Draw graph");
		drawGraph.addActionListener(this);

		center.add(addition);
		center.add(subtraction);
		center.add(multiplication);
		center.add(division);
		center.add(evaluatePanel);
		center.add(exponentiationPanel);
		center.add(derivation);
		center.add(integration);
		center.add(areEqual);
		center.add(findRoot);
		center.add(drawGraph);
		this.add(center);

	}

	private void initializeSouthSection() {
		south = new JPanel();
		rezult = new JTextArea(1, 10);
		rezult.setEditable(false);
		south.add(rezult);
		this.add(south);
	}

	private void checkForBothPolynomials() {
		if (pol1.getText().equals("") || pol2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIALS, JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == addition) {
			checkForBothPolynomials();
			rezult.setText(
					operations
							.add(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
									new IntPolynomial(new PolynomialProcess().getPolynomial(pol2.getText())))
							.toString());
		} else if (event.getSource() == subtraction) {
			checkForBothPolynomials();
			rezult.setText(
					operations
							.subtract(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
									new IntPolynomial(new PolynomialProcess().getPolynomial(pol2.getText())))
							.toString());
		} else if (event.getSource() == multiplication) {
			checkForBothPolynomials();
			rezult.setText(
					operations
							.multiply(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
									new IntPolynomial(new PolynomialProcess().getPolynomial(pol2.getText())))
							.toString());
		} else if (event.getSource() == division) {
			checkForBothPolynomials();
			DoublePolynomial[] values = operations.division(
					new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
					new IntPolynomial(new PolynomialProcess().getPolynomial(pol2.getText())));
			rezult.setText("Quotient: " + values[0].toString() + "\n");
			rezult.append("Rest: " + values[1].toString());
		} else if (event.getSource() == evaluate) {
			double number;
			if (pol1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (eval.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				number = Double.parseDouble(eval.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, ERROR, "You didn't enter a valid number.",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			rezult.setText(decimalFormat.format(operations
					.evaluate(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())), number)));
		} else if (event.getSource() == exponentiation) {
			int number;
			if (pol1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (exp.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, "You didn't enter any value.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				number = Integer.parseInt(exp.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, ERROR, "You didn't enter a valid number.",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			rezult.setText(String.valueOf(operations
					.exponentiation(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())), number)));
		} else if (event.getSource() == derivation) {
			if (pol1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			rezult.setText(operations
					.derivation(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText()))).toString());
		} else if (event.getSource() == integration) {
			if (pol1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			rezult.setText(operations
					.integration(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText()))).toString());
		} else if (event.getSource() == areEqual) {
			checkForBothPolynomials();
			if (operations.areEqual(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
					new IntPolynomial(new PolynomialProcess().getPolynomial(pol2.getText())))) {
				rezult.setText("Polynomials are equal.");
			} else {
				rezult.setText("Polynomials are not equal.");
			}
		} else if (event.getSource() == findRoot) {
			if (pol1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, ERROR, ERROR_POLYNOMIAL, JOptionPane.ERROR_MESSAGE);
				return;
			}
			rezult.setText(
					operations.findRoot(new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText()))));
		} else if (event.getSource() == drawGraph) {
			polynomialGraph = new Graph();
			polynomialGraph.setYCoordinates(operations.computeYCoordinatesForGraph(
					new IntPolynomial(new PolynomialProcess().getPolynomial(pol1.getText())),
					polynomialGraph.getXCoordinates()));
			JFrame graphDisplay = new JFrame("Graph");
			graphDisplay.add(polynomialGraph);
			graphDisplay.setSize(430, 430);
			graphDisplay.setVisible(true);
			graphDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

	}
}
