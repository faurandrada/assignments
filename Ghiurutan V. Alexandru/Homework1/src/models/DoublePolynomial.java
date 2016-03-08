package models;

import controller.Helper;

/**
 * 
 * @author Alexandru
 *
 */
public class DoublePolynomial implements Cloneable {
	private double[] coeffs;
	private int degree;
	protected static Helper helperPolynomial;

	static {
		helperPolynomial = new Helper();
	}

	// varargs declaration
	public DoublePolynomial(double... coeffs) {
		this.coeffs = coeffs;
		degree = coeffs.length - 1;
	}

	public int getDegree() {
		return this.degree;
	}

	public double[] getDoublePolynomial() {
		return this.coeffs;
	}

	@Override
	public String toString() {
		return helperPolynomial.toString(getDoublePolynomial(), getDegree());
	}

	@Override
	public Object clone() {
		DoublePolynomial p;
		try {
			p = (DoublePolynomial) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		return p;
	}
}
