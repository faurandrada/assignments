package poly.operations;

import poly.model.Polynomial;
import poly.model.RealCoeff;

/**
 * 
 * @author Dia
 *
 *         The public class Functions describes the methods that can be
 *         performed upon polynomials. These are: sum, difference, multiply,
 *         divide, differentiate, integrate, evaluate.
 */
public class Functions {
	public Polynomial sum(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial(maxDegree(p1.degree, p2.degree));

		for (int i = 0; i <= result.degree; i++) {
			if (i <= p1.degree) {
				if (i <= p2.degree) {
					result.coeff[i].updateCoeff(p1.coeff[i].getCoeff() + p2.coeff[i].getCoeff());
				} else {
					result.coeff[i].updateCoeff(p1.coeff[i].getCoeff());
				}
			} else {
				result.coeff[i].updateCoeff(p2.coeff[i].getCoeff());
			}
		}
		return result;
	}

	public Polynomial difference(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial(maxDegree(p1.degree, p2.degree));

		for (int i = 0; i <= result.degree; i++) {
			if (i <= p1.degree) {
				if (i <= p2.degree) {
					result.coeff[i].updateCoeff(p1.coeff[i].getCoeff() - p2.coeff[i].getCoeff());
				} else {
					result.coeff[i].updateCoeff(p1.coeff[i].getCoeff());
				}
			} else {
				result.coeff[i].updateCoeff(-p2.coeff[i].getCoeff());
			}
		}
		return result;
	}

	public Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial(p1.degree + p2.degree);
		result.coeff[p1.degree + p2.degree].updateCoeff(0);
		for (int i = 0; i <= p1.degree; i++) {
			for (int j = 0; j <= p2.degree; j++) {
				result.coeff[i + j]
						.updateCoeff(result.coeff[i + j].getCoeff() + p1.coeff[i].getCoeff() * p2.coeff[j].getCoeff());
			}
		}
		return result;
	}

	public Polynomial divide(Polynomial p1, Polynomial p2) {
		// supposing p1.grade >= p2.grade
		if (p1.degree >= p2.degree) {
			try {
				Polynomial result = new Polynomial(p1.degree - p2.degree);
				result.coeff[p1.degree - p2.degree].updateCoeff(0);

				if (p2.degree == 0 && p2.coeff[p2.degree].isZero()) {
					throw new RuntimeException("division by ZERO");
				} else {
					double[] quotient = new double[p1.degree - p2.degree + 1]; // ~result
					double[] remainder = new double[p1.degree + 1];

					int degreeRem = p1.degree;

					for (int i = 0; i <= p1.degree; i++) {
						remainder[i] = (double) p1.coeff[i].getCoeff();
					}

					while (degreeRem >= p2.degree) {
						quotient[degreeRem
								- p2.degree] = (double) (remainder[degreeRem] / p2.coeff[p2.degree].getCoeff());
						for (int y = p2.degree - 1; y >= 0; y--) {
							int aux = p2.coeff[y].getCoeff();
							remainder[y + degreeRem - p2.degree] -= aux * quotient[degreeRem - p2.degree];
						}
						degreeRem--;
					}
					for (int i = 0; i <= p1.degree - p2.degree; i++) {
						result.coeff[i] = new RealCoeff(quotient[i]);
					}
					return result;
				}

			} catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
				return null;
			}
		}
		Polynomial result = new Polynomial(0);
		result.coeff[0].updateCoeff(0);
		return result;
	}

	public Polynomial differentiate(Polynomial p1) {
		Polynomial result = new Polynomial(p1.degree - 1);
		for (int i = 1; i <= p1.degree; i++) {
			result.coeff[i - 1].updateCoeff(p1.coeff[i].getCoeff() * i);
		}
		return result;
	}

	public Polynomial integrate(Polynomial p1) {
		Polynomial result = new Polynomial(p1.degree + 1); // !REAL
		result.coeff[0].updateCoeff(0);
		for (int i = 0; i <= p1.degree; i++) {
			result.coeff[i + 1] = new RealCoeff((double) p1.coeff[i].getCoeff() / (i + 1));
		}
		return result;
	}

	public int maxDegree(int a, int b) {
		if (a > b)
			return a;
		return b;
		// return (a < b) ? b : a;
	}

	public double evaluate(Polynomial p1, int x) {
		double result = 0;
		long power = 1;
		for (int i = 0; i <= p1.degree; i++) {
			result += (double) (power * p1.coeff[i].getCoeff());
			power *= x;
		}
		return result;
	}
}
