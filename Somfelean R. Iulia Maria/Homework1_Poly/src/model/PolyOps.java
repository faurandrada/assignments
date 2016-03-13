package model;

import java.text.DecimalFormat;
import java.util.ListIterator;

/**
 * @author iulia
 *
 *         This class is responsible with performing operations on real and
 *         integer polynomials. Operations are: Addition, Subtraction,
 *         Multiplying, Division, Differentiation, Integration.
 */

public class PolyOps {

	/**
	 * Addition on real polynomials.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static RealPolynomial addR(RealPolynomial poly1, RealPolynomial poly2) {
		RealPolynomial addition = new RealPolynomial();
		int degPoly1, degPoly2;
		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();

		double coeff1, coeff2;
		for (int i = 0; i <= Math.max(degPoly1, degPoly2); i++) {

			Monomial<Double> term1 = poly1.getMonAtExp(i);
			Monomial<Double> term2 = poly2.getMonAtExp(i);

			if (term1 != null)
				coeff1 = term1.getCoefficient();
			else
				coeff1 = 0;

			if (term2 != null)
				coeff2 = term2.getCoefficient();
			else
				coeff2 = 0;

			addition.addToMonomialsList(coeff1 + coeff2, i);
		}
		// System.out.print("Addition:");
		// addition.printPoly();
		return addition;
	}

	/**
	 * Subtracts from the highest degree real polynomial the one with the lower
	 * degree.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static RealPolynomial subtractR(RealPolynomial poly1, RealPolynomial poly2) {
		RealPolynomial subtraction = new RealPolynomial();

		int degPoly1, degPoly2;

		double coeff1, coeff2;

		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();

		if (degPoly1 >= degPoly2) {
			for (int i = 0; i <= degPoly1; i++) {

				Monomial<Double> term1 = poly1.getMonAtExp(i);
				Monomial<Double> term2 = poly2.getMonAtExp(i);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				subtraction.addToMonomialsList(coeff1 - coeff2, i);
			}

		} else {
			for (int i = 0; i <= degPoly2; i++) {

				Monomial<Double> term1 = poly1.getMonAtExp(i);
				Monomial<Double> term2 = poly2.getMonAtExp(i);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				subtraction.addToMonomialsList(coeff2 - coeff1, i);
			}
		}
		// System.out.print("Subtraction:");
		// subtraction.printPoly();
		return subtraction;
	}

	/**
	 * Multiplies two real polynomials.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static RealPolynomial multiplyR(RealPolynomial poly1, RealPolynomial poly2) {
		RealPolynomial multiplication = new RealPolynomial();
		int degPoly1, degPoly2;
		double coeff, coeff1, coeff2;

		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();

		for (int i = 0; i <= degPoly1; i++) {
			Monomial<Double> term1 = poly1.getMonAtExp(i);
			for (int j = 0; j <= degPoly2; j++) {
				Monomial<Double> term = multiplication.getMonAtExp(i + j);

				Monomial<Double> term2 = poly2.getMonAtExp(j);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				coeff = coeff1 * coeff2;
				if (term != null) {
					term.setCoefficient(term.getCoefficient() + coeff);
				}

				else
					multiplication.addToMonomialsList(coeff, i + j);
			}
		}
		// System.out.print("Multiplication:");
		// multiplication.printPoly();
		return multiplication;
	}

	/**
	 * Addition on integer polynomials.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static IntPolynomial add(IntPolynomial poly1, IntPolynomial poly2) {
		IntPolynomial addition = new IntPolynomial();
		int degPoly1, degPoly2;
		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();
		for (int i = 0; i <= Math.max(degPoly1, degPoly2); i++) {

			int coeff1, coeff2;

			Monomial<Integer> term1 = poly1.getMonAtExp(i);
			Monomial<Integer> term2 = poly2.getMonAtExp(i);

			if (term1 != null)
				coeff1 = term1.getCoefficient();
			else
				coeff1 = 0;

			if (term2 != null)
				coeff2 = term2.getCoefficient();
			else
				coeff2 = 0;

			addition.addToMonomialsList(coeff1 + coeff2, i);
		}
		// System.out.print("Addition:");
		// addition.printPoly();
		return addition;
	}

	/**
	 * Subtracts from the highest degree integer polynomial the one with the
	 * lower degree.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static IntPolynomial subtract(IntPolynomial poly1, IntPolynomial poly2) {
		IntPolynomial subtraction = new IntPolynomial();
		// subtracting always the lowest degree polynomial from the highest
		// degree polynomial
		int degPoly1, degPoly2;

		int coeff1, coeff2;

		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();

		if (degPoly1 >= degPoly2) {
			for (int i = 0; i <= degPoly1; i++) {

				Monomial<Integer> term1 = poly1.getMonAtExp(i);
				Monomial<Integer> term2 = poly2.getMonAtExp(i);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				subtraction.addToMonomialsList(coeff1 - coeff2, i);
			}

		} else {
			for (int i = 0; i <= degPoly2; i++) {

				Monomial<Integer> term1 = poly1.getMonAtExp(i);
				Monomial<Integer> term2 = poly2.getMonAtExp(i);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				subtraction.addToMonomialsList(coeff2 - coeff1, i);
			}
		}
		System.out.print("Subtraction:");
		subtraction.printPoly();
		return subtraction;
	}

	/**
	 * Multiplies two integer polynomials.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static IntPolynomial multiply(IntPolynomial poly1, IntPolynomial poly2) {
		IntPolynomial multiplication = new IntPolynomial();
		int degPoly1, degPoly2;
		int coeff, coeff1, coeff2;

		degPoly1 = poly1.getDegree();
		degPoly2 = poly2.getDegree();

		for (int i = 0; i <= degPoly1; i++) {
			Monomial<Integer> term1 = poly1.getMonAtExp(i);
			for (int j = 0; j <= degPoly2; j++) {
				Monomial<Integer> term = multiplication.getMonAtExp(i + j);

				Monomial<Integer> term2 = poly2.getMonAtExp(j);

				if (term1 != null)
					coeff1 = term1.getCoefficient();
				else
					coeff1 = 0;

				if (term2 != null)
					coeff2 = term2.getCoefficient();
				else
					coeff2 = 0;

				coeff = coeff1 * coeff2;
				if (term != null) {
					term.setCoefficient(term.getCoefficient() + coeff);
				}

				else
					multiplication.addToMonomialsList(coeff, i + j);
			}
		}
		System.out.print("Multiplication:");
		multiplication.printPoly();
		return multiplication;
	}

	/**
	 * Divides two real polynomials.
	 * 
	 * @param poly1
	 * @param poly2
	 * @return
	 */
	public static RealPolynomial[] divide(RealPolynomial poly1, RealPolynomial poly2) {
		RealPolynomial[] division = new RealPolynomial[2];
		division[0] = new RealPolynomial();// the quotient
		division[1] = new RealPolynomial();// the rest
		RealPolynomial quotient = new RealPolynomial();
		RealPolynomial remainder = new RealPolynomial();

		double coeffR, coeffPoly2;
		remainder = poly1;

		while (!remainder.isEqualToZero() && remainder.getDegree() >= poly2.getDegree()) {

			coeffR = remainder.getMonAtExp(remainder.getDegree()).getCoefficient();
			coeffPoly2 = poly2.getMonAtExp(poly2.getDegree()).getCoefficient();
			Monomial<Double> term = new Monomial<Double>(coeffR / coeffPoly2,
					remainder.getDegree() - poly2.getDegree());
			RealPolynomial t = new RealPolynomial();
			t.addToMonomialsList(term.getCoefficient(), term.getExponent());
			t.printPoly();
			quotient = PolyOps.addR(quotient, t);
			remainder = PolyOps.subtractR(remainder, PolyOps.multiplyR(t, poly2));

		}
		division[0] = quotient;
		division[1] = remainder;
		return division;
	}

