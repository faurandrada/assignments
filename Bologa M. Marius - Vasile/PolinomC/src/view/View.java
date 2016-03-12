package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import models.Polynomial;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton addA, addB, deleteA, deleteB;
	private JTextField polynomA, polynomB, polynomC, coefficient;
	private JComboBox<String> operations;

	public View(Polynomial model) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(5, 1, 15, 15));
		polynomA = new JTextField();
		polynomB = new JTextField();
		polynomC = new JTextField();
		operations = new JComboBox<>();
		coefficient = new JTextField();
		JPanel content = new JPanel();
		JPanel content1 = new JPanel();
		JPanel content2 = new JPanel();
		JPanel content3 = new JPanel();
		JPanel content4 = new JPanel();
		content1.add(new JLabel("Polynomial A is: "));
		content1.add(polynomA);
		content2.add(new JLabel("Polynomial B is: "));
		content2.add(polynomB);
		content3.add(new JLabel("The result is: "));
		content3.add(polynomC);
		content4.add(new JLabel("Choose operation--> "));
		content4.add(operations);
		content.add(coefficient);
		addA = new JButton("Add in A");
		content.add(addA);
		addB = new JButton("Add in B");
		content.add(addB);
		deleteA = new JButton("Delete A");
		content.add(deleteA);
		deleteB = new JButton("Delete B");
		content.add(deleteB);

		this.add(content1);
		this.add(content2);
		this.add(content3);
		this.add(content4);
		content1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		content2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		content3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		polynomA.setPreferredSize(new Dimension(350, 25));
		polynomB.setPreferredSize(new Dimension(350, 25));
		polynomC.setPreferredSize(new Dimension(350, 25));
		operations.setPreferredSize(new Dimension(150, 25));
		operations.addItem("Add");
		operations.addItem("Subtract");
		operations.addItem("Multiply");
		operations.addItem("Derivative of A");
		operations.addItem("Derivative of B");
		operations.addItem("Integration of A");
		operations.addItem("Integration of B");
		operations.addItem("Evaluate A at");
		operations.addItem("Evaluate B at");
		operations.addItem("Multiply A by ");
		operations.addItem("Multiply B by");
		operations.addItem("A divided by B");
		content.setLayout(new GridLayout(1, 5));
		this.add(content);
		this.setPreferredSize(new Dimension(500, 250));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("System for Polynomial Processing");
		this.setLocationRelativeTo(null);
	}

	public void addOperationsChangeListener(ActionListener listener) {
		operations.addActionListener(listener);
	}

	public void addDeleteAListener(ActionListener listener) {
		deleteA.addActionListener(listener);
	}

	public void addDeleteBListener(ActionListener listener) {
		deleteB.addActionListener(listener);
	}

	public JComboBox<String> getOperations() {
		return operations;
	}

	public void setPolynomialA(String result) {
		polynomA.setText(result);
	}

	public void setPolynomialB(String result) {
		polynomB.setText(result);
	}

	public void setResult(String result) {
		polynomC.setText(result);
	}

	public void addCoefA(ActionListener listener) {
		addA.addActionListener(listener);
	}

	public void addCoefB(ActionListener listener) {
		addB.addActionListener(listener);
	}

	public String getCoef() {
		return coefficient.getText();
	}

	public void clearCoefficient() {
		coefficient.setText("");
	}

	public int getTheScalar() {
		String number = JOptionPane.showInputDialog(String.format("Give the scalar:"));
		Integer scalar = Integer.parseInt(number);
		return scalar;
	}
}
