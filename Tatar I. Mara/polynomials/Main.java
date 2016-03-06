package polynomials;

public class Main {

	public static void main(String[] args) {
		
		IntCoeffTerm t1=new IntCoeffTerm(1, 2);
		IntCoeffTerm t2=new IntCoeffTerm(2, 2);
	//	IntCoeffTerm t3=new IntCoeffTerm(0, 2);
		
		IntCoeffTerm t4=new IntCoeffTerm(3, 2);
		IntCoeffTerm t5=new IntCoeffTerm(2, 2);
	//	IntCoeffTerm t6=new IntCoeffTerm(1, 2);
				
				
		Polynomial<IntCoeffTerm> t=new Polynomial<IntCoeffTerm>();
		t.addTermPoly(t1);
		t.addTermPoly(t2);
	//	t.addTermPoly(t3);
		
		
	Polynomial<IntCoeffTerm> z=new Polynomial<IntCoeffTerm>();
	z.addTermPoly(t4);
	z.addTermPoly(t5);
//	z.addTermPoly(t6);
	
	OperationsOnTwoPoly op=new OperationsOnTwoPoly(t, z);
	OperationsOnTwoPoly op1=new OperationsOnTwoPoly(t, z);
//	op.addition().print();
	System.out.println("\n");
//	op1.substraction().print();
	op.multiplication().print();
	
		

	}

}
