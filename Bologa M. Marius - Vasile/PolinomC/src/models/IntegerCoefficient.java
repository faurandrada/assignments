package models;


public class IntegerCoefficient extends Coefficient {
	private int coefficient;
	
	 	public IntegerCoefficient(int coefficient) {
	 		super(TypeOfCoefficient.integer);
	 		this.coefficient = coefficient;
	 	}
	 	
	 	
	
	 	public void setCoefficient(int coefficient) {
	 		this.coefficient = coefficient;
	 	}
	 
	 	public int getCoefficient() {
	 		return coefficient;
	 	}

}
