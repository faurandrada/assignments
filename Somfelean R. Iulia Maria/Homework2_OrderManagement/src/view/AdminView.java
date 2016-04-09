package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Product;

public class AdminView extends JFrame {

	private JTextField id;
	private JTextField book;
	private JTextField author;
	private JTextField publisher;
	private JTextField price;
	private JTextField quantity;

	private JScrollPane scroll;
	private JTable table;
	public DefaultTableModel model;

	private JMenu viewAndChange;
	private JMenu viewOrders;
	
	private JPanel panelAdd;
	private JPanel panelView;
	private JPanel panelOrders;
	
	private JButton buttonAdd;
	private JButton buttonRemoveProduct;
	
	private Vector listData;
	private JList orderList;
	private JScrollPane scrollPane;
	
	public AdminView() {
		
		super("Administrator");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenuBar();

		panelAdd = new JPanel(new GridLayout(7, 2));
		createAddPanel(panelAdd);
		this.add(panelAdd);

		panelView = new JPanel();
		createViewPanel(panelView);
		this.add(panelView);
		
		panelOrders = new JPanel();
		createOrderPanel(panelOrders);
		this.add(panelOrders);
		
		panelAdd.setVisible(false);
		panelView.setVisible(false);
		
		this.setVisible(false);
	}

	private void createAddPanel(JPanel panelAdd) {

		panelAdd.setSize(440, 440);

		JLabel idL = new JLabel("ID: ");
		id = new JTextField();
		id.setBounds(5, 5, 10, 10);
		panelAdd.add(idL);
		panelAdd.add(id);

		JLabel bookL = new JLabel("Book: ");
		book = new JTextField();
		book.setBounds(5, 5, 10, 10);
		panelAdd.add(bookL);
		panelAdd.add(book);

		JLabel authorL = new JLabel("Author: ");
		author = new JTextField();
		author.setBounds(5, 5, 10, 10);
		panelAdd.add(authorL);
		panelAdd.add(author);

		JLabel publisherL = new JLabel("Publisher: ");
		publisher = new JTextField();
		publisher.setBounds(5, 5, 10, 10);
		panelAdd.add(publisherL);
		panelAdd.add(publisher);

		JLabel priceL = new JLabel("Price: ");
		price = new JTextField();
		price.setBounds(5, 5, 10, 10);
		panelAdd.add(priceL);
		panelAdd.add(price);

		JLabel quantityL = new JLabel("Quantity: ");
		quantity = new JTextField();
		quantity.setBounds(5, 5, 10, 10);
		panelAdd.add(quantityL);
		panelAdd.add(quantity);

		JLabel emptyLabel = new JLabel();
		panelAdd.add(emptyLabel);

		buttonAdd = new JButton("Add product");
		buttonAdd.setBounds(5, 5, 10, 10);
		panelAdd.add(buttonAdd);
	}

	private void createViewPanel(JPanel panelView) {

		panelView.setSize(480, 480);

		Object[] rowCount = new Object[] { "ID", "Book", "Author", "Publisher", "Price", "Quantity" };
		model = new DefaultTableModel(null, rowCount);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480,200));
		panelView.add(scroll);
		
		panelView.add(this.getPanelAdd());
		
		buttonRemoveProduct = new JButton("Remove product");
		buttonRemoveProduct.setSize(10,10);
		
		panelView.add(buttonRemoveProduct);
		

	}
	
	private void createOrderPanel(JPanel panelOrders){
		
		panelOrders.setSize(480,480);
		
		listData = new Vector();
		orderList = new JList(listData);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(480,400));
		scrollPane.getViewport().add(orderList);
		panelOrders.add(scrollPane);
		
	}

	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();

		viewAndChange = new JMenu("View & Change stock");
		viewOrders = new JMenu("View orders");
		
		menubar.add(viewAndChange);
		menubar.add(viewOrders);

		setJMenuBar(menubar);

	}

	public String getId() {
		return id.getText();
	}

	
	public String getBook() {
		return book.getText();
	}

	public void setBook(JTextField book) {
		this.book = book;
	}

	public String getAuthor() {
		return author.getText();
	}

	public void setAuthor(JTextField author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher.getText();
	}

	public void setPublisher(JTextField publisher) {
		this.publisher = publisher;
	}

	public String getPrice() {
		return price.getText();
	}

	public void setPrice(JTextField price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity.getText();
	}

	public void setQuantity(JTextField quantity) {
		this.quantity = quantity;
	}

	public DefaultTableModel getModel() {
		return this.model;
	}

	

	public JMenu getViewAndChange() {
		return viewAndChange;
	}

	public void setViewAndChange(JMenu viewAndChange) {
		this.viewAndChange = viewAndChange;
	}

	public Vector getListData() {
		return listData;
	}
	
	public JList getOrderList(){
		return orderList;
	}
	public void setListData(Vector listData) {
		this.listData = listData;
	}

	public JPanel getPanelAdd(){
		return this.panelAdd;
	}
	
	public JPanel getPanelView(){
		return this.panelView;
	}
	
	public JPanel getPanelOrders(){
		return this.panelOrders;
	}
	
	public JTable getTable(){
		return this.table;
	}
	public void addTableActionListener(TableModelListener tme) {
		model.addTableModelListener(tme);
	}
	public void viewChangeListener(MenuListener l) {

		viewAndChange.addMenuListener(l);
	}
	
	public void viewOrdersListener(MenuListener l) {

		viewOrders.addMenuListener(l);
	}
	
	public void addAddButtonListener(ActionListener l){
		buttonAdd.addActionListener(l);
	}
	public void addRemoveButtonListener(ActionListener l){
		buttonRemoveProduct.addActionListener(l);
	}

	public void paintTable(TreeSet<Product> products) {
		
		 
		Iterator<Product> itr = products.iterator();
		while (itr.hasNext()) {
			Product p1 = itr.next();
			this.model.addRow(new Object[] { p1.getIdProduct(), p1.getTitle(), p1.getAuthor(), p1.getPublisher(),
					p1.getPrice(), p1.getQuantity() });
		}
		
	}

	
}
