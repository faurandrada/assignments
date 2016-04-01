package Control;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

public class Helper {

	public <T> void updateSerFile(Set<T> dataSet, String fileName) {
		try {

			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (T iterator : dataSet)
				oos.writeObject(iterator);

			oos.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: creating the file" + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: writing in the file" + fileName);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void readFromSerFile(Set<T> dataSet, String fileName) {
		try {

			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);

			try {
				while (true) {
					dataSet.add((T) ois.readObject());
				}
			} catch (EOFException e) {
				System.out.println("\n********************************\nAll data read from file " + fileName
						+ "\n********************************\n");
			} finally {
				ois.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: creating the file " + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: writing in the file " + fileName);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: class not found for file " + fileName);
			e.printStackTrace();
		}
	}

}
