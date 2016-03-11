package pt.polynomials.models;
/**
 * 
 * @author Chiti
 *The class implementa an astract monom
 * @param <T>
 * the type of the coefficient choose integer,double
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractMonom<T> implements Comparable{
  private int degree;
  private T coefficient;

public int getDegree() {
	return degree;
}
public void setDegree(int degree) {
	this.degree = degree;
}
public T getCoefficient() {
	return coefficient;
}
public void setCoefficient(T coefficient) {
	this.coefficient = coefficient;
}
/**
 * gets the reprezentation of the monom
 * @return String
 */
@Override
public String toString() {
	if (degree==0){
		return " "+ coefficient;}
	else return "("+coefficient+"*X^"+degree+")";
}
/**
 * compare on the degrees
 * @return int
 */
@Override
public int compareTo(Object o) {
	int comparedegree=((AbstractMonom)o).getDegree();
	return this.degree-comparedegree;
}
}
