package Ex1;

public class I2 {
	/**
	 * Interleaves the characters of two strings by taking one character at a
	 * time, starting with the first string.
	 * e.g <code>"abc"</code> and <code>"defghi"</code> becomes
	 * <code>"adbecfghi"</code>.
	 *
	 * Given strings of length N and M, the time complexity is O(N+M) because
	 * the time complexity of the StringBuilder append method is amortized O(1).
	 * So all N+M append operations take O(N+M) time.
	 *
	 * @param s1 the 1st string
	 * @param s2 the 2nd string
	 * @throws IllegalArgumentException if either string missing
	 * @return the new string
	 */
	public static String interleave(String s1, String s2) {
		if(s1==null|| s2==null) {
			throw new IllegalArgumentException();
		}
		
		StringBuilder s3 = new StringBuilder(s1.length() + s2.length());
		
		int minlen = Math.min(s1.length(), s2.length());
		
		for(int i=0; i<minlen; i++) {			
			s3.append(s1.charAt(i));
			s3.append(s2.charAt(i));
		}
		
		String larger  = s1.length() > s2.length() ? s1:s2;
		
		for(int i= minlen; i<larger.length(); i++) {
			
			s3.append(larger.charAt(i));
			
		}
	
			return new String(s3);
	}

	/**
	 * Interleaves the characters of two strings by taking one character at a
	 * time, starting with the first string.
	 * e.g <code>"abc"</code> and <code>"defghi"</code> becomes
	 * <code>"adbecfghi"</code>.
	 *
	 * This implementation uses string concatenation ("+"), which is
	 * inefficient since a new string is created over and over again.
	 *
	 * Given strings of length N and M, the time complexity is O((N+M)^2)
	 * because string concatenation is linear in the length of the strings.
	 * If adding one character at a time, the cost of adding N characters
	 * become become O(1+2+..+N) = O(N^2).
	 *
	 * @param s1 the 1st string
	 * @param s2 the 2nd string
	 * @throws IllegalArgumentException if either string missing
	 * @return the new string
	 */
	public static String interleave2(String s1, String s2) {
		if( s1 == null || s2 == null ){
			throw new IllegalArgumentException();
		}

		String s3 = "";

		// length of the shortest string
		int minlen = Math.min(s1.length(), s2.length());

		for(int i = 0 ; i < minlen ; i++) {
			s3 += s1.charAt(i);
			s3 += s2.charAt(i);
		}

		// the longest string
		String maxstr = s1.length() > s2.length() ? s1 : s2;

		for(int i = minlen ; i < maxstr.length() ; i++) {
			s3 += maxstr.charAt(i);
		}

		return s3;
	}

	/**
	 * Interleaves the characters of two strings by taking one character at a
	 * time, starting with the first string.
	 * e.g <code>"abc"</code> and <code>"defghi"</code> becomes
	 * <code>"adbecfghi"</code>.
	 *
	 * This implementation uses an array of characters instead of
	 * StringBuilder. This would have been necessary if StringBuilder was
	 * not available.
	 *
	 * Given strings of length N and M, the time complexity is O(N+M) since
	 * a single array with enough space is allocated. No reallocation needed.
	 * Each array index is written to exactly once.
	 *
	 * @param s1 the 1st string
	 * @param s2 the 2nd string
	 * @throws IllegalArgumentException if either string missing
	 * @return the new string
	 */
	public static String interleave3(String s1, String s2) {
		if( s1 == null || s2 == null ){
			throw new IllegalArgumentException();
		}

		// create an array for all characters in the new string
		char[] s3 = new char[s1.length() + s2.length()];

		// length of the shortest string
		int minlen = Math.min(s1.length(), s2.length());

		int i = 0;
		while(i < minlen) {
			s3[2*i] = s1.charAt(i);
			s3[2*i+1] = s2.charAt(i);
			i++;
		}

		// the longest string
		String maxstr = s1.length() > s2.length() ? s1 : s2;

		assert i == minlen;
		while(i < maxstr.length()) {
			s3[minlen + i] = maxstr.charAt(i);
			i++;
		}

		return new String(s3);
	}

	private static boolean runtest() {
		if( ! interleave("abc","defghi").equals("adbecfghi") )return false;
		if( ! interleave("patrik","anna").equals("paantnraik") )return false;
		if( ! interleave("anna","patrik").equals("apnantarik") )return false;

		if( ! interleave2("abc","defghi").equals("adbecfghi") )return false;
		if( ! interleave2("patrik","anna").equals("paantnraik") )return false;
		if( ! interleave2("anna","patrik").equals("apnantarik") )return false;

		if( ! interleave3("abc","defghi").equals("adbecfghi") )return false;
		if( ! interleave3("patrik","anna").equals("paantnraik") )return false;
		if( ! interleave3("anna","patrik").equals("apnantarik") )return false;

		return true;
	}

	public static void main(String[] args) {
		System.out.println("I3: " + (runtest() ? "TEST OK" : "TEST FAILED"));
	}
}