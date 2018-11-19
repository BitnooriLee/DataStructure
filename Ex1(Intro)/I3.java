package Ex1;

import java.lang.Character;

public class I3 {

    // copy the value of a string into a Character[] and return it
    private static Character[] copyCharArray(String str){
	Character[] chars = new Character[str.length()];
	for(int i = 0; i < str.length(); i++)	    
	    chars[i] = str.charAt(i);
	return chars;
    }

    // re-uses reverse from earlier, but copies data twice
    public static boolean isPalindrome1(String str){
	Character[] original = copyCharArray(str);
	Character[] copy     = copyCharArray(str);
	I1.reverse(copy);
	return copy.equals(original);
    }

    // does not copy anything, but re-produces reverse like code
    public static boolean isPalindrome2(String str){
	int n = str.length(), last = n - 1;
	boolean isPalindrome = true;
	for(int i = 0; i < n / 2; i++)	    
	    isPalindrome = isPalindrome && str.charAt(i) == str.charAt(last - i);  //?
	return isPalindrome;
    }
    
}