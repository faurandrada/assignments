package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public class Difference implements Operation {

	public Difference(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		changeSign(secondPoly, secondPoly.size());
		mergePoly(firstPoly, secondPoly, resultPoly);
		sortPoly(resultPoly, resultPoly.size());
		opPoly(resultPoly, resultPoly.size());
	}

	private void changeSign(ArrayList<Term> secondPoly, int size) {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			secondPoly.get(i).setCoef(secondPoly.get(i).getCoef() * -1);
		}
	}

}
