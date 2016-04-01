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
import Control.CustomerActions;
import Control.UserDialog;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 2461034986517981909L;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	private static final int FRAME_POSITION_X = 50;
	private static final int FRAME_POSITION_Y = 200;

	private JPanel actionsPanel;

	private JButton filterButton;
	private JButton placeOrderButton;
	private JButton viewHistoryButton;
	private JButton seeStockButton;
	private JButton logoutButton;

	private JLabel nameLabel;

	private CustomerActions actions;

	public CustomerFrame(String name) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(FRAME_POSITION_X, FRAME_POSITION_Y);
		setTitle("Customer Frame");
		setLayout(new BorderLayout());

		nameLabel = new JLabel("Welcome customer: " + name);
		nameLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 22));
		nameLabel.setForeground(Color.decode("0x0f0f0f"));

		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.decode("0xa4d3ee"));
		namePanel.add(nameLabel);

		actions = new CustomerActions(name);

		createActionsPanel();
		actionsPanel.setBackground(Color.decode("0xf6f5a2"));
		add(namePanel, BorderLayout.NORTH);
		add(actionsPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private void createActionsPanel() {
		actionsPanel = new JPanel();
		actionsPanel.setLayout(new GridBagLayout());

		filterButton = new CustomizedButton("Filter Products");
		placeOrderButton = new CustomizedButton("Order Product");
		viewHistoryButton = new CustomizedButton("View Orders History");
		seeStockButton = new CustomizedButton("View All Products");
		logoutButton = new CustomizedButton("Log Out");
		logoutButton.setBackground(Color.RED);
		logoutButton.setForeground(Color.WHITE);

		logoutButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				getFrame().hide();
				new UserDialog();
			}
		});

		seeStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.seeProducts();
			}
		});

		placeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.orderProduct();
			}
		});

		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.filterProducts();
			}
		});
		
		viewHistoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions.seeHistory();
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		actionsPanel.add(seeStockButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		actionsPanel.add(placeOrderButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		actionsPanel.add(filterButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		actionsPanel.add(viewHistoryButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		actionsPanel.add(logoutButton, gbc);

	}
	
	public JFrame getFrame() {
		return this;
	}

}
