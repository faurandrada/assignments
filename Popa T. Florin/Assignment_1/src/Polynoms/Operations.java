package Polynoms;

public class Operations {

	int[] addP(int[] polOne, int[] polTwo){
		int minSize, maxSize;
		
		if(polOne.length < polTwo.length){
			minSize = polOne.length;
			maxSize = polTwo.length;
		}
		else {
			minSize = polTwo.length;
			maxSize = polOne.length;
		}
		
		int[] result = new int[maxSize];
		
		for(int i = 0; i < minSize; i++){
			result[i] = polOne[i] + polTwo[i];
		}
		
		return result;
	}
	
	
}
