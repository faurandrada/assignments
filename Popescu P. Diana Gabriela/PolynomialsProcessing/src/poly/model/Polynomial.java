package poly.model;

/**
 * 
 * @author Dia
 *
 *         The public class Polynomial describes the structure of a polynomial,
 *         i.e. it has a degree and an array of coefficients. It also contains
 *         the method normalizePoly, which says that if a n-degree polynomial
 *         has the n-coefficient zero, then it is actually of n-1 degree.
 */
public class Polynomial {

	public int degree;
	public Coefficient[] coeff;

	public Polynomial(int degree) {
		this.degree = degree;
		System.out.println("ok degree p");
		coeff = new Coefficient[degree + 1];
		System.out.println("ok coeff arrList");
		for (int i = degree - 1; i >= 0; i--) { // initialize 0->n coeff
			coeff[i] = new IntCoeff(0);
			System.out.printf("ok coeff" + i);
		}
		coeff[degree] = new IntCoeff(1);
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void normalizePoly() {
		while (coeff[this.degree + 1].isZero() && this.degree > 0) {
			this.degree--;
		}
	}
}
