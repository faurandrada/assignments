package models;

public class RealCoeffTerm extends Term<Float>{

	private float coeff;
	public RealCoeffTerm(int power,float coeff){
		super(power);
		this.coeff=coeff;
	}
	@Override
	public Float getCoeff() {
		// TODO Auto-generated method stub
		return coeff;
	}
	public void setCoeff(float coeff) {
		this.coeff=coeff;
		
	}
	
   

}
