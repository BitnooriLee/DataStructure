/*public class LinSearchIntSet implements IntSet {
   
   int[] set = new int[1];
   int size = 0;

   public void add(int element) {
      if(size==set.length) {
         int [] newSet = new int[2*set.length]; // 1) 여기서 기존 어레이가 다 차버리면 크기로 2배로 늘림, 그래서 입력된 원소의 개수의 뒷부분은 '0'으로 가득차게됨
         for(int i=0; i<set.length; i++) {
            newSet[i] = set[i];
         }
         set = newSet;
      }
      set[size] = element;
      size++; // 2) 여기서 현재까지 입력된 원소의 개수를 카운트
   }

   public boolean contains(int element) {
      if (indexOf(element) == -1) {
         return false;
      }
        return true;
   }
         
   public int indexOf(int element) {
      for (int i=0; i<size; i++) { // 3) 여기서 뒤의 '0'으로 가득찬 부분을 무시하기 위해 'size'까지만 유무 여부를 확인
         if (element == set[i]) {
            return i;
         }
      }
      return -1;
   }

   public void remove(int element) {
      int index = indexOf(element);

      if (index == -1) return;
      
      int[] newSet = new int[set.length-1];
      
      for (int i=0; i<index; i++) {
         newSet[i] = set[i];
      }
      while (contains(element)) {
    	 index = indexOf(element);
         for (int i=index; i<set.length-1; i++){ // 5) 마찬가지로 뒤의 '0'은 볼 필요가 없으니께네~
            newSet[i]=set[i+1]; // remove???
         }
         set = newSet;
            size--; // 4) 원소가 제거될 때마다 size를 감소시켜줌
         //index = indexOf(element);
      }
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
        IntSet set = new LinSearchIntSet();
        set.add(1);
        set.add(2);
        set.add(1);
        set.remove(3);
        set.remove(1);
        System.out.println(set.contains(1)); // prints false
        System.out.println(set.contains(2)); // prints true
        System.out.println(set.contains(3)); // prints false
        System.out.println(set.contains(0));
   }

   }
   
   */


public class LinSearchIntSet implements IntSet{
	
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
			size--;
			}
		}
	
		
	public static void main(String[] args) {
        IntSet set = new LinSearchIntSet();
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



