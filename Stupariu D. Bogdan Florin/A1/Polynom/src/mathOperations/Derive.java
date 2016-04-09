package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public class Derive implements Operation {

	public Derive(ArrayList<Term> poly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated constructor stub
		sortPoly(poly, poly.size());
		opPoly(poly, poly.size());
		deriveOp(poly, resultPoly, poly.size());
	}

	private void deriveOp(ArrayList<Term> temPoly, ArrayList<Term> resultPoly, int size) {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			Term tempTerm = new Term();
			tempTerm.setCoef(temPoly.get(i).getCoef() * temPoly.get(i).getPower());
			tempTerm.setPower(temPoly.get(i).getPower() - 1);
			if (tempTerm.getPower() < 0) {
				tempTerm.setPower(0);
			}
			resultPoly.add(tempTerm);
		}
	}

}
