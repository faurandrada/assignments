package Main;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import Monomials.MonomInteger;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton addPolynomial1;
	private JButton addPolynomial2;
	private JTextPane Polynomial1;
	private JTextPane Polynomial2;
	private JTextPane rez;
	private JTextPane rez1;
	private JMenuBar menu;
	private JTextField addcoefficient;
	private JTextField adddegree;
	private JLabel coefficient;
	private JLabel degree;
	private JLabel result;
	private JLabel rest;
	private JLabel content;
	private JButton derivation;
	private JButton integration;
	private JButton sum;
	private JButton subtraction;
	private JButton multiplication;
	private JButton division;
	private Polynomial polynomial1;
	private Polynomial polynomial2;

	public GUI() {
		polynomial1 = new Polynomial();
		polynomial2 = new Polynomial();

		frame = new JFrame("Polynomial Processing");
		frame.setBounds(200, 30, 550, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);

		menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu file = new JMenu("File");
		menu.add(file);

		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}); 

		file.add(eMenuItem);

		addPolynomial1 = new JButton("Polynomial 1");
		addPolynomial1.setBounds(30, 120, 130, 25);

		frame.getContentPane().add(addPolynomial1);

		addPolynomial2 = new JButton("Polynomial 2");
		addPolynomial2.setBounds(30, 150, 130, 25);
		frame.getContentPane().add(addPolynomial2);

		derivation = new JButton("Derivation");
		derivation.setBounds(50, 230, 100, 25);
		frame.getContentPane().add(derivation);

		integration = new JButton("Integration");
		integration.setBounds(50, 260, 100, 25);
		frame.getContentPane().add(integration);

		sum = new JButton("Addition");
		sum.setBounds(50, 290, 100, 25);
		frame.getContentPane().add(sum);

		subtraction = new JButton("Subtraction");
		subtraction.setBounds(180, 230, 100, 25);
		frame.getContentPane().add(subtraction);

		multiplication = new JButton("Multiplication");
		multiplication.setBounds(180, 260, 110, 25);
		frame.getContentPane().add(multiplication);

		division = new JButton("Division");
		division.setBounds(180, 290, 100, 25);
		frame.getContentPane().add(division);

		addcoefficient = new JTextField(30);
		addcoefficient.setBounds(130, 20, 40, 30);
		frame.getContentPane().add(addcoefficient);

		adddegree = new JTextField(30);
		adddegree.setBounds(130, 50, 40, 30);
		frame.getContentPane().add(adddegree);

		Polynomial1 = new JTextPane();
		Polynomial1.setBounds(170, 120, 200, 25);
		Polynomial1.setOpaque(true);
		Polynomial1.setEditable(false);
		frame.getContentPane().add(Polynomial1);

		Polynomial2 = new JTextPane();
		Polynomial2.setBounds(170, 150, 200, 25);
		Polynomial2.setOpaque(true);
		Polynomial2.setEditable(false);
		frame.getContentPane().add(Polynomial2);

		rez = new JTextPane();
		rez.setBounds(170, 330, 330, 25);
		rez.setOpaque(true);
		rez.setEditable(false);
		frame.getContentPane().add(rez);

		rez1 = new JTextPane();
		rez1.setBounds(170, 360, 330, 25);
		rez1.setOpaque(true);
		rez1.setEditable(false);
		frame.getContentPane().add(rez1);

		coefficient = new JLabel();
		coefficient.setText("Coefficient:");
		coefficient.setBounds(40, 20, 80, 20);
		frame.getContentPane().add(coefficient);

		content = new JLabel();
		content.setText("For derivation and integration you must use the first polynomial!");
		content.setForeground(Color.BLACK);
		content.setBounds(55, 390, 1000, 40);
		frame.getContentPane().add(content);
		
		content = new JLabel();
		content.setText("Lorand Berekmeri");
		content.setForeground(Color.BLACK);
		content.setBounds(360, 20, 1000, 40);
		frame.getContentPane().add(content);
		
		content = new JLabel();
		content.setText("Group: 30425");
		content.setForeground(Color.BLACK);
		content.setBounds(360, 50, 1000, 40);
		frame.getContentPane().add(content);

		result = new JLabel();
		result.setText("Result:");
		result.setBounds(55, 330, 250, 20);
		frame.getContentPane().add(result);

		rest = new JLabel();
		rest.setText("The rest of division:");
		rest.setBounds(55, 360, 250, 20);
		frame.getContentPane().add(rest);

		degree = new JLabel();
		degree.setText("Degree:");
		degree.setBounds(40, 60, 60, 20);
		frame.getContentPane().add(degree);

		addPolynomial1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addPolynomial1) {
					int coefficient = Integer.parseInt(addcoefficient.getText());
					int degree = Integer.parseInt(adddegree.getText());
					MonomInteger mon = new MonomInteger(coefficient, degree);
					polynomial1.polynomial.add(mon);
					Collections.sort(polynomial1.polynomial);
					Polynomial1.setText(polynomial1.toStringPolynomial());
					addcoefficient.setText("");
					adddegree.setText("");
				}
			}
		});

		addPolynomial2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addPolynomial2) {

					int coefficient = Integer.parseInt(addcoefficient.getText());
					int degree = Integer.parseInt(adddegree.getText());

					MonomInteger mon = new MonomInteger(coefficient, degree);
					polynomial2.polynomial.add(mon);
					Collections.sort(polynomial2.polynomial);
					Polynomial2.setText(polynomial2.toStringPolynomial());
					addcoefficient.setText("");
					adddegree.setText("");

				}
			}
		});

		derivation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == derivation) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial PolynomialDerivat = new Polynomial();
						PolynomialDerivat = polynomial1.getDerivataPolynomial();
						Collections.sort(PolynomialDerivat.polynomial);
						rez.setText(PolynomialDerivat.toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Introduceti Polynomialul");
				}
			}
		});

		integration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == integration) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial PolynomialIntegrat = new Polynomial();
						PolynomialIntegrat = polynomial1.getIntegralaPolynomial();
						Collections.sort(PolynomialIntegrat.polynomial);
						rez.setText(PolynomialIntegrat.toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Introduceti Polynomialul");
				}
			}
		});

		sum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sum) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial sumpolynomialoame = new Polynomial();
						sumpolynomialoame = polynomial1.Addition(polynomial2);
						Collections.sort(sumpolynomialoame.polynomial);
						rez.setText(sumpolynomialoame.toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Please enter the polynomials!");
				}
			}
		});

		subtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == subtraction) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial diferentapolynomialoame = new Polynomial();
						diferentapolynomialoame = polynomial1.Subtraction(polynomial2);
						rez.setText(diferentapolynomialoame.toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Please enter the polynomials!");
				}
			}
		});

		multiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == multiplication) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial multiplicationpolynomialoame = new Polynomial();
						multiplicationpolynomialoame = polynomial1.Multiplication(polynomial2);
						Collections.sort(multiplicationpolynomialoame.polynomial);
						rez.setText(multiplicationpolynomialoame.toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Please enter the polynomials!");
				}
			}
		});

		division.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == division) {
					if (!Polynomial1.getText().isEmpty()) {
						Polynomial[] divisionpolynomialoame = new Polynomial[2];
						divisionpolynomialoame = polynomial1.Division(polynomial2);
						Collections.sort(divisionpolynomialoame[0].polynomial);
						Collections.sort(divisionpolynomialoame[1].polynomial);
						rez1.setText(divisionpolynomialoame[1].toStringPolynomial());
						rez.setText(divisionpolynomialoame[0].toStringPolynomial());

					} else
						JOptionPane.showMessageDialog(null, "Please enter the polynomials!");
				}
			}
		});

		frame.setVisible(true);

	}

	public static void main(String[] args) {

		GUI i = new GUI();

	}

}
