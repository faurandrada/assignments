package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The main window
 */
public class MainView extends JFrame{

	private static JTextArea logging = new JTextArea();
	private JButton start = new JButton("Start!");
	private JTextField numberOfQueues = new JTextField("Number of Queues");
	private JTextField simulationInterval = new JTextField("Simulation Interval");
	private JTextField minArrivalInterval = new JTextField("Minimum Arrival Interval");
	private JTextField maxArrivalInterval = new JTextField("Maximum Arrival Interval");
	private JTextField minServiceTime = new JTextField("Minimum Service Time");
	private JTextField maxServiceTime = new JTextField("Maximum Service Time");
	private JTextField maxLoadPerServer = new JTextField("Maximum Load Per Queue");
	private QueueEvolution queueEvolution = new QueueEvolution();
	private JScrollPane scrollLogging;
	
	public MainView(String title){
		super(title);
		this.setLayout(new FlowLayout());
		Dimension dim = new Dimension(200, 20);
		numberOfQueues.setPreferredSize(dim);
		simulationInterval.setPreferredSize(dim);
		minArrivalInterval.setPreferredSize(dim);
		maxArrivalInterval.setPreferredSize(dim);
		minServiceTime.setPreferredSize(dim);
		maxServiceTime.setPreferredSize(dim);
		maxLoadPerServer.setPreferredSize(dim);
		start.setPreferredSize(dim);
		queueEvolution.setPreferredSize(new Dimension(400, 200));
		logging.setLineWrap(true);
	    logging.setEditable(false);
	    scrollLogging = new JScrollPane(logging);
	    scrollLogging.setPreferredSize(new Dimension(400, 100));
		scrollLogging.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setSize(500, 500);
		add(queueEvolution);
		add(numberOfQueues);
		add(maxLoadPerServer);
		add(simulationInterval);
		add(minArrivalInterval);
		add(maxArrivalInterval);
		add(minServiceTime);
		add(maxServiceTime);
		add(start);
		add(scrollLogging);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	public void refresh(){
		queueEvolution.repaint();
		revalidate();
		repaint();
	}
	
	public void setStartButtonActionListener(ActionListener a){
		start.addActionListener(a);
	}

	public static JTextArea getLogging() {
		return logging;
	}

	public JTextField getNumberOfQueues() {
		return numberOfQueues;
	}

	public JTextField getSimulationInterval() {
		return simulationInterval;
	}

	public JTextField getMinArrivalInterval() {
		return minArrivalInterval;
	}

	public JTextField getMaxArrivalInterval() {
		return maxArrivalInterval;
	}

	public JTextField getMinServiceTime() {
		return minServiceTime;
	}

	public JTextField getMaxServiceTime() {
		return maxServiceTime;
	}

	public JTextField getMaxLoadPerServer() {
		return maxLoadPerServer;
	}

	public JButton getStart() {
		return start;
	}
	
}
