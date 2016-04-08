package mathOperations;

import java.util.ArrayList;

import polynom.Polynom;
import polynom.Term;

public class Sum implements Operation{

	Polynom polynom;

	public Sum(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {

		mergePoly(firstPoly, secondPoly, resultPoly);
		sortPoly(resultPoly, resultPoly.size());
		opPoly(resultPoly, resultPoly.size());

	}
	

	



	

}