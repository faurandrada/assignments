package models;

/**
 * 
 * this is the real coefficient class
 *
 */
public class CoefficientReal extends Coefficient{
	private double coefficient;
	
	public CoefficientReal(double coefficient) {
		super(CoefficientType.REAL);
		this.coefficient = coefficient;
	}
	
	public void setCoefficient(double coefficient){
		this.coefficient = coefficient;
	}
	public double getCoefficient(){
		return coefficient;
	}
}
