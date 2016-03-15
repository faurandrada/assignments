package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Polynomial;
import UserInterface.OutputFrame;

/*
 * This class is the one managing all the operations and side functions that we need to perform
 * By 'side functions' I mean for example computing the String that we have as representation of a 
 * polynomial to make it more clear for the user (not showing just arrays of coefficients, but polynomial
 * formats).
 */
public class Functionality {

	private Polynomial mainPolynomial;
	private Polynomial secondaryPolynomial = null;

	private String outputString;
	private UserDialog dialog;
	private OutputFrame outputFrame;

	private Operations operation = new Operations();

	public Polynomial createMainPolynomial() {
		dialog = new UserDialog();
		mainPolynomial = new Polynomial(dialog.askForDegree(), dialog.askForCoefficients());
		outputFrame = new OutputFrame("Output after Computations");

		return mainPolynomial;
	}

	public Polynomial getMainPolynomial() {
		return this.mainPolynomial;
	}

	public Polynomial getSecondaryPolynomial() {
		return this.secondaryPolynomial;
	}

	public String createStringIntPolynomial(Polynomial pol) {

		String polynomial = "";
		int[] coeff = pol.getIntCoefficients();

		if (pol.getDegree() != 0) {
			for (int i = pol.getDegree(); i >= 1; i--) {
				if (coeff[i] < 0)
					polynomial += " " + coeff[i] + "X^" + i;
				else if (coeff[i] == 0)
					polynomial += "";
				else
					polynomial += " + " + coeff[i] + "X^" + i;
			}
			if (coeff[0] > 0)
				polynomial += " + " + coeff[0];
			else if (coeff[0] < 0)
				polynomial += " " + coeff[0];
		} else
			polynomial = "" + coeff[0];

		return polynomial;
	}

	public String createStringDoublePolynomial(Polynomial pol) {

		String polynomial = "";
		double[] coeff = pol.getDoubleCoefficients();
		double[] coeffFormat = new double[coeff.length];

		/// format all the coefficients so that they only have at most 3 decimal
		/// digits so as
		/// not to have very long strings to display ///
		for (int i = 0; i < coeff.length; i++) { ///
			coeffFormat[i] = Double.parseDouble(String.format("%.3f", coeff[i])); ///
			if (coeff[i] != coeffFormat[i]) ///
				coeff[i] = coeffFormat[i]; ///
		}
		///////////////////////////////////////////////////////////////////////////////////////

		if (pol.getDegree() != 0) {
			for (int i = pol.getDegree(); i >= 1; i--) {
				if (coeff[i] < 0)
					polynomial += " " + coeff[i] + "X^" + i;
				else if (coeff[i] == 0)
					polynomial += "";
				else
					polynomial += " + " + coeff[i] + "X^" + i;
			}
			if (coeff[0] > 0)
				polynomial += " + " + coeff[0];
			else if (coeff[0] < 0)
				polynomial += " " + coeff[0];
		} else
			polynomial = "" + coeff[0];

		return polynomial;
	}

	public void displayIntPolynomial(Polynomial pol) {
		outputFrame.getOutputArea().append(" = " + createStringIntPolynomial(pol) + "\n");
	}

	public void displayDoublePolynomial(Polynomial pol) {
		outputFrame.getOutputArea().append(" = " + createStringDoublePolynomial(pol) + "\n");
	}

	public void activateValueAtPoint() {
		outputString = "";
		String stringX = JOptionPane.showInputDialog("Input point value for Polynomial computation:");
		double valueX = 0;
		double finalValue = 0;
		try {

			valueX = Double.parseDouble(stringX);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please input a numerical format !", "Not numeric",
					JOptionPane.ERROR_MESSAGE);
			activateValueAtPoint();
		}

