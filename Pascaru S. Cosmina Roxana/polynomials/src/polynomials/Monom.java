package polynomials;

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
	
	public void setCoeff(int coeff){
		this.coeff = coeff;
	}

	public String toString() {
		return String.format("%dX^%d", coeff, power);
	}

}
