package models;

/**
 * 
 * This is the Coefficient class. Polynomials have coefficients as an array of
 * Coefficient objects.
 *
 */
public class Coefficient {

	private String type;

	public Coefficient(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
