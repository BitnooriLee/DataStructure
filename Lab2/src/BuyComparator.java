public class BuyComparator implements Comparator<Bid> {
	
	public int compare(Bid a, Bid b) {
		
		if(a.bid < b.bid) {
    		return 1;
    	}
    	if((a.toString().equals(b.toString()))) {
    		return 0;
    	}
    	else {
    		return -1;
    	}
		
	}

}




/*public class BuyComparator implements Comparator<Bid> {
	
	public int compare(Bid a, Bid b) {
		
		if (a.bid > b.bid) 
	           return 1; 
	    else 
	           return -1; 
		
	}

}*/
