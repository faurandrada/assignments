package Polynomials;

public class Polynomial {
	private int[] coefficient;
	private int degree;

	// Initializes the coefficient's vector.
	public Polynomial() {
		coefficient = new int[200];
		degree = 200;
	}

	// Initializes a polynomial given it's max degree.
	public Polynomial(int deg) {
		coefficient = new int[deg];
		degree = deg;
	}

	// Returns the degree of the polynomial.
	public int getDegree() {
		return degree;
	}

	// Sets the coefficient of a term,given it's arguments.
	public void setCoefficient(int index, int value) {
		coefficient[index] = value;
	}

	// Returns the coefficient of a term,given it's arguments.
	public int getCoefficient(int index) {
		return coefficient[index];
	}

	// Returns the degree of the polynomial.
	public int getGrad() {
		for (int i = 99; i >= 0; i--) {
			if (coefficient[i] != 0) {
				return i;
			}
		}
		return 0;
	}

	// Sets all the coefficients of the polynomial to 0.
	public void setZero() {
		for (int i = 99; i <= 0; i--) {
			coefficient[i] = 0;
		}
	}
}
