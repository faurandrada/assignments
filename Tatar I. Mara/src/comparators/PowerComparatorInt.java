package comparators;

import java.util.Comparator;

import models.IntCoeffTerm;

public class PowerComparatorInt implements Comparator<IntCoeffTerm>{

	@Override
	public int compare(IntCoeffTerm o1, IntCoeffTerm o2) {
		return ((Integer)o1.getPower()).compareTo(o2.getPower());
	}

	
}
