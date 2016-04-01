package Stock;

import java.util.Set;
import java.util.TreeSet;

import Control.Helper;

public class Warehouse {

	private Set<Product> stock;
	private Helper helper;

	public void updateStock() {
		stock = new TreeSet<Product>();
		helper = new Helper();
		helper.readFromSerFile(stock, "stock.bin");
	}

	public Set<Product> getStock() {
		return stock;
	}

	public void addProduct(Product product) {
		stock.add(product);
		helper.updateSerFile(stock, "stock.bin");
	}

	public void removeProduct(Product product) {
		stock.remove(product);
		helper.updateSerFile(stock, "stock.bin");
	}
	
	public void modifyCurrentStock(){
		helper.updateSerFile(stock, "stock.bin");
	}
	
	public void seeStock(){
		for(Product product : stock){
			System.out.println(product);
		}
	}
}