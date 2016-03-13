package model;

/**
 * @author iulia
 * 
 *         Class used by the constructor of the Polynomial class to throw an
 *         exception when a polynomial can't be created from the string received
 *         as input
 */
public class NotAPolynomialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct an exception object
	 * 
	 * @param s
	 *            - the message
	 * 
	 */
	public NotAPolynomialException(String s) {
		super(s);
	}
}
