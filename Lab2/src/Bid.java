
public class Bid {
   final public String name;
   final public String action;
   final public int bid;
   
   public int compare(Bid A) {
	  if (A.bid< this.bid) 
           return 1; 
       else if (A.bid> this.bid) 
           return -1; 
	   return 0;
   }

   public Bid(String name, String action, int bid) {
      this.name = name;
      this.action = action;
      this.bid = bid;
   }

   public int hashCode() {
      return 1 + 23*bid + 31*name.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Bid)) return false;
      /*check if an object is an instance of a specific class. 
       * You normally use it, when you have a reference or parameter to an object 
       * that is of a super class or interface type and 
       * need to know whether the actual object has some other type */

      Bid bid = (Bid) obj;

      return this.name.equals(bid.name) && (this.bid==bid.bid);
   }

   public String toString(){
        return this.name + " " + this.action + " " + this.bid;
   }
}



