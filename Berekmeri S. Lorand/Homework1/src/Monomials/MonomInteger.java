package Monomials;

public class MonomInteger extends Monom {

	private Integer coefficient;

	public MonomInteger(Integer coefficient, int degree) {
		this.degree = degree;
		this.coefficient = coefficient;

	}

	public Integer getcoefficient() {
		return coefficient.intValue();

	}

	public MonomInteger getDerivataMonom() {
		MonomInteger mon = new MonomInteger(coefficient * degree, degree - 1);
		if (degree == 0)
			mon = new MonomInteger(0, 0);

		return mon;
	}

	public MonomFloat getIntegralaMonom() {
		MonomFloat mon = new MonomFloat((float) (coefficient.floatValue() / (degree + 1)), degree + 1);
		return mon;
	}

	public MonomInteger getMonom() {
		MonomInteger mon = new MonomInteger(coefficient, degree);
		return mon;
	}

	public void sumMonom(Monom mon) {
		coefficient += mon.getcoefficient().intValue();

	}

	@Override
	public void DiferentaMonom(Monom mon) {
		coefficient = coefficient - mon.getcoefficient().intValue();

	}

	@Override
	public MonomInteger getcoefficientMinus() {
		MonomInteger mon = new MonomInteger((-1) * coefficient.intValue(), degree);
		// coefficient= (-1)*coefficient.intValue();
		return mon;
	}

	@Override
	public MonomInteger multiplicationMonom(Monom mon) {

		int coef = coefficient * mon.getcoefficient().intValue();
		int gr = degree + mon.getdegree();
		MonomInteger m = new MonomInteger(coef, gr);
		return m;

	}

	@Override
	public MonomFloat getMonomReal() {
		MonomFloat mon = new MonomFloat((float) coefficient, degree);
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