public class BinSearchIntSet implements IntSet{

		
	int[] set = new int[1];
	int indexOfElement = 0;
	int size = 0;

	/*public void add(int element) {
					
		if(!contains(element)) {
			int[] newSet = new int[set.length+1];
			newSet[] = 
			
			indexOfElement = newSet.length-1;
			for(int i=0; i<indexOfElement; i++) {
				newSet[i] = set[i];
			}		    	
			newSet[indexOfElement] = element;
			
			for(int i=indexOfElement+1;i<set.length;i++) {
				newSet[i+1] = set[i];
			}
			set = newSet;
		}
			
	   return;
	   
	 }*/
	
	
	public void add(int element) {
    	if(!(contains(element))) {
    		int [] newSet = new int[size+1];
    		for(int i=0; i<size; i++) {
    			newSet[i] = set[i];
    			}
    		newSet[size] = element;
    		set = newSet;
    		sort();
    		size++;
    		}
    	}
		
		public boolean contains(int element){
			sort();
			
			int upperBound=size-1;
			int lowerBound = 0;
			
			
			while(lowerBound <= upperBound) {
				int mid = (lowerBound+upperBound)/2;
				if(element<set[mid]) {
					upperBound = mid -1;
				}
				else if(element>set[mid]) {
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
			int temp = 0;
			for(int i=0; i<set.length-1; i++) {
				for(int j=0; j<set.length-i-1; j++) {
					if(set[j]>set[j+1]) {
					temp = set[j];
					set[j] =set[j+1];
					set[j+1]=temp;
					}
				}			
			}
			return;
		}
		
		public int indexOf(int element){
			for(int i=0; i<set.length; i++) {
				if(element == set[i]) {
					return i;
				}
			}
			return -1;
		}
		
						    	
		  		   
		/*public void remove(int element){
			int index = indexOf(element);
			if (index == -1) return;
			if(set.length==0) return;
			
			int[] newSet = new int[set.length-1];
			
				for(int i=0; i<index; i++) {
					newSet[i]=set[i];
				}
				while(contains(element)) {
				for(int i=index; i<set.length-1; i++) {
						newSet[i]=set[i+1]; // remove???
					}
				set = newSet;
				index = indexOf(element);
				}
			}*/
		
		
	/*	public void remove(int element){
			//if (index == -1) return;
			if(set.length==0) return;
			
			int[] newSet = new int[set.length-1];
			
				for(int i=0; i<indexOfElement; i++) {
					newSet[i]=set[i];
				}
				while(contains(element)) {
				for(int i=indexOfElement; i<set.length-1; i++) {
						newSet[i]=set[i+1]; // remove???
					}
				set = newSet;
				//index = indexOfElement;
				}
			}
		*/
		
		   
		public void remove(int element){
			int index = indexOf(element);
				while(contains(element)) {
				for(int i=index; i<size-1; i++) {
						set[i]=set[i+1]; // remove???
					}
				index = indexOf(element);
				size--;
				}
			}
			
		
		public static void main(String[] args) {
	        IntSet set = new BinSearchIntSet();
	        set.add(1);
	        set.add(2);
	        set.add(1);
	        set.remove(3);
	        set.remove(1);
	        System.out.println(set.contains(1)); // prints false
	        System.out.println(set.contains(2)); // prints true
	        System.out.println(set.contains(3)); // prints false
	        System.out.println(set.contains(0)); // prints false

		}

	}
