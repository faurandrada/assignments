package views;

import java.util.*;

import controllers.Main;
import models.OPDept;
import models.Order;

public class HistoryView extends Frame{
	
	private ArrayList<HistoryItem> historyItems = new ArrayList<HistoryItem>();
	
	public HistoryView(String title){
		super(title);
	}

	public void refresh(){
		contentPanel.removeAll();
		contentPanel.revalidate();
		this.repaint();
		for(Order o:OPDept.getInstance().getOrders()){
			o.getStatus().processStatus(); 
			HistoryItem hi = new HistoryItem(o);
			historyItems.add(hi);
			contentPanel.add(hi);
		}
		contentPanel.revalidate();
		this.repaint();
	}
	
}
