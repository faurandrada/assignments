package models;

import java.io.Serializable;

public class Customer implements Serializable{
	private String name;
	private String username;
	private String pass;
	public Customer(String username,String pass){
		this.setName(username);
		this.username=username;
		this.pass=pass;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return pass;
	}
	public void setPassword(String pass){
		this.pass=pass;
	}
}
