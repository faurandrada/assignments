package poly;

public class Operations {
	private Polynomial p;
	private Monomial mon1, mon2, mon;
	private int i, degree, deg;

	public Polynomial addition(Polynomial p1, Polynomial p2) {
		p = new Polynomial();
		mon1 = null;
		mon2 = null;
		if (p1.getDegree() >= p2.getDegree()) {
			degree = p1.getDegree();
			deg = p2.getDegree();
			p.setDegree(degree);
			for (i = degree; i >= 0; i--) {
				mon = new Monomial();
				mon1 = p1.getElement(degree - i);
				if (i > deg) {
					mon.setPower(i);
					mon.setIntCoeff(mon1.getIntCoeff());
				} else {
					for (int j = 0; j <= deg; j++) {
						mon2 = p2.getElement(j);
						if (mon2.getPower() == mon1.getPower()) {
							mon.setPower(mon1.getPower());
							mon.setIntCoeff(mon1.getIntCoeff() + mon2.getIntCoeff());
						}
					}
				}
				p.addToPoly(mon);
			}
		} else {
			degree = p2.getDegree();
			deg = p1.getDegree();
			p.setDegree(degree);
			for (i = degree; i >= 0; i--) {
				mon = new Monomial();
				mon2 = p2.getElement(degree - i);
				if (i > deg) {
					mon.setPower(i);
					mon.setIntCoeff(mon2.getIntCoeff());
				} else {
					for (int j = 0; j <= deg; j++) {
						mon1 = p1.getElement(j);
						if (mon1.getPower() == mon2.getPower()) {
							mon.setPower(mon2.getPower());
							mon.setIntCoeff(mon1.getIntCoeff() + mon2.getIntCoeff());
						}
					}
				}
				p.addToPoly(mon);
			}
		}
		return p;
	}

	public Polynomial subtraction(Polynomial p1, Polynomial p2) {
		p = new Polynomial();
		mon1 = null;
		mon2 = null;
		if (p1.getDegree() >= p2.getDegree()) {
			degree = p1.getDegree();
			deg = p2.getDegree();
			p.setDegree(degree);
			for (i = degree; i >= 0; i--) {
				mon = new Monomial();
				mon1 = p1.getElement(degree - i);
				if (i > deg) {
					mon.setPower(i);
					mon.setIntCoeff(mon1.getIntCoeff());
				} else {
					for (int j = 0; j <= deg; j++) {
						mon2 = p2.getElement(j);
						if (mon2.getPower() == mon1.getPower()) {
							mon.setPower(mon1.getPower());
							mon.setIntCoeff(mon1.getIntCoeff() - mon2.getIntCoeff());
						}
					}
				}
				p.addToPoly(mon);
			}
		} else {
			degree = p2.getDegree();
			deg = p1.getDegree();
			p.setDegree(degree);
			for (i = degree; i >= 0; i--) {
				mon = new Monomial();
				mon2 = p2.getElement(degree - i);
				if (i > deg) {
					mon.setPower(i);
					mon.setIntCoeff(mon2.getIntCoeff());
				} else {
					for (int j = 0; j <= deg; j++) {
						mon1 = p1.getElement(j);
						if (mon1.getPower() == mon2.getPower()) {
							mon.setPower(mon2.getPower());
							mon.setIntCoeff(mon1.getIntCoeff() - mon2.getIntCoeff());
						}
					}
				}
				p.addToPoly(mon);
			}
		}
		return p;
	}

	public Polynomial derivate(Polynomial p1) {
		p = new Polynomial();
		degree = p1.getDegree();
		p.setDegree(degree-1);
		mon1 = null;
		for (int i = degree; i >= 0; i--) {
			mon = new Monomial();
			if (i != degree) {
				mon1 = p1.getElement(i);
				mon.setIntCoeff(mon1.getIntCoeff() * mon1.getPower());
				mon.setPower(mon1.getPower() - 1);
				p.addToPoly(mon);
			}
		}
		return p;
	}

