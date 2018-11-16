package Ex2;

import java.util.Arrays;
import java.util.Comparator;

public class Ex5_remove_duplicate{
	
	public static <E> void removeDuplicates(E[] a, Comparator<? super E> c) {
		
		if(a==null||c==null|| hasNulls(a)) {
			
			throw new IllegalArgumentException();
			}
		
		Arrays.sort(a,c);
		
		
		for(int i=0; i<a.length-1;i++) {
			if(c.compare(a[i],a[i+1])==0) {
				a[i]=null;
			}
		}
		
	}
	
	
	private static <E> boolean hasNulls(E[] a) {
		for(E anA : a) {
			if (anA == null) return true;
		}
		return false;
	}
	
	private static <E> boolean unique(E[] a, Comparator<? super E> c) {
		for(int i = 0; i< a.length; i++) {
			for(int j = 0; j<a.length; j++) {
				if(a[i]!= null &&
						a[j]!= null &&
						c.compare(a[i],a[j])==0 && i !=j)
					return false;
				
			}
		}	
		return true;	
	}
	private static final int N =7;
	
	
	private static boolean runtest(Integer[] a, Comparator<Integer> c, int m) {
		if( m == a.length) {
			Integer[] b = a.clone();
			removeDuplicates(b,c);
			return unique(b,c);
			}
		
		for(int i=1; i<=N; i++) {
			a[m] = i;
			if(!runtest(a, c, m+1)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Integer[] a = new Integer[N];
		
		Comparator<Integer> c = new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return Integer.compare(i1, i2);
			}
		};
	
	
	System.out.println("S6: " + (runtest(a,c,0) ? "Test Ok" : "Test Failed"));
	
	}
}
