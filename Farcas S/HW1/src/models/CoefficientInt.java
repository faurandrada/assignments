package models;

/**
 * 
 * This is the integer coefficient class
 *
 */
public class CoefficientInt extends Coefficient {
	private int coefficient;

	public CoefficientInt(int coefficient) {
		super(CoefficientType.INT);
		this.coefficient = coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getCoefficient() {
		return coefficient;
	}
}
