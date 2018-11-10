package Ex1;

import java.util.Scanner;
import java.util.Random;

public class ex1_5 {
	static int endRange = 10000;
	static int startRange = 1;
	
	public static int getAnswer() {
		Scanner keyboard = new Scanner(System.in); //create keyboard scanner 
		int input = keyboard.nextInt(); // get input value
		keyboard.close();
		return input;
		}
		
	public static int guessNumber() {
		Random rand = new Random();
		int n = rand.nextInt(endRange) + startRange ;
		return n;
		}
	
	public static void checkNumber() { 
		int answer = 1;
		while(answer!=0) {
			System.out.print(guessNumber());
			System.out.println(" it's correct, press 0, bigger than this press1, smaller than this press 2");
			answer = getAnswer();
			if(answer==1){
				startRange = guessNumber()+1;
				endRange -= startRange;
				} 
			else if(answer==2) {
				endRange = guessNumber()-1;
				}
			/*else {
				System.out.println("give me answer again");
				answer = getAnswer();
					}*/
			}
		System.out.println("Right Answer!");
		return;
		}
	
	public static void main(String[] args) {
		System.out.println("Please select interger 1 - 10000"); //change line
		checkNumber();
		}

}