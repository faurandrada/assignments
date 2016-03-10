package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Polynomial<T extends Term<?>> {

	private List<T> polynomial;
	
	public Polynomial(){
		polynomial = new ArrayList<T>();
		
	}
	
	
	public void addTermPoly(T term){
		polynomial.add(term);
		
	}
	public List<T> getTerms(){
		return polynomial;
	}
	
	public void print(){
	;				for (T t1: polynomial){
					System.out.print("+"+t1.getCoeff()+"*X^"+t1.getPower());
				}
	}
	public void sortPoly(Comparator<T> comparable){
		List<T> temp=new ArrayList<T>();
		temp.addAll(polynomial);
		Collections.sort(temp,comparable);
		polynomial=new ArrayList<T>(temp);

	}
	
	
	
}

	

