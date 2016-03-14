package Monomials;

public abstract class Monom implements Comparable<Monom> {

	protected int degree;

	/* Functia getdegree returneaza degreeul monomului */
	public int getdegree() {
		return degree;
	}

	/*
	 * Functie care va compana monoamele in functie de degree, in ordine
	 * descrescatoare
	 */
	public int compareTo(Monom mon) {
		return mon.degree - degree;
	}

	/*
	 * Metoda getcoefficient() returneaza coefficientului monomului cu care apelam
	 */
	public abstract Number getcoefficient();

	/* Metoda getDerivataMonom() returneaza derivata unui Monom */
	public abstract Monom getDerivataMonom();

	/* Metoda getIntegralaMonom() returneaza integrala monomului apelat */
	public abstract MonomFloat getIntegralaMonom();

	/* Metoda getMonom() returneaza un monom */
	public abstract Monom getMonom();

	/* Metoda sumMonom(Monom mon) calculeaza sum a doua monoame */
	public abstract void sumMonom(Monom mon);

	/*
	 * Metoda DiferentaMonom(Monom mon) calculeaza diferenta dintre doua monoame
	 */
	public abstract void DiferentaMonom(Monom mon);

	/* Metoda getcoefficientMinus() transforma un monom in opusul sau */
	public abstract Monom getcoefficientMinus();

	/*
	 * Metoda multiplicationMonom(Monom mon) returneaza resultul inmultirii a doua
	 * monoame
	 */
	public abstract Monom multiplicationMonom(Monom mon);

	/* Metoda getMonomReal() transforma un monom intreg in real */
	public abstract MonomFloat getMonomReal();

	/*
	 * Metoda divisionMonom(Monom mon) returneaza resultul impartirii dintre
	 * doua monoame
	 */
	public abstract MonomFloat divisionMonom(Monom mon);

	/* Metoda toString afiseaza monomul intr-un mod mai aranjat */
	public abstract String toString();

}
