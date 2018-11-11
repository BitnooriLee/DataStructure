public class Bin2 implements IntSet{
	
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
		for(int i=0; i<set.length; i++) {
			if(element == set[i]) {
				return i;
			}
		}
		return -1;
	}
			  	
	   
	public void remove(int element){
		int index = indexOf(element);
		int[] newSet = new int[set.length-1];
        for(int i=0; i<index; i++) {
            newSet[i]=set[i];
        }
        while(contains(element)) {
            for(int i=index; i<newSet.length; i++) {
                System.out.println(newSet.length + ", " + set.length);
                newSet[i]=set[i+1]; // remove???
            }
            set = newSet;
            index = indexOf(element);
        }
    }
		
	public static void main(String[] args) {
        IntSet set = new Bin2();
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