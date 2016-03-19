package views;

import java.util.ArrayList;

import javax.swing.*;

import models.*;

public class HistoryItem extends JPanel {

	private JLabel ID = new JLabel();
	private JLabel status = new JLabel();
	private ArrayList<JLabel> orderItems = new ArrayList<JLabel>();

	public HistoryItem(Order order) {
		this.ID.setText(String.valueOf(order.getID()) + "   ");
		this.add(ID);
		this.status.setText(order.getStatus().getStatus() + "   ");
		this.add(status);
		for (OrderItem oi : order.getOrderItems()) {
			JLabel label = new JLabel(oi.getProduct().getName() + " (" + oi.getQuantity() + ") ");
			orderItems.add(label);
			this.add(label);
		}
	}

}
