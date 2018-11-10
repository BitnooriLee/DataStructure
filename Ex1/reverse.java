package Ex1;

public class reverse{

public static String reverseArray(String arr) {
	String revArray = "";
	for(int i=0; i<arr.length(); i++) {
		revArray += arr.charAt(arr.length()-i-1);
	}
	return revArray;
	
}

public static void main(String[] args) {
	String arr = "abcde";
	
	System.out.println(arr);
	System.out.print(reverseArray(arr));
	}
}