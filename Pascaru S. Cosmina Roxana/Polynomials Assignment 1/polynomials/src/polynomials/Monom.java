package polynomials;

/**
 * Represents an abstract interpretation of the monoms from a polynomial
 * 
 * @author Cosmina
 *
 */

public class Monom {

	private int power;
	private int coeff;

	public Monom(int coeff, int power) {
		this.power = power;
		this.coeff = coeff;
	}

	public int getPower() {
		return power;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public String toString() {
		if (coeff == 0 && power == 0) {
			return String.format("%s", "");
		} else if (coeff == 0 && power != 0) {
			return String.format("%s", "");
		} else if (coeff != 0 && power == 0) {
			if (coeff > 0)
				return String.format(" +%d", coeff);
			else
				return String.format(" %d", coeff);
		} else if (coeff == 1 && power == 1) {
			return String.format(" +X", "");
		} else if (coeff == -1 && power == 1) {
			return String.format(" -X", "");
		} else if (power == 1 && coeff != 1) {
			if (coeff > 0)
				return String.format(" +%dX", coeff);
			else
				return String.format(" %dX", coeff);
		} else if (coeff == 1) {
			return String.format(" +X^%d", power);
		} else if (coeff == -1) {
			return String.format(" -X%^d", power);
		} else {
			if (coeff > 0)
				return String.format(" +%dX^%d", coeff, power);
			else
				return String.format(" %dX^%d", coeff, power);
		}
	}

}
