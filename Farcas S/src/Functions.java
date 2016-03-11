package models;

/**
 * 
 * The functions class defines all the possibles functions on a polynomial: add,
 * sub, mul, div, differentiate, integrate, find root, evaluate
 *
 */
public class Functions {

	public static double ERROR = 0.00001, MAXRANGE = 1_000_000;

	/**
	 * the add function adds two polynomial
	 * 
	 * @param p1
	 *            -- the first polynomial
	 * @param p2
	 *            -- the second polynomial
	 * @return -- the resulting polinomial.
	 * 
	 *         Since the add function is not used in functions handling
	 *         polynomial with real coefficients (divide and integrate) it is
	 *         only defined for polynomials with integer coefficients
	 */
	public static Polynomial add(Polynomial p1, Polynomial p2) {
		Polynomial p;
		int degree;
		if (p1.getDegree() < p2.getDegree()) {
			degree = p2.getDegree();
		} else {
			degree = p1.getDegree();
		}
		Coefficient[] coefficients = new Coefficient[degree + 1];
		int i;
		for (i = 0; i <= degree; i++) {
			if (i > p1.getDegree()) {
				coefficients[degree - i] = new CoefficientInt(
						((CoefficientInt) p2.getCoefficients()[degree - i]).getCoefficient());
			} else if (i > p2.getDegree()) {
				coefficients[degree - i] = new CoefficientInt(
						((CoefficientInt) p1.getCoefficients()[degree - i]).getCoefficient());
			} else {
				coefficients[degree - i] = new CoefficientInt(
						((CoefficientInt) p1.getCoefficients()[p1.getDegree() - i]).getCoefficient()
								+ ((CoefficientInt) p2.getCoefficients()[p2.getDegree() - i]).getCoefficient());
			}
		}
		p = new Polynomial(StringToPolynomial.eraseLeadingZeros(coefficients, degree));
		return p;
	}

	/**
	 * the function subtracts two polynomial
	 * 
	 * @param p1
	 *            -- first polynomial
	 * @param p2
	 *            -- second polynomial
	 * @return -- returns the resulting polynomial.
	 * 
	 *         Since the subtract function is used in functions handling
	 *         polynomial with real coefficients (divide) it is defined for
	 *         polynomials with both integer and real coefficients
	 */
	public static Polynomial subtract(Polynomial p1, Polynomial p2) {
		Polynomial p;
		int degree;
		if (p1.getDegree() < p2.getDegree()) {
			degree = p2.getDegree();
		} else {
			degree = p1.getDegree();
		}
		Coefficient[] coefficients = new Coefficient[degree + 1];
		int i;
		if (p1.getCoefficients()[0].getType() == CoefficientType.INT) {
			for (i = 0; i <= degree; i++) {
				if (i > p1.getDegree()) {
					coefficients[degree - i] = new CoefficientInt(
							-((CoefficientInt) p2.getCoefficients()[degree - i]).getCoefficient());
				} else if (i > p2.getDegree()) {
					coefficients[degree - i] = new CoefficientInt(
							((CoefficientInt) p1.getCoefficients()[degree - i]).getCoefficient());
				} else {
					coefficients[degree - i] = new CoefficientInt(
							((CoefficientInt) p1.getCoefficients()[p1.getDegree() - i]).getCoefficient()
									- ((CoefficientInt) p2.getCoefficients()[p2.getDegree() - i]).getCoefficient());
				}
			}
		} else {
			for (i = 0; i <= degree; i++) {
				if (i > p1.getDegree()) {
					coefficients[degree - i] = new CoefficientReal(
							-((CoefficientReal) p2.getCoefficients()[degree - i]).getCoefficient());
				} else if (i > p2.getDegree()) {
					coefficients[degree - i] = new CoefficientReal(
							((CoefficientReal) p1.getCoefficients()[degree - i]).getCoefficient());
				} else {
					coefficients[degree - i] = new CoefficientReal(
							((CoefficientReal) p1.getCoefficients()[p1.getDegree() - i]).getCoefficient()
									- ((CoefficientReal) p2.getCoefficients()[p2.getDegree() - i]).getCoefficient());
				}
			}
		}
		p = new Polynomial(StringToPolynomial.eraseLeadingZeros(coefficients, degree));
		return p;
	}

