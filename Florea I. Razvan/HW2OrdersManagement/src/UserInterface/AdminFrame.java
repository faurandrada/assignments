package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.AdminActions;
import Control.UserDialog;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = -3019566299406309519L;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	private static final int FRAME_POSITION_X = 50;
	private static final int FRAME_POSITION_Y = 200;

	private JPanel actionsPanel;

	private JButton addProductButton;
	private JButton removeProductButton;
	private JButton removeCustomerButton;
	private JButton modify;
	private JButton warehouseButton;
	private JButton logOutButton;
	private JButton seeOrdersButton;
	private JButton seeCustomersButton;
	private JButton manageOrdersButton;
	
	private AdminActions actions;

	private JLabel nameLabel;

	public AdminFrame(String name) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(FRAME_POSITION_X,FRAME_POSITION_Y);
		setTitle("Admin Frame");
		setLayout(new BorderLayout());
		
		nameLabel = new JLabel("Welcome admin: " + name);
		nameLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 22));
		nameLabel.setForeground(Color.BLUE);
		
		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.decode("0xffec8b"));
		namePanel.add(nameLabel);
		
		actions = new AdminActions(name);
		createActionsPanel();
		
		add(namePanel, BorderLayout.NORTH);
		add(actionsPanel, BorderLayout.CENTER);
		
		setVisible(true);

	}

	private void createActionsPanel() {
		actionsPanel = new JPanel();
		actionsPanel.setLayout(new GridBagLayout());
		actionsPanel.setBackground(Color.decode("0xffec8b"));

		addProductButton = new CustomizedButton("Add Product");
		removeProductButton = new CustomizedButton("Remove Product");
		removeCustomerButton = new CustomizedButton("Remove Customer");
		modify = new CustomizedButton("Modify Stock");
		warehouseButton = new CustomizedButton("View Current Stock");
		seeOrdersButton = new CustomizedButton("View Orders");
		seeCustomersButton = new CustomizedButton("View Customers");
		manageOrdersButton = new CustomizedButton("Manage Orders");
		logOutButton = new CustomizedButton("Log Out");
		logOutButton.setBackground(Color.RED);
		logOutButton.setForeground(Color.WHITE);
		
		removeCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.customerRemoval();
			}
		});
		
		addProductButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.addProductToStock();
			}
		});
		
		removeProductButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.removeProductFromStock();
			}
		});
		
		warehouseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.showWarehouse();
			}
		});

		modify.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.modifyStock();		
			}
		});
		
		logOutButton.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				getFrame().hide();
				new UserDialog();
			}
		});

		seeOrdersButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.seeOrders();
			}
		});
		
		manageOrdersButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.manageOrders();
			}
		});
		
		seeCustomersButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.seeCustomers();	
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		actionsPanel.add(addProductButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		actionsPanel.add(removeProductButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		actionsPanel.add(modify, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		actionsPanel.add(warehouseButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		actionsPanel.add(removeCustomerButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		actionsPanel.add(seeCustomersButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		actionsPanel.add(manageOrdersButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		actionsPanel.add(seeOrdersButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		actionsPanel.add(logOutButton, gbc);
	}

	private JFrame getFrame(){
		return this;
	}
}
