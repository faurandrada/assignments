package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import models.*;

public class UserRepository {

	private static UserRepository instance;
	
	private BufferedReader br = null;
	private Admin admin;
	private Customer customer;

	private UserRepository() {
		pullUsersFromFile();
	}
	
	public static UserRepository getInstance() {
		if (instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}

	public Admin getAdmin() {
		return admin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void pullUsersFromFile() {
		String username;
		String pass;
		String type;
		try {
			br = new BufferedReader(
					new FileReader("D:\\UT\\2nd year\\PT\\Lab\\HW2\\OrderManagement\\src\\models\\Users"));
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				if (st.hasMoreTokens()) {
					username = st.nextToken();
					pass = st.nextToken();
					type = st.nextToken();
					if (type.equals("admin")) {
						admin = new Admin(username, pass);
					}else{
						customer = new Customer(username, pass);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
