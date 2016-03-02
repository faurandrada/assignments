package models;

/**
 * models the Polynomial in general
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
	private int degree;
	private Coefficient[] coefficients;

	public Polynomial(Coefficient[] coefficients) {
		this.degree = coefficients.length - 1;
		this.coefficients = coefficients;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return this.degree;
	}

	public void setCoefficients(Coefficient[] coefficients) {
		this.coefficients = coefficients;
	}

	public Coefficient[] getCoefficients() {
		return this.coefficients;
	}

	public String toString() {
		return PolynomialToString.getStringRepresentation(this);
	}

}
