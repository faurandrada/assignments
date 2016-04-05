import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CustomerView extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel tab;
	Customer customer;
	OPDept orders;
	String[] items = { "BLOUSE", "DRESS", "TROUSERS" };
	String[] colors = { "BLUE", "CREAM", "ROSE" };
	String[] sizes = { "S", "M", "L" };
	JComboBox<String> sizeList;
	JComboBox<String> colorList;
	JComboBox<String> itemList;
	JButton addToCart;
	JFrame newFrame;
	JPanel northPanel,infoPanel,upperPanel,lowerPanel,middlePanel;
	ImageIcon myPicture,blueBlouse,blueDress,creamDress,roseBlouse,creamBlouse,roseDress,trousers,trousers2 ;
	JLabel picLabel,picLabel1,picLabel2,picLabel3,picLabel4,picLabel5,picLabel6,picLabel7,picLabel8,inStockLabel,sizeLabel,colorLabel,itemLabel;
	Container pane;
	CardLayout layout;
	JTextArea inStock;
	SerializeDemo serialize;
	DeserializeDemo deserialize;
	Warehouse wh;
	public CustomerView(Customer customer){
		this.customer=customer;
		wh=new Warehouse();
		orders=new OPDept();
		deserialize=new DeserializeDemo();
		serialize=new SerializeDemo();
		wh=deserialize.deserializeWarehouse();
		orders=deserialize.deserializeOPDept();
	
		newFrame = new JFrame("new");
		newFrame.setSize(550,650);
		newFrame.setBackground(new Color(255,255,204));
		newFrame.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setBackground(new Color(255,255,204));
		
		
		myPicture = new ImageIcon(getClass().getResource("mara.jpg"));
		picLabel = new JLabel(myPicture);
		northPanel.add(picLabel);
		newFrame.add(northPanel,BorderLayout.NORTH);
		
		pane = getContentPane();
		layout=new CardLayout();
	    pane.setLayout(layout);
	    
	    
	    blueBlouse = new ImageIcon(getClass().getResource("blueBlouse.jpg"));
	    picLabel1 = new JLabel(blueBlouse);
	    tab = new JPanel();
	    tab.add(picLabel1);
	    pane.add(tab,"BLUE BLOUSE");
	    
	    blueDress = new ImageIcon(getClass().getResource("blueDress.jpg"));
	    picLabel2 = new JLabel(blueDress);
	    tab = new JPanel();
	    tab.add(picLabel2);
	    pane.add(tab,"BLUE DRESS");
	    
	    creamDress = new ImageIcon(getClass().getResource("creamDress.jpg"));
	    picLabel3 = new JLabel(creamDress);
	    tab = new JPanel();
	    tab.add(picLabel3);
	    pane.add(tab,"CREAM DRESS");
	    
	    roseBlouse = new ImageIcon(getClass().getResource("roseBlouse.jpg"));
	    picLabel4 = new JLabel(roseBlouse);
	    tab = new JPanel();
	    tab.add(picLabel4);
	    pane.add(tab,"ROSE BLOUSE");
	    
	    creamBlouse = new ImageIcon(getClass().getResource("creamBlouse.jpg"));
	    picLabel5 = new JLabel(creamBlouse);
	    tab = new JPanel();
	    tab.add(picLabel5);
	    pane.add(tab,"CREAM BLOUSE");
	    
	    roseDress = new ImageIcon(getClass().getResource("roseDress.jpg"));
	   picLabel6 = new JLabel(roseDress);
	    tab = new JPanel();
	    tab.add(picLabel6);
	    pane.add(tab,"ROSE DRESS");
	    
	  
	    trousers = new ImageIcon(getClass().getResource("trousers.jpg"));
	    picLabel7 = new JLabel(trousers);
	    tab = new JPanel();
	    tab.add(picLabel7);
	    pane.add(tab,"BLUE TROUSERS");
	    
	    trousers2 = new ImageIcon(getClass().getResource("trousers2.jpg"));
	    picLabel8 = new JLabel(trousers2);
	    tab = new JPanel();
	    tab.add(picLabel8);
	    pane.add(tab,"ROSE TROUSERS");
	    
	    pane.setBackground(new Color(255,255,204));
	    newFrame.add(pane,BorderLayout.CENTER);
	    
	    infoPanel=new JPanel(new GridLayout(3,1));
	    upperPanel=new JPanel(new GridLayout(3,2));
	    //UPPERPANEL
		itemLabel = new JLabel("Item");
		itemLabel.setBackground(new Color(255,255,204));
		upperPanel.add(itemLabel);
		
	    itemList = new JComboBox(items);
	    itemList.setSelectedIndex(0);
	    itemList.addActionListener(this);
		itemList.setBackground(new Color(255,255,230));
		upperPanel.add(itemList);
		
		colorLabel = new JLabel("Color");
		colorLabel.setBackground(new Color(255,255,204));
		upperPanel.add(colorLabel);
		
		colorList = new JComboBox(colors);
		colorList.setSelectedIndex(0);
		colorList.addActionListener(this);
		colorList.setBackground(new Color(255,255,230));
		upperPanel.add(colorList);
		
		sizeLabel = new JLabel("Size");
		sizeLabel.setBackground(new Color(255,255,204));
		upperPanel.add(sizeLabel);
		
		sizeList = new JComboBox(sizes);
		sizeList.setSelectedIndex(0);
		sizeList.addActionListener(this);
		sizeList.setBackground(new Color(255,255,230));
		upperPanel.add(sizeList);
		
		
		infoPanel.add(upperPanel);
		
		//panel 2
		middlePanel=new JPanel(new GridLayout(1,2));
		middlePanel.setBackground(new Color(255,255,204));
		inStockLabel=new JLabel("In Stock");
		inStockLabel.setBackground(new Color(255,255,204));
        middlePanel.add(inStockLabel);
		
		inStock = new JTextArea();
		inStock.setFont(new Font("Courier New", Font.BOLD, 30));
        middlePanel.add(inStock);
        
	
		infoPanel.add(middlePanel);
		
		lowerPanel=new JPanel(new GridLayout(2,1));
		

		addToCart=new JButton("ADD TO CART");
		addToCart.addActionListener(this);
		addToCart.setBackground(new Color(255,255,204));
		
		lowerPanel.add(addToCart);
		infoPanel.add(lowerPanel);//aici am ramas
	    
	    infoPanel.setBackground(new Color(255,255,204));
	    newFrame.add(infoPanel,BorderLayout.LINE_END);
	    
	    layout.show(this.getContentPane(),"ROSE DRESS");
	    
		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	@Override
	
	public void actionPerformed(ActionEvent e) {
	  
    	

            if(e.getSource().equals(sizeList))
            {
            	String item = itemList.getSelectedItem().toString(); // item
            	String color = colorList.getSelectedItem().toString(); // color
            
            	layout.show(this.getContentPane(),color + " " +item);
            	
            	repaint();
            }

           if(e.getSource().equals(addToCart)){
        	 String item = itemList.getSelectedItem().toString(); // item
            String color = colorList.getSelectedItem().toString(); // color
        	String size = sizeList.getSelectedItem().toString(); // color
           	 size = sizeList.getSelectedItem().toString(); // color
               Product product=(Product)wh.traverse(item,size,color);
               serialize.serializeWarehouse(wh);
               if (product!=null){
                	Order order=new Order(product,customer);
                	orders.addOrder(order);
                	serialize.serializeOrders(orders);
               }
            
               else{
            	   
               }
            	repaint();
            }
		
	}
}