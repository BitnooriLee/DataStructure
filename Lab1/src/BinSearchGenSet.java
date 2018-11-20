
import java.util.ArrayList;


public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E> {
   
	ArrayList<E> set = new ArrayList<E>();
	
    public void add(E element) {
    	if(!set.contains(element)) {
    	set.add(element);
    	sort();
    	}
    }
    public boolean contains(E element) {
    	//return set.contains(element);
    	
		int upperBound=set.size()-1;
		int lowerBound = 0;
		int indexOfElement = 0;
				
		while(lowerBound <= upperBound) {
			int mid = (lowerBound+upperBound)/2;
			if(set.get(mid).compareTo(element)>0) {
				upperBound = mid -1;
			}
			else if(set.get(mid).compareTo(element)<0) {
				lowerBound = mid +1;
			}
			else{
				indexOfElement = mid;
					return true;
				
				}
			}
		return false;
    }
    
    public void sort() {
		E temp;
		for(int i=0; i<set.size()-1; i++) {
			for(int j=0; j<set.size()-i-1; j++) {
				if(set.get(j).compareTo(set.get(j+1))>0) {
				temp = set.get(j);
				set.set(j,set.get(j+1));
				//set.get(j)=set.get(set.indexOf(j+1));
				set.set(j+1,temp);
				}
			}			
		}
		return;
	}
    
    public void remove(E element) {
    	while(set.contains(element)) {
    		set.remove(element);
    	}
    }


	public static void main(String[] args) {
	
	GenSet<String> kurser = new BinSearchGenSet<>();
	kurser.add("DAT037");
	IntSet set = new BinSearchGenSetAsIntSet();
	set.remove(1);
	set.add(2);
	set.add(1);
	System.out.println(kurser.contains("DAT037")); // prints true
	
	}
}