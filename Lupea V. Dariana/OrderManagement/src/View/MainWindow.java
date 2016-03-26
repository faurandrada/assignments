package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


//////////not used/////////////////////
public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JRadioButton b1, b2;
	private ButtonGroup group;
	private JPanel p1, p2;
	private JLabel text;
	private JButton ok;
	boolean rad1,rad2;

	public MainWindow() {
		super("Online Shop");
		text = new JLabel();
		b1 = new JRadioButton("Admin");
		b1.addActionListener(this);
		b2 = new JRadioButton("Customer");
		b2.addActionListener(this);
		group = new ButtonGroup();
		p1 = new JPanel();
		p2 = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(this);
		rad1 = false;
		rad2 = false; 
		text.setText("Please choose how you want to log in: ");
		group.add(b1);
		group.add(b2);
		p1.add(text);
		p1.add(b1);
		p1.add(b2);
		p2.add(ok);
		

		
		getContentPane().setLayout(new BorderLayout());
		//getContentPane().setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		//add(ok, BorderLayout.CENTER);
		setVisible(true);
		setSize(500, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object c = (Object)event.getSource();
		
		if ( c == b1){
			rad1 = true;
			rad2 = false;
		}
		
		if (c == b2){
			rad1 = false;
			rad2 = true;
		}
		if ((rad1 == true) && (event.getSource() == ok )){
			new AdminWindow();
		}
		else if ((rad2 == true) && (event.getSource() == ok )){
			
		}
		
	}
	
	
	

}
