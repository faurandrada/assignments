package pt.onlineShop.processing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pt.onlineShop.models.Administrator;
import pt.onlineShop.models.Costumer;
import pt.onlineShop.models.Order;
import pt.onlineShop.models.Product;
import pt.onlineShop.models.Warehouse;

/**
 * Read and write the state of the warehouse,admin and costumers
 * @author Chiti
 *
 */
public class SerializeObjects {
	private Warehouse warehouse=new Warehouse();
	private Administrator administrator=new Administrator();
	private Costumer costumer1=new Costumer();
	private Costumer costumer2=new Costumer();

	public void writeWarehouse() {
		try (FileOutputStream fs = new FileOutputStream("warehouse.bin")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeInt(warehouse.getWarehouse().size());
			for (Product product : warehouse.getWarehouse())
				os.writeObject(product);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readWarehouse() {
		try (FileInputStream fs = new FileInputStream("warehouse.bin")) {
			ObjectInputStream os = new ObjectInputStream(fs);
			int warehouseSize=os.readInt();
			for (int i=0;i<warehouseSize;i++)
				warehouse.addProduct((Product)os.readObject());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeAdministrator() {
		try (FileOutputStream fs = new FileOutputStream("administrator.bin")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);
			Administrator admin=new Administrator();
			admin.setName("Administartor");
			admin.setPassword("1234");
			os.writeObject(admin);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readAdministrator() {
		try (FileInputStream fs = new FileInputStream("administrator.bin")) {
			ObjectInputStream os = new ObjectInputStream(fs);
			administrator=(Administrator)os.readObject();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeCostumer1() {
		try (FileOutputStream fs = new FileOutputStream("costumer1.bin")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);
			costumer1.setUsername("Cristina");
			costumer1.setPasswoard("12");
			os.writeObject(costumer1);
			os.writeInt(costumer1.getOrders().size());
			for (Order i:costumer1.getOrders())
				os.writeObject(i);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setCostumer2(Costumer costumer2) {
		this.costumer2 = costumer2;
	}

	public void readCostumer1() {
		try (FileInputStream fs = new FileInputStream("costumer1.bin")) {
			ObjectInputStream os = new ObjectInputStream(fs);
			costumer1=(Costumer)os.readObject();
			int warehouseSize=os.readInt();
			for (int i=0;i<warehouseSize;i++)
				costumer1.getOrders().add((Order)os.readObject());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeCostumer2() {
		try (FileOutputStream fs = new FileOutputStream("costumer2.bin")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);
			costumer2.setUsername("Guest");
			costumer2.setPasswoard("0000");
			os.writeObject(costumer2);
			os.writeInt(costumer2.getOrders().size());
			for (Order i:costumer2.getOrders())
				os.writeObject(i);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readCostumer2() {
		try (FileInputStream fs = new FileInputStream("costumer2.bin")) {
			ObjectInputStream os = new ObjectInputStream(fs);
			costumer2=(Costumer)os.readObject();
			int warehouseSize=os.readInt();
			for (int i=0;i<warehouseSize;i++)
				costumer2.getOrders().add((Order)os.readObject());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}
	/**
	 * @return the administrator
	 */
	public Administrator getAdministrator() {
		return administrator;
	}
	/**
	 * @return the costumer1
	 */
	public Costumer getCostumer1() {
		return costumer1;
	}
	/**
	 * @return the costumer2
	 */
	public Costumer getCostumer2() {
		return costumer2;
	}

	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @param administrator the administrator to set
	 */
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	/**
	 * @param costumer1 the costumer1 to set
	 */
	public void setCostumer1(Costumer costumer1) {
		this.costumer1 = costumer1;
	}

	/**
	 * @param costumer2 the costumer2 to set
	 */
}
