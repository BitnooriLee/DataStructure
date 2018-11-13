package Ex2;

import java.util.ArrayList;

public class InsertSort{
	
	int[] array = {4,6,8,2,9,5,1,7,3};
	
	public void Sort() {
		
		for(int i=1; i<array.length; i++) {
			for(int j=0; j<i; j++) {
				if(array[j]>array[i]) {
					int temp = array[i];
					Shift(j,i);
					array[j]=temp;
				
				}
				
			}
		}
	}
		
		
	
	
	public void Shift(int index, int insert) {
		for(int i=insert-1;i>index-1; i--) {
			array[i+1]=array[i];
		}
	}
	
	public void printArray() {
		for(int i=0; i<array.length;i++) {
			System.out.print(array[i]+" ");
			}
	}


	
	public static void main(String[] args) {
		
		InsertSort newArray = new InsertSort();
		newArray.Sort();
		System.out.println("This is sorted result with InsertSort ");
		//print Sorted array
		
		newArray.printArray();
		
	}
	
}


