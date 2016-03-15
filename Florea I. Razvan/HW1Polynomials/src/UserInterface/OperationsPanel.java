package UserInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Functionality;

public class OperationsPanel extends JPanel {

	private static final long serialVersionUID = 3497899707272338816L;

	/// 4 panels for different areas of the main frame//////////////////////////////////////////
	private JPanel polynomPanel = new JPanel();
	private JPanel preferencesPanel = new JPanel();
	private JPanel simpleOperations = new JPanel();
	private JPanel complexOperations = new JPanel();
	
	//////fields and labels for displaying the polynomials that we perform operations on////////
	private JTextField mainPolynomField = new JTextField(25);
	private JLabel mainPolynomLabel = new JLabel("Main Polynomial  P(X):");
	private JTextField secondaryPolynomField = new JTextField(25);
	private JLabel secondaryPolynomLabel = new JLabel("Secondary Polynomial  Q(X):");

	///buttons for user interaction///////////////////////////////////////////////////////////////
	private CustomizedButton addPolynomial = new CustomizedButton("Input Polynomial Q(X)");   // 1
	private CustomizedButton clear = new CustomizedButton("Clear Screen");					  // 2
	private CustomizedButton addition = new CustomizedButton("P(X) + Q(X)");				  // 3
	private CustomizedButton subtraction = new CustomizedButton("P(X) - Q(X)");				  // 4
	private CustomizedButton multiplication = new CustomizedButton("P(X) * Q(X)");			  // 5
	private CustomizedButton division = new CustomizedButton("P(X) / Q(X)");				  // 6
	private CustomizedButton findRoots = new CustomizedButton("Find Roots");				  // 7
	private CustomizedButton differentiate = new CustomizedButton("Differentiate");			  // 8
	private CustomizedButton integrate = new CustomizedButton("Integrate"); 				  // 9
	private CustomizedButton valueAtPoint = new CustomizedButton("Value at a given point");   // 10

	//// fonts for labels and 'function' object for the actions performed by buttons////////////////
	private Functionality function = new Functionality();
	private Fonts fonts = new Fonts();

	public OperationsPanel() {

		giveFunctionsToButtons();
		setBackground(Color.GRAY);

		/// setting main panel////////////////////////////////////////////////
		setLayout(new GridLayout(4, 1));

		/// create Polynomial panel//////////////////////////////////////////
		polynomPanel.setBackground(Color.LIGHT_GRAY);
		polynomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		polynomPanel.add(mainPolynomLabel, gbc);

		gbc.gridx = 1;
		mainPolynomField.setText(function.createStringIntPolynomial(function.createMainPolynomial()));
		mainPolynomField.disable();
		mainPolynomField.setDisabledTextColor(Color.CYAN);
		mainPolynomField.setFont(fonts.getInputFont());
		mainPolynomField.setBackground(Color.GRAY);
		polynomPanel.add(mainPolynomField, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		polynomPanel.add(secondaryPolynomLabel, gbc);

		gbc.gridx = 1;
		secondaryPolynomField.disable();
		secondaryPolynomField.setDisabledTextColor(Color.CYAN);
		secondaryPolynomField.setFont(fonts.getInputFont());
		secondaryPolynomField.setBackground(Color.GRAY);
		polynomPanel.add(secondaryPolynomField, gbc);

		
		add(polynomPanel);

		/// create preferences panel///////////////////////////////////////////
		preferencesPanel.setBackground(Color.GRAY);
		preferencesPanel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		preferencesPanel.add(addPolynomial, gbc);
		gbc.gridx = 1;
		preferencesPanel.add(clear, gbc);
		add(preferencesPanel);

		/// create panel for arithmetical operations/////////////////////////
		simpleOperations.setBackground(Color.LIGHT_GRAY);
		simpleOperations.setLayout(new GridBagLayout());
		gbc.gridy = 0;
		gbc.gridx = 0;
		simpleOperations.add(addition, gbc);
		gbc.gridx = 1;
		simpleOperations.add(subtraction, gbc);
		gbc.gridx = 2;
		simpleOperations.add(multiplication, gbc);
		gbc.gridx = 3;
		simpleOperations.add(division, gbc);
		add(simpleOperations);

		/// create panel for complex operations////////////////////////////////
		complexOperations.setBackground(Color.GRAY);
		complexOperations.setLayout(new GridBagLayout());
		gbc.gridy = 0;
		gbc.gridx = 0;
		complexOperations.add(differentiate, gbc);
		gbc.gridx = 1;
		complexOperations.add(integrate, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		complexOperations.add(valueAtPoint, gbc);
		gbc.gridx = 1;
		complexOperations.add(findRoots, gbc);
		add(complexOperations);
	}

	public void giveFunctionsToButtons() {
		addPolynomial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				function.activateInputSecondPolynomial();
				secondaryPolynomField.setText(function.createStringIntPolynomial(function.getSecondaryPolynomial()));
			}
		});

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				function.activateClearScreen();
			}
		});

		valueAtPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				function.activateValueAtPoint();
			}
		});

		findRoots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				function.activateFindRoots();
			}
		});

		addition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (function.getSecondaryPolynomial() != null)
					function.activatePolynomialAddition();
			}
		});

		subtraction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (function.getSecondaryPolynomial() != null)
					function.activatePolynomialSubtraction();
			}
		});

		multiplication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (function.getSecondaryPolynomial() != null)
					function.activatePolynomialMultiplication();
			}
		});

		division.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(function.getSecondaryPolynomial() != null)
					function.activatePolynomialDivision();
			}	
		});
		
		differentiate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				function.activatePolynomialDifferentiation();
			}
		});
		
		integrate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				function.activatePolynomialIntegration();
			}
			
		});
	}
}
