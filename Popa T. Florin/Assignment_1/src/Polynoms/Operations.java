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
	
	int[] subP(int[] polOne, int[] polTwo){
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
			result[i] = polOne[i] - polTwo[i];
		}
		
		return result;
	}
	
	int[] mulP(int[] temp1, int[] temp2){
		int size1, size2;
		size1 = temp1.length;
		size2 = temp2.length;
		
		int[] result = new int[size1 + size2];
		
		for(int i = 0; i < size1; i++){
			for(int j = 0; j < size2; j++){
				result[i + j] = result[i + j] + (temp1[i] * temp2 [j]); 
			}
		}			
		
		return result;
	}
	
	int[] derP(int[] temp){
		int size = temp.length;
		int result[] = new int[size - 1];
		
		for(int i = 1; i < size; i++){
			result[i-1] = temp[i] * i;
		}
		
		return result;
	}
	
	double[] intgP(double[] temp){
		int size = temp.length;
		double result[] = new double[size + 1];
		
		for(int i = 0; i < size; i++){
			result[i+1] = temp[i] / (i+1);
		}
		
		return result;
	}
	
	
}
