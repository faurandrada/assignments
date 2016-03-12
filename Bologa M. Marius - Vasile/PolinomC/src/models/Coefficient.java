package models;

public class Coefficient {

	private TypeOfCoefficient type;

	public Coefficient(TypeOfCoefficient type) {
		this.type = type;
	}

	public TypeOfCoefficient getType() {
		return type;
	}

	public void setType(TypeOfCoefficient type) {
		this.type = type;
	}

}
