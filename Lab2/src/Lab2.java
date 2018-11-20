import java.io.*;
import java.util.*;

public class Lab2 {

    public class Bid {
        final public String name;
        final public String action;
        final public int bid;

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

   public String pureMain(String[] commands) {
      // TODO: declaration of two priority queues
        ArrayList<Bid> buy_pq = new ArrayList<>();
        ArrayList<Bid> sell_pq = new ArrayList<>();

      StringBuilder sb = new StringBuilder();

      for(int line_no=0;line_no<commands.length;line_no++){
         String line = commands[line_no];
         if( line.equals("") )continue;

         String[] parts = line.split("\\s+"); /*matches sequence of one or more whitespace characters.*/
         if( parts.length != 3 && parts.length != 4)
            throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
         String name = parts[0];
         if( name.charAt(0) == '\0' )
            throw new RuntimeException("line " + line_no + ": invalid name");
         String action = parts[1];
         int price;
         try {
            price = Integer.parseInt(parts[2]); /* convert the string into its integer equivalent*/
         } catch(NumberFormatException e){
            throw new RuntimeException("line " + line_no + ": invalid price");
         }

            System.out.println("data: " + name + ", " + action + ", " + price + (parts.length==4?(", " + parts[3]):""));

         if( action.equals("K") ) {
            // TODO: add new buy bid
                buy_pq.add(new Bid(name, action, price));
         } else if( action.equals("S") ) {
            // TODO: add new sell bid
                sell_pq.add(new Bid(name, action, price));
         } else if( action.equals("NK") ){
            // TODO: update existing buy bid. use parts[3].
                int index = buy_pq.indexOf(new Bid(name, "K", price));
                buy_pq.remove(index);
                buy_pq.add(index, new Bid(name, "K", Integer.parseInt(parts[3])));

         } else if( action.equals("NS") ){
            // TODO: update existing sell bid. use parts[3].
                int index = sell_pq.indexOf(new Bid(name, "S", price));
                sell_pq.remove(index);
                sell_pq.add(index, new Bid(name, "S", Integer.parseInt(parts[3])));
         } else {
            throw new RuntimeException(
                  "line " + line_no + ": invalid action");
         }

         if( sell_pq.size() == 0 || buy_pq.size() == 0 ) continue;

         // TODO:
         // compare the bids of highest priority from each of
         // each priority queues.
         // if the lowest seller price is lower than or equal to
         // the highest buyer price, then remove one bid from
         // each priority queue and add a description of the
         // transaction to the output.

      }

        System.out.println("----NS 업데이트가 완료된 ArrayList------");
        for (int i=0; i<sell_pq.size(); i++) {
            System.out.println(sell_pq.get(i).toString());
        }

        System.out.println("----NK 업데이트가 완료된 ArrayList------");
        for (int i=0; i<buy_pq.size(); i++) {
            System.out.println(buy_pq.get(i).toString());
        }

        System.out.println("----------------------------------------");

      sb.append("Order book:\n");

      sb.append("Sellers: ");
      // TODO: print remaining sellers.
      //       can remove from priority queue until it is empty.

      sb.append("Buyers: ");
      // TODO: print remaining buyers
      //       can remove from priority queue until it is empty.

      return sb.toString();
   }

   public static void main(String[] args) throws IOException {
      final BufferedReader actions;
      if( args.length != 1 ){
         actions = new BufferedReader(new InputStreamReader(System.in));
      } else {
         actions = new BufferedReader(new FileReader(args[0]));
      }

      List<String> lines = new LinkedList<String>();
      while(true){
         String line = actions.readLine();
         if( line == null)break;
         lines.add(line);
      }
      actions.close();



        Lab2 lab2 = new Lab2();
      System.out.println(lab2.pureMain(lines.toArray(new String[lines.size()])));
   }
}