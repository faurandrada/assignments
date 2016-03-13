package controllers;

import models.Polynomial;
import view.View;

public class MainController {
	public static void main(String[] args) {
		Polynomial poli = new Polynomial(0);
		View view = new View(poli);
		new Controller(view);
		view.setVisible(true);
	}
}
