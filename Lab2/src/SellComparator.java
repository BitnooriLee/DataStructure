public class SellComparator implements Comparator<Bid>{
    public int compare(Bid a, Bid b) {
    	if(a.bid > b.bid) {
    		return 1;
    	}
    	if(a.bid == b.bid && a.name == b.name) {
    		return 0;
    	}
    	else {
    		return -1;
    	}
    }
}


