package Ex1;

public class ex1_2 {


public static void interleaves(String arr1, String arr2){

	String arr3 = "";
	String shortString = arr1;
	String longString = arr2;
	
	if(arr1.length()>arr2.length()) {
		shortString = arr2;
		longString = arr1;
		} 
	for(int i =0; i<shortString.length(); i++) {
		arr3 += shortString.charAt(i);
		arr3 += longString.charAt(i);
		}
	for(int j =shortString.length(); j<longString.length(); j++) {
		arr3 += longString.charAt(j);
		
	}
	System.out.print(arr3);	
}

public static void main(String[] args) {
	
	String arr1 = "anna";
	String arr2 = "patrik";
	interleaves(arr1,arr2);
	
	}
}
