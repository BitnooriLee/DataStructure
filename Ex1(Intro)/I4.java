package Ex1;

import java.lang.Character;

public class I4 {

    // copy the alphabets in a String into another String and return it
    // we have no option other than to copy here because String is "immutable"
    // i.e., the value of an object of type String cannot be changed
    private static String filterAlphabets(String str){
	StringBuilder sb = new StringBuilder();
	for(int i = 0; i < str.length(); i++){
	    char c = str.charAt(i);
	    if(Character.isAlphabetic(c))
		sb.append(Character.toUpperCase(c));
	}
	return sb.toString();
    }
    
    // check if the alphabets in the string form a palindrome
    public static boolean isPalindrome(String str){
	String cleanStr = filterAlphabets(str);
	return I3.isPalindrome2(cleanStr);
    }
    
}