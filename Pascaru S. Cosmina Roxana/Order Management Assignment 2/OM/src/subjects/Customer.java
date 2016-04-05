package subjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable,Comparable<Customer>{

	private String name;
	private String address;
	private String email;
	private String phone;
	private String password;

	public Customer(String name,  String password,String address, String email, String phone) {
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public int compareTo(Customer c) {
		return name.compareTo(c.getName());
	}

}
