package pt.onlineShop.models;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * The class can add products.Erase products.Process orders.
 * Change stock(found in wearhouse).
 * @author Chiti
 *
 */
public class Administrator implements Serializable{
	private static final long serialVersionUID = 2499836154055582707L;
	private String password;
	private String name;
	/**
	 *  Add a product in warehouse
	 * @param warehouse
	 * @param name
	 * @param value
	 */
  public void addProductInWarehouse(Warehouse warehouse,String name,int value,int price){
	  if (!warehouse.containsProduct(new Product(name)))
	    warehouse.addProduct(new Product(name,value,price));
	  else infoBox("Product already exists!","Administrative issue");
  }
  /**
   * delete a product from warehouse
   * @param warehouse
   * @param name
   */
  public void deleteProductInWarehouse(Warehouse warehouse,String name){
	  if (warehouse.containsProduct(new Product(name)))
	    warehouse.deleteProduct(new Product(name));
	  else infoBox("Product do not exist!","Administrative issue");
  }
  /**
   * Change the stock by adding elements
   * @param warehouse
   * @param name
   * @param value
   */
  public void incrementStockOfproduct(Warehouse warehouse,String name,int value){
	  if (warehouse.containsProduct(new Product(name)))
	    warehouse.incrementStockOnProduct(name, value);
	  else infoBox("Product do not exist!","Administrative issue");
  }
  /**
   * updates price on product
   * @param warehouse
   * @param name
   * @param price
   */
  public void updatePriceOnProduct(Warehouse warehouse,String name,int price){
	  if (warehouse.containsProduct(new Product(name)))
	    warehouse.updatePriceOnProduct(name, price);
	  else infoBox("Product do not exist!","Administrative issue");
  }
  /**
   * Change the stock by deleting elements
   * @param warehouse
   * @param name
   * @param value
   */
  public void decrementStockOfproduct(Warehouse warehouse,String name,int value){
	  if (warehouse.containsProduct(new Product(name)))
	    warehouse.decrementStockOnProduct(name, value);
	  else infoBox("Product do not exist!","Administrative issue");
  }
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
  /**
   * A info box to use when the action should not be done
   * @param infoMessage
   * @param titleBar
   */
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
