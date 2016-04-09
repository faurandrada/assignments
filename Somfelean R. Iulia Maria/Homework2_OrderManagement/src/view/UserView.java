package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.OrderedProduct;
import model.Product;

public class UserView extends JFrame {

	private JTextField searchField;

	private JPanel panelView;
	private JPanel panelSearch;

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;

	private JButton searchButton;
	private JButton orderHistoryButton;
	private JButton addToCartButton;
	private JList shoppingList;
	private JScrollPane scrollPane;
	private JButton emptyCart;
	private JPanel panelCart;
	private JPanel panelOrders;
	private JButton buyButton;
	private Vector listData;

	private Vector listDataO;
	private JList orderList;
	private JScrollPane scrollPaneO;

	public UserView() {

		super("User");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new GridLayout(4, 1));

		panelSearch = new JPanel();
		panelSearch.setPreferredSize(new Dimension(200, 20));
		createSearchPanel(panelSearch);
		this.add(panelSearch);

		panelView = new JPanel();
		panelView.setPreferredSize(new Dimension(480, 200));
		createViewPanel(panelView);
		this.add(panelView);
		
		panelCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCart.setPreferredSize(new Dimension(300, 300));
		createShoppingcartPanel(panelCart);
		this.add(panelCart);

		panelOrders = new JPanel();
		panelOrders.setPreferredSize(new Dimension(480, 100));
		createOrderPanel(panelOrders);
		this.add(panelOrders);
		
		this.setVisible(false);
	}

	private void createSearchPanel(JPanel panelSearch) {

		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 20));
		panelSearch.add(searchField);

		searchButton = new JButton("Search");
		searchButton.setSize(new Dimension(20, 20));
		panelSearch.add(searchButton);
		
		orderHistoryButton = new JButton("Order history");
		panelSearch.add(orderHistoryButton);
		
	}
	private void createOrderPanel(JPanel panelOrders){
		
		listDataO = new Vector();
		orderList = new JList(listDataO);
		scrollPaneO = new JScrollPane();
		scrollPaneO.setPreferredSize(new Dimension(480,100));
		scrollPaneO.getViewport().add(orderList);
		panelOrders.add(scrollPaneO);
		
	}
	private void createShoppingcartPanel(JPanel panelCart) {

		addToCartButton = new JButton("Add to shopping cart!");
		addToCartButton.setBounds(30, 30, 30, 30);
		panelCart.add(addToCartButton);

		emptyCart = new JButton("Empty shopping cart!");
		emptyCart.setBounds(30, 30, 30, 30);
		panelCart.add(emptyCart);

		listData = new Vector();
		shoppingList = new JList(listData);
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(shoppingList);
		panelCart.add(scrollPane);

		buyButton = new JButton("Buy");
		buyButton.setBounds(30, 30, 30, 30);
		panelCart.add(buyButton);

	}

	private void createViewPanel(JPanel panelView) {

		Object[] rowCount = new Object[] { "ID", "Book", "Author", "Publisher", "Price", "Status" };
		model = new DefaultTableModel(null, rowCount);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480, 200));
		panelView.add(scroll);

	}

	private String getSearchField() {
		return searchField.getText();
	}

	public JPanel getPanelView() {
		return panelView;
	}

	public void setPanelView(JPanel panelView) {
		this.panelView = panelView;
	}

	public DefaultTableModel getModel() {
		return this.model;
	}

	public JList getShoppingList() {
		return this.shoppingList;
	}

	public void addSearchButtonActionListener(ActionListener l) {
		searchButton.addActionListener(l);
	}

	public void addTableActionListener(TableModelListener tme) {
		model.addTableModelListener(tme);
	}

	public void addToListActionListener(ActionListener l) {
		addToCartButton.addActionListener(l);
	}

	public void addEmptyListActionListener(ActionListener l) {
		emptyCart.addActionListener(l);
	}

	public void addBuyActionListener(ActionListener l) {

		buyButton.addActionListener(l);
	}

	public void orderHistoryActionListener(ActionListener l) {

		orderHistoryButton.addActionListener(l);
	}
	public void paintTable(TreeSet<Product> products) {

		this.getModel().setRowCount(0);
		
		Iterator<Product> itr = products.iterator();
		while (itr.hasNext()) {
			Product p1 = itr.next();
			this.model.addRow(new Object[] { p1.getIdProduct(), p1.getTitle(), p1.getAuthor(), p1.getPublisher(),
					p1.getPrice(), p1.getStatus() });
		}

	}

	public JPanel getPanelOrders(){
		
		return this.panelOrders;
	}
	public JTable getTable() {
		return this.table;
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

	public Vector getListDataO() {
		return listDataO;
	}

	public void setListDataO(Vector listData) {
		this.listDataO = listData;
	}
	public String getSearchText() {
		return searchField.getText();
	}
}
