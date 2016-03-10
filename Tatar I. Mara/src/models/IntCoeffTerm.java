package models;

public class IntCoeffTerm extends Term<Integer> {

	private int coeff;
	
	public IntCoeffTerm(int power,int coeff){
		super(power);
		this.coeff=coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	@Override
	public Integer getCoeff() {
		// TODO Auto-generated method stub
		return coeff;
	}

	
	
	
	
 
}
