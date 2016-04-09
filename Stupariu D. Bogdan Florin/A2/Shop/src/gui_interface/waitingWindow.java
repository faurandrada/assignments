package gui_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import run.Const;

public class waitingWindow {
	
	JFrame waiting = new JFrame("waiting");
	JPanel waitingPanel = new JPanel();
	
	public waitingWindow(){
		
		int WIDTH = Const.MESSAGE_WIDTH;
		int HEIGHT = Const.MESSAGE_HEIGHT;
		
		waiting.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		waiting.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		waiting.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		waiting.setResizable(false);
		waiting.setLocationRelativeTo(null);
		
		waitingPanel.setLayout(new BorderLayout());
		waitingPanel.setBackground(Color.WHITE);
		waitingPanel.setForeground(Color.BLACK);
		JLabel waitingText = new JLabel();
		waitingText.setForeground(Color.BLACK);
		waitingText.setBounds(10, 10, 100, 100);
		waitingText.setFont(Const.tableFont);
		waitingText.setText("Processing ... .. .");
		waitingPanel.add(waitingText,BorderLayout.CENTER);
		waiting.add(waitingPanel);
		
		waiting.setVisible(true);
	}

	public void close() {
		// TODO Auto-generated method stub
		waiting.dispose();
	}

}
