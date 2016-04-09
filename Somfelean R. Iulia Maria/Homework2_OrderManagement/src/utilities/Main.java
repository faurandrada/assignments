package utilities;

import controllers.AdminController;
import controllers.LoginController;
import controllers.SerializationController;
import controllers.UserController;
import model.Customer;
import model.OPDept;
import model.Order;
import model.OrderedProduct;
import model.Product;
import model.Warehouse;
import view.AdminView;
import view.LogInView;
import view.UserView;
import view.LogInView;

public class Main {

	public static void main(String[] args) {

		SerializationController sc = new SerializationController();

		Customer c1 = new Customer(1, UserType.REGULAR_USER, "Somfelean Iulia", "iulia", "1234", "00000",
				"iuliasomfelean@yahoo.com");

		Warehouse w = new Warehouse();

		Product p1 = new Product(1, "Book1", "Author1", "Publisher1", 34.4, 10);
		Product p2 = new Product(2, "Book2", "Author2", "Publisher1", 45, 1);
		Product p3 = new Product(3, "Book3", "Author1", "Publisher1", 14, 33);
		Product p4 = new Product(4, "Book4", "Author4", "Publisher4", 43.6, 4);

		w.addProduct(p1);
		w.addProduct(p2);
		w.addProduct(p3);
		w.addProduct(p4);

		System.out.println("Initial version of the warehouse");
		w.view();

		OPDept opdept = new OPDept();


		/*
		OrderedProduct op = new OrderedProduct(p1, 3);
		order.addOrderedProduct(op);
		OrderedProduct op1 = new OrderedProduct(p3, 19);
		order.addOrderedProduct(op1);

		// order.printOrder();

		Order order1 = new Order(2, 1);

		OrderedProduct op2 = new OrderedProduct(p2, 1);
		order1.addOrderedProduct(op2);
		OrderedProduct op3 = new OrderedProduct(p4, 3);
		order1.addOrderedProduct(op3);

		// order1.printOrder();

		Order order1 = new Order(0, 1);

		OrderedProduct op2 = new OrderedProduct(p2, 1);
		order1.addOrderedProduct(op2);
		OrderedProduct op3 = new OrderedProduct(p4, 3);
		order1.addOrderedProduct(op3);
		
		
	//	opdept.addOrders(order);
		opdept.addOrders(order1);

	//	System.out.println("OPDEPT STARTs");
		opdept.processOrders();

		sc.serializeOrders(opdept);
*/
		w = sc.deserializeWarehouse();
		opdept = sc.deserializeOrders();
		
		AdminView av = new AdminView();
		AdminController ac = new AdminController(av, sc);
		UserView uv = new UserView();
		UserController uc = new UserController(uv,c1, sc);
		 uv.setVisible(true);
		 av.setVisible(true);
	//	LogInView loginview = new LogInView();
	//	new LoginController(loginview, av, uv);

		
		sc.serializeOrders(opdept);
		sc.serializeWarehouse(w);
		/*
		 * Customer c1 = new Customer(1, UserType.REGULAR_USER,
		 * "Somfelean Iulia", "iulia", "1234", "00000" ,
		 * "iuliasomfelean@yahoo.com"); Order order = new Order(); OPDept opdept
		 * = new OPDept();
		 * 
		 * SerializationController sc = new SerializationController();
		 * 
		 * Product p1 = new Product(1, "Book1", "Author1", "Publisher1", 34.4,
		 * 10); Product p2 = new Product(2, "Book2", "Author2", "Publisher1",
		 * 45, 1); Product p3 = new Product(3, "Book3", "Author1", "Publisher1",
		 * 14, 33); Product p4 = new Product(4, "Book4", "Author4",
		 * "Publisher4", 43.6, 4);
		 * 
		 * p1.printProduct();
		 * 
		 * Warehouse w = new Warehouse(); w.addProduct(p1); w.addProduct(p2);
		 * w.addProduct(p3); w.addProduct(p4);
		 * 
		 * w.view();
		 * 
		 * 
		 * 
		 * //LogInFrame login = new LogInFrame(); //AdminView adminview = new
		 * AdminView();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * w = sc.deserializeWarehouse(); //opdept = sc.deserializeOrders();
		 * 
		 * OrderedProduct op = new OrderedProduct(p1, 1);
		 * order.addOrderedProduct(op); OrderedProduct op1 = new
		 * OrderedProduct(p3, 20); order.addOrderedProduct(op1);
		 * //System.out.println("hrll");
		 * 
		 * order.printOrder();
		 * 
		 * opdept.addOrders(order); opdept.processOrders();
		 * 
		 * //w.view();
		 * 
		 * //w= sc.deserializeWarehouse();
		 * 
		 * 
		 * 
		 * AdminView av = new AdminView(); AdminController ac = new
		 * AdminController(av, w, sc); UserView uv = new UserView();
		 * UserController uc = new UserController(uv, w, order, c1, sc);
		 * LogInView loginview = new LogInView(); new LoginController(loginview,
		 * av, uv);
		 * 
		 * 
		 * ;
		 */
		
	}
}
