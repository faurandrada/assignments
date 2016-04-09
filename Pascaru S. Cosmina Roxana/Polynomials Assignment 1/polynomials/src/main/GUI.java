package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import operations.BiOperation;
import operations.MonoOperation;
import operations.Operation;
import polynomials.Monom;
import polynomials.Polynomial;

/**
 * Creates the graphical user interface through which the user can give input to
 * the application and also get the output
 * 
 * @author Cosmina
 *
 */

public class GUI implements ActionListener {

	private JFrame frame = new JFrame("Polynomials");

	private JPanel bigPanel = new JPanel();
	private JPanel polyPanel = new JPanel();
	private JPanel operationPanel = new JPanel();
	private JPanel resultPanel = new JPanel();

	private JTextField poly1TextField = new JTextField();
	private JTextField poly2TextField = new JTextField();
	private JTextField degree1TextField = new JTextField();
	private JTextField degree2TextField = new JTextField();
	private JTextField evaluateTextField = new JTextField();
	private JTextArea resultTextArea = new JTextArea();

	private JButton addPoly1 = new JButton("Add Poly1");
	private JButton addPoly2 = new JButton("Add Poly2");
	private JButton add = new JButton("+");
	private JButton subtract = new JButton("-");
	private JButton multiply = new JButton("X");
	private JButton differentiate1 = new JButton("Derivate p1");
	private JButton differentiate2 = new JButton("Derivate p2");
	private JButton integrate1 = new JButton("Integrate p1");
	private JButton integrate2 = new JButton("Integrate p2");
	private JButton evaluate1 = new JButton("Evaluate p1");
	private JButton evaluate2 = new JButton("Evaluate p2");
	private JButton empty = new JButton("");

	private JLabel evaluatePoint = new JLabel("Add point:");

	private String poly1, poly2;
	private int degree1, degree2, point;
	private Tokens token1, token2;

	private Monom m;
	private Polynomial p;
	private Polynomial p1 = new Polynomial();
	private Polynomial p2 = new Polynomial();

	private Operation monoOp = new MonoOperation();
	private Operation biOp = new BiOperation();

	public GUI() {
		showInstructions();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);

		bigPanel.setLayout(new GridLayout(3, 1));
		bigPanel.setBackground(Color.WHITE);
		bigPanel.setVisible(true);

		polyPanel.setLayout(new GridLayout(2, 3));

		degree1TextField.setEditable(true);
		degree1TextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		degree1TextField.addActionListener(this);

		poly1TextField.setPreferredSize(new Dimension(300, 30));
		poly1TextField.setEditable(true);
		poly1TextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		poly1TextField.addActionListener(this);

		degree2TextField.setEditable(true);
		degree2TextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		degree2TextField.addActionListener(this);

		poly2TextField.setPreferredSize(new Dimension(300, 30));
		poly2TextField.setEditable(true);
		poly2TextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		poly2TextField.addActionListener(this);

		addPoly1.addActionListener(this);
		addPoly1.setActionCommand("addPoly1");
		addPoly2.addActionListener(this);
		addPoly2.setActionCommand("addPoly2");

		polyPanel.add(degree1TextField);
		polyPanel.add(poly1TextField);
		polyPanel.add(addPoly1);
		polyPanel.add(degree2TextField);
		polyPanel.add(poly2TextField);
		polyPanel.add(addPoly2);

		operationPanel.setLayout(new GridLayout(3, 4));

		evaluateTextField.setEditable(true);
		evaluateTextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		evaluateTextField.addActionListener(this);

		add.addActionListener(this);
		add.setActionCommand("add");
		subtract.addActionListener(this);
		subtract.setActionCommand("substract");
		multiply.addActionListener(this);
		multiply.setActionCommand("multiply");
		differentiate1.addActionListener(this);
		differentiate1.setActionCommand("derivate1");
		differentiate2.addActionListener(this);
		differentiate2.setActionCommand("derivate2");
		integrate1.addActionListener(this);
		integrate1.setActionCommand("integrate1");
		integrate2.addActionListener(this);
		integrate2.setActionCommand("integrate2");
		evaluate1.addActionListener(this);
		evaluate1.setActionCommand("evaluate1");
		evaluate2.addActionListener(this);
		evaluate2.setActionCommand("evaluate2");

