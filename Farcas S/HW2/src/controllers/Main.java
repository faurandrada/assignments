package controllers;

import views.*;
import models.*;

public class Main {
	
	public static void main(String[] args){
		new LogInController(new LogIn("Main Menu"), false);
	}
	
}
