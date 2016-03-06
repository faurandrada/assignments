package polynomials;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OperationsOnTwoPoly {
	private Polynomial<IntCoeffTerm> p1;
	private Polynomial<IntCoeffTerm> p2;
	private Polynomial<IntCoeffTerm> p3;
	private Polynomial<IntCoeffTerm> p4;
	private Polynomial<IntCoeffTerm> p5;
	private List<IntCoeffTerm> helper;
	private boolean ok;
	private boolean ok1;

	
	public OperationsOnTwoPoly(Polynomial<IntCoeffTerm> p1,Polynomial<IntCoeffTerm> p2) {
		this.p1=p1;
		this.p2=p2;
		p3=new Polynomial<IntCoeffTerm>();
		p4=new Polynomial<IntCoeffTerm>();
		p5=new Polynomial<IntCoeffTerm>();
		ok=false;
		ok1=false;
	    helper=new ArrayList<IntCoeffTerm>();}
	
	public Polynomial<IntCoeffTerm> addition(){
		for (IntCoeffTerm t1 : p1.getSetTerms()){
			for (IntCoeffTerm t2 : p2.getSetTerms()){
				if (t1.getPower()==t2.getPower()){
					IntCoeffTerm term=new IntCoeffTerm(t1.getPower(), t1.getCoeff()+t2.getCoeff());
					p3.addTermPoly(term);
					ok=true;
				}
			}
			if (ok==false) 
				p3.addTermPoly(t1);
			    ok=false;
		}
		for (IntCoeffTerm t1 : p2.getSetTerms()){
			for (IntCoeffTerm t2 : p3.getSetTerms()){
				if (t1.getPower()==t2.getPower()){
					ok=true;
				}
				}
			if (ok==false) p3.addTermPoly(t1);
			}
		return p3;
		
	}
	
	public Polynomial<IntCoeffTerm> substraction(){
		for (IntCoeffTerm t1 : p1.getSetTerms()){
			for (IntCoeffTerm t2 : p2.getSetTerms()){
				if (t1.getPower()==t2.getPower()){
					IntCoeffTerm term2=new IntCoeffTerm(t1.getPower(), t1.getCoeff()-t2.getCoeff());
					p4.addTermPoly(term2);
					ok1=true;
				}
			}
			if (ok1==false) 
				p4.addTermPoly(t1);
			    ok1=false;
		}
		for (IntCoeffTerm t1 : p2.getSetTerms()){
			for (IntCoeffTerm t2 : p3.getSetTerms())
				if (t1.getPower()==t2.getPower()){
					ok1=true;
				}
			if (ok1==false){ 
				IntCoeffTerm term3=new IntCoeffTerm(t1.getPower(),-t1.getCoeff());
				p4.addTermPoly(term3);
			}
			}
		return p4;
		
	}
	public Polynomial<IntCoeffTerm> multiplication(){
		for (IntCoeffTerm t1 : p1.getSetTerms())
			for (IntCoeffTerm t2 : p2.getSetTerms()){
				IntCoeffTerm mul=new IntCoeffTerm(t1.getPower()+t2.getPower(),t1.getCoeff()*t2.getCoeff());
			    p5.addTermPoly(mul);
				helper.add(mul);
			}

		return p5;
			
		}
	/*public Polynomial<? extends Term<?>> division(){
		for (IntCoeffTerm t1 : p1.getSetTerms()){
			for (IntCoeffTerm t2 : p2.getSetTerms()){
				IntCoeffTerm mul=new IntCoeffTerm(t1.getPower()+t2.getPower(),t1.getCoeff()+t2.getCoeff());
				p5.addTermPoly(mul);
				helper.add(mul);
			}
		}
		for (IntCoeffTerm t1 : p5.getSetTerms()){
			for (IntCoeffTerm t2: helper){
				if (t1.getPower()==t2.getPower()){
					t1.setCoeff(t1.getCoeff()+1);
				}
			
			}     
	//	for (IntCoeffTerm t1:p5.getSetTerms()){
			t1.setCoeff(t1.getCoeff()-1);
//		}
		return p5;
		}}
	
			
	
	
*/
}
