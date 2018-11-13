package Ex1;

public class isPalindrome {

	
	public static String reverseArray(String arr) {
		String revArray = "";
		for(int i=0; i<arr.length(); i++) {
			revArray += arr.charAt(arr.length()-i-1);
		}
		return revArray;	
	}
	
	public static void checkPalindrome(String arr){
		
		if(reverseArray(arr).equals(arr)) {
			System.out.println("It's palindrome");
		}else {
			System.out.println("It's not palindrome");
		}
	}

	public static void main(String[] args) {
		String arr = "madamimadam";
		
		System.out.println(arr);
		System.out.println(reverseArray(arr));
		System.out.println((1+2)/2);
		checkPalindrome(arr);
		
		}
	}



