package Ex2;

public class MergeSort {

	
	
	 void merge(int arr[], int l, int m, int r) {
		
		 int Left[] = new int[m-l+1];
		 int Right[] = new int[r-m];
		 
		 for(int i=0; i<m-l+1; i++) {
			 Left[i] = arr[i+l];
		 }
		 
		 for(int j=0; j<r-m; j++) {
			 Right[j] = arr[j+m+1];
		 }
		 
		 
		 int i=0, j=0; 
		 int k=l;
		 
		 
		 while((i<m-l+1)&&(j<r-m)) {
			 
			 if(Left[i]<=Right[j]) {
				 
				 arr[k]=Left[i];
				 i++;
				 k++;
			 } 
			 else {
				 
				 
				 arr[k]=Right[j];
				 j++;
				 k++;
			 }
			 
		 }
		 
		 while(i<m-l+1) {
			 arr[k]=Left[i];
			 i++;
			 k++;
			 
		 }
		 while (j<r-m) {
			 
			 arr[k]=Right[j];
			 j++;
			 k++;
		 }	 
		 
	 }
	 
	 
	 void sort(int arr[], int l, int r) {
		 
		 if(l<r) {
			 
			 
			 int m = (l+r)/2;
			 
			 sort(arr,l,m);
			 sort(arr,m+1,r);
			
			 merge(arr, l, m, r);
		 	}
			 
		 }
		 
		 
	 static void printArray(int arr[]) {
		 
		 for(int i=0; i<arr.length; i++) {
			 
			 System.out.print(arr[i]+ " ");
		 }
	 }
	 
	 
	 
	public static void main(String[] args) {
		
		int arr[] = {12, 11, 13, 5, 6, 7}; 
		
		System.out.println("Given array");
		printArray(arr);
		 
		MergeSort sortedArray = new MergeSort();
		
		System.out.println("\nSorted array");
		sortedArray.sort(arr,0,arr.length-1);
		printArray(arr);
		

	}

}
