
public class Test {

	
	public static void main(String[] args){
		
		Server s = new Server();
		Thread th =new Thread(s);
		th.start();
	}
}
