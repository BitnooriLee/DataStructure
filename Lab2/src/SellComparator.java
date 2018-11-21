
public class SellComparator implements Comparator<Bid> {
	
	public int compare(Bid a, Bid b) {
		
		if (a.bid < b.bid) 
	           return 1; 
	    else 
	           return -1; 
		
	}

}
