package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Product implements Entity,Serializable{
	
	private int ID;
	private String item;
	private String size;
	private String color;
	private double price;
	private int stock;
	
	public Product(int ID,String item,String size,String color,double price,int stock){
		this.ID = ID;
		this.item=item;
		this.size=size;
		this.color=color;
		this.setPrice(price);
		this.setStock(stock);
	}

	public String getItem(){
		return item;
	}
	public String setItem(String item){
		return item;
	}
	public String getSize(){
		return size;
	}
	public String setSize(String size){
		return size;
	}
	public String getColor(){
		return color;
	}
	public String setColor(String color){
		return color;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	public int getStock(){
		return stock;
	}
	public void setStock(int stock){
		this.stock=stock;
	}
	public int getID(){
		return ID;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	
	
}
