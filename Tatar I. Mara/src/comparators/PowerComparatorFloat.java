package comparators;

import java.util.Comparator;

import models.RealCoeffTerm;

public class PowerComparatorFloat implements Comparator<RealCoeffTerm>{

	@Override
	public int compare(RealCoeffTerm o1, RealCoeffTerm o2) {
		
		return ((Integer)o1.getPower()).compareTo(o2.getPower());
	}

	
}

