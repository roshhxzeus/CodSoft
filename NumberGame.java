import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain;

        do {
            int attempts = 0;
            int maxAttempts = 5; 
            int numberToGuess = random.nextInt(100) + 1; 
            boolean hasGuessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    hasGuessedCorrectly = true;
                    score++;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);

            while (true) {
                System.out.print("Do you want to play again? (Y/N): ");
                String response = scanner.next();

                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                    playAgain = true;
                    break;
                } else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}

