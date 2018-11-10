package Ex1;

import java.util.Scanner;

public class ex1_5_2 {
	
	static int endRange = Integer.MAX_VALUE-1;
	static int startRange = 1;
	
	public int getInput() {
		Scanner answer = new Scanner(System.in);
		int input = answer.nextInt();
		return input;
	}
	
	public void guess() {
		
		int answer = -1;
		
		while(answer!=0) {
		
		int middle = (int)(endRange+startRange)/2;
		System.out.println(endRange);
		System.out.println(middle + " correct? press 0, if it's higher press 1, lower press2");
		while(true) {
				answer = getInput();
				if(answer==1||answer==2||answer==0) {
					break;
				}
			System.out.println(middle + " correct? press 0, if it's higher press 1, lower press2");
		}
		if (answer==1) {
			startRange = middle+1;
		}else if(answer==2) {
			endRange = middle -1;
		}
	} System.out.println("Got it!"); 
		return;
}
	

	public static void main(String[] args) {
		ex1_5_2 exercise = new ex1_5_2();
		exercise.guess();
	}

}
