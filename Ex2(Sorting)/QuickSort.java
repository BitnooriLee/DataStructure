package Ex2;

public class QuickSort {


	
	int partition(int[] a, int low, int high) {
		
		
		int pi = a[high];
		int i = low-1;
		
		for(int j=low; j<high; j++) {
			
			if(a[j]<=pi) {
				
				i++;
				
				int temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				
			}
		}
		
		int temp = a[i+1];
		a[i+1] = a[high];
		a[high] = temp;
		
		return i+1;
	}
	
	
	
	
	
	// call as sort(a, 0, a.length-1);
	void sort(int[] a, int low, int high) {
		
		if (low >= high) return;
			int pivot = partition(a, low, high);
				// assume that partition returns the
				// index where the pivot now is
			sort(a, low, pivot-1);
			sort(a, pivot+1, high);
			
	}
	 
	

	public static void printArray(int arr[]) { 
		
		for(int i=0; i<arr.length; i++) {
			
			System.out.print(arr[i]+" ");
		}				
	}
	 
	public static void main(String[] args) {
	
		int arr[] = {12, 11, 13, 5, 6, 7}; 
		
		System.out.println("Given array");
		printArray(arr);
		 
		QuickSort sortedArray = new QuickSort();
		
		System.out.println("\nSorted array");
		sortedArray.sort(arr,0,arr.length-1);
		printArray(arr);
		

	}

}
