package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Customer;

public class CustomerSample implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -528318986793977624L;
	List<Customer> customers;
	
	public CustomerSample(){
		customers=new ArrayList<Customer>();
	}
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	public List<Customer> getCustomers(){
		return customers;
	}
	public void print() {
		for (Customer customer:customers)
		System.out.println(customer.getName()+" "+customer.getPassword());
	}
}
