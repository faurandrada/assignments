package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public interface Operation {

	default void opPoly(ArrayList<Term> resultPoly, int size) {
		// TODO Auto-generated method stub
		if (size != 0) {
			for (int i = 0; i < size - 1; i++) {
				if (resultPoly.get(i).getPower() == resultPoly.get(i + 1).getPower()) {
					resultPoly.get(i + 1).setCoef(resultPoly.get(i + 1).getCoef() + resultPoly.get(i).getCoef());
				}
			}
			for (int i = size - 1; i > 0; i--) {
				if (resultPoly.get(i).getPower() == resultPoly.get(i - 1).getPower()) {
					resultPoly.remove(i - 1);
				}
			}
		}
	}

	default void mergePoly(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated method stub
		merge(firstPoly, resultPoly);
		merge(secondPoly, resultPoly);
	}

	default void merge(ArrayList<Term> poly, ArrayList<Term> resultPoly) {
		for (int i = 0; i < poly.size(); i++) {
			Term tempTerm = new Term();
			tempTerm.setCoef(poly.get(i).getCoef());
			tempTerm.setPower(poly.get(i).getPower());
			resultPoly.add(tempTerm);
		}
	}

	default void sortPoly(ArrayList<Term> resultPoly, int size) {
		// TODO Auto-generated method stub
		int i, j, minIndex;
		for (i = 0; i < size - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < size; j++) {
				if (resultPoly.get(j).getPower() > resultPoly.get(minIndex).getPower()) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				Term tempTerm = new Term();
				tempTerm.setCoef(resultPoly.get(i).getCoef());
				tempTerm.setPower(resultPoly.get(i).getPower());
				resultPoly.get(i).setPower(resultPoly.get(minIndex).getPower());
				resultPoly.get(i).setCoef(resultPoly.get(minIndex).getCoef());
				resultPoly.get(minIndex).setPower(tempTerm.getPower());
				resultPoly.get(minIndex).setCoef(tempTerm.getCoef());

			}

		}
	}
}
