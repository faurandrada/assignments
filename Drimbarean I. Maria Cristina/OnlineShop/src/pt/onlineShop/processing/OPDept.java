package pt.onlineShop.processing;

import pt.onlineShop.models.*;

/**
 * @author Chiti
 **/
public class OPDept {
	private Warehouse warehouse=new Warehouse();
	private Administrator administrator=new Administrator();
	private Costumer costumer1=new Costumer();
	private Costumer costumer2=new Costumer();
	private SerializeObjects inOut=new SerializeObjects();
	public OPDept(){
		initializeDepartment();
	}
	public void initializeDepartment(){
		inOut.readAdministrator();
		inOut.readCostumer1();
		inOut.readCostumer2();
		inOut.readWarehouse();
		warehouse=inOut.getWarehouse();
		administrator=inOut.getAdministrator();
		costumer1=inOut.getCostumer1();
		costumer2=inOut.getCostumer2();
	}
	public void closeAdministratorSession(){
		inOut.writeAdministrator();
		inOut.setWarehouse(warehouse);
		inOut.writeWarehouse();
	}
	public void closeCostumer1session(){
		inOut.setCostumer1(costumer1);
		inOut.writeCostumer1();
		inOut.setWarehouse(warehouse);
		inOut.writeWarehouse();
	}
	public void closeCostumer2session(){
		inOut.setCostumer2(costumer2);
		inOut.writeCostumer2();
		inOut.setWarehouse(warehouse);
		inOut.writeWarehouse();
	}
	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}
	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	/**
	 * @return the administrator
	 */
	public Administrator getAdministrator() {
		return administrator;
	}
	/**
	 * @param administrator the administrator to set
	 */
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	/**
	 * @return the costumer1
	 */
	public Costumer getCostumer1() {
		return costumer1;
	}
	/**
	 * @param costumer1 the costumer1 to set
	 */
	public void setCostumer1(Costumer costumer1) {
		this.costumer1 = costumer1;
	}
	/**
	 * @return the costumer2
	 */
	public Costumer getCostumer2() {
		return costumer2;
	}
	/**
	 * @param costumer2 the costumer2 to set
	 */
	public void setCostumer2(Costumer costumer2) {
		this.costumer2 = costumer2;
	}
}
