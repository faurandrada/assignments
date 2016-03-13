package pt.polynomials.models;
/**
 * The monom object with double coefficients
 * @author Chiti
 *
 */
public class DoubleMonom extends AbstractMonom<Double>{
  public DoubleMonom(int degree,double coefficient){
	  super.setDegree(degree);
	  super.setCoefficient(coefficient);
  }
}
