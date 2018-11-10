package Ex1;

public class ex1_4_2 {
	
	
	public static boolean isPalindromeextend(String str) {
		
		int startIndex = 0;
		int endIndex = str.length()-1;
			while(startIndex-endIndex>1) {
				if(!Character.isAlphabetic(str.charAt(startIndex))) {
					startIndex++;
				}
				if(!Character.isAlphabetic(str.charAt(endIndex))) {
					endIndex--;
				}
				
				if (Character.toUpperCase(str.charAt(startIndex))!=Character.toUpperCase(str.charAt(endIndex))) {
				return false;
				} 
				else {
					startIndex++;
					endIndex--;
				}
		}
		return true;		
	}
	

		
	
	public static void main(String[] args) {
		String str = "Madam, I’m Adam!";
		System.out.print(str);
		System.out.print(isPalindromeextend(str));

	}
}


