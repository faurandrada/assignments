package poly.model;

/**
 * 
 * @author Dia
 *
 *         The public class IntCoeff implementing Coefficient interface
 *         describes the integer coefficients of the polynomials.
 */
public class IntCoeff implements Coefficient {
	public int coeff;

	public IntCoeff(int coeff) {
		this.coeff = coeff;
	}

	@Override
	public char getSign() {
		if (this.coeff < 0)
			return '-';
		else
			return '+';
	}

	@Override
	public boolean isZero() {
		if (this.coeff == 0)
			return true;
		return false;
	}

	@Override
	public void updateCoeff(int input) {
		this.coeff = input;
	}

	@Override
	public void updateCoeff(double input) {
		this.coeff = (int) input;
	}

	@Override
	public int getCoeff() {
		return this.coeff;
	}

	@Override
	public double getRealCoeff() {
		return (double) this.coeff;
	}
}
