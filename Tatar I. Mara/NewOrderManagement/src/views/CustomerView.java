package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import io.IOStream;
import models.Customer;
import models.OPDept;
import models.Order;
import models.Product;
import models.Warehouse;

public class CustomerView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2030222122604244787L;
	private static int i;
	List<Product> shoppingCart;
	Warehouse wh;
	OPDept orders;
	IOStream io;
	JFrame frame;
	JPanel tab;
	double totalPrice;
	String[] items = { "Blouse", "Dress", "Trousers" };
	String[] colors = { "Blue", "Cream", "Rose" };
	String[] sizes = { "S", "M", "L" };
	JComboBox<String> sizeList;
	JComboBox<String> colorList;
	JComboBox<String> itemList;
	JButton addToCart, viewCart, placeOrder;
	JFrame newFrame;
	JPanel northPanel, infoPanel, upperPanel, lowerPanel, middlePanel;
	ImageIcon myPicture, blueBlouse, blueDress, creamDress, roseBlouse, creamBlouse, roseDress, trousers, trousers2;
	JLabel picLabel, picLabel1, picLabel2, picLabel3, picLabel4, picLabel5, picLabel6, picLabel7, picLabel8,
			inStockLabel, sizeLabel, colorLabel, itemLabel, totalLabel;
	Container pane;
	CardLayout layout;
	JTextArea inStock;
    Customer customer;
	public CustomerView(Customer customer) {
		Random rand=new Random();
		i=rand.nextInt(32000);
		System.out.println(i);
        this.customer=customer;
		shoppingCart = new ArrayList<Product>();
		totalPrice = 0;
		wh = new Warehouse();
		orders=new OPDept();
		io = new IOStream();
		orders=io.deserializeOPDept();
		wh = io.deserializeWarehouse();
		wh.print();
		newFrame = new JFrame("new");
		newFrame.setSize(550, 650);
		newFrame.setBackground(new Color(255, 255, 204));
		newFrame.setLayout(new BorderLayout());

		northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 204));

		myPicture = new ImageIcon(getClass().getResource("mara.jpg"));
		picLabel = new JLabel(myPicture);
		northPanel.add(picLabel);
		newFrame.add(northPanel, BorderLayout.NORTH);

		pane = getContentPane();
		layout = new CardLayout();
		pane.setLayout(layout);

		blueBlouse = new ImageIcon(getClass().getResource("blueBlouse.jpg"));
		picLabel1 = new JLabel(blueBlouse);
		tab = new JPanel();
		tab.add(picLabel1);
		pane.add(tab, "Blue Blouse");

		blueDress = new ImageIcon(getClass().getResource("blueDress.jpg"));
		picLabel2 = new JLabel(blueDress);
		tab = new JPanel();
		tab.add(picLabel2);
		pane.add(tab, "Blue Dress");

		creamDress = new ImageIcon(getClass().getResource("creamDress.jpg"));
		picLabel3 = new JLabel(creamDress);
		tab = new JPanel();
		tab.add(picLabel3);
		pane.add(tab, "Cream Dress");

		roseBlouse = new ImageIcon(getClass().getResource("roseBlouse.jpg"));
		picLabel4 = new JLabel(roseBlouse);
		tab = new JPanel();
		tab.add(picLabel4);
		pane.add(tab, "Rose Blouse");

		creamBlouse = new ImageIcon(getClass().getResource("creamBlouse.jpg"));
		picLabel5 = new JLabel(creamBlouse);
		tab = new JPanel();
		tab.add(picLabel5);
		pane.add(tab, "Cream Blouse");

		roseDress = new ImageIcon(getClass().getResource("roseDress.jpg"));
		picLabel6 = new JLabel(roseDress);
		tab = new JPanel();
		tab.add(picLabel6);
		pane.add(tab, "Rose Dress");

		trousers = new ImageIcon(getClass().getResource("trousers.jpg"));
		picLabel7 = new JLabel(trousers);
		tab = new JPanel();
		tab.add(picLabel7);
		pane.add(tab, "Blue Trousers");

		trousers2 = new ImageIcon(getClass().getResource("trousers2.jpg"));
		picLabel8 = new JLabel(trousers2);
		tab = new JPanel();
		tab.add(picLabel8);
		pane.add(tab, "Rose Trousers");

		pane.setBackground(new Color(255, 255, 204));
		newFrame.add(pane, BorderLayout.CENTER);

		infoPanel = new JPanel(new GridLayout(3, 1));
		upperPanel = new JPanel(new GridLayout(3, 2));
		// UPPERPANEL
		itemLabel = new JLabel("Item");
		itemLabel.setBackground(new Color(255, 255, 204));
		upperPanel.add(itemLabel);

		itemList = new JComboBox(items);
		itemList.setSelectedIndex(0);
		itemList.addActionListener(this);
		itemList.setBackground(new Color(255, 255, 230));
		upperPanel.add(itemList);

		colorLabel = new JLabel("Color");
		colorLabel.setBackground(new Color(255, 255, 204));
		upperPanel.add(colorLabel);

		colorList = new JComboBox(colors);
		colorList.setSelectedIndex(0);
		colorList.addActionListener(this);
		colorList.setBackground(new Color(255, 255, 230));
		upperPanel.add(colorList);

		sizeLabel = new JLabel("Size");
		sizeLabel.setBackground(new Color(255, 255, 204));
		upperPanel.add(sizeLabel);

		sizeList = new JComboBox(sizes);
		sizeList.setSelectedIndex(0);
		sizeList.addActionListener(this);
		sizeList.setBackground(new Color(255, 255, 230));
		upperPanel.add(sizeList);

		infoPanel.add(upperPanel);

		// panel 2
		middlePanel = new JPanel(new GridLayout(1, 2));
		middlePanel.setBackground(new Color(255, 255, 204));
		addToCart = new JButton("ADD TO CART");
		addToCart.addActionListener(this);
		addToCart.setBackground(new Color(255, 255, 204));

		viewCart = new JButton("VIEW CART");
		viewCart.addActionListener(this);
		viewCart.setBackground(new Color(255, 255, 204));

		middlePanel.add(addToCart);
		middlePanel.add(viewCart);

		infoPanel.add(middlePanel);

		lowerPanel = new JPanel(new GridLayout(2, 1));

		totalLabel = new JLabel();
		totalLabel.setBackground(new Color(255, 255, 204));

		placeOrder = new JButton("PLACE ORDER");
		placeOrder.addActionListener(this);
		placeOrder.setBackground(new Color(255, 255, 204));

		lowerPanel.add(placeOrder);
		lowerPanel.add(totalLabel);
		infoPanel.add(lowerPanel);// aici am ramas

		infoPanel.setBackground(new Color(255, 255, 204));
		newFrame.add(infoPanel, BorderLayout.LINE_END);

		layout.show(this.getContentPane(), "ROSE DRESS");

		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(sizeList)) {
			String item = itemList.getSelectedItem().toString(); // item
			String color = colorList.getSelectedItem().toString(); // color

			layout.show(this.getContentPane(), color + " " + item);

			repaint();
		}

		if (e.getSource().equals(addToCart)) {
			Product product = wh.findProductAfterItem(itemList.getSelectedItem().toString(),
					sizeList.getSelectedItem().toString(), colorList.getSelectedItem().toString());

			if (product != null) {
				if (product.getStock() >= 1) {
					shoppingCart.add(product);
					product.setStock(product.getStock() - 1);
					totalPrice = totalPrice + product.getPrice();
					totalLabel.setText(((Double) totalPrice).toString());

				} else {
					JOptionPane.showMessageDialog(null, "The product is out of stock!");
					wh.remove(product);
				}
			} else {
				JOptionPane.showMessageDialog(null, "The product is out of stock!");
			}

		}
		if (e.getSource().equals(placeOrder)) {
			
			
		    Order order=new Order(i+1,customer);
		    for (Product p:shoppingCart){
		    	order.addProduct(p);
		    }
		    orders.addOrder(order);
			System.out.println("CUSTOMER BOUGHT STH");
		//	wh.print();
			io.SerializeWarehouse(wh);
            io.SerializeOPdept(orders);
            orders.print();
			JOptionPane.showMessageDialog(null, "Thank you for ordering!");
			totalPrice=0;
			repaint();
		
		}
	}

}
