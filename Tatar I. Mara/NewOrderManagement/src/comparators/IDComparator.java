package comparators;

import java.io.Serializable;
import java.util.Comparator;

import models.Entity;

public class IDComparator implements Comparator<Entity>,Serializable{

	@Override
	public int compare(Entity arg0, Entity arg1) {
		int comp = 0;
		if (arg0.getID() < arg1.getID()) {
			comp = -1;
		} else if (arg0.getID() == arg1.getID()) {
			comp = 0;
		} else if (arg0.getID() > arg1.getID()) {
			comp = 1;
		}
		return comp;
	}

}
