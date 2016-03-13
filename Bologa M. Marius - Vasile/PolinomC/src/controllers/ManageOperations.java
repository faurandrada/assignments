package controllers;

import models.Operation;
import models.Polynomial;
import utilities.Utilities;
import view.View;

public class ManageOperations {
	public void manageOperation(View view, Polynomial a, Polynomial b) {
		switch (view.getOperations().getSelectedIndex()) {
		case 0:
			view.setResult(Utilities.toString(Operation.addition(a, b)));
			break;
		case 1:
			view.setResult(Utilities.toString(Operation.subtract(a, b)));
			break;
		case 2:
			view.setResult(Utilities.toString(Operation.multiply(a, b)));
			break;
		case 3:
			view.setResult(Utilities.toString(Operation.derivation(a)));
			break;
		case 4:
			view.setResult(Utilities.toString(Operation.derivation(b)));
			break;
		case 5:
			Polynomial c = new Polynomial(a.getDegree() + 1);
			c = Operation.integration(a);
			view.setResult(Utilities.toString(c));
			break;
		case 6:
			Polynomial c1 = new Polynomial(b.getDegree() + 1);
			c1 = Operation.integration(b);
			view.setResult(Utilities.toString(c1));
			break;
		case 7:
			int scalar = view.getTheScalar();
			view.setResult(String.valueOf(Operation.evaluate(a, scalar)));
			break;
		case 8:
			int scalar1 = view.getTheScalar();
			view.setResult(String.valueOf(Operation.evaluate(b, scalar1)));
			break;
		case 9:
			int scalar2 = view.getTheScalar();
			view.setResult(Utilities.toString(Operation.multiplyByScalar(a, scalar2)));
			break;
		case 10:
			int scalar3 = view.getTheScalar();
			view.setResult(Utilities.toString(Operation.multiplyByScalar(b, scalar3)));
			break;

		}

	}

}
