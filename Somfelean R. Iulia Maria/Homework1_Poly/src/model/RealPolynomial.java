package model;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * @author iulia
 *
 *         This class extends Polynomial and represents a polynomial with real
 *         coefficients.
 */
public class RealPolynomial extends Polynomial<Double> {

	public RealPolynomial() {
		super();
	}

	/**
	 * Creates a polynomial from a string received from input.
	 * 
	 * @param poly
	 * @throws NotAPolynomialException
	 */
	public RealPolynomial(String poly) throws NotAPolynomialException {

		monomials = new ArrayList<Monomial<Double>>();
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

			double coeff = 0;
			int exp = 0;
			String coeffStr = stMonomial.nextToken();
			String expStr;

			try {
				coeff = Double.parseDouble(coeffStr);
				System.out.println(coeff);
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

	public boolean addToMonomialsList(Double coefficient, int exponent) {
		if (coefficient == 0)
			return true;
		int i = 0;
		Monomial<Double> monomial = new Monomial<>(coefficient, exponent);

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
		System.out.println(i);
		if (i == 0) {

			return str.append("0.0").toString();
		}

		while (i > 1) {

			i--;
			str.append(" ");
			str.append(Double.toString((double) monomials.get(i).getCoefficient()).substring(0, 3));
			str.append("*x^");
			str.append(Double.toString(monomials.get(i).getExponent()));
			str.append(" ");
			if ((double) monomials.get(i - 1).getCoefficient() > 0)
				str.append("+");

			System.out.print(monomials.get(i).getCoefficient() + "*x^" + monomials.get(i).getExponent() + " + ");

			System.out.println("i:" + i);
		}
		i--;
		str.append(" ");
		str.append(Double.toString((double) monomials.get(i).getCoefficient()).substring(0, 3));
		str.append("*x^");
		str.append(Double.toString(monomials.get(i).getExponent()));
		System.out.print(monomials.get(i).getCoefficient() + "*x^" + monomials.get(i).getExponent());
		System.out.println("i:" + i);
		return str.toString();
	}

	/**
	 * Checks whether a polynomial is equal or not with the polynomial received
	 * as parameter.
	 * 
	 * @param poly2
	 * @return
	 */
	public boolean isEqual(RealPolynomial poly2) {
		int i1 = monomials.size();
		int i2 = poly2.monomials.size();
		if (i1 != i2)
			return false;
		for (int i = 0; i < i1; i++) {
			if (!getMonAtExp(i).equals(poly2.getMonAtExp(i)))
				return false;
		}
		return true;
	}

	/**
	 * 
	 * Checks whether a real polynomial is equal or not with zero.
	 * 
	 * @return
	 */
	public boolean isEqualToZero() {
		boolean ok = true;
		ListIterator<Monomial<Double>> it = (ListIterator<Monomial<Double>>) monomials.listIterator();
		while (it.hasNext()) {
			Monomial<Double> term = it.next();
			if (term.getCoefficient() != 0)
				ok = false;
		}
		return ok;
	}

}
