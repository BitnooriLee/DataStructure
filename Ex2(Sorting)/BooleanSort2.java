package Ex2;

import java.util.Comparator;

public class BooleanSort2 {
    /**
     * Sorts an array of booleans.
     *
     * Given array size of N, the method takes O(N) time and uses O(1) extra
     * space.
     *
     * @param a the array
     * @throws IllegalArgumentException if array is missing
     */
    public static void boolSort1(boolean[] a){
        if( a == null ){
            throw new IllegalArgumentException();
        }

        int low = 0;
        int high = a.length - 1;
        while( true ){
            while( high >= 0 && a[high] == true ){
                high--;
            }
            while( low < a.length && a[low] == false ){
                low++;
            }

            if( low >= high )
                break;

            // swap true and false
            boolean t = a[low];
            a[low] = a[high];
            a[high] = t;
        }
    }

    /**
     * Sorts an array of booleans.
     *
     * Given array size of N, the method takes O(N) time and uses O(1) extra
     * space.
     *
     * @param a the array
     * @throws IllegalArgumentException if array is missing
     */
    public static void boolSort2(boolean[] a){
        if( a == null ){
            throw new IllegalArgumentException();
        }

        int fi = 0;

        // find first true element
        while( fi < a.length && a[fi] == false ){
            fi++;
        }

        for(int ti = fi+1 ; ti < a.length ; ti++ ){
            assert fi < a.length && a[fi] == true;

            // continue until false element found
            if( a[ti] == true){
                continue;
            }

            // swap true and false
            boolean t = a[fi];
            a[fi] = a[ti];
            a[ti] = t;

            // find next true element
            while( fi < a.length && a[fi] == false ){
                fi++;
            }
        }
    }

    private static boolean isSorted(boolean[] a){
        int i = 0;

        // find first true
        while( i < a.length ){
            if( a[i] == true )
                break;
            i++;
        }

        assert i == a.length || a[i] == true;

        // find next false
        while( i < a.length ){
            if( a[i] == false )
                return false;
            i++;
        }

        assert i == a.length;

        // found no false after first true
        return true;
    }

    private static final int N = 7;

    private static boolean runtest(boolean[] a, Comparator<Boolean> c, int m) {
        if( m == a.length ){
            boolean[] b;

            b = a.clone();
            boolSort1(b);
            if( ! isSorted(b) )return false;

            b = a.clone();
            boolSort2(b);
            if( ! isSorted(b) )return false;

            return true;
        }

        a[m] = false;
        if( !runtest(a, c, m+1) ){
            return false;
        }

        a[m] = true;
        if( !runtest(a, c, m+1) ){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        boolean[] a = new boolean[N];

        Comparator<Boolean> c = new Comparator<Boolean>(){
            public int compare(Boolean b1, Boolean b2){
                return Boolean.compare(b1, b2);
            }
        };

        System.out.println("S5: " + (runtest(a,c,0) ? "TEST OK" : "TEST FAILED"));
    }
}