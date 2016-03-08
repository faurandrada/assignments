package models;

public class IntPolynomial extends DoublePolynomial {
	public IntPolynomial(int... coeffs) {
		super(DoublePolynomial.helperPolynomial.integerToDouble(coeffs));
	}
}