	public Polynomial integrate(Polynomial p1) {
		double coeff, pow;
		p = new Polynomial();
		degree = p1.getDegree();
		p.setDegree(degree+1);
		mon1 = null;
		for (int i = degree; i >= 0; i--) {
			mon = new Monomial();
			mon1 = p1.getElement(i);
			coeff = (double) mon1.getIntCoeff();
			pow = (double) mon1.getPower();
			if (pow != 0) {
				mon.setDoubleCoeff(coeff / (pow+1));
			} else {
				mon.setDoubleCoeff(coeff);
			}
			mon.setPower(mon1.getPower() + 1);
			p.addToPoly(mon);
		}
		return p;
	}
	
	public Polynomial multiply(Polynomial p1, Polynomial p2){
		p=new Polynomial();
		int pow1, pow2, coeff1, coeff2;
		Polynomial partial=new Polynomial();
		degree=p1.getDegree()+p2.getDegree();
		mon1=null;
		mon2=null;
		for(int i=0;i<p1.getSize();i++){
			mon1=p1.getElement(i);
			pow1=mon1.getPower();
			coeff1=mon1.getIntCoeff();
			for(int j=0;j<p2.getSize();j++){
				mon2=p2.getElement(j);
				pow2=mon2.getPower();
				coeff2=mon2.getIntCoeff();
				mon=new Monomial();
				mon.setPower(pow1+pow2);
				mon.setIntCoeff(coeff1*coeff2);
				partial.addToPoly(mon);
			}
		}
		for(int i=0;i<partial.getSize();i++){
			mon1=partial.getElement(i);
			pow1=mon1.getPower();
			coeff1=mon1.getIntCoeff();
			for(int j=i+1;j<partial.getSize();j++){
				mon2=partial.getElement(j);
				pow2=mon2.getPower();
				coeff2=mon2.getIntCoeff();
				if(pow1==pow2){
					coeff1+=coeff2;
					partial.removeElem(j);
				}
			}
			mon=new Monomial();
			mon.setIntCoeff(coeff1);
			mon.setPower(pow1);
			p.addToPoly(mon);
		}
		return p;
	}
	
	public Polynomial divide(Polynomial p1, Polynomial p2){
		int deg1, deg2;
		double coeff1, coeff2;
		Polynomial partial = new Polynomial();
		Polynomial x = new Polynomial();
		Monomial mo;
		p=new Polynomial();
		deg1=p1.getDegree();
		deg2=p2.getDegree();
		mon=new Monomial();
		mon1=p1.getElement(deg1);
		mon2=p2.getElement(deg2);
		coeff1=(double) mon1.getIntCoeff();
		coeff2=(double) mon2.getIntCoeff();
		if((mon1.getPower()>mon2.getPower()) || (mon1.getPower()==mon2.getPower() && coeff1>=coeff2)){
			mon.setDoubleCoeff(coeff1/coeff2);
			mon.setPower(mon1.getPower()-mon2.getPower());
			p.addToPoly(mon);
			for(int i=0; i<=deg2+mon.getPower();i++){
				mo=new Monomial();
				if(i<=deg2){
					mon2=p2.getElement(i);
					mo.setDoubleCoeff(mon.getDoubleCoeff()*coeff1);
					mo.setPower(mon.getPower()+mon2.getPower());
				}
				else{
					mo.setDoubleCoeff(0);
					mo.setPower(deg2+mon.getPower()-i);
				}
				partial.addToPoly(mo);
			}
			for(i=0;i<partial.getSize();i++){
				if(i<=deg2)
					x.addToPoly(partial.getElement(i));
				else{
					mo=new Monomial();
					mo.setDoubleCoeff(0);
					mo.setPower(i);
					x.addToPoly(mo);
				}
			}
			if(x.getDegree()>=partial.getDegree()){
				partial=subtraction(x, partial);
				p=divide(partial, p2);
			}
		}
		else{
			mon.setDoubleCoeff(0);
			mon.setPower(0);
		}
		return p;
	}
}
