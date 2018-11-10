package Ex1;

import java.io.*;

/** "Guess-the-number" game **/
public class I5 {
    private static final int MIN = 0;
    private static final int MAX = 10000; /* For the second part of the assignment
                                             replace this with Integer.MAX_VALUE */

    /* This solution implements binary search algorithm.
       The program makes the first guess as an average of MIN and MAX... */
    private static int FIRST_GUESS = MIN + (MAX - MIN) / 2;

    /* ... and tries to narrow down lower and upper bounds of
       the range where picked number could be... */
    private static int lowerBound = MIN;
    private static int upperBound = MAX;

    /* ... by bisecting left or right range of numbers based on
       whether or nor it is greater than the current guess . */
    private static int nextGuess(int previousGuess, boolean isGreater) {
        /* If we narrowed down the search to just one number and player still claims
           that it's not the one, we should get suspicious */
        if (lowerBound == upperBound) {
            String angryMessage = String.format("You were supposed to pick a number between %d and %d! " +
                                                "I don't play with cheaters!", MIN, MAX);
            System.out.println(angryMessage);
            System.exit(0);
        }

        if (isGreater) {
            lowerBound = previousGuess + 1;
            return previousGuess + (int)Math.ceil((double)(upperBound - previousGuess) / 2);
        } else {
            upperBound = previousGuess - 1;
            return lowerBound + (int)Math.floor((double)(previousGuess - lowerBound) / 2);
        }
    }

    private static enum Answer { YES, LESS, GREATER }

    public static void main(String[] args) {
        System.out.println(String.format("Let's play a game! Pick a number between %d and %d", MIN, MAX));
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int currentGuess = FIRST_GUESS;
        while (true) {
            System.out.println(String.format("Is it %d?", currentGuess));
            Answer answer = null;
            while (answer == null) {
                System.out.println("Please type 'yes', 'less' or 'greater' (without quotes)");
                System.out.print("> ");
                try {
                    answer = Answer.valueOf(input.readLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    continue;
                } catch (IOException e) {
                    System.out.println("IO Error!");
                }
            }

            if (answer == Answer.YES) {
                System.out.println(String.format("Oh, it was %d all along? Cool!", currentGuess));
                return;
            } else {
                currentGuess = nextGuess(currentGuess, answer == Answer.GREATER);
            }
        }
    }
}