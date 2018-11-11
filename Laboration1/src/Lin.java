

public class Lin implements IntSet{
	
	int[] set = new int[1];
	int size = 0;

	public void add(int element) {
	    	if(size==set.length) {
	    		int [] newSet = new int[2*set.length];
	    		for(int i=0; i<set.length; i++) {
	    			newSet[i] = set[i];
	    			}
	    		set = newSet;
	    		}
	    	set[size] = element;
	    	size++;
	    }
	public boolean contains(int element){
		if(indexOf(element) == -1) {
			return false;
			
		}return true;
	}
			
	public int indexOf(int element){
		for(int i=0; i<size; i++) {
			if(element == set[i]) {
				return i;
			}
		}
		return -1;
	}
			
	    	
	   
	public void remove(int element){
		int index = indexOf(element);
			while(contains(element)) {
			for(int i=index; i<size-1; i++) {
					set[i]=set[i+1]; // remove???
				}
			index = indexOf(element);
			}
		}
		
	public static void main(String[] args) {
        IntSet set = new Lin();
        set.add(1);
        set.add(2);
        set.add(1);
        set.remove(3);
        set.remove(1);
        System.out.println(set.contains(1)); // prints false
        System.out.println(set.contains(2)); // prints true
        System.out.println(set.contains(3)); // prints false

	}

}
