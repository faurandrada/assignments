package models;

/**
 * 
 * This is the Coefficient class. Polynomials have coefficients as an array of
 * Coefficient objects.
 *
 */
public class Coefficient {

	private CoefficientType type;

	public Coefficient(CoefficientType type) {
		this.type = type;
	}

	public CoefficientType getType() {
		return type;
	}

	public void setType(CoefficientType type) {
		this.type = type;
	}

}
