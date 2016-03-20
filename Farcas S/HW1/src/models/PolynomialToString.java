package models;

/**
 * 
 * translates a polynomial (represented by an array of coefficients and a
 * degree) into a mathematical string representation
 *
 */
public class PolynomialToString {

	/**
	 * this method generates a mathematical string representation of a given
	 * polynomial
	 * 
	 * @param p
	 *            -- Polynomial to be represented
	 * @return -- mathematical representation
	 */
	public static String getStringRepresentation(Polynomial p) {
		String poly = "";
		int i;
		for (i = 0; i <= p.getDegree(); i++) {
			if (i == 0) {
				poly = getMaxTerm(p);
			} else if (i == p.getDegree()) {
				poly = getMinTerm(p, poly);
			} else {
				poly = getTerm(p, poly, i);
			}
		}
		return poly;
	}

	/**
	 * this method returns the string representation of the highest degree term
	 * 
	 * @param p
	 *            -- the polynomial
	 * @return -- a string with the highest degree term
	 */
	public static String getMaxTerm(Polynomial p) {
		double x = 0;
		String poly = "";
		Coefficient c = p.getCoefficients()[0];
		if (p.getDegree() >= 1) {
			poly = getMaxTermWhenDegreeGreaterOrEqualThanOne(p);
		} else {
			if (c.getType() == CoefficientType.INT) {
				x = ((CoefficientInt) c).getCoefficient();
			} else {
				x = ((CoefficientReal) c).getCoefficient();
			}
			poly = x + "";
		}
		return poly;
	}

	/**
	 * this method return the the string representation of the max term when
	 * degree is greater or equal than one
	 * 
	 * @param p
	 *            -- polynomial
	 * @return -- a string with the highest degree term if the degree is greater
	 *         than one
	 */
	public static String getMaxTermWhenDegreeGreaterOrEqualThanOne(Polynomial p) {
		String poly = "";
		double x = 0;
		Coefficient c = p.getCoefficients()[0];
		if (c.getType() == CoefficientType.INT) {
			x = ((CoefficientInt) c).getCoefficient();
		} else {
			x = ((CoefficientReal) c).getCoefficient();
		}
		if (x == 1) {
			poly = "x^" + p.getDegree();
		} else if (x == -1) {
			poly = "-x^" + p.getDegree();
		} else {
			poly = x + "x^" + p.getDegree();
		}
		return poly;
	}

	/**
	 * this method appends to the string poly the representation of the lowest
	 * degree term
	 * 
	 * @param p
	 *            -- the polynomial
	 * @param poly
	 *            -- the string representation so far
	 * @return -- the poly string concatenated with the lowest degree term
	 */
	public static String getMinTerm(Polynomial p, String poly) {
		double x = 0;
		Coefficient c = p.getCoefficients()[p.getDegree()];
		if (c.getType() == CoefficientType.INT) {
			x = ((CoefficientInt) c).getCoefficient();

		} else {
			x = ((CoefficientReal) c).getCoefficient();
		}
		if (x > 0) {
			poly = poly + "+" + x;
		} else if (x < 0) {
			poly = poly + x;
		}
		return poly;
	}

	/**
	 * this method appends to the string poly the representation of the term
	 * with degree i
	 * 
	 * @param p
	 *            -- the polynomial
	 * @param poly
	 *            -- the string so far
	 * @param i
	 *            -- the degree of the term to be appended
	 * @return
	 */
	public static String getTerm(Polynomial p, String poly, int i) {
		double x = 0;
		Coefficient c = p.getCoefficients()[i];
		if (c.getType() == CoefficientType.INT) {
			x = ((CoefficientInt) c).getCoefficient();
		} else {
			x = ((CoefficientReal) c).getCoefficient();
		}
		if (x > 0) {
			if (x == 1) {
				poly = poly + "+" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + "+" + x + "x^" + (p.getDegree() - i);
			}
		} else if (x < 0) {
			if (x == -1) {
				poly = poly + "-" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + x + "x^" + (p.getDegree() - i);
			}
		}
		return poly;
	}
}
