package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author iulia
 *
 *         The class extends Polynomial and represents an integer polynomial.
 * 
 */
public class IntPolynomial extends Polynomial<Integer> {

	public IntPolynomial() {
		super();
	}

	/**
	 * Creates a polynomial from a string received from input.
	 * 
	 * @param poly
	 * @throws NotAPolynomialException
	 */
	public IntPolynomial(String poly) throws NotAPolynomialException {

		monomials = new ArrayList<Monomial<Integer>>();
		int pos = 0;
		char sign = 0;
		if (poly.length() == 0) {
			throw new NotAPolynomialException("The polynomial cannot be created!");
		}

		if (poly.charAt(0) == '-') {
			sign = '-';
			poly = poly.substring(1);
		}

		StringTokenizer stSign = new StringTokenizer(poly, "+-");
		while (stSign.hasMoreTokens()) {
			String monomial = stSign.nextToken();
			pos += monomial.length();

			StringTokenizer stMonomial = new StringTokenizer(monomial, "*");

			int coeff = 0, exp = 0;
			String coeffStr = stMonomial.nextToken();
			String expStr;

			try {
				coeff = Integer.parseInt(coeffStr);
			} catch (NumberFormatException e) {
				throw new NotAPolynomialException("The polynomial cannot be created!");
			}

			if (sign == '-')
				coeff = -coeff;

			int posMonomial = coeffStr.length() + 3;
			if (posMonomial < monomial.length())
				expStr = monomial.substring(posMonomial);
			else
				throw new NotAPolynomialException("The polynomial cannot be created!");

			try {
				exp = Integer.parseInt(expStr);
			} catch (NumberFormatException e) {
				throw new NotAPolynomialException("The polynomial cannot be created!");
			}

			if (pos < poly.length()) {
				sign = poly.charAt(pos);
				pos++;
			}
			if (!addToMonomialsList(coeff, exp))
				throw new NotAPolynomialException("The polynomial cannot be created!");
		}

	}

	@Override
	public boolean addToMonomialsList(Integer coefficient, int exponent) {
		if (coefficient == 0)
			return true;
		int i = 0;
		Monomial<Integer> monomial = new Monomial<>(coefficient, exponent);

		while (i < monomials.size() && monomials.get(i).getExponent() < exponent)
			i++;
		if (i == monomials.size()) {
			monomials.add(monomial);
			return true;
		} else {
			if (monomials.get(i).getExponent() > exponent) {
				monomials.add(i, monomial);
				return true;
			}

		}
		return false;
	}

	@Override
	public String printPoly() {

		StringBuffer str = new StringBuffer();
		int i = monomials.size();
		if (i == 0) {

			return str.append("0").toString();
		}

		while (i > 1) {

			i--;
			str.append(" ");
			str.append(Integer.toString((int) monomials.get(i).getCoefficient()));
			str.append("*x^");
			str.append(Integer.toString(monomials.get(i).getExponent()));
			str.append(" ");
			if ((int) monomials.get(i - 1).getCoefficient() > 0)
				str.append("+");

			System.out.print(monomials.get(i).getCoefficient() + "*x^" + monomials.get(i).getExponent() + " + ");
		}
		i--;
		str.append(" ");
		str.append(Integer.toString((int) monomials.get(i).getCoefficient()));
		str.append("*x^");
		str.append(Integer.toString(monomials.get(i).getExponent()));
		System.out.println(monomials.get(i).getCoefficient() + "*x^" + monomials.get(i).getExponent());

		return str.toString();
	}

}
