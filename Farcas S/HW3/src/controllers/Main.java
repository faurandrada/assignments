package controllers;

import views.*;
import models.*;

import java.util.Date;
import java.util.Random;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	new MainViewController(new MainView("Queues"));
		    }
		});
	}

}
