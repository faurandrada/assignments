package models;

public class RealCoefficient extends Coefficient {
	private double coefficient;

	public RealCoefficient(double coefficient) {
		super(TypeOfCoefficient.real);
		this.coefficient = coefficient;
	}

	public void setCoefficient(double d) {
		this.coefficient = d;
	}

	public double getCoefficient() {
		return coefficient;
	}
}
