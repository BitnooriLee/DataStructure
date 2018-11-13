package Ex1;

public class ex1_1 {

	static void reverseArray(String name[]){
		for(int i=0;i<name.length/2;i++) {
			String temp = name[i];
			name[i] = name[name.length-i-1];
			name[name.length-i-1] =temp;
		}	
		return; 
	}
	
	static void printArray(String arr[]) {
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		String [] name = {"a","b","c","d","e"};
		printArray(name);
		reverseArray(name);
		printArray(name);
		
	}
}


//
