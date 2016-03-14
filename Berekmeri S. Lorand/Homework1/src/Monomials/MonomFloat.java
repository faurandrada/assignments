package Monomials;

public class MonomFloat extends Monom {

	private Float coefficient;

	public MonomFloat(Float coefficient, int degree) {
		this.degree = degree;
		this.coefficient = coefficient;

	}

	public Float getcoefficient() {
		return coefficient;

	}

	public MonomFloat getDerivataMonom() {
		MonomFloat mon;
		if (degree == 0) {
			mon = new MonomFloat((float) 0, 0);
			return mon;
		} else
			mon = new MonomFloat(coefficient * degree, degree - 1);

		return mon;
	}

	public MonomFloat getIntegralaMonom() {
		MonomFloat mon = new MonomFloat(coefficient / (degree + 1), degree + 1);
		return mon;
	}

	public MonomFloat getMonom() {
		MonomFloat mon = new MonomFloat(coefficient, degree);
		return mon;
	}

	public void sumMonom(Monom mon) {
		coefficient = coefficient + mon.getcoefficient().floatValue();

	}

	@Override
	public void DiferentaMonom(Monom mon) {
		coefficient = coefficient - mon.getcoefficient().floatValue();

	}

	@Override
	public MonomFloat getcoefficientMinus() {
		MonomFloat mon = new MonomFloat((-1) * coefficient.floatValue(), degree);
		return mon;
	}

	@Override
	public MonomFloat multiplicationMonom(Monom mon) {

		float coef = coefficient * mon.getcoefficient().floatValue();
		int gr = degree + mon.getdegree();
		MonomFloat m = new MonomFloat(coef, gr);
		return m;

	}

	@Override
	public MonomFloat getMonomReal() {
		MonomFloat mon = new MonomFloat((float) coefficient.intValue(), degree);
		return mon;
	}

	public MonomFloat divisionMonom(Monom mon) {
		float coef = (float) coefficient.floatValue() / mon.getcoefficient().floatValue();
		int gr = degree - mon.getdegree();
		MonomFloat m = new MonomFloat(coef, gr);
		return m;
	}

	@Override
	public String toString() {
		String display = "";
		if (coefficient != 0)
			if (coefficient > 0 && coefficient != 1) {
				if (degree == 0)
					display += "+" + coefficient;
				else if (degree == 1)
					display += "+" + coefficient + "x";
				else
					display += "+" + coefficient + "x^" + degree;
			} else if (coefficient == 1) {
				if (degree == 0)
					display += "+" + coefficient;
				else if (degree == 1)
					display += "+" + "x";
				else
					display += "+" + "x^" + degree;

			} else {
				if (degree == 0)
					display += coefficient;
				else if (degree == 1)
					display += coefficient + "x";
				else
					display += coefficient + "x^" + degree;
			}

		return display;

	}

}
