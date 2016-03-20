package controllers;

/**
 * the class responsible for the input and output of the polynomials. Note that the input is going to be an array of integers 
 * e.g.: p1 = 2 0 -1 4 (meaning p1 = 2x^3 - x + 4)
 * some functions (e.g. evaluateAt(p, x)) work properly only if x is provided
 */
import views.*;
import models.*;

public class InputOutput {

	/**
	 * generates the adding of the inputs provided in window
	 * 
	 * @param window
	 */
	public static void add(Window window) {
		window.getOutput()
				.setText(
						Functions
								.add(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()),
										StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()))
								.toString());
	}

	/**
	 * generates the subtracting of the inputs provided in window
	 * 
	 * @param window
	 */
	public static void sub(Window window) {
		window.getOutput()
				.setText(
						Functions
								.subtract(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()),
										StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()))
								.toString());
	}

	/**
	 * generates the multiplying of the inputs provided in window
	 * 
	 * @param window
	 */
	public static void mul(Window window) {
		window.getOutput()
				.setText(
						Functions
								.multiply(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()),
										StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()))
								.toString());
	}

	/**
	 * generates the division of the inputs provided in window
	 * 
	 * @param window
	 */
	public static void div(Window window) {
		Polynomial[] p = new Polynomial[2];
		try {
			p = Functions.divide(StringToPolynomial.stringToRealPolynomial(window.getInput1().getText()),
					StringToPolynomial.stringToRealPolynomial(window.getInput2().getText()));
			window.getOutput().setText(p[0].toString() + "; " + p[1].toString());
		} catch (ArithmeticException e) {
			window.getOutput().setText(e.getMessage());
		}
	}

	/**
	 * generates the differentiation of the first input provided in window
	 * 
	 * @param window
	 */
	public static void diff1(Window window) {
		window.getOutput().setText(Functions
				.differentiate(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText())).toString());
	}

	/**
	 * generates the differentiation of the second input provided in window
	 * 
	 * @param window
	 */
	public static void diff2(Window window) {
		window.getOutput().setText(Functions
				.differentiate(StringToPolynomial.stringToIntPolynomial(window.getInput2().getText())).toString());
	}

	/**
	 * generates the integration of the first input provided in window
	 * 
	 * @param window
	 */
	public static void int1(Window window) {
		window.getOutput().setText(Functions
				.integrate(StringToPolynomial.stringToRealPolynomial(window.getInput1().getText())).toString());
	}

	/**
	 * generates the integration of the second input provided in window
	 * 
	 * @param window
	 */
	public static void int2(Window window) {
		window.getOutput().setText(Functions
				.integrate(StringToPolynomial.stringToRealPolynomial(window.getInput2().getText())).toString());
	}

	/**
	 * evaluates p1 at x
	 * 
	 * @param window
	 */
	public static void evalP1(Window window) {
		window.getOutput()
				.setText(Double.toString(
						Functions.evaluateAt(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()),
								Integer.parseInt(window.getInputX().getText()))));
	}

	/**
	 * evaluates p2 at x
	 * 
	 * @param window
	 */
	public static void evalP2(Window window) {
		window.getOutput()
				.setText(Double.toString(
						Functions.evaluateAt(StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()),
								Integer.parseInt(window.getInputX().getText()))));
	}

	/**
	 * finds the closest root to x of polynomial p1
	 * 
	 * @param window
	 */
	public static void rootP1(Window window) {
		try {
			window.getOutput()
					.setText(Double.toString(Functions.rootArroundPoint(
							StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()),
							Integer.parseInt(window.getInputX().getText()))));
		} catch (HasNoRealRootException e) {
			window.getOutput().setText(e.getMessage());
		}
	}

	/**
	 * finds the closest root to x of polynomial p2
	 * 
	 * @param window
	 */
	public static void rootP2(Window window) {
		try {
			window.getOutput()
					.setText(Double.toString(Functions.rootArroundPoint(
							StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()),
							Integer.parseInt(window.getInputX().getText()))));
		} catch (HasNoRealRootException e) {
			window.getOutput().setText(e.getMessage());
		}
	}

	/**
	 * graphs p1
	 * 
	 * @param window
	 */
	public static void graphP1(Window window) {
		new GraphFrame(StringToPolynomial.stringToIntPolynomial(window.getInput1().getText()), "GraphP1");
	}

	/**
	 * graphs p2
	 * 
	 * @param window
	 */
	public static void graphP2(Window window) {
		new GraphFrame(StringToPolynomial.stringToIntPolynomial(window.getInput2().getText()), "GraphP2");
	}
}
