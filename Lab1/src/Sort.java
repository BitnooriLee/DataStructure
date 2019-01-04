public class Sort {
    
   int[] set = {1,5,3,4,2};
   int[] newSet = new int[set.length-1];
    
   public void sorting() {
      int temp = 0;
      for(int i=0; i<set.length-1; i++) {
         for(int j=0; j<set.length-1; j++) {
            if(set[j]<set[j+1]) {
                    temp = set[j];
                    set[j] = set[j+1];
                    set[j+1] = temp;
            }
         }
      }
      return;
   }

   public static void main(String[] args) {
      Sort m = new Sort();
      m.sorting();
      for(int i =0; i<m.set.length; i++) {
          System.out.println(m.set[i]);
      }
   }
}


