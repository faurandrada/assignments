package Project;

/* The class Main  contains the main method to run the program,	
 * when the application is ran for the first time.  
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	public static void main(String[] args) {
		Interface frame = new Interface();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setTitle("Order Management - Homework2");
		frame.pack();
		frame.setVisible(true);

	}
}
