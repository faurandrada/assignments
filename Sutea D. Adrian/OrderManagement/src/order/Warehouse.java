package order;

import java.io.*;
import java.util.*;

public class Warehouse {
	private Product product;
	private ArrayList<Product> prods;
	File file1 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\items.txt");
	File file2 = new File("D:\\Documents\\JavaProj\\OrderManagement\\res\\prices.txt");
	Scanner fileScanner;
	private String name = new String();
	private int quantity;

	public Warehouse() {
		try {
			PrintWriter wr = new PrintWriter(file2);
			wr.print("");
			wr.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileScanner = new Scanner(file1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		prods = new ArrayList<Product>();
		while (fileScanner.hasNext()) {
			product = new Product();
			name = fileScanner.next();
			quantity = Integer.parseInt(fileScanner.next());
			product.setName(name);
			product.setQuantity(quantity);
			product.setPrice(quantity*2/3);
			prods.add(product);
			try (FileWriter fw = new FileWriter(file2, true)) {
				fw.write(product.getName() + " ");
				fw.write(product.getPrice() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
		fileScanner.close();
	}

	public void addItem(File file, String s, int q, int x) {
		try (FileWriter fw = new FileWriter(file, true)) {
			fw.write(s + " ");
			fw.write(q + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		product = new Product();
		product.setName(s);
		product.setQuantity(q);
		product.setPrice(x);
		try (FileWriter fw = new FileWriter(file2, true)) {
			fw.write(s + " ");
			fw.write(x + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		prods.add(product);
	}

	public void removeItem(File file, String s) {
		for (int i = 0; i < prods.size(); i++) {
			product = prods.get(i);
			if (Objects.equals(product.getName(), s)) {
				prods.remove(i);
				removeLineFromFile(file, s+" "+product.getQuantity());
				removeLineFromFile(file2, s+" "+product.getPrice());
			}
		}
	}

	public void updateQuantity(String s, int x) {
		int newQuantity;
		for (int i = 0; i < prods.size(); i++) {
			product = prods.get(i);
			if (Objects.equals(product.getName(), s)) {
				newQuantity=product.getQuantity()+x;
				updateLineFromFile(file1, s+" "+product.getQuantity(), s+" "+newQuantity);
				product.setQuantity(product.getQuantity() + x);
			}
		}
	}

	public void removeLineFromFile(File file, String lineToRemove) {
		try {
			if (!file.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}
			File tempFile = new File(file.getAbsolutePath() + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (!line.trim().equals(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();
		 
			if (!file.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			if (!tempFile.renameTo(file))
				System.out.println("Could not rename file");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateLineFromFile(File file, String lineToUpdate, String updatedLine) {
		try {
			if (!file.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}
			File tempFile = new File(file.getAbsolutePath() + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (!line.trim().equals(lineToUpdate)) {
					pw.println(line);
					pw.flush();
				}
				else{
					pw.println(updatedLine);
					pw.flush();
				}
			}
			pw.close();
			br.close();
			if (!file.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			if (!tempFile.renameTo(file))
				System.out.println("Could not rename file");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public String searchName(String s){
		String temp = new String();
		String low = new String();
		for (int i = 0; i < prods.size(); i++) {
			product = prods.get(i);
			low = product.getName().toLowerCase();
			if (low.contains(s.toLowerCase())) {
				temp=temp+product.getName()+" "+product.getPrice()+"\n";
			}
		}
		return temp;
	}
	
	public String searchPrice(int x){
		String temp = new String();
		for(int i=0;i<prods.size();i++){
			product=prods.get(i);
			if(x>=product.getPrice()){
				temp=temp+product.getName()+" "+product.getPrice()+"\n";
			}
		}
		return temp;
	}
	
	public Product isProduct(String s){
		for(int i=0;i<prods.size();i++){
			product=prods.get(i);
			if(Objects.equals(product.getName(), s))
				return product;
		}
		return null;
	}
	
}
