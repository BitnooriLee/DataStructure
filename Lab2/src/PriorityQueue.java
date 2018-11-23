import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
    private ArrayList<E> heap = new ArrayList<E>();
    private Comparator<E> comparator;

    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Returns the size of the priority queue.
    public int size() {
        return heap.size();
    }

    // Adds an item to the priority queue.
    public void add(E x)
    {
        throw new UnsupportedOperationException();
    }
    
    // Returns the smallest item in the priority queue.
    // Throws NoSuchElementException if empty.
    public E minimum() {
        if (size() == 0)
            throw new NoSuchElementException();

        return heap.get(0);
    }

    public E get(int i) {
        if(size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(parent(leftChild(i)));
    }
    
    public int indexOf(E x) {
    	for(int i = 0; i < heap.size(); i++) {
    		if(comparator.compare(heap.get(i), x) == 0) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public void delete(E x) {
    	int elementIndex = indexOf(x);
    	if(elementIndex == -1) {
    		return;
    	}
    	if(heap.size()==0) {
    		return;
    	}
    	
    	E y = heap.get(heap.size()-1);
    	heap.set(elementIndex, y);
    	heap.remove(heap.size()-1);

    	if(elementIndex == heap.size() || heap.size() == 0) {
    		// Element was the last element, nothing needs to be done
    		return;
    	}
    	siftDown(elementIndex);
    	if(comparator.compare(y, heap.get(parent(elementIndex))) < 0) {
    		siftUp(elementIndex);
    	}
    	if(comparator.compare(y, heap.get(parent(elementIndex))) > 0) {
    		siftDown(elementIndex);
    	}
    }
    
    // Removes the smallest item in the priority queue.
    // Throws NoSuchElementException if empty.
    public void deleteMinimum() {
        if (size() == 0)
            throw new NoSuchElementException();

        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        if (heap.size() > 0) siftDown(0);
    }

    public E poll() {
    	E val = minimum();
    	delete(val);
    	return val;
    }
    
    // Sifts a node up.
    // siftUp(index) fixes the invariant if the element at 'index' may
    // be less than its parent, but all other elements are correct.
    private void siftUp(int nodeIndex) {
        int parentIndex;
        E tmp;
        if (nodeIndex != 0) {
              parentIndex = parent(nodeIndex);
              if (comparator.compare(heap.get(parentIndex), 
            		  heap.get(nodeIndex)) > 0) {
                    tmp = heap.get(parentIndex);
                    heap.set(parentIndex, heap.get(nodeIndex));
                    heap.set(nodeIndex, tmp);
                    siftUp(parentIndex);
              }
        }
  }

    // Sifts a node down.
    // siftDown(index) fixes the invariant if the element at 'index' may
    // be greater than its children, but all other elements are correct.
    private void siftDown(int index) {
        E value = heap.get(index);

        // Stop when the node is a leaf.
        while (leftChild(index) < heap.size()) {
            int left    = leftChild(index);
            int right   = rightChild(index);

            // Work out whether the left or right child is smaller.
            // Start out by assuming the left child is smaller...
            int child = left;
            E childValue = heap.get(left);

            // ...but then check in case the right child is smaller.
            // (We do it like this because maybe there's no right child.)
            if (right < heap.size()) {
                E rightValue = heap.get(right);
                if (comparator.compare(childValue, rightValue) > 0) {
                    child = right;
                    childValue = rightValue;
                }
            }

            // If the child is smaller than the parent,
            // carry on downwards.
            if (comparator.compare(value, childValue) > 0) {
                heap.set(index, childValue);
                index = child;
            } else break;
        }
        heap.set(index, value);
    }

    public void insert( E x )
   {
    	 heap.add(x);
		 siftUp(heap.size()-1);
   }
        
    // Helper functions for calculating the children and parent of an index.
    private final int leftChild(int index) {
        return 2*index+1;
    }

    private final int rightChild(int index) {
        return 2*index+2;
    }

    private final int parent(int index) {
        return (index-1)/2;
    }
}