	/**
	 * the function multiplies two polynomial
	 * 
	 * @param p1
	 *            -- first polynomial
	 * @param p2
	 *            -- second polynomial
	 * @return -- returns the resulting polynomial.
	 * 
	 *         Since the multiply function is used in functions handling
	 *         polynomial with real coefficients (divide) it is defined for
	 *         polynomials with both integer and real coefficients
	 */
	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial p;
		int i, j, degree = p1.getDegree() + p2.getDegree();
		Coefficient[] coefficients = new Coefficient[degree + 1];
		if (p1.getCoefficients()[0].getType() == CoefficientType.INT) {
			for (i = 0; i <= degree; i++) {
				coefficients[i] = new CoefficientInt(0);
				for (j = i; j >= 0; j--) {
					if (j <= p1.getDegree() && (i - j) <= p2.getDegree()) {
						((CoefficientInt) coefficients[i])
								.setCoefficient(((CoefficientInt) coefficients[i]).getCoefficient()
										+ ((CoefficientInt) p1.getCoefficients()[j]).getCoefficient()
												* ((CoefficientInt) p2.getCoefficients()[i - j]).getCoefficient());
					}
				}
			}
		} else {
			for (i = 0; i <= degree; i++) {
				coefficients[i] = new CoefficientReal(0);
				for (j = i; j >= 0; j--) {
					if (j <= p1.getDegree() && (i - j) <= p2.getDegree()) {
						((CoefficientReal) coefficients[i])
								.setCoefficient(((CoefficientReal) coefficients[i]).getCoefficient()
										+ ((CoefficientReal) p1.getCoefficients()[j]).getCoefficient()
												* ((CoefficientReal) p2.getCoefficients()[i - j]).getCoefficient());
					}
				}
			}
		}
		p = new Polynomial(StringToPolynomial.eraseLeadingZeros(coefficients, degree));
		return p;
	}

	/**
	 * the function divides two polynomial
	 * 
	 * @param p1
	 *            -- first polynomial
	 * @param p2
	 *            -- second polynomial
	 * @return -- returns the resulting polynomial.
	 * 
	 *         the divide function is used only for polynomials with real
	 *         coefficients
	 */
	public static Polynomial[] divide(Polynomial p1, Polynomial p2) throws ArithmeticException {

		if (p2.getDegree() == 0 && ((CoefficientReal) p2.getCoefficients()[0]).getCoefficient() == 0) {
			throw new ArithmeticException("Cannot divide by zero");
		}

		int quotientDegree = p1.getDegree() - p2.getDegree();
		Coefficient[] coefficientsQuotient;
		Polynomial quotient;
		Polynomial rest;
		Polynomial copyOfP1 = p1;
		Polynomial[] p = new Polynomial[2];
		if (p1.getDegree() < p2.getDegree()) {
			coefficientsQuotient = new CoefficientReal[] { new CoefficientReal(0) };
		} else {
			coefficientsQuotient = new CoefficientReal[quotientDegree + 1];
			int i;
			for (i = 0; i <= quotientDegree; i++) {
				coefficientsQuotient[i] = new CoefficientReal(0);
			}
			for (i = 0; copyOfP1.getDegree() >= p2.getDegree(); i++) {
				((CoefficientReal) coefficientsQuotient[i])
						.setCoefficient(((CoefficientReal) copyOfP1.getCoefficients()[0]).getCoefficient()
								/ ((CoefficientReal) p2.getCoefficients()[0]).getCoefficient());
				copyOfP1 = subtract(p1, multiply(p2,
						new Polynomial(StringToPolynomial.eraseLeadingZeros(coefficientsQuotient, quotientDegree))));
				if (copyOfP1.getDegree() == 0) {
					break;
				}
			}
		}
		quotient = new Polynomial(StringToPolynomial.eraseLeadingZeros(coefficientsQuotient, quotientDegree));
		rest = subtract(p1, multiply(p2, quotient));
		p[0] = quotient;
		p[1] = rest;
		return p;
	}

	/**
	 * this evaluates a polynomial at a given point
	 * 
	 * @param p
	 *            -- polynomial to be evaluated
	 * @param n
	 *            -- point to be evaluated
	 * @return -- p(n)
	 */
	public static double evaluateAt(Polynomial p, double n) {
		int i;
		double pOfN = 0;
		for (i = 0; i <= p.getDegree(); i++) {
			pOfN = pOfN + ((CoefficientInt) p.getCoefficients()[i]).getCoefficient() * (Math.pow(n, p.getDegree() - i));
		}
		return pOfN;
	}

	/**
	 * this function finds the closest root of polynomial p to a point using the
	 * bisection method
	 * 
	 * @param p
	 * @param point
	 * @return -- the closest root to point of polynomial p
	 * @throws HasNoRealRootException
	 */
	public static double rootArroundPoint(Polynomial p, double point) throws HasNoRealRootException {
		double a = point - 1, b = point + 1, c, pAtA, pAtB, pAtC;
		pAtA = evaluateAt(p, a);
		pAtB = evaluateAt(p, b);
		while (pAtA * pAtB > 0 && a > point - MAXRANGE && b < point + MAXRANGE) {
			a--;
			b++;
			pAtA = evaluateAt(p, a);
			pAtB = evaluateAt(p, b);
		}
		if (pAtA == 0)
			return a;
		if (pAtB == 0)
			return b;
		if (a <= point - MAXRANGE || b >= point + MAXRANGE) {
			throw new HasNoRealRootException("Has no real root arround point " + point);
		}
		c = 0.5 * (a + b);
		pAtC = evaluateAt(p, c);
		while (pAtC != 0 && Math.abs(c - a) > ERROR) {
			if (pAtC * pAtA > 0) {
				a = c;
			} else {
				b = c;
			}
			c = 0.5 * (a + b);
			pAtC = evaluateAt(p, c);
			pAtA = evaluateAt(p, a);
		}
		return c;
	}

	/**
	 * this function calculates the derivative of a polynomial p
	 * 
	 * @param p
	 *            -- polynomial to be differentiated
	 * @return -- p'
	 * 
	 *         Since differentiate is not used in functions handling polynomials
	 *         with real coefficients (integrate, divide) it is only defined for
	 *         polynomials with integer coefficients
	 */
	public static Polynomial differentiate(Polynomial p) {
		Polynomial derivative;
		Coefficient[] coefficientsOfDerivative;
		if (p.getDegree() == 0) {
			coefficientsOfDerivative = new Coefficient[] { new CoefficientInt(0) };
		} else {
			coefficientsOfDerivative = new Coefficient[p.getDegree()];
			int i;
			for (i = 0; i < p.getDegree(); i++) {
				coefficientsOfDerivative[i] = new CoefficientInt(
						((CoefficientInt) p.getCoefficients()[i]).getCoefficient() * (p.getDegree() - i));
			}
		}
		derivative = new Polynomial(coefficientsOfDerivative);
		return derivative;
	}

	/**
	 * this function integrates a polynomial
	 * 
	 * @param p
	 *            -- Polynomial to be integrated
	 * @return -- integrated polynomial
	 * 
	 *         this function is only defined for polynomials with real
	 *         coefficients
	 */
	public static Polynomial integrate(Polynomial p) {
		Polynomial antiderivative;
		Coefficient[] coefficientOfAntiDerivative = new CoefficientReal[p.getDegree() + 2];
		int i;
		for (i = 0; i < p.getDegree() + 1; i++) {
			coefficientOfAntiDerivative[i] = new CoefficientReal(
					((CoefficientReal) p.getCoefficients()[i]).getCoefficient() / (p.getDegree() + 1 - i));
		}
		coefficientOfAntiDerivative[i] = new CoefficientReal(0);
		antiderivative = new Polynomial(
				StringToPolynomial.eraseLeadingZeros(coefficientOfAntiDerivative, p.getDegree() + 1));
		return antiderivative;
	}

}
