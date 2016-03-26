package pt.onlineShop.models;

import java.util.TreeSet;

/**
 * In this class we have all the products.
 * A tree set that holds all products.Search the product with BinarySearchTree
 * @author Chiti
 */
public class Warehouse {
  private TreeSet<Product> warehouse=new TreeSet<Product>();
  
  public void addProduct(Product product){
	  warehouse.add(product);
  }
  public void deleteProduct(Product product){
	  warehouse.remove(product);
  }
 public boolean containsProduct(Product product){
	 if (warehouse.contains(product))
		 return true;
	 return false;
 }
 public Product search(String name){
	 for(Product p:warehouse)
		 if (p.compareTo(new Product(name))==0)
			 return p;
	 return null;
 }
 public void incrementStockOnProduct(String name,int value){
	 for(Product p:warehouse)
		 if (p.compareTo(new Product(name))==0)
		 {
			 p.incrementStock(value);
		 }
 }
 public void decrementStockOnProduct(String name,int value){
	 for(Product p:warehouse)
		 if (p.compareTo(new Product(name))==0)
		 {
			 p.decrementStock(value);
			 if (p.getStock()<0)
				 p.setStock(0);
		 }
 }
 public void updatePriceOnProduct(String name,int price){
	 for(Product p:warehouse)
		 if (p.compareTo(new Product(name))==0)
		 {
			 p.setPrice(price);
		 }
 }
/**
 * @return the warehouse
 */
public TreeSet<Product> getWarehouse() {
	return warehouse;
}

/**
 * @param warehouse the warehouse to set
 */
public void setWarehouse(TreeSet<Product> warehouse) {
	this.warehouse = warehouse;
}
  
}
