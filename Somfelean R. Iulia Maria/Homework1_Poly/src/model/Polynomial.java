package model;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

/**
 * @author iulia This class represent a polynomial composed of monomials, whose
 *         coefficients have a generic type.
 * @param <T>
 */

public abstract class Polynomial<T> {

	public List<Monomial<T>> monomials;

	public Polynomial() {

		setMonomials(new ArrayList<Monomial<T>>());
	}

	// this method intends to add one more element to the monomials list
	public abstract boolean addToMonomialsList(T coefficient, int exponent);

	// this method return a printable form of the polynomial
	public abstract String printPoly();

	public List<Monomial<T>> getMonomials() {
		return monomials;
	}

	public void setMonomials(List<Monomial<T>> monomials) {
		this.monomials = monomials;
	}

	/**
	 * Returns the degree of the polynomial.
	 * 
	 * @return
	 */
	public int getDegree() {

		int monomialsSize = monomials.size();
		if (monomialsSize > 0)
			return monomials.get(monomialsSize - 1).getExponent();
		return -1;
	}

	/**
	 * Returns the monomial having the exponent given as parameter.
	 * 
	 * @param exponent
	 * @return
	 */
	public Monomial<T> getMonAtExp(int exponent) {
		int i = 0;
		while (i < monomials.size() && monomials.get(i).getExponent() != exponent)
			i++;
		if (i < monomials.size())
			return monomials.get(i);
		return null;
	}

	public ListIterator<Monomial<T>> iterator() {
		return monomials.listIterator();
	}

}
