package models;

public final class Polynomial {

	private Coefficient[] coefficient;

	private int degree;

	public Polynomial(int deg) {
		coefficient = new Coefficient[deg + 1];
		this.degree = deg;
	}

	public Polynomial(Coefficient[] coeff, int d) {
		coefficient = new Coefficient[d + 1];
		for (int i = 0; i < d + 1; i++) {
			coefficient[i] = coeff[i];
		}
		degree = d;
	}
	public Polynomial(Coefficient coeff,int deg) {
		coefficient = new Coefficient[deg + 1];
		coefficient[deg]=coeff;
		this.degree = deg;
	}
	public int getDegree() {
		return degree;
	}

	public Coefficient[] getCoefficient() {
		return coefficient;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void setCoefficient(final Coefficient[] coefficient) {
		this.coefficient = coefficient;
	}
}
