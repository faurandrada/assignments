package mathOperations;

import java.util.ArrayList;

import polynom.Term;

public class Division implements Operation {

	public Division(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		sortPoly(firstPoly, firstPoly.size());
		sortPoly(secondPoly, secondPoly.size());
		opPoly(firstPoly, firstPoly.size());
		opPoly(secondPoly, secondPoly.size());
		divide(firstPoly,secondPoly,resultPoly);

		
		sortPoly(resultPoly, resultPoly.size());
		opPoly(resultPoly, resultPoly.size());
	}

	private void divide(ArrayList<Term> firstPoly, ArrayList<Term> secondPoly, ArrayList<Term> resultPoly) {
		// TODO Auto-generated method stub
		ArrayList<Term> buffer = new ArrayList<Term>();
		buffer.addAll(firstPoly);

		int i = 0;
		while (i < buffer.size() && buffer.get(i).power >= secondPoly.get(0).power) {
			//while (buffer.get(i).power >= secondPoly.get(0).power) {

				Term term = new Term();
				term.coef = buffer.get(i).coef / secondPoly.get(0).coef;
				term.power = buffer.get(i).power - secondPoly.get(0).power;

				System.out.println(term.coef);
				System.out.println(term.power);

				resultPoly.add(term);

				ArrayList<Term> buffer_result = new ArrayList<Term>();
				buffer_result.add(term);
				ArrayList<Term> buffer_multip = new ArrayList<Term>();
				new Multiplication(buffer_result, secondPoly, buffer_multip);

				ArrayList<Term> buffer_diff = new ArrayList<Term>();
				new Difference(buffer, buffer_multip, buffer_diff);

				buffer.clear();

				buffer.addAll(buffer_diff);

				sortPoly(buffer, buffer.size());
				opPoly(buffer, buffer.size());

			//}
			i++;
		}
	}

}
