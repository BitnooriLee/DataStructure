int search(Comparable[] array, Comparable key) {
    int low = 0;
    int high = array.length - 1;
    
    while(low <= high) {
        int mid = (low + high) / 2;        
        if (array[mid].compare(key) < 0) {
        // array[mid] < key
            low = mid+1;
        } else if (array[mid].compare(key) > 0) {
        // array[mid] > key
            high = mid-1;
        } else {
        // array[mid] == key
            return mid;
        }
    }
    return -1;
}