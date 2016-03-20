package models;

import java.io.*;
import java.util.*;

public class Warehouse {

	private static Warehouse instance;
	
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private TreeSet<ProductStock> productStocks = new TreeSet<ProductStock>(new MyComparator());
	
	private Warehouse() {
		pullProductStockFromFile();
	}

	public static Warehouse getInstance() {
		if (instance == null) {
			instance = new Warehouse();
		}
		return instance;
	}
	
	public void increaseDecreaseStock(ProductStock productStock) {
		ProductStock toBeUpdated = new ProductStock(new Product("", 0), 0);
		for(ProductStock ps: productStocks){
			if(ps.getProduct().getName().equals(productStock.getProduct().getName())){
				toBeUpdated  = ps;
			}
		}
		productStocks.remove(toBeUpdated);
		productStocks.add(productStock);
		pushProductStockToFile();
	}

	public void addProduct(ProductStock productStock) {
		productStocks.add(productStock);
		pushProductStockToFile();
	}

	public void deleteProduct(ProductStock productStock) {
		productStocks.remove(productStock);
		pushProductStockToFile();
	}

	public TreeSet<ProductStock> filter(String filter) {
		TreeSet<ProductStock> filtered = new TreeSet<ProductStock>(new MyComparator());
		for(ProductStock ps: productStocks){
			if(ps.getProduct().getName().matches(".*(" + filter + ").*")){
				filtered.add(ps);
			}
		}
		return filtered;
	}

	public TreeSet<ProductStock> getProductStock() {
		return productStocks;
	}

	public void setProductStock(TreeSet<ProductStock> productStock) {
		this.productStocks = productStock;
	}

	public void pushProductStockToFile() {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("D:\\UT\\2nd year\\PT\\Lab\\HW2\\OrderManagement\\src\\models\\Stock"));
			for (ProductStock p : productStocks) {
				bw.write(p.getProduct().getName() + " " + p.getProduct().getPrice() + " " + p.getStock());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}

	}

	public void pullProductStockFromFile() {
		double price;
		String name;
		int stock;
		try {
			br = new BufferedReader(
					new FileReader("D:\\UT\\2nd year\\PT\\Lab\\HW2\\OrderManagement\\src\\models\\Stock"));
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				if (st.hasMoreTokens()) {
					name = st.nextToken();
					price = Double.parseDouble(st.nextToken());
					stock = Integer.parseInt(st.nextToken());
					productStocks.add(new ProductStock(new Product(name, price), stock));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public class MyComparator implements Comparator<ProductStock> {

		@Override
		public int compare(ProductStock p1, ProductStock p2) {
			return p1.getProduct().getName().compareTo(p2.getProduct().getName());
		}
	}

}