		for (int i = 0; i < mainPolynomial.getIntCoefficients().length; i++) {
			finalValue += mainPolynomial.getIntCoefficients()[i] * (Math.pow(valueX, i));
		}
		outputString = "P(" + valueX + ") = " + finalValue;
		outputFrame.getOutputArea().append(outputString + "\n");
	}

	public void activateFindRoots() {

		if (mainPolynomial.getDegree() > 2)
			outputFrame.getOutputArea().append("Try for degree less than 3\n");
		else
			switch (mainPolynomial.getDegree()) {

			case 0:
				outputFrame.getOutputArea().append("P(X) has no roots. It is a costant\n");
				break;

			case 1:
				double a = mainPolynomial.getDoubleCoefficients()[1];
				double b = mainPolynomial.getDoubleCoefficients()[0];
				double root = -(b / a);
				double rootFormat = Double.parseDouble(String.format("%.3f", root));

				if (root != rootFormat)
					root = rootFormat;

				outputFrame.getOutputArea().append("P(X) has one root:\nx = " + root + "\n");
				break;

			case 2:
				a = mainPolynomial.getDoubleCoefficients()[2];
				b = mainPolynomial.getDoubleCoefficients()[1];
				double c = mainPolynomial.getDoubleCoefficients()[0];
				double root1 = ((-b) + (Math.sqrt(b * b - (4 * a * c)))) / (2 * a);
				double root2 = ((-b) - (Math.sqrt(b * b - (4 * a * c)))) / (2 * a);
				double root1Format = Double.parseDouble(String.format("%.3f", root1));
				double root2Format = Double.parseDouble(String.format("%.3f", root2));

				if (root1 != root1Format)
					root1 = root1Format;

				if (root2 != root2Format)
					root2 = root2Format;

				int numberOfRoots = 0;
				if (!(Double.isNaN(root1)))
					numberOfRoots++;
				if (!(Double.isNaN(root2)))
					numberOfRoots++;

				switch (numberOfRoots) {
				case 0:
					outputFrame.getOutputArea().append("P(X) has no real roots.\n");
					break;

				case 1:
					if (Double.isNaN(root1))
						outputFrame.getOutputArea().append("P(X) has one root:\nx = " + root2 + "\n");
					else
						outputFrame.getOutputArea().append("P(X) has one root:\nx = " + root1 + "\n");

					break;

				case 2:
					if (root1 != root2)
						outputFrame.getOutputArea()
								.append("P(X) has two roots:\nx1 = " + root1 + "\n" + "x2 = " + root2 + "\n");
					else
						outputFrame.getOutputArea()
							    .append("P(X) has one duplicate root:\nx = " + root1 + "\n");
					break;
				}

				break;
			}
	}

	public void activateInputSecondPolynomial() {
		secondaryPolynomial = new Polynomial(dialog.askForDegree(), dialog.askForCoefficients());
	}

	public void activateClearScreen() {
		outputFrame.getOutputArea().setText(null);
	}

	public void activatePolynomialAddition() {
		outputFrame.getOutputArea().append("P(X) + Q(X)");
		displayIntPolynomial(operation.addition(mainPolynomial, secondaryPolynomial));
	}

	public void activatePolynomialSubtraction() {
		outputFrame.getOutputArea().append("P(X) - Q(X)");
		displayIntPolynomial(operation.subtraction(mainPolynomial, secondaryPolynomial));
	}

	public void activatePolynomialMultiplication() {
		outputFrame.getOutputArea().append("P(X) * Q(X)");
		displayIntPolynomial(operation.multiplication(mainPolynomial, secondaryPolynomial));
	}

	public void activatePolynomialDivision() {

		List<Polynomial> polynomialList = new ArrayList<Polynomial>();
		polynomialList = operation.division(mainPolynomial, secondaryPolynomial);
		if (polynomialList.get(0).getDoubleCoefficients()[0] == 0)
			outputFrame.getOutputArea().append("Quotient = 0\n");
		else {
			outputFrame.getOutputArea().append("Quotient");
			displayDoublePolynomial(polynomialList.get(0));
		}
		outputFrame.getOutputArea().append("Rest");
		displayDoublePolynomial(polynomialList.get(1));
	}

	public void activatePolynomialDifferentiation() {
		outputFrame.getOutputArea().append("(P(X))'");
		displayIntPolynomial(operation.differentiation(mainPolynomial));
	}

	public void activatePolynomialIntegration() {

		int c = 0x222B; // unicode value for integral sign
		String integral = Character.toString((char) c);

		outputFrame.getOutputArea().append(integral + " (P(X))");
		displayDoublePolynomial(operation.integration(mainPolynomial));
	}
}
