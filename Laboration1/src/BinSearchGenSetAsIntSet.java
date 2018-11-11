


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
    
}