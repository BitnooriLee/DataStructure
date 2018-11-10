package Ex1;

public class ex1_1_2 {

	
public static <T> void reverse(T[] array) {
        for (int i = array.length - 1; i >= 0; i--)
            System.out.println(array[i]);
}


	
public static <T> void reverseArray(T[] name){
	for(int i=0;i<name.length/2;i++) {
		T temp = name[i];
		name[i] = name[name.length-i-1];
		name[name.length-i-1] =temp;
		}	
	return; 
	}

static <T> void printArray(T[] arr) {
	for (int i=0; i<arr.length; i++)
		System.out.print(arr[i]+" ");
	System.out.println();
}

public static void main(String[] args) {
	
	String [] name = {"1","2","3","d","e"};
	printArray(name);
	reverseArray(name);
	printArray(name);
	
	}
}


//E[]

