
import java.util.ArrayList;


public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E> {
   
	ArrayList<E> set = new ArrayList<E>();
	
    public void add(E element) {
    	if(!set.contains(element)) {
    	set.add(element);
    	}
    }
    public boolean contains(E element) {
    	return set.contains(element);
    }
    public void remove(E element) {
    	while(set.contains(element)) {
    		set.remove(element);
    	}
    }


	public static void main(String[] args) {
	
	GenSet<String> kurser = new BinSearchGenSet<>();
	kurser.add("DAT037");
	System.out.println(kurser.contains("DAT037")); // prints true
	
	}
}