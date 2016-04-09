package poly;

public class Monomial {
	private int power;
	private int intCoeff;
	private double doubleCoeff;

	public void setPower(int x) {
		this.power = x;
	}

	public int getPower() {
		return power;
	}

	public void setIntCoeff(int x) {
		this.intCoeff = x;
	}

	public int getIntCoeff() {
		return intCoeff;
	}

	public void setDoubleCoeff(double x) {
		this.doubleCoeff = x;
	}

	public double getDoubleCoeff() {
		return doubleCoeff;
	}
}
