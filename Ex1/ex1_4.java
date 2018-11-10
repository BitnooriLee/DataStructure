package Ex1;

public class ex1_4 {
	
	
	public static boolean isPalindrome(String str) {
		for(int i = 0; i<str.length()/2; i++) {
			if (str.charAt(i)!=str.charAt(str.length()-i-1)) {
				return false;
				}			
		}
		return true;		
	}
	
	public static String arrayConvertChar(String arr) {
		String convertedArray= "";
		for(int i=0; i<arr.length(); i++) {
			if(Character.isAlphabetic(arr.charAt(i)))
				convertedArray +=Character.toUpperCase(arr.charAt(i));
		}
		return convertedArray;
	}
		
	
	public static void main(String[] args) {
		String str = "Madam, I’m Adam!";
		str = arrayConvertChar(str);
		System.out.print(str);
		System.out.print(isPalindrome(str));

	}
}


