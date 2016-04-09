package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public class Integral implements Operation {

	public Integral(ArrayList<Term> poly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated constructor
		sortPoly(poly, poly.size());
		opPoly(poly, poly.size());
		integralOp(poly, resultPoly, poly.size());

	}

	private void integralOp(ArrayList<Term> temPoly, ArrayList<Term> resultPoly, int size) {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			Term tempTerm = new Term();
			tempTerm.setPower(temPoly.get(i).getPower() + 1);
			tempTerm.setCoef(temPoly.get(i).getCoef() / tempTerm.getPower());
			resultPoly.add(tempTerm);
		}
	}

}
