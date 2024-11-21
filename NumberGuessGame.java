import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
  // Create a static input field
  static Scanner input = new Scanner(System.in);

  /*
   * Method to calculate time consumed in seconds using System.currentTimeMillis
   */
  public static int getTime(long start, long end) {
    return (int) ((end - start) / 1000);
  }

  /* Method to Generate Random Number between 1-100 */
  public static int getRandom() {
    Random newRandom = new Random();
    return newRandom.nextInt(100) + 1;
  }

  /* Method to Select Difficulty */
  public static int selectDifficulty() {
    int difficultyLevel;
    do {
      System.out.println("\nPlease select the difficulty: ");
      System.out.println("1-Easy (10-Chances)");
      System.out.println("2-Medium (5-Chances)");
      System.out.println("3-Hard (3-Chances)");
      System.out.print("\nEnter your choice: ");
      difficultyLevel = input.nextInt();
      if (difficultyLevel < 1 || difficultyLevel > 3)
        System.out.println("Invalid choice! Please try again.");
    } while (difficultyLevel < 1 || difficultyLevel > 3);

    return difficultyLevel;
  }

  /* Method to display appropriate message as per difficulty */
  public static void displayMessage(int difficultyLevel) {
    switch (difficultyLevel) {
      case 1 -> System.out.println("\nGreat! You selected 'Easy' difficulty level.\nLet's start the game!");
      case 2 -> System.out.println("\nGreat! You selected 'Medium' difficulty level.\nLet's start the game!");
      case 3 -> System.out.println("\nGreat! You selected 'Hard' difficulty level.\nLet's start the game!");
      default -> System.out.println("\nInappropriate Value of difficulty!");
    }
  }

  /* Method for number of chances */
  public static int getNumberOfChances(int difficultyLevel) {
    return switch (difficultyLevel) {
      case 1 -> 10;
      case 2 -> 5;
      case 3 -> 3;
      default -> 10;
    };
  }

  /* Main Method */
  public static void main(String[] args) {
    System.out.println("\n****Welcome to the Number Guessing Game***");
    int difficultyLevel = selectDifficulty();
    final int NUMBER_OF_CHANCES = getNumberOfChances(difficultyLevel);
    // Display Appropriate message
    displayMessage(difficultyLevel);
    int count = 0;

    // Display Message;
    int random = getRandom();
    System.out.println("\nI am thinking of a number between 1-100:" +
        " You have " + NUMBER_OF_CHANCES + (NUMBER_OF_CHANCES == 1 ? " chance" : " chances")
        + " chances to guess the correct number");
    // Loop to continue guess the number unless count == NUMBER_OF_CHANCES
    long startTime = System.currentTimeMillis();
    while (count < NUMBER_OF_CHANCES) {
      System.out.print("Enter your guess: ");
      int guess = input.nextInt();
      // Check for out of bound guess
      if (guess < 1 || guess > 100) {
        System.out.println("Your guess is out of bounds! Please guess a number between 1 and 100.");
        continue;
      }
      // check for decimal guess
      if (!input.hasNextInt()) {
        System.out.println("Invalid input! Please enter a valid number.");
        input.next(); // Clear invalid input
        continue;
      }
      if (guess > random)
        System.out.println("Incorrect! The number is less than " + guess);
      else if (guess < random)
        System.out.println("Incorrect! The number is greater than " + guess);
      else {
        long endTime = System.currentTimeMillis();
        System.out.println("Congratulations! You guessed the number correctly in " + count + 1 + " chances");
        System.out.println("Total time consumed is: " + getTime(startTime, endTime) + " s");
        return;
      }
      count++;
    }
    // Display Message if count == NUMBER_OF_CHANCES
    long endTime = System.currentTimeMillis();
    System.out.println("\nSorry! You've exhausted all your chances. The number was " + random);
    System.out.println("Total time consumed is: " + getTime(startTime, endTime) + " s");
    input.close();
  }
}