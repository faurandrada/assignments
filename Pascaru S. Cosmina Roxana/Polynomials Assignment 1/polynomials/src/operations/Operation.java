package operations;

import polynomials.Polynomial;

/**
 * Contains all the methods that the classes MonoOperation and BiOperation will
 * overwrite
 * 
 * @author Cosmina
 *
 */
public interface Operation {

	public abstract Polynomial add(Polynomial p1, Polynomial p2);

	public abstract Polynomial subtract(Polynomial p1, Polynomial p2);

	public abstract Polynomial multiply(Polynomial p1, Polynomial p2);

	public abstract Polynomial differentiate(Polynomial p1);

	public abstract Polynomial integrate(Polynomial p1);

	public abstract int evaluatePolyAtPoint(int point, Polynomial p1);

}
