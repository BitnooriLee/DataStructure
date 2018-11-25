import java.io.*;
import java.util.*;

public class Lab2 {


   public String pureMain(String[] commands) {
	   
	   PriorityQueue<Bid> buy_pq = new PriorityQueue<>(new BuyComparator());
	   PriorityQueue<Bid> sell_pq = new PriorityQueue<>(new SellComparator());

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

            //System.out.println("data: " + name + ", " + action + ", " + price + (parts.length==4?(", " + parts[3]):""));

         if( action.equals("K") ) {
            // TODO: add new buy bid
                buy_pq.insert(new Bid(name, action, price));
         } else if( action.equals("S") ) {
            // TODO: add new sell bid
                sell_pq.insert(new Bid(name, action, price));
         } else if( action.equals("NK") ){
            // TODO: update existing buy bid. use parts[3].
            //    int index = buy_pq.indexOf(new Bid(name, "K", price));
        	 Bid oldBid = new Bid(name, "K", Integer.parseInt(parts[2]));
     	 	 Bid newBid = new Bid(name, "K", Integer.parseInt(parts[3]));
     	 	 buy_pq.update(oldBid, newBid);

         } else if( action.equals("NS") ){
            // TODO: update existing sell bid. use parts[3].
             //   int index = sell_pq.indexOf(new Bid(name, "S", price));
        	 	Bid oldBid = new Bid(name, "S", Integer.parseInt(parts[2]));
        	 	Bid newBid = new Bid(name, "S", Integer.parseInt(parts[3]));
        	 	sell_pq.update(oldBid, newBid);
                
         } else {
            throw new RuntimeException(
                  "line " + line_no + ": invalid action");
         }

         if( sell_pq.size() == 0 || buy_pq.size() == 0 ) continue;
         
         String sellerName;
         String buyerName;
         Integer priceAmount;
                  
         if(sell_pq.get(0).bid <=  buy_pq.get(0).bid) {
        	 
        	 Bid buyer = buy_pq.poll();
        	 Bid seller = sell_pq.poll();
        	 
        	 sellerName = seller.name;
        	 buyerName = buyer.name;
        	 priceAmount = seller.bid;

        	 sb.append(buyerName + " buys a share from " + sellerName + 
 		 			" for " + Integer.toString(priceAmount) + " kr\n");
         }
         // TODO:
         // compare the bids of highest priority from each of
         // each priority queues.
         // if the lowest seller price is lower than or equal to
         // the highest buyer price, then remove one bid from
         // each priority queue and add a description of the
         // transaction to the output.
      }

      sb.append("\nOrderbook:\n");

      sb.append("Sellers: ");
      
      int sellSize = sell_pq.size();
      for(int i = 0; i < sellSize; i++) {
    	  Bid min = sell_pq.poll();
    	  sb.append(min.name + " " + min.bid);
    	  if(i != sellSize - 1) {
    		  sb.append(", ");
    	  }
      }
      
//      for(int i = 0; i < sell_pq.size(); i++) {
//    	  sb.append(sell_pq.get(i).name + " " + sell_pq.get(i).bid);
//    	  if(i != sell_pq.size() - 1) {
//    		  sb.append(", ");
//    	  }
//      }

      sb.append("\nBuyers: ");
      int buySize = buy_pq.size();
      for(int i = 0; i < buySize; i++) {
    	  Bid min = buy_pq.poll();
    	  sb.append(min.name + " " + min.bid);
    	  if(i != buySize - 1) {
    		  sb.append(", ");
    	  }
      }
      
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