package models;
/**
 * 
 * @author Ghiurutan
 *Class that reads that process first the coeffs.
 */
public class IntPolynomial extends DoublePolynomial {
	public IntPolynomial(int... coeffs) {
		super(DoublePolynomial.helperPolynomial.integerToDouble(coeffs));
	}
}
