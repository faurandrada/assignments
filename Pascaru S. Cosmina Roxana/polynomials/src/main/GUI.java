package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import operations.BiOperation;
import operations.MonoOperation;
import polynomials.Monom;
import polynomials.Polynomial;
import polynomials.Tokens;

public class GUI implements ActionListener {

	JFrame frame = new JFrame("Polynomials");

	JPanel bigPanel = new JPanel();
	JPanel polyPanel = new JPanel();
	JPanel operationPanel = new JPanel();
	JPanel resultPanel = new JPanel();

	JTextField poly1TextField = new JTextField();
	JTextField poly2TextField = new JTextField();
	JTextField degree1TextField = new JTextField();
	JTextField degree2TextField = new JTextField();
	JTextField evaluateTextField = new JTextField();
	JTextArea resultTextField = new JTextArea();

	JButton addPoly1 = new JButton("Add Poly1");
	JButton addPoly2 = new JButton("Add Poly2");
	JButton add = new JButton("+");
	JButton substract = new JButton("-");
	JButton multiply = new JButton("X");
	JButton derivate1 = new JButton("d p1");
	JButton derivate2 = new JButton("d p2");
	JButton integrate1 = new JButton("integrate p1");
	JButton integrate2 = new JButton("integrate p2");
	JButton evaluate1 = new JButton("evaluate p1");
	JButton evaluate2 = new JButton("evaluate p2");

	private String poly1, poly2;
	private int degree1, degree2, point;
	private Tokens tokens1, tokens2;

	private Monom m;
	private Polynomial p = new Polynomial();
	private Polynomial p1 = new Polynomial();
	private Polynomial p2 = new Polynomial();

	private MonoOperation monoOp = new MonoOperation();
	private BiOperation biOp = new BiOperation();

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
		substract.addActionListener(this);
		substract.setActionCommand("substract");
		multiply.addActionListener(this);
		multiply.setActionCommand("multiply");
		derivate1.addActionListener(this);
		derivate1.setActionCommand("derivate1");
		derivate2.addActionListener(this);
		derivate2.setActionCommand("derivate2");
		integrate1.addActionListener(this);
		integrate1.setActionCommand("integrate1");
		integrate2.addActionListener(this);
		integrate2.setActionCommand("integrate2");
		evaluate1.addActionListener(this);
		evaluate1.setActionCommand("evaluate1");
		evaluate2.addActionListener(this);
		evaluate2.setActionCommand("evaluate2");

		operationPanel.add(add);
		operationPanel.add(substract);
		operationPanel.add(multiply);
		operationPanel.add(derivate1);
		operationPanel.add(derivate2);
		operationPanel.add(integrate1);
		operationPanel.add(integrate2);
		operationPanel.add(evaluateTextField);
		operationPanel.add(evaluate1);;
		operationPanel.add(evaluate2);

		resultPanel.setLayout(new GridLayout(1, 1));

		resultTextField.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 12));
		resultTextField.setVisible(true);

		resultPanel.add(resultTextField);

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
			tokens1 = new Tokens(poly1, degree1);
			p1.setDegree(degree1);

			for (int j = 0; j < tokens1.getPolLength(); j++) {
				m = new Monom(tokens1.getCoeff(j), tokens1.getPower(j));
				p1.addMonom(m);
			}
		} else if (e.getActionCommand().equals("addPoly2")) {
			degree2 = Integer.parseInt(degree2TextField.getText());
			poly2 = poly2TextField.getText();
			tokens2 = new Tokens(poly2, degree2);
			p2.setDegree(degree2);

			for (int j = 0; j < tokens2.getPolLength(); j++) {
				m = new Monom(tokens2.getCoeff(j), tokens2.getPower(j));
				p2.addMonom(m);
			}

		} else if (e.getActionCommand().equals("add")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = biOp.add(p1, p2);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append(m.toString() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("substract")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = biOp.substract(p1, p2);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append(m.toString() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("multiply")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = biOp.multiply(p1, p2);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append(m.toString() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("derivate1")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = monoOp.differentiate(p1);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append(m.toString() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("derivate2")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = monoOp.differentiate(p2);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append(m.toString() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("integrate1")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = monoOp.integrate(p1);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append("(" + m.toString() + ")" + " / " + m.getPower() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		} else if (e.getActionCommand().equals("integrate2")) {
			int i = 0;
			ArrayList<Monom> arrayList = new ArrayList<Monom>();
			resultTextField.setText("");
			arrayList = monoOp.integrate(p2);
			for (Monom m : arrayList) {
				if (i != arrayList.size() - 1) {
					resultTextField.append("(" + m.toString() + ")" + " / " + m.getPower() + " + ");
					i++;
				} else
					resultTextField.append(m.toString());
			}
		}else if(e.getActionCommand().equals("evaluate1")){
			resultTextField.setText("");
			int p = Integer.parseInt(evaluateTextField.getText());
			point = monoOp.evaluatePolyAtPoint(p, p1);
			String ps = Integer.toString(point);
			resultTextField.append(ps);
		}else if(e.getActionCommand().equals("evaluate2")){
			resultTextField.setText("");
			int p = Integer.parseInt(evaluateTextField.getText());
			point = monoOp.evaluatePolyAtPoint(p, p2);
			String ps = Integer.toString(point);
			resultTextField.append(ps);
		}
	}

	public void showInstructions() {
		JOptionPane.showMessageDialog(null,
				"First you must add the degree of the polynom in the first text field.\n Then you must enter the polynoms in the following way\naX^p1 +bX^p2 +... +zX^pn\nThe coefficients can be negative too.\nIn order to save the polynoms click the 'Add poly' button near the text field\nNext, choose the operation you want to be performed\n");
	}
}
