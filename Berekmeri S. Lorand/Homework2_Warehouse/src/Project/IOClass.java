package Project;

import java.io.*;

/* The class IOClass contains static methods that 
*  allow to write and read a serializable object from and to a file
*/
public class IOClass {

	public static void saveInfo(Object oo, String dest) {
		FileOutputStream outputFile = null;
		try {

			System.out.println(dest);
			outputFile = new FileOutputStream(dest);
		} catch (FileNotFoundException e2) {
			System.out.println("Error save1");
		}
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(outputFile);

			outputStream.writeObject(oo);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			System.out.println("Error save2");
		}
	}

	public static Object loadInfo(String dest) {
		FileInputStream inputFile = null;
		ObjectInputStream inputStream = null;
		Object oo = null;
		try {

			System.out.println(dest);
			inputFile = new FileInputStream(dest);
		} catch (FileNotFoundException e) {
			System.out.println("Error 1");
		}
		try {
			try {
				inputStream = new ObjectInputStream(inputFile);

				try {
					oo = inputStream.readObject();
				} catch (ClassNotFoundException e3) {
					System.out.println("Error 2");
				}
				inputStream.close();
			} catch (NullPointerException e4) {
				System.out.println("Error 4");
			}
		} catch (IOException e) {
			System.out.println("Error 3");
		}
		return oo;
	}

}
