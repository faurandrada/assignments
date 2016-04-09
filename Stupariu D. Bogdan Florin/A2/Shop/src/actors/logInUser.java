package actors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class logInUser {

	private boolean loggedIn;
	List<String> list = new ArrayList<>();
	File file = new File("C:\\Users\\Stupariu\\workspace\\Shop\\src\\actors\\users.txt");

	public logInUser() {

	}

	public boolean checkLogInUser(String user, String password) throws FileNotFoundException {
		// TODO Auto-generated constructor stub

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			list.add(scanner.nextLine());
		}
		
		for (int i = 0; i < list.size(); i++) {
			String buffer = list.get(i);
			String[] output = buffer.split("(,)");
			if(user.contentEquals(output[0]) && password.contentEquals(output[1])){
				loggedIn=true;
			}
		}

		return loggedIn;
	}

}
