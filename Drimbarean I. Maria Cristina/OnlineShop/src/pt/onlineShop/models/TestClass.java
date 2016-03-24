package pt.onlineShop.models;

import java.util.Collections;

import pt.onlineShop.processing.*;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       SerializeObjects s=new SerializeObjects();
       //s.writeCostumer1();
       //s.writeCostumer2();
       s.readCostumer1();
       s.readCostumer2();
       System.out.println(s.getCostumer1().getUsername()+s.getCostumer1().getPasswoard());
       System.out.println(s.getCostumer2().getUsername()+s.getCostumer2().getPasswoard());
       for (Order i:s.getCostumer1().getOrders())
    	   System.out.println(i.toString());
	}

}
