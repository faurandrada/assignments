package model;

/**
 * @author iulia
 * 
 *         A polynomial is composed by one or more terms, and we call that terms
 *         as monomials. A monomial has a coefficient and an exponent
 * @param <T>
 *            In our situation, T is a generic type corresponding to the
 *            coefficient which can take an integer value or a double value
 */
public class Monomial<T> {

	private int exponent;
	private T coefficient;

	public Monomial(T coefficient, int exponent) {
		this.setCoefficient(coefficient);
		this.setExponent(exponent);
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public T getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(T coefficient) {
		this.coefficient = coefficient;
	}

}