		operationPanel.add(add);
		operationPanel.add(subtract);
		operationPanel.add(multiply);
		operationPanel.add(empty);
		operationPanel.add(differentiate1);
		operationPanel.add(differentiate2);
		operationPanel.add(integrate1);
		operationPanel.add(integrate2);
		operationPanel.add(evaluatePoint);
		operationPanel.add(evaluateTextField);
		operationPanel.add(evaluate1);
		operationPanel.add(evaluate2);

		resultPanel.setLayout(new GridLayout(1, 1));

		resultTextArea.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 12));
		resultTextArea.setVisible(true);

		resultPanel.add(resultTextArea);

		bigPanel.add(polyPanel);
		bigPanel.add(operationPanel);
		bigPanel.add(resultPanel);

		frame.add(bigPanel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("addPoly1")) {
			degree1 = Integer.parseInt(degree1TextField.getText());
			poly1 = poly1TextField.getText();
			token1 = new Tokens(poly1, degree1);
			p1.setDegree(degree1);
			for (int j = 0; j < token1.getPolLength(); j++) {
				m = new Monom(token1.getCoeff(j), token1.getPower(j));
				p1.addMonom(m);
			}
		} else if (e.getActionCommand().equals("addPoly2")) {
			degree2 = Integer.parseInt(degree2TextField.getText());
			poly2 = poly2TextField.getText();
			token2 = new Tokens(poly2, degree2);
			p2.setDegree(degree2);
			for (int j = 0; j < token2.getPolLength(); j++) {
				m = new Monom(token2.getCoeff(j), token2.getPower(j));
				p2.addMonom(m);
			}
		} else if (e.getActionCommand().equals("add")) {
			resultTextArea.setText("");
			p = biOp.add(p1, p2);
			for (Monom m : p.getArray()) {
				resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("substract")) {
			resultTextArea.setText("");
			p = biOp.subtract(p1, p2);
			for (Monom m : p.getArray()) {
				resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("multiply")) {
			resultTextArea.setText("");
			p = biOp.multiply(p1, p2);
			for (Monom m : p.getArray()) {
				resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("derivate1")) {
			resultTextArea.setText("");
			p = monoOp.differentiate(p1);
			for (Monom m : p.getArray()) {
				resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("derivate2")) {
			resultTextArea.setText("");
			p = monoOp.differentiate(p2);
			for (Monom m : p.getArray()) {
				resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("integrate1")) {
			int i = 0;
			resultTextArea.setText("");
			p = monoOp.integrate(p1);
			for (Monom m : p.getArray()) {
				if (i != p.getArray().size() - 1) {
					float coeff = (float) m.getCoeff() / m.getPower();
					resultTextArea.append(coeff + "X^" + m.getPower() + "+");
					i++;
				} else
					resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("integrate2")) {
			int i = 0;
			resultTextArea.setText("");
			p = monoOp.integrate(p2);
			for (Monom m : p.getArray()) {
				if (i != p.getArray().size() - 1) {
					float coeff = (float) m.getCoeff() / m.getPower();
					resultTextArea.append(coeff + "X^" + m.getPower() + "+");
					i++;
				} else
					resultTextArea.append(m.toString());
			}
		} else if (e.getActionCommand().equals("evaluate1")) {
			resultTextArea.setText("");
			int p = Integer.parseInt(evaluateTextField.getText());
			point = monoOp.evaluatePolyAtPoint(p, p1);
			String ps = Integer.toString(point);
			resultTextArea.append(ps);
		} else if (e.getActionCommand().equals("evaluate2")) {
			resultTextArea.setText("");
			int p = Integer.parseInt(evaluateTextField.getText());
			point = monoOp.evaluatePolyAtPoint(p, p2);
			String ps = Integer.toString(point);
			resultTextArea.append(ps);
		}
	}

	public void showInstructions() {
		JOptionPane.showMessageDialog(null,
				"First you must add the degree of the polynom in the first text field.\n Then you must enter the polynoms in the following way\naX^p1 +bX^p2 +... +zX^pn\nThe coefficients can be negative too.\nIn order to save the polynoms click the 'Add poly' button near the text field\nNext, choose the operation you want to be performed\n");
	}
}
