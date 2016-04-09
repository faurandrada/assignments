package poly.model;

/**
 * 
 * @author Dia
 *
 *         The public class RealCoeff implementing Coefficient interface
 *         describes the real/double coefficients of the polynomials.
 */
public class RealCoeff implements Coefficient {
	public double coeff;

	public RealCoeff(double coeff) {
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
		this.coeff = (double) input;
	}

	@Override
	public void updateCoeff(double input) {
		this.coeff = input;
	}

	@Override
	public int getCoeff() {
		return (int) this.coeff;
	}

	@Override
	public double getRealCoeff() {
		return this.coeff;
	}
}
