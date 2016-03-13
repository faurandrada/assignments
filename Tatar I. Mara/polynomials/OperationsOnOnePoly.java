package polynomials;

import java.util.ArrayList;
import java.util.List;

public class OperationsOnOnePoly {
private List<Float> roots;
private Polynomial<IntCoeffTerm> p1;
	public OperationsOnOnePoly(Polynomial<IntCoeffTerm> p1) {
		this.p1=p1;
		roots=new ArrayList<Float>();
	}
	
	public Polynomial<IntCoeffTerm> integration(){
		return p1;
	}
	
	public Polynomial<IntCoeffTerm> derivation(){
		return p1;
	}
	
	public List<Float> roots(){
		return roots;
	}
		
	

}
