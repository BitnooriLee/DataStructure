package Ex1;

import Ex2.QuickSort;

public class GenericReverse {

	
	public static <E> void reverse(E[] a) {
		
		if(a==null) {
			throw new IllegalArgumentException();
		} // throws if array missing
		
		int low = 0;
		int high = a.length-1; // index to swap;
		
		
		//keep swapping until the low index has passed the high index
		
		while(low<high) {
			
			E tmp = a[low];
			a[low] = a[high];
			a[high] = tmp;
			
			low++;
			high--;
			
		}	
	}
	
	public static <E> void  reverse2(E[] a) {
		
		if(a == null) {
			throw new IllegalArgumentException();
		}
		
		for(int i=0; i<a.length/2; i++) {
			E tmp = a[i];
			a[i] = a[a.length-1];
			a[a.length-1] = tmp;
			}
		}
	
	
	public static <E> void printArray(E[] arr) { 
		
		for(int i=0; i<arr.length; i++) {
			
			System.out.print(arr[i]+" ");
		}				
	}
	 
	
	public static void main(String[] args) {
	
		Integer[] a= new Integer[]{12, 11, 13, 5, 6, 7}; 
		
		System.out.println("array");
		printArray(a);
		 
		GenericReverse reversedArray = new GenericReverse();
		
		System.out.println("\nreversed array with method1");
		reversedArray.reverse(a);
		printArray(a);
		
		System.out.println("\nreversed array with method2");
		
		reversedArray.reverse2(a);
		
		printArray(a);
		
		
	}

}
