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
			} else if (i == p.getDegree() - 1) {
				poly = getSecondMinTerm(p, poly);
			} else {
				Coefficient c = p.getCoefficients()[i];
				if (c.getType() == CoefficientType.INT) {
					CoefficientInt cInt = (CoefficientInt) c;
					if (cInt.getCoefficient() > 0) {
						poly = getTermWithCoefficientBiggerThanZero(p, poly, i);
					} else if (cInt.getCoefficient() < 0) {
						poly = getTermWithCoefficientLessThanZero(p, poly, i);
					}
				} else {
					CoefficientReal cReal = (CoefficientReal) c;
					if (cReal.getCoefficient() > 0) {
						poly = getTermWithCoefficientBiggerThanZero(p, poly, i);
					} else if (cReal.getCoefficient() < 0) {
						poly = getTermWithCoefficientLessThanZero(p, poly, i);
					}
				}
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
		String poly = "";
		Coefficient c = p.getCoefficients()[0];
		if (p.getDegree() > 1) {
			poly = getMaxTermWhenDegreeGreaterThanOne(p);
		} else if (p.getDegree() == 1) {
			poly = getMaxTermWhenDegreeEqualsOne(p);
		} else {
			if (c.getType() == CoefficientType.INT) {
				poly = ((CoefficientInt) c).getCoefficient() + "";
			} else {
				poly = ((CoefficientReal) c).getCoefficient() + "";
			}
		}
		return poly;
	}

	/**
	 * this method return the the string representation of the max term when
	 * degree is greater than one
	 * 
	 * @param p
	 *            -- polynomial
	 * @return -- a string with the highest degree term if the degree is greater
	 *         than one
	 */
	public static String getMaxTermWhenDegreeGreaterThanOne(Polynomial p) {
		String poly = "";
		Coefficient c = p.getCoefficients()[0];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() == 1) {
				poly = "x^" + p.getDegree();
			} else if (cInt.getCoefficient() == -1) {
				poly = "-x^" + p.getDegree();
			} else {
				poly = cInt.getCoefficient() + "x^" + p.getDegree();
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() == 1) {
				poly = "x^" + p.getDegree();
			} else if (cReal.getCoefficient() == -1) {
				poly = "-x^" + p.getDegree();
			} else {
				poly = cReal.getCoefficient() + "x^" + p.getDegree();
			}
		}
		return poly;
	}

	/**
	 * this method return the the string representation of the max term when
	 * degree is one
	 * 
	 * @param p
	 *            -- polynomial
	 * @return -- a string with the highest degree term if the degree is one
	 */
	public static String getMaxTermWhenDegreeEqualsOne(Polynomial p) {
		String poly = "";
		Coefficient c = p.getCoefficients()[0];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() == 1) {
				poly = "x";
			} else if (cInt.getCoefficient() == -1) {
				poly = "-x";
			} else {
				poly = cInt.getCoefficient() + "x";
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() == 1) {
				poly = "x";
			} else if (cReal.getCoefficient() == -1) {
				poly = "-x";
			} else {
				poly = cReal.getCoefficient() + "x";
			}
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
		Coefficient c = p.getCoefficients()[p.getDegree()];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() > 0) {
				poly = poly + "+" + cInt.getCoefficient();
			} else if (cInt.getCoefficient() < 0) {
				poly = poly + cInt.getCoefficient();
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() > 0) {
				poly = poly + "+" + cReal.getCoefficient();
			} else if (cReal.getCoefficient() < 0) {
				poly = poly + cReal.getCoefficient();
			}
		}
		return poly;
	}

	/**
	 * this method appends to the string poly the representation of the
	 * second-lowest degree term
	 * 
	 * @param p
	 *            -- the polynomial
	 * @param poly
	 *            -- the string representation so far
	 * @return -- the poly string concatenated with the second-lowest degree
	 *         term
	 */
	public static String getSecondMinTerm(Polynomial p, String poly) {
		Coefficient c = p.getCoefficients()[p.getDegree() - 1];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() > 0) {
				if (cInt.getCoefficient() == 1) {
					poly = poly + "+" + "x";
				} else {
					poly = poly + "+" + cInt.getCoefficient() + "x";
				}
			} else if (cInt.getCoefficient() < 0) {
				if (cInt.getCoefficient() == -1) {
					poly = poly + "-" + "x";
				} else {
					poly = poly + cInt.getCoefficient() + "x";
				}
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() > 0) {
				if (cReal.getCoefficient() == 1) {
					poly = poly + "+" + "x";
				} else {
					poly = poly + "+" + cReal.getCoefficient() + "x";
				}
			} else if (cReal.getCoefficient() < 0) {
				if (cReal.getCoefficient() == -1) {
					poly = poly + "-" + "x";
				} else {
					poly = poly + cReal.getCoefficient() + "x";
				}
			}
		}
		return poly;
	}

	/**
	 * this method appends to the string poly the representation of the term
	 * with degree i if it is bigger than zero
	 * 
	 * @param p
	 * -- the polynomial
	 * @param poly
	 * -- the string so far
	 * @param i
	 * -- the degree of the term to be appended
	 * @return
	 */
	public static String getTermWithCoefficientBiggerThanZero(Polynomial p, String poly, int i) {
		Coefficient c = p.getCoefficients()[i];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() == 1) {
				poly = poly + "+" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + "+" + cInt.getCoefficient() + "x^" + (p.getDegree() - i);
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() == 1) {
				poly = poly + "+" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + "+" + cReal.getCoefficient() + "x^" + (p.getDegree() - i);
			}
		}
		return poly;
	}

	/**
	 * this method appends to the string poly the representation of the term
	 * with degree i if it is less than zero
	 * 
	 * @param p
	 * -- the polynomial
	 * @param poly
	 * -- the string so far
	 * @param i
	 * -- the degree of the term to be appended
	 * @return
	 */
	public static String getTermWithCoefficientLessThanZero(Polynomial p, String poly, int i) {
		Coefficient c = p.getCoefficients()[i];
		if (c.getType() == CoefficientType.INT) {
			CoefficientInt cInt = (CoefficientInt) c;
			if (cInt.getCoefficient() == -1) {
				poly = poly + "-" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + cInt.getCoefficient() + "x^" + (p.getDegree() - i);
			}
		} else {
			CoefficientReal cReal = (CoefficientReal) c;
			if (cReal.getCoefficient() == -1) {
				poly = poly + "-" + "x^" + (p.getDegree() - i);
			} else {
				poly = poly + cReal.getCoefficient() + "x^" + (p.getDegree() - i);
			}
		}
		return poly;
	}
}
