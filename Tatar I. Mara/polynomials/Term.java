package polynomials;

public abstract class Term<Q> {

	private int power;
	
	public Term(int power){
		this.power=power;
	}
	
	public abstract Q getCoeff();
	public int getPower(){
		return power;
	}
}
