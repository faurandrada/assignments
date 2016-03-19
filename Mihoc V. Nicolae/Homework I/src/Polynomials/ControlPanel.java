package Polynomials;

import java.awt.*;
import javax.swing.*;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	// Define a constant string that will be outputed on the status code.
	private static final String ERROR_MESSAGE = "Invalid input";

	// define the panels that will be added to the frame
	private JPanel textPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();

	// define text areas that will be added to the first panel
	private JTextArea inputText = new JTextArea();
	private JTextArea resultText = new JTextArea();
	private JTextArea statusText = new JTextArea();
	private JTextArea restText = new JTextArea();

	// define the buttons that will be added to the second panel
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton mul = new JButton("*");
	private JButton div = new JButton("/");
	private JButton deriv = new JButton("'");
	private JButton equals = new JButton("=");
	private JButton set = new JButton("Set");
	private JButton integral = new JButton("\u222b");

	/*
	 * define some panels that will have the role to help the user with the
	 * interface
	 */
	private JLabel inputName = new JLabel("Input:");
	private JLabel resultName = new JLabel("Result:");
	private JLabel statusName = new JLabel("Status:");
	private JLabel restName = new JLabel("Rem:");

	// define polynomials to work with
	private Polynomial input = new Polynomial();
	private Polynomial result = new Polynomial();

	// We set bounds for each component of the frame.
	// We add action listeners in order for the program to respond to user
	// inputs.

	public ControlPanel() {
		// we set the bounds for the text areas
		inputName.setBounds(0, 0, 50, 20);
		resultName.setBounds(0, 25, 50, 20);
		statusName.setBounds(0, 50, 50, 20);
		restName.setBounds(0, 75, 50, 20);
		inputText.setBounds(50, 0, 525, 20);
		inputText.setRows(1);
		resultText.setBounds(50, 25, 525, 20);
		resultText.setRows(1);
		statusText.setBounds(50, 50, 525, 20);
		restText.setBounds(50, 75, 525, 20);
		restText.setBackground(Color.orange);
		restText.setVisible(true);
		restText.setRows(1);
		textPanel.add(restText);
		// we set colors for result text and status text in order to distinguish
		// them easily
		resultText.setBackground(Color.red);
		statusText.setBackground(Color.GRAY);
		/*
		 * we set the result text,rest text and the status text as non-editable
		 * //in order for the user not to modify them
		 */
		resultText.setEditable(false);
		statusText.setEditable(false);
		restText.setEditable(false);
		/*
		 * we set the layouts to null because we add the components by
		 * specifying for each the bounds
		 */
		textPanel.setLayout(null);
		buttonPanel.setLayout(null);
		this.setLayout(null);

		textPanel.setBounds(0, 0, 525, 100);
		buttonPanel.setBounds(0, 100, 525, 100);
		// we add to the text panel the components that were configured
		textPanel.add(inputName);
		textPanel.add(resultName);
		textPanel.add(statusName);
		textPanel.add(restName);
		textPanel.add(inputText);
		textPanel.add(resultText);
		textPanel.add(statusText);

		// we add action listeners for each of the buttons
		plus.addActionListener(this);
		minus.addActionListener(this);
		mul.addActionListener(this);
		div.addActionListener(this);
		deriv.addActionListener(this);
		equals.addActionListener(this);
		set.addActionListener(this);
		integral.addActionListener(this);

		// we set the bounds for the buttons and add them to the button panel
		plus.setBounds(5, 0, 60, 30);
		buttonPanel.add(plus);
		minus.setBounds(65, 0, 60, 30);
		buttonPanel.add(minus);
		mul.setBounds(125, 0, 60, 30);
		buttonPanel.add(mul);
		div.setBounds(185, 0, 60, 30);
		buttonPanel.add(div);
		deriv.setBounds(185, 30, 60, 30);
		buttonPanel.add(deriv);
		equals.setBounds(5, 30, 60, 30);
		buttonPanel.add(equals);
		set.setBounds(65, 30, 60, 30);
		buttonPanel.add(set);
		integral.setBounds(125, 30, 60, 30);
		buttonPanel.add(integral);
		// we finally add the two panels
		this.add(textPanel);
		this.add(buttonPanel);
	}

	/**
	 * Reads from a text area. A message,error message if the input does not fit
	 * the specifications ,another message if the input was read successfully.
	 */
	public String read(JTextArea text) {
		String str, buffer;
		// we memorise the input text into a string that will be used
		// for splitting into tokens
		str = text.getText();
		str = str.substring(0, str.length());
		char c;

		// clear the input polynom
		for (int i = 0; i < 100; i++) {
			input.setCoefficient(i, 0);
		}

		// We separate the string into tokens,the delimiter being "+"
		StringTokenizer st = new StringTokenizer(str, "+ ");
		while (st.hasMoreTokens()) {
			buffer = st.nextToken();
			int i = 0;
			int numeric = 0;
			int degree = 0;
			int sign = 1;
			c = '0';
			// check for exception in writting
			if (buffer.charAt(i) == '-') {
				sign = -1;
				i++;
			}
			if (buffer.charAt(i) == 'x') {
				numeric = 1;
				c = buffer.charAt(i);
				i++;
			} else if (!Character.isDigit(buffer.charAt(i))) {
				return ERROR_MESSAGE;
			} else
				do {
					// compute the coefficient
					numeric = numeric * 10 + Character.digit(c, 10);
					numeric = numeric * sign;
					c = buffer.charAt(i);
					i++;
				} while (Character.isDigit(c) && i < buffer.length());

			// Check if we have to deal with the free term
			if (i >= buffer.length() && c != 'x') {
				numeric = numeric * 10 + Character.digit(c, 10);
				numeric = numeric * sign;
				input.setCoefficient(0, numeric);
				return "good";
			}

			// check for input error
			if (c != 'x') {
				return ERROR_MESSAGE;
			}
			// checks if we have to deal with the term having the degree equal
			// to 1
			if (i >= buffer.length())
				input.setCoefficient(1, numeric);
			else {
				c = buffer.charAt(i);
				// check for input error
				if (c != '^') {
					return ERROR_MESSAGE;
				}
				i++;
				// checks if there is ^ without its corresponding exponent
				if (i >= buffer.length()) {
					return ERROR_MESSAGE;
				}
				// read the degree of the term
				do {
					c = buffer.charAt(i);
					degree = degree * 10 + Character.digit(c, 10);
					i++;
				} while (Character.isDigit(c) && i < buffer.length());
				// checks for error: the size of the polynom is limited
				if (degree > 100) {
					return ERROR_MESSAGE;
				}

				input.setCoefficient(degree, numeric);
			}
		}
		return "Good";
	}

	// Copies the input polynomial to the result polynomial.
	public void copyToResult() {
		for (int i = 0; i < 100; i++) {
			result.setCoefficient(i, input.getCoefficient(i));
		}
	}

	// Adds the two polynomials input and result.
	public void add() {
		for (int i = 0; i < 100; i++) {
			if (input.getCoefficient(i) != 0 || result.getCoefficient(i) != 0) {
				result.setCoefficient(i, input.getCoefficient(i) + result.getCoefficient(i));
			}
		}
	}

	// Subtracts the minus polynomial from the result.
	public void minus() {
		for (int i = 0; i < 100; i++) {
			if (input.getCoefficient(i) != 0 || result.getCoefficient(i) != 0) {
				result.setCoefficient(i, result.getCoefficient(i) - input.getCoefficient(i));
			}
		}
	}

	// Multiplies the two polynomials.
	public void mul() { // we use the aux in order to keep teporarily the result
		int[] aux = new int[200];
		for (int i = 0; i < 100; i++) {
			if (input.getCoefficient(i) != 0) {
				for (int j = 0; j < 100; j++) {
					if (result.getCoefficient(j) != 0) {
						aux[i + j] = aux[i + j] + result.getCoefficient(j) * input.getCoefficient(i);
					}
				}
			}
		}
		// the result is copied from aux to result
		for (int i = 0; i < 100; i++) {
			result.setCoefficient(i, aux[i]);
		}
	}

	// returns the degree of a polynomial given as an array of coefficients.
	public int getGrad(double[] arr) {
		for (int i = 99; i >= 0; i--) {
			if (arr[i] != 0) {
				return i;
			}
		}
		return 0;
	}

	// Divide the two polynomials.
	public void div() {
		Polynomial d = new Polynomial();
		double[] q = new double[100];
		double[] r = new double[100];
		// array used for memorizing intermediate results
		double[] res = new double[100];
		int[] init = new int[200];

		for (int i = 99; i >= 0; i--) {
			init[i] = result.getCoefficient(i);
			res[i] = (double) result.getCoefficient(i);
		}
		if (input.getGrad() == 0) {
			q[0] = 1;
		} else if (getGrad(res) >= input.getGrad()) {
			int dif;
			/* sets the initial result to 0 */
			for (int i = 0; i < 100; i++) {
				q[i] = 0.0;
			}
			/*
			 * Memorise in d the coefficients of input polynomial shifted by the
			 * difference between result polynomial and input polynomial.
			 */
			while (getGrad(res) >= input.getGrad()) {
				dif = getGrad(res) - input.getGrad();
				for (int i = 99; i >= dif; i--) {
					d.setCoefficient(i, input.getCoefficient(i - dif));
				}
				for (int i = dif - 1; i >= 0; i--) {
					d.setCoefficient(i, 0);
				}
				q[dif] = res[getGrad(res)] / (double) (d.getCoefficient(d.getGrad()));
				for (int i = 99; i >= 0; i--) {
					res[i] = res[i] - d.getCoefficient(i) * q[dif];
				}
			}
			// copy into the remainder array the last result of res
			for (int i = 99; i >= 0; i--) {
				r[i] = res[i];
			}
		} else {
			for (int i = 0; i < 100; i++) {
				q[i] = 0.0;
			}
			for (int i = 99; i >= 0; i--) {
				r[i] = (double) result.getCoefficient(i);
			}
		}
		// clear the input text area and result text area
		resultText.setText("");
		inputText.setText("");
		restText.setText("");
		// displays the result stored in q
		for (int i = 99; i >= 0; i--) {
			q[i] = Math.round(q[i] * 100.0) / 100.0;
			if (q[i] != 0.0)
				resultText.append(Double.toString(q[i]) + "x" + "^" + Integer.toString(i) + "+");
			result.setCoefficient(i, (int) q[i]);
			r[i] = Math.round(r[i] * 100.0) / 100.0;
			if (r[i] != 0.0)
				restText.append(Double.toString(r[i]) + "x" + "^" + Integer.toString(i) + "+");
		}
		// erase the last + from result text area and rest text area
		String str = resultText.getText();
		String str2 = restText.getText();
		if (str.length() > 0) {
			resultText.setText(str.substring(0, str.length() - 1));
		}
		if (str2.length() > 0) {
			restText.setText(str2.substring(0, str2.length() - 1));
		}
	}

	/**
	 * Computes the derivative for the input polynomial. The result will be
	 * stored in the result polynomial.
	 */
	public void deriv() {
		result.setZero();
		for (int i = 1; i < 100; i++) {
			if (input.getCoefficient(i) != 0) {
				result.setCoefficient(i - 1, input.getCoefficient(i) * i);
			}
		}
	}

	/**
	 * Computes the integral of the polynomial and outputs it to the result text
	 * area. We store in the result polynomial the result of the operations but
	 * the coefficients will be truncated from real coefficients to integral
	 * coefficients.
	 */
	public void integral() {
		result.setZero();
		resultText.setText("");
		inputText.setText("");
		double[] x = new double[100];
		for (int i = 99; i >= 0; i--) {
			if (input.getCoefficient(i) != 0) {
				result.setCoefficient(i + 1, input.getCoefficient(i) / (i + 1));
				x[i + 1] = (double) input.getCoefficient(i) / (i + 1);
			}
		}
		/* displaying the result */
		for (int i = 99; i >= 0; i--) {
			if (x[i] != 0) {
				x[i] = Math.round(x[i] * 100.0) / 100.0;
				resultText.append(Double.toString(x[i]) + "x" + "^" + Integer.toString(i) + "+");
			}
		}
		/* erase the last + */
		String str = resultText.getText();
		if (str.length() > 0) {
			resultText.setText(str.substring(0, str.length() - 1));
		}
	}

	/**
	 * Outputs the result stored in the result polynomial to the result text
	 * area.
	 */
	public void setResult() {
		String str;
		inputText.setText("");
		resultText.setText("");
		for (int i = 99; i >= 0; i--) {
			if (result.getCoefficient(i) != 0) {
				if (result.getCoefficient(i) == 1) {
					if (i == 0) {
						resultText.append(result.getCoefficient(i) + "+");
					} else if (i == 1) {
						resultText.append("x" + "+");
					} else {
						resultText.append("x" + "^" + Integer.toString(i) + "+");
					}
				} else {
					if (i == 1) {
						resultText.append(Integer.toString(result.getCoefficient(i)) + "x" + "+");
					} else if (i == 0) {
						resultText.append(Integer.toString(result.getCoefficient(i)) + "+");
					} else {
						resultText.append(
								Integer.toString(result.getCoefficient(i)) + "x" + "^" + Integer.toString(i) + "+");

					}
				}
			}
		}
		str = resultText.getText();
		/* check if the result polynomial is not null */
		if (str.length() > 0) {
			resultText.setText(str.substring(0, str.length() - 1));
		}
	}

	/**
	 * This method is activated whenever the user presses a button computing
	 * will be done according to the button that was pressed.
	 */
	public void actionPerformed(ActionEvent event) {
		// Puts in the result text area the polynomial stored in result

		if (event.getSource() == equals) {
			setResult();
		}
		/*
		 * If set is pressed the polynomial from input text area will be read
		 * and will be copied to result.
		 */
		if (event.getSource() == set) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				copyToResult();
				setResult();
			}
		}
		/*
		 * If plus is pressed the polynomial from input text area will be read
		 * and the two polynomials will be added.The result will be outputed in
		 * the output text area.
		 */
		if (event.getSource() == plus) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				add();
				setResult();
			}
		}
		/*
		 * If minus is pressed the polynomial from input text area will be
		 * subtracted from the result polynomial
		 */
		if (event.getSource() == minus) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				minus();
				setResult();
			}
		}
		/*
		 * If mul is pressed the polynomial from input text area will be
		 * multiplied with the result polynomial.
		 */
		if (event.getSource() == mul) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				mul();
				setResult();
			}
		}
		/*
		 * If div is pressed the polynomial from result will be divided by the
		 * polynomial from input.
		 */
		if (event.getSource() == div) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				div();
			}
		}
		/**
		 * If deriv is pressed the polynomial from input text area will be
		 * derived and displayed in the result text area.
		 */
		if (event.getSource() == deriv) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				deriv();
				setResult();
			}
		}
		/**
		 * If integral is pressed the integral of the polynomial from input text
		 * area will be displayed
		 */
		if (event.getSource() == integral) {
			if (read(inputText).equals(ERROR_MESSAGE)) {
				statusText.setText(ERROR_MESSAGE);
			} else {
				statusText.setText("Give me another polynom");
				integral();
			}
		}
	}
}
