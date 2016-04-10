package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import io.IOStream;
import sample.CustomerSample;
import views.LoginView;

public class Runner {
	public static void main(String[] args) {
		new LoginView();
	/*   IOStream io=new IOStream();
		Warehouse wh = io.deserializeWarehouse();
		Product p = new Product(6, "Dress", "M", "Red", 200, 5);

		wh.add(p);
		System.out.println("AFTER ADD");
		wh.print();
		Product product=wh.findProduct(6, "Dress", "M", "Red", 200, 5);
		wh.remove(product);
		System.out.println("AFTER REMOVE");
		wh.print();*/
	//	System.out.println("AFTER ADD");
	//	wh.print();
	/*	IOStream io = new IOStream();
		Warehouse warehouse = new Warehouse();
		Order orders[] = new Order[4];
		Product products[] = new Product[5];
		OPDept ordersDept = new OPDept();
		Customer customer[] = new Customer[4];
		CustomerSample sample = new CustomerSample();

		customer[0] = new Customer("Mara Tatar", "mara");
		customer[1] = new Customer("Beyonce Knowles", "beyonce");
		customer[2] = new Customer("Kate Middleton", "kate");
		customer[3] = new Customer("Rihanna", "rihanna");
		sample.addCustomer(customer[0]);
		sample.addCustomer(customer[1]);
		sample.addCustomer(customer[2]);
		sample.addCustomer(customer[3]);
		try {

			FileOutputStream file3Out = new FileOutputStream("customers.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(file3Out);
			out2.writeObject(sample);
			out2.close();
			file3Out.close();
			System.out.println("Serialized data is saved in customers.ser");

		} catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("\nCUSTOMERS");
		io.deserializeCustomerSample();
		

		products[0] = new Product(1,"Dress", "M", "Blue", 200, 2);
		products[1] = new Product(2,"Blouse", "S", "Red", 200, 3);
		products[2] = new Product(3,"Socks", "L", "Pink", 200, 5);
		products[3] = new Product(4,"Trousers", "M", "Green", 200, 4);
		products[4] = new Product(5,"Hat", "M", "Orange", 200, 1);

		warehouse.add(products[0]);
		warehouse.add(products[1]);
		warehouse.add(products[2]);
		warehouse.add(products[3]);
		warehouse.add(products[4]);
		try {

			FileOutputStream fileOut = new FileOutputStream("warehouse.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(warehouse);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in warehouse.ser");

		} catch (IOException i) {
			i.printStackTrace();
		}

		System.out.println("\nWAREHOUSE AFTER ADDING PRODUCTS");
		io.deserializeWarehouse(); 
		warehouse.print();

		orders[0] = new Order(1,customer[0]);
		orders[0].addProduct(products[0]);

		orders[1] = new Order(2,customer[1]);
		orders[1].addProduct(products[1]);
		orders[1].addProduct(products[2]);

		orders[2] = new Order(3,customer[2]);
		orders[2].addProduct(products[3]);

		orders[3] = new Order(4,customer[3]);
		orders[3].addProduct(products[4]);

		ordersDept.addOrder(orders[0]);
		warehouse.processOrder(orders[0].getShoppingCart());
		ordersDept.addOrder(orders[1]);
		warehouse.processOrder(orders[1].getShoppingCart());
		ordersDept.addOrder(orders[2]);
		warehouse.processOrder(orders[2].getShoppingCart());
		ordersDept.addOrder(orders[3]);
		warehouse.processOrder(orders[3].getShoppingCart());

		try {

			FileOutputStream file2Out = new FileOutputStream("orders.ser");
			ObjectOutputStream out1 = new ObjectOutputStream(file2Out);
			out1.writeObject(ordersDept);
			out1.close();
			file2Out.close();
			System.out.println("Serialized data is saved in orders.ser");

		} catch (IOException i) {
			i.printStackTrace();
		}

		io.SerializeWarehouse(warehouse);
		System.out.println("\nWAREHOUSE AFTER PROCESSING ORDERS");
		io.deserializeWarehouse(); //
		warehouse.print();
		io.SerializeWarehouse(warehouse);

		System.out.println("OPDEPT AFTER ADDING PRODUCTS");
		io.deserializeOPDept(); 
		ordersDept.print();
		io.SerializeOPdept(ordersDept);

	}*/

}
}