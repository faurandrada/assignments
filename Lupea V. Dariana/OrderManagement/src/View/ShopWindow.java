package View;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Order;
import Model.Product;
import Model.Warehouse;
import Serializing.ReadFileS;

public class ShopWindow extends JFrame implements ActionListener {

	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	private JButton but1, but2, but3, but4, but5, but6, but7, but8;
	private JTextField field1, field2, field3, field4, field5, field6, field7, field8;
	private ArrayList<JLabel> labels;
	private ArrayList<JButton> buttons;
	private ArrayList<String> input;
	private JButton shoppingBag, placeOrder;
	private int rasp, banana, orange, apple;
	Warehouse wh;

	private static final long serialVersionUID = 1L;

	public ShopWindow() {
		super("*******Dari's SHOP*******");
		getContentPane().setLayout(null);

		labels = new ArrayList<JLabel>();
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);
		labels.add(label5);
		labels.add(label6);
		labels.add(label7);
		labels.add(label8);

		label1 = new JLabel();
		label1.setText("Raspberry");
		label1.setBounds(30, 20, 60, 20);

		label2 = new JLabel();
		label2.setText("Apple");
		label2.setBounds(180, 20, 60, 20);

		label3 = new JLabel();
		label3.setText("Orange");
		label3.setBounds(330, 20, 60, 20);

		label4 = new JLabel();
		label4.setText("Banana");
		label4.setBounds(480, 20, 60, 20);
		//// to be continued...

		add(label1);
		add(label2);
		add(label3);
		add(label4);

		ImageIcon water = new ImageIcon("Images/berry.jpg");
		Image img = water.getImage();
		Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newww = new ImageIcon(newimg);
		but1 = new JButton(newww);

		ImageIcon water1 = new ImageIcon("Images/apple.jpg");
		Image img1 = water1.getImage();
		Image newimg1 = img1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newww1 = new ImageIcon(newimg1);
		but2 = new JButton(newww1);

		ImageIcon water2 = new ImageIcon("Images/orange.jpg");
		Image img2 = water2.getImage();
		Image newimg2 = img2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newww2 = new ImageIcon(newimg2);
		but3 = new JButton(newww2);

		ImageIcon water3 = new ImageIcon("Images/banana.jpg");
		Image img3 = water3.getImage();
		Image newimg3 = img3.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newww3 = new ImageIcon(newimg3);
		but4 = new JButton(newww3);

		///// Replace with this method!!!
		// setImageOnButton(but1,"Images/berry.jpg" );

		but1.setBounds(15, 50, 100, 100);
		// but2 = new JButton("2");
		but2.setBounds(160, 50, 100, 100);
		// but3 = new JButton("3");
		but3.setBounds(305, 50, 100, 100);
		// but4 = new JButton("4");
		but4.setBounds(450, 50, 100, 100);

		add(but1);
		add(but2);
		add(but3);
		add(but4);

		field1 = new JTextField("0");
		field1.setBounds(55, 150, 20, 20);
		field2 = new JTextField("0");
		field2.setBounds(200, 150, 20, 20);
		field3 = new JTextField("0");
		field3.setBounds(345, 150, 20, 20);
		field4 = new JTextField("0");
		field4.setBounds(495, 150, 20, 20);
		// buttons.add(but2);
		// buttons.add(but3);
		// buttons.add(but4);

		add(field1);
		add(field2);
		add(field3);
		add(field4);

		shoppingBag = new JButton("Shopping bag");
		shoppingBag.setBounds(600, 50, 120, 30);
		shoppingBag.addActionListener(this);
		add(shoppingBag);

		placeOrder = new JButton("Place order");
		placeOrder.setBounds(600, 100, 120, 30);
		placeOrder.addActionListener(this);
		add(placeOrder);

		wh = new Warehouse();

		setVisible(true);
		setSize(900, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void setImageOnButton(JButton button, String path) {

		ImageIcon image = new ImageIcon(path);
		Image takeImage = image.getImage();
		Image resizedImg = takeImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(resizedImg);
		button = new JButton(newImage);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		rasp = Integer.parseInt(field1.getText().trim());
		apple = Integer.parseInt(field2.getText().trim());
		orange = Integer.parseInt(field3.getText().trim());
		banana = Integer.parseInt(field4.getText().trim());

		if (event.getSource() == shoppingBag) {

			Integer[] quantity = new Integer[4];
			quantity[0] = rasp;
			quantity[1] = apple;
			quantity[2] = orange;
			quantity[3] = banana;
			ArrayList<String> fruits = new ArrayList<String>();
			fruits.add("Raspberry: ");
			fruits.add("Apple: ");
			fruits.add("Orange: ");
			fruits.add("Banana: ");

			String output = "";
			for (int i = 0; i < fruits.size(); i++) {
				String everything = fruits.get(i).toString();
				String everything2 = quantity[i].toString();

				output += everything + " " + everything2 + "\n";
			}
			JOptionPane.showMessageDialog(this, output, "Your order is", JOptionPane.PLAIN_MESSAGE);

		}

		if (event.getSource() == placeOrder) {
			
			//Order o = new Order(1, "today", "pending");
			//Set<Product> newProd = new HashSet<Product>();
		    //	o.setOrderedProducts(newProd);
			
			ReadFileS read = new ReadFileS();
			Warehouse www = read.ReadFile();
			
			Iterator<Product> iterator = www.checkProducts();
			while (iterator.hasNext()) {
				Product p = iterator.next();
				System.out.println(p.getName());
					if (p.getName() == "banana") {
						if (p.getQuantity() <= banana) {
							System.out.println("bananas in stock");
						}
						else {
							System.out.println("bananas NOT in stock");
						}
					}
					else if(p.getName() == "raspberry"){
						if (p.getQuantity() <= rasp){
							System.out.println("rasps in stock");
						}
						else {
							System.out.println("rasps NOT in stock");
						}
					}
					else if(p.getName() == "apple"){
						if (p.getQuantity() <= apple){
							System.out.println("apples in stock");
						}
						
						else {
							System.out.println("apples NOT in stock");
						}
					}
					else if (p.getName() == "orange"){
						if (p.getQuantity() <= orange){
							System.out.println("oranges in stock");
						}
						else {
							System.out.println("oranges NOT in stock");
						}
					}
					
				
				System.out.println("dari");
			}

		}
	}

}
