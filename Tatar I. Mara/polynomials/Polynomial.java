package polynomials;

import java.util.HashSet;
import java.util.Set;


public class Polynomial<T extends Term<?>> {

	private Set<T> polynomial;
	
	
	public Polynomial(){
		polynomial = new HashSet<T>();
	  
	}
	
	
	public void addTermPoly(T term){
		polynomial.add(term);
		
	}
	public Set<T> getSetTerms(){
		return polynomial;
	}
	public void print(){
		//for (int i=0;i<polynomial.size();i++){
		//	if (i>0)
			//System.out.print("+"+polynomial.get(i).getCoeff()+"*X^"+polynomial.get(i).getPower());
			//else System.out.print(polynomial.get(i).getCoeff()+"*X^"+polynomial.get(i).getPower());
				for (T t1: polynomial){
					System.out.print("+"+t1.getCoeff()+"*X^"+t1.getPower());
				}
	}
	
}
