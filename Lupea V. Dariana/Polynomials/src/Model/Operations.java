package Model;

/**
 * This class contains different operations that can be performed on polynomials
 */
public class Operations {

	/**
	 * Performs addition of two polynomials.
	 * 
	 * @param p1 the first polynomial
	 * @param p2 the second polynomial
	 * @return Polynomial the sum of the 2 polynomials
	 */
	public Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
		int i;
		int[] addCoefficients;
		int diffLength = getDiffLength(p1.getDegree(), p2.getDegree());//order
																		// difference

		if (p1.getDegree() > p2.getDegree()) {
			addCoefficients = new int[p1.getDegree() + 1];//maximum length

			for (i = 0; i < diffLength; i++) {
				addCoefficients[i] = p1.getThisCoeff(i);
			}
			for (i = diffLength; i <= p1.getDegree(); i++) {
				addCoefficients[i] = p1.getThisCoeff(i) + p2.getThisCoeff(i - diffLength);
			}
		} else {
			addCoefficients = new int[p2.getDegree() + 1];
			for (i = 0; i < diffLength; i++) {
				addCoefficients[i] = p2.getThisCoeff(i);
			}
			for (i = diffLength; i <= p2.getDegree(); i++) {
				addCoefficients[i] = p2.getThisCoeff(i) + p1.getThisCoeff(i - diffLength);
			}
		}
		return new Polynomial(addCoefficients);
	}
	
	/**
	 * Performs subtraction of two polynomials.
	 * The second polynomial is subtracted from the first one.
	 * 
	 * @param p1 the first polynomial
	 * @param p2 the second polynomial
	 * @return Polynomial the difference between the two polynomials
	 */
	public Polynomial subtractPolynomials(Polynomial p1, Polynomial p2) {
		int i;
		int[] subCoefficients;
		int diffLength = getDiffLength(p1.getDegree(), p2.getDegree());

		if (p1.getDegree() >= p2.getDegree()) {
			subCoefficients = new int[p1.getDegree() + 1];//maximum length

			for (i = 0; i < diffLength; i++) {
				subCoefficients[i] = p1.getThisCoeff(i);
			}
			for (i = diffLength; i <= p1.getDegree(); i++) {
				subCoefficients[i] = p1.getThisCoeff(i) - p2.getThisCoeff(i - diffLength);
			}
		} else {
			subCoefficients = new int[p2.getDegree() + 1];
			for (i = 0; i < diffLength; i++) {
				subCoefficients[i] = -p2.getThisCoeff(i);
			}
			for (i = diffLength; i <= p2.getDegree(); i++) {
				subCoefficients[i] = p1.getThisCoeff(i - diffLength) - p2.getThisCoeff(i);
			}
		}

		return new Polynomial(subCoefficients);
	}
	
	/**
	 * Performs the product of two polynomials.
	 * The two polynomials are multiplied.
	 * 
	 * @param p1 the first polynomial
	 * @param p2 the second polynomial
	 * @return Polynomial the product of the two polynomials
	 */
	public Polynomial multiplyPolynomials(Polynomial p1, Polynomial p2) {
		int i, j;
		int[] mulCoefficients = new int[p1.getDegree() + p2.getDegree() + 1];
		for (i = 0; i <= p1.getDegree(); i++) {
			for (j = 0; j <= p2.getDegree(); j++) {
				mulCoefficients[i + j] = mulCoefficients[i + j] + (p1.getThisCoeff(i) * p2.getThisCoeff(j));
			}
		}
		return new Polynomial(mulCoefficients);
	}

	/**
	 * Performs the division of two polynomials.
	 * The first polynomial(dividend) is divided by the second polynomial(divisor)
	 * 
	 * @param p1 the first polynomial
	 * @param p2 the second polynomial
	 * @return Polynomial  the quotient of the division
	 */
	
	public Polynomial dividePolynomials(Polynomial p1, Polynomial p2) {

		Polynomial bottom, middle;
		middle = p1;
		Polynomial end = new Polynomial(0, 0);
		int n = p1.getDegree() - p2.getDegree();
		int[] coeff = new int[n + 1];
		int i;
		for (i = 0; i <= n; i++) {
			coeff[i] = 0;
		}
		do {
			coeff[n] = middle.getThisCoeff(middle.getDegree()) / p2.getThisCoeff(p2.getDegree());
			bottom = multiplyPolynomials(p2, new Polynomial(coeff[n], n));
			middle = subtractPolynomials(middle, bottom);
			n--;
		} while (!middle.equals(end) && n >= 0);
		return new Polynomial(coeff);

	}
	 
	/**
	 * Calculates the derivative of a polynomial.
	 * 
	 * @param p1 the polynomial to be differentiated
	 * @return Polynomial  the quotient of the division
	 */
	public Polynomial findDerivative(Polynomial p) {
		int i;
		int[] derivativeCoeff = new int[p.getDegree()];
		for (i = 0; i < p.getDegree(); i++) {
			derivativeCoeff[i] = p.getThisCoeff(i) * (p.getDegree() - i);
		}
		return new Polynomial(derivativeCoeff);
	}

	/**
	 * Evaluates the polynomial at a given point.
	 * 
	 * @param p the polynomial to be evaluated
	 * @param x the value for evaluation
	 * @return int the result of the evaluation
	 */
	public int evaluatePolynomial(Polynomial p, int x) {
		int result = 0;
		int i;
		for (i = 0; i <= p.getDegree(); i++) {
			result += p.getThisCoeff(i) * Math.pow(x, p.getDegree() - i);
		}
		return result;

	}

	/**
	 * Calculates the indefinite integral of the given Polynomial
	 * 
	 * @param p the polynomial to be integrated
	 * @return Polynomial the integrand (result of integration)
	 */
	public Polynomial integratePolynomial(Polynomial p) {
		int[] integralCoeff = new int[p.getDegree() + 2];
		for (int i = 0; i <= p.getDegree(); i++) {
			integralCoeff[i] = p.getThisCoeff(i) / (p.getDegree() - i + 1);
		}
		integralCoeff[p.getDegree() + 1] = 0;
		Polynomial integral = new Polynomial(integralCoeff);
		return integral;
	}

	/**
	 * Calculates the definite integral of the given Polynomial,
	 * from a to b.
	 * 
	 * @param a lower limit of integral
	 * @param b upper limit of integral
	 * @param p the polynomial to be integrated
	 * @return int the result of integration 
	 */
	public int findDefiniteIntegral(int a, int b, Polynomial p) {
		Polynomial integral = integratePolynomial(p);
		return evaluatePolynomial(integral, b) - evaluatePolynomial(integral, a);
	}
	
	/**
	 * Multiplies the given Polynomial with a scalar.
	 * 
	 * @param p the polynomial to be multiplied
	 * @param scalar the value for multiplication
	 * @return Polynomial the resulting Polynomial
	 */
	public Polynomial multiplyWithScalar(Polynomial p, int scalar) {

		for (int i = 0; i <= p.getDegree(); i++) {
			p.setThisCoeff(p.getThisCoeff(i) * scalar, i);
		}
		return p;
	}
	
	public int getDiffLength(int degree1, int degree2) {
		return Math.abs(degree1 - degree2);
	}
}





	


