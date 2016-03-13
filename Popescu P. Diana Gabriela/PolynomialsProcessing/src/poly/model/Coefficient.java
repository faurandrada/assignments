package poly.model;

/**
 * 
 * @author Dia
 *
 *         The public interface Coefficient describes the capabilities of the
 *         two types of coefficients: integers or double. The methods are:
 *         getSign, isZero, updateCoeff(int input), updateCoeff(double input),
 *         getCoeff(), getRealCoeff().
 */
public interface Coefficient {

	public char getSign();

	public boolean isZero();

	public void updateCoeff(int input);

	public void updateCoeff(double input);

	public int getCoeff();

	public double getRealCoeff();
}
