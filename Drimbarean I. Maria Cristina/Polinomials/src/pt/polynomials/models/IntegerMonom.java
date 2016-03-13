package pt.polynomials.models;
/**
 * The monom object with integer coefficients
 * @author Chiti
 *
 */
public class IntegerMonom extends AbstractMonom<Integer>{
  public IntegerMonom(int degree,int coefficient){
	  super.setCoefficient(coefficient);
	  super.setDegree(degree);
  }
}
