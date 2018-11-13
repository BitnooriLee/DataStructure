package Ex1;

import java.util.Scanner;
import java.util.Random;

public class ex1_5_1 {
    int endRange = 10000;
    int startRange = 1;
    
    public int guessNumber() {
        Random rand = new Random();
        int n = rand.nextInt(endRange-startRange+1) + startRange;
        return n;
    }
    
    public void checkNumber() { 
        int answer = -1;
        while(answer != 0) {
            int guess = guessNumber();
            System.out.print(guess);
            System.out.println(" it's correct, press 0, bigger than this press 1, smaller than this press 2");
            
            Scanner keyboard = new Scanner(System.in); //create keyboard scanner

            while (true) {
                answer = keyboard.nextInt();
                if (answer == 1 || answer == 2|| answer==0) {
                    break;
                }
                System.out.println("give me answer again");
            }

            if(answer == 1) {
                startRange = guess+1;
            } 
            else if(answer == 2) {
                endRange = guess-1;
            }
        }

        System.out.println("Right Answer!");
        return;
    }
        

    public static void main(String[] args) {
        System.out.println("Please select interger 1 - 10000"); //change line

        ex1_5_1 ex = new ex1_5_1();
        ex.checkNumber();
    }

}