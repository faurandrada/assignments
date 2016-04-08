package run;

import actors.User;
import gui_interface.GUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new GUI(Const.MAIN_HEIGHT,Const.MAIN_WIDTH);
		new User("admin","admin","admin","admin","admin",1);
		
		
	}

}
