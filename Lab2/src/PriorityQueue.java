import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
    private ArrayList<E> heap = new ArrayList<E>();
    private Comparator<E> comparator;
    private Map<E, Integer> hashMap = new HashMap<>(); // ???

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
    // Complexity O(1)
    public E minimum() {
		assert invariant() : showHeap();
        if (size() == 0)
            throw new NoSuchElementException();
		assert invariant() : showHeap();
        return heap.get(0);
    }

    // Gets element sat index i from heap.
    // Complexity O(1)
    public E get(int i) {
		assert invariant() : showHeap();
        if(size() == 0) {
            throw new NoSuchElementException();
        }
		assert invariant() : showHeap();
        return heap.get(parent(leftChild(i)));
    }
    
    //Updates element from oldBid to newBid.
    // Complexity O(log n)
    public void update(E oldBid, E newBid) {
    	delete(oldBid);
        insert(newBid);
        hashMap.remove(oldBid);
    }
    
    // Returns index of given element using a hashmap
    // Complexity O(1)
    public int indexOf(E x) {
		assert invariant() : showHeap();
    	if (hashMap.get(x) != null) { 
    		assert invariant() : showHeap();
    		return hashMap.get(x); 
    	} 
    	else { 
    		assert invariant() : showHeap();
    		return -1;
    	} 
    }
    
    // Deletes given element from heap and sifts up or down to keep invariant.
    // Complexity O(log n)
    public void delete(E x) {
		assert invariant() : showHeap();
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
		assert invariant() : showHeap();
    }
    
    // Removes the smallest item in the priority queue.
    // Throws NoSuchElementException if empty.
    // Complexity O(log n)
    public void deleteMinimum() {
		assert invariant() : showHeap();
        if (size() == 0)
            throw new NoSuchElementException();

        heap.set(0, heap.get(heap.size()-1));
        hashMap.put(heap.get(heap.size()-1), 0);
        hashMap.remove(heap.get(heap.size()-1));
        
        heap.remove(heap.size()-1);
        
        if (heap.size() > 0) siftDown(0);
		assert invariant() : showHeap();
    }

    // Returns the root element of heap and deletes it. Complexity O(log n)
    public E poll() {
		assert invariant() : showHeap();
    	E val = minimum();
    	delete(val);
		assert invariant() : showHeap();
    	return val;
    }
    
    // Sifts a node up.
    // siftUp(index) fixes the invariant if the element at 'index' may
    // be less than its parent, but all other elements are correct.
    // Complexity O(log n)
    private void siftUp(int nodeIndex) {
        int parentIndex;
        E tmp;
        if (nodeIndex != 0) {
              parentIndex = parent(nodeIndex);
              if (comparator.compare(heap.get(parentIndex), heap.get(nodeIndex)) > 0) {
                    tmp = heap.get(parentIndex);
                    E node = heap.get(nodeIndex);
                    heap.set(parentIndex, node);
                    heap.set(nodeIndex, tmp);
                    
                    // Update hashmap
                    int indexTemp = hashMap.get(tmp);
                    hashMap.put(tmp, nodeIndex);
                    hashMap.put(node, indexTemp);
                    
                    siftUp(parentIndex);
              }
        }
  }
    // Sifts a node down.
    // siftDown(index) fixes the invariant if the element at 'index' may
    // be greater than its children, but all other elements are correct.
    // Complexity O(log n)
    
    private void siftDown(int index) {
        E value = heap.get(index);

        // Stop when the node is a leaf.
        while (leftChild(index) < heap.size()) {
            int left    = leftChild(index);
            int right   = rightChild(index);

            // Work out whether the left or right child is smaller.
            // Start out by assuming the left child is smaller�
            int child = left;
            E childValue = heap.get(left);

            // �but then check in case the right child is smaller.
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
                hashMap.put(childValue, index);
                index = child;
                
            } else break;
        }
        heap.set(index, value);
       hashMap.put(value, index);
    }
    
    // Insert a new element int the correct position in heap. Complexity O(log n)
    public void insert( E x )
    {
		 assert invariant() : showHeap();
     	 heap.add(x);
     	 hashMap.put(x, heap.size()-1);
 		 siftUp(heap.size()-1);
 		 assert invariant() : showHeap();
    }
        
    // Helper functions for calculating the children and parent of an index. All have complexity O(1)
    private final int leftChild(int index) {
        return 2*index+1;
    }

    private final int rightChild(int index) {
        return 2*index+2;
    }

    private final int parent(int index) {
        return (index-1)/2;
    }
    
 // Check that the property of the binary heap holds. Complexity O(n)
 	private boolean invariant(){
 		int current = 0;
 		while(leftChild(current) < size() && rightChild(current) < size()) {
 			E left = heap.get(leftChild(current));
 			E right = heap.get(rightChild(current));
 			// If child element is less than its parent class is not invariant.
 			if(comparator.compare(left, heap.get(current)) < 0 ||
 					comparator.compare(right, heap.get(current)) < 0) {
 				return false;
 			}
 			current += 1;
 		}
 		return true;
 	}
 	
 	// Goes through every row of the heap and draws it on screen. Complexity O(n)
 	private String showHeap(){
 		StringBuilder sb = new StringBuilder();
 		int current = 0;
 		int prevRow = 0;
 		while(current < size()) {
 			// New row if current element is a power of 2
 			if(current+1 == Math.pow(2, prevRow)) {
 				sb.append(System.getProperty("line.separator"));
 				prevRow += 1;
 			}
 			sb.append(heap.get(current) + " ");
 			current += 1;
 		}
 		
 		return sb.toString();
 	}
    
}