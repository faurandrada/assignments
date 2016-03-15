package Model;

public class Polynomial {

	private int degree;
	private int[] intCoefficients;
	private double[] doubleCoefficients;

/*
 *The two constructors make sure we can instantiate polynomials with both integer and double coefficients
 */
	public Polynomial(int degree, int[] coefficients) {
		this.degree = degree;
		this.intCoefficients = coefficients;
	
		doubleCoefficients = new double[degree + 1];
		for (int i = 0; i < intCoefficients.length; i++) {
			doubleCoefficients[i] = (double)intCoefficients[i];
		}
	}

	public Polynomial(int degree, double[] coefficients) {
		this.degree = degree;
		this.doubleCoefficients = coefficients;

		intCoefficients = new int[degree + 1];
		for (int i = 0; i < doubleCoefficients.length; i++) {
			intCoefficients[i] = (int)doubleCoefficients[i];
		}
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int[] getIntCoefficients() {
		return intCoefficients;
	}

	public double[] getDoubleCoefficients() {
		return doubleCoefficients;
	}

	public void setIntCoefficients(int[] coefficients) {
		this.intCoefficients = coefficients;
	}

	public void setDoubleCoefficients(double[] coefficients) {
		this.doubleCoefficients = coefficients;
	}
}
