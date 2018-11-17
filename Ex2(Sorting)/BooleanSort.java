/*Suppose you have an array of booleans of size n. 
 * Give an O(n) algorithm that sorts the array so that all false elements 
 * come before all true elements. Your algorithm should use O(1) extra memory. */


package Ex2;

public class BooleanSort {
	
	Boolean[] array = {true,true,false,false,true,false,true,false,true,false};
	
	public void	Sort() {
		
		int count = 0;
		
		Boolean[] newarray = new Boolean[array.length];
		
		for(int i=0; i<array.length;  i++) {
			
			if(array[i]==false){
				count++;
			}
		}
		
		for(int i=0; i< count; i++) {
			
			newarray[i] = false;
		}
		
		for(int i= count; i<array.length; i++) {
			
			newarray[i]= true;
		}
		
		array = newarray;
			
	}
		
	
	public void printArray() {
		for(int i=0; i<array.length;i++) {
			//System.out.print(array[i]+" ");
			}
		
		for(Boolean anA : array) {
			System.out.print(anA+" ");
		}
	}



	public static void main(String[] args) {
		
		BooleanSort array = new BooleanSort();
		array.Sort();
		
		array.printArray();
		
	}

}