	/**
	 * Differentiates an integer polynomial.
	 * 
	 * @param poly1
	 * @return
	 */
	public static IntPolynomial differentiate(IntPolynomial poly1) {
		IntPolynomial differentiation = new IntPolynomial();
		differentiation.setMonomials(poly1.monomials);

		ListIterator<Monomial<Integer>> it = differentiation.iterator();
		while (it.hasNext()) {
			Monomial<Integer> term = it.next();
			//System.out.println(term.getExponent() + " " + term.getCoefficient());
			if (term.getExponent() > 0) {
				term.setCoefficient(term.getCoefficient() * term.getExponent());
				term.setExponent(term.getExponent() - 1);
			} else
				term.setCoefficient(0);

			if (term.getCoefficient() == 0 || term.getExponent() == 0)
				it.remove();
		}
		differentiation.printPoly();
		return differentiation;
	}

	/**
	 * Integrates an integer polynomial.
	 * 
	 * @param poly1
	 * @return
	 */
	public static RealPolynomial integrate(RealPolynomial poly1) {
		RealPolynomial integration = new RealPolynomial();
		integration = poly1;

		ListIterator<Monomial<Double>> it = integration.iterator();
		while (it.hasNext()) {
			Monomial<Double> term = it.next();
			if (term.getExponent() != 0)
				term.setCoefficient(term.getCoefficient() * term.getExponent() / (term.getExponent() + 1));
			term.setExponent(term.getExponent() + 1);
			System.out.println(term.getCoefficient() + " " + term.getExponent());
			integration.printPoly();

		}
		return integration;

	}

}