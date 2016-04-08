package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public class Multiplication implements Operation {

	public Multiplication(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated constructor stub
		multiply(firstPoly, secondPoly, resultPoly);
		sortPoly(resultPoly, resultPoly.size());
		opPoly(resultPoly, resultPoly.size());
	}

	private void multiply(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated method stub
		for (int i = 0; i < firstPoly.size(); i++) {
			for (int j = 0; j < secondPoly.size(); j++) {
				Term tempTerm = new Term();
				tempTerm.setCoef(firstPoly.get(i).getCoef() * secondPoly.get(j).getCoef());
				tempTerm.setPower(firstPoly.get(i).getPower() + secondPoly.get(j).getPower());
				resultPoly.add(tempTerm);
			}
		}
	}

}
