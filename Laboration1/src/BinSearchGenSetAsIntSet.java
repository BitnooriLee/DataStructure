import java.util.ArrayList;

public class BinSearchGenSetAsIntSet implements IntSet {
    private GenSet<Integer> set = new BinSearchGenSet<Integer>();
    
    @Override
    public void add(int element) {
        set.add(element);
    }

    @Override
    public boolean contains(int element) {
        return set.contains(element);
    }

    @Override
    public void remove(int element) {
        set.remove(element);
    }
    
    
	public static void main(String[] args) {
		
		GenSet<String> kurser = new BinSearchGenSet<>();
		kurser.add("DAT037");
		System.out.println(kurser.contains("DAT037")); // prints true
		
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

