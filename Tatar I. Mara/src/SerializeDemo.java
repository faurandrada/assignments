import java.io.*;


public class SerializeDemo {

	public void serializeWarehouse(Warehouse warehouse) {

		try {
			FileOutputStream fileOut = new FileOutputStream("C:/Users/marat/Desktop/warehouse.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(warehouse);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in warehouse.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
		

	}

	public void serializeOrders(OPDept orders) {

		try {
			FileOutputStream fileOut = new FileOutputStream("C:/Users/marat/Desktop/orders.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(orders);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in warehouse.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
