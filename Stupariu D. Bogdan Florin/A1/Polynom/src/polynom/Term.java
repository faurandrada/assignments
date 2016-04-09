package polynom;

public class Term {
	public double coef;
	public int power;
	
	public void setCoef(double coef){
		this.coef = coef;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public double getCoef(){
		return this.coef;
	}
	
	public int getPower(){
		return this.power;
	}
}
