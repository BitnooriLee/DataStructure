package Ex1;

import java.util.Arrays;

public class I1 {
	/**
	 * Reverse the order of all elements in the array.
	 *
 	 * Given array of length N, the time complexity is O(N).
	 *
	 * @param a the array to reverse
	 * @throws IllegalArgumentException if array missing
	 */
	public static <E> void reverse(E[] a){
		if( a == null ){
			throw new IllegalArgumentException(); 
		}

		// two variables to keep track of the current two indices to swap
			int i = 0;
			int j = a.length-1;

			while(i<j) {
		// keep swapping until the low index has passed the high index
				E temp = a[i];
				a[i]=a[j];
				a[j] =temp;
			// swap elements between indices low and high
				i++;
				j--;
		}
	}

	/**
	 * Reverse the order of all elements in the array.
	 *
 	 * Given array of length N, the time complexity is O(N).
	 *
	 * @param a the array to reverse
	 * @throws IllegalArgumentException if array missing
	 */
	public static <E> void reverse2(E[] a){
		if(a==null) {
			throw new IllegalArgumentException();
		}

		// traverse over the left indices in all pairs of elements to swap.
		// note that a.length/2 is correct for both even and odd amounts
		// of elements.
		
			for(int i=0; i<a.length/2; i++) {
			// swap elements between indices i and a.length-1-i
				E tmp = a[i];
				a[i] =a[a.length-1-i];
				a[a.length-1-i] = tmp;
		}
	}

	private static boolean runtest() {
		// test with odd array length
		Integer[] a = new Integer[]{4,7,8,2,0};
		reverse(a);
		if( ! Arrays.equals(a,new Integer[]{0,2,8,7,4}) )return false;
		reverse2(a);
		if( ! Arrays.equals(a,new Integer[]{4,7,8,2,0}) )return false;

		// test with even array length
		a = new Integer[]{4,7,8,2,0,3};
		reverse(a);
		if( ! Arrays.equals(a,new Integer[]{3,0,2,8,7,4}) )return false;
		reverse2(a);
		if( ! Arrays.equals(a,new Integer[]{4,7,8,2,0,3}) )return false;

		return true;
	}

	public static void main(String[] args) {
		System.out.println("I2: " + (runtest() ? "TEST OK" : "TEST FAILED"));
	}
}