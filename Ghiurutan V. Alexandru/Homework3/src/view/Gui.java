package view;

import javax.swing.*;

import controller.TaskGenerator;

import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String INVALID_FIELDS = "Invalid fields.Please enter again more carefully.";
	private static final String ERROR = "ERROR";
	private JTextField minimumArrival, maximumArrival, minimumService, maximumService, numberQueues,
			numberOfTasksPerQueue, simulationInterval;
	private JPanel south, arrivalPanel, servicePanel, queuesPanel, simulationPanel;
	private JButton startButton;
	private int minArrival, maxArrival, minService, maxService, nrQueues, nrOfTasksPerQueue, simInterval;
private TaskGenerator taskGenerator;
	public Gui() {
		this.setTitle("Simulation");
		this.setLayout(new BorderLayout());
		initializeSouth();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initializeSouth() {
		south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		arrivalPanel = new JPanel();
		minimumArrival = new JTextField("Minimum Arrival Time");
		/*
		 * minimumArrival.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * minimumArrival.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		maximumArrival = new JTextField("Maximum Arrival Time");
		/*
		 * maximumArrival.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * maximumArrival.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		arrivalPanel.add(minimumArrival);
		arrivalPanel.add(maximumArrival);
		servicePanel = new JPanel();
		minimumService = new JTextField("Minimum Time Of Service");
		/*
		 * minimumService.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * minimumService.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		maximumService = new JTextField("Maximum Time Of Service");
		/*
		 * maximumService.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * maximumService.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		servicePanel.add(minimumService);
		servicePanel.add(maximumService);
		queuesPanel = new JPanel();
		numberQueues = new JTextField("Number Of Queues");
		numberOfTasksPerQueue = new JTextField("Number of tasks per queue");
		/*
		 * nrQueues.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * nrQueues.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		queuesPanel.add(numberQueues);
		queuesPanel.add(numberOfTasksPerQueue);
		simulationPanel = new JPanel();
		simulationInterval = new JTextField("Simulation time");
		/*
		 * simulationInterval.addFocusListener(new FocusListener() {
		 * 
		 * @Override public void focusGained(FocusEvent e) {
		 * simulationInterval.setText(""); }
		 * 
		 * @Override public void focusLost(FocusEvent e) { } });
		 */
		startButton = new JButton("Start simulation");
		startButton.addActionListener(this);
		simulationPanel.add(simulationInterval);
		south.add(arrivalPanel);
		south.add(servicePanel);
		south.add(queuesPanel);
		south.add(simulationPanel);
		south.add(startButton);
		this.add(south, BorderLayout.SOUTH);
	}

	private boolean checkFields() {
		try {
			minArrival = Integer.valueOf(minimumArrival.getText());
			maxArrival = Integer.valueOf(maximumArrival.getText());
			minService = Integer.valueOf(minimumService.getText());
			maxService = Integer.valueOf(maximumService.getText());
			nrQueues = Integer.valueOf(numberQueues.getText());
			nrOfTasksPerQueue = Integer.valueOf(numberOfTasksPerQueue.getText());
			simInterval = Integer.valueOf(simulationInterval.getText());
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == startButton) {
			if (checkFields()) {
          taskGenerator=new TaskGenerator(minArrival,maxArrival,minService,maxService,simInterval,nrQueues,nrOfTasksPerQueue);
          taskGenerator.start();
			} else {
				JOptionPane.showMessageDialog(this, INVALID_FIELDS, ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
