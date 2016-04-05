package tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import subjects.Customer;
import subjects.ProductStock;

public class CustomersTableModel extends AbstractTableModel{

	private String[] columnNames =
	    {
	        "Name",
	        "Address",
	        "Email",
	        "Phone"
	    };
	private ArrayList<Customer> customers;
	
	public CustomersTableModel()
    {
		customers = new ArrayList<Customer>();
    }
	
	public CustomersTableModel(ArrayList<Customer> customers)
    {
		this.customers = customers;
    }
	
	@Override
	public int getColumnCount() {
		 return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
		 return customers.size();
	}
	public String getColumnName(int column){
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		Customer customer = getCustomer(row);
		switch(column){
		case 0: return customer.getName();
		case 1: return customer.getAddress();
		case 2: return customer.getEmail();
		case 3: return customer.getPhone();
		}
		return customer;
	}

	public Customer getCustomer(int row){
		return customers.get(row);
	}
	
	public void addCustomer(Customer customer){
		insertCustomer(getRowCount(),customer);
	}
	
	public void insertCustomer(int row, Customer customer){
		customers.add(row,customer);
		fireTableRowsInserted(row, row);
	}
	
	public void removeCustomer(int row){
		customers.remove(row);
		fireTableRowsDeleted(row,row);
	}
	
}
