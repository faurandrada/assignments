package main;

import controller.PolyController;
import model.PolyOps;
import view.MenuWithOperationsGUI;


public class Main {

	public static void main(String[] args){
		
		PolyOps model = new PolyOps();
		MenuWithOperationsGUI mainWindowTest = new MenuWithOperationsGUI("Polynomials");
		new PolyController(model, mainWindowTest);
		
		
		
	}	
}
