package views;

import models.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * The drawing part
 */
public class QueueEvolution extends JPanel {

	private ArrayList<Server> servers;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 400, 200);
		servers = TaskScheduler.getInstance().getServers();
		int i, j;
		for (i = 0; i < servers.size(); i++) {
			g.setColor(Color.WHITE);
			g.fillRect(i * 100 + 10, 10, 80, 10);
			g.setColor(Color.BLACK);
			g.drawString(servers.get(i).getName(), i * 100 + 20, 20);
			j = 1;
			for (Task t : servers.get(i).getTasks()) {
				g.setColor(Color.CYAN);
				g.fillRect(i * 100 + 10, j * 20 + 10, 80, 10);
				g.setColor(Color.BLACK);
				g.drawString(t.getName(), i * 100 + 20, j * 20 + 20);
				j++;
			}
		}
	}

}
