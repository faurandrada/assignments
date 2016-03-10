package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import models.Converter;
import models.IntCoeffTerm;
import models.Operation;
import models.Polynomial;

public class Frame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel upperFrame;
	private JTextField poly1;
	private JTextField poly2;
	private JPanel choseOp;
	private JPanel resultBox;
	private JTextArea resultArea;
	private Button additionButton, subtractionButton, derivationButton, integrationButton, multiplicationButton,
			divisionButton;

	public Frame() {
		setDefaultLookAndFeelDecorated(true);

		new JFrame("Polynomial calculator");
		setLayout(new GridLayout(4, 1));

		// Upper Part of Frame
		upperFrame = new JPanel(new GridLayout(3, 1));
		ImageIcon myPicture = new ImageIcon(getClass().getResource("mara.png"));
		JLabel picLabel = new JLabel(myPicture);
		add(picLabel);

		poly1 = new JTextField();
		poly2 = new JTextField();

		// Set border for first polynomial
		javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder poly1title = BorderFactory.createTitledBorder(blackline, "First Polynomial");
		poly1title.setTitleJustification(TitledBorder.CENTER);
		poly1.setBorder(poly1title);

		TitledBorder poly2title = BorderFactory.createTitledBorder(blackline, "Second Polynomial");
		poly2title.setTitleJustification(TitledBorder.CENTER);
		poly2.setBorder(poly2title);

		upperFrame.add(poly1, BorderLayout.CENTER);
		upperFrame.add(poly2, BorderLayout.SOUTH);
		add(upperFrame);

		// MiddleFrame
		choseOp = new JPanel(new GridLayout(3, 2));
		additionButton = new Button("\u002B");
		additionButton.addActionListener(this);
		additionButton.setFont(new Font("Courier New", Font.ITALIC, 25));
		choseOp.add(additionButton);
		subtractionButton = new Button("\u2212");
		subtractionButton.addActionListener(this);
		subtractionButton.setFont(new Font("Courier New", Font.BOLD, 25));
		choseOp.add(subtractionButton);
		divisionButton = new Button("\u00F7");
		divisionButton.addActionListener(this);
		divisionButton.setFont(new Font("Courier New", Font.ITALIC, 25));
		choseOp.add(divisionButton);
		multiplicationButton = new Button("\u00D7");
		multiplicationButton.setFont(new Font("Courier New", Font.ITALIC, 25));
		multiplicationButton.addActionListener(this);
		choseOp.add(multiplicationButton);
		integrationButton = new Button("\u222B");
		integrationButton.addActionListener(this);
		integrationButton.setFont(new Font("Courier New", Font.ITALIC, 25));
		choseOp.add(integrationButton);
		derivationButton = new Button("x'");
		derivationButton.addActionListener(this);
		derivationButton.setFont(new Font("Courier New", Font.ITALIC, 25));
		choseOp.add(derivationButton);

		TitledBorder opBoxTitle = BorderFactory.createTitledBorder(blackline, "Select Operation");
		opBoxTitle.setTitleJustification(TitledBorder.CENTER);
		choseOp.setBorder(opBoxTitle);
		add(choseOp, BorderLayout.CENTER);

		// LowerFrame
		resultBox = new JPanel(new BorderLayout());
		
		resultArea = new JTextArea();
		resultArea.setFont(new Font("Courier New", Font.BOLD, 30));
		resultBox.add(resultArea, BorderLayout.CENTER);
		add(resultBox, BorderLayout.SOUTH);
		setVisible(true);
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Converter toPolynomialConverter1 = new Converter();
		Converter toPolynomialConverter2 = new Converter();
		Converter toStingConverter = new Converter();
		Polynomial<IntCoeffTerm> p1 = toPolynomialConverter1.toPolynomial(poly1.getText());
		Polynomial<IntCoeffTerm> p2 = toPolynomialConverter2.toPolynomial(poly2.getText());

		if (e.getSource() == additionButton) {
			resultArea.setText(toStingConverter.toStringInt(Operation.addition(p1, p2)));
			repaint();

		} else if (e.getSource() == subtractionButton) {
			resultArea.setText(toStingConverter.toStringInt(Operation.substraction(p1, p2)));
			repaint();

		} else if (e.getSource() == multiplicationButton) {
			resultArea.setText(toStingConverter.toStringInt(Operation.multiplication(p1, p2)));
			repaint();
		} else if (e.getSource() == divisionButton) {
			Operation division=new Operation();
			division.division(p1, p2);
			resultArea.setText("Q:  "+toStingConverter.toStringFloat(division.getQuotient())+"\nR:  "+toStingConverter.toStringFloat(division.getRemainder()));
			repaint();

		} else if (e.getSource() == integrationButton) {
			resultArea.setText(toStingConverter.toStringFloat(Operation.integration(p1)));
			repaint();
		} else if (e.getSource() == derivationButton) {
			resultArea.setText(toStingConverter.toStringInt(Operation.derivation(p1)));
			repaint();
		}

	}

}
