package hangman;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class RandomGuess {

    public static void main(String[] args) {
        char[] text;
        char[] alphabet;
        int incorrect;

        // Input the word to be guessed
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word: ");
        String word = scanner.nextLine().toLowerCase();
        scanner.close();

        text = word.toCharArray();

        if(text.length > 10 || text.length < 3) {
            System.out.println("Please enter a word between 3 and 10 characters.");
        }

        // char array test will be checked for values and reduced
        // char array guess will be given values
        char[] test = Arrays.copyOf(text, text.length);
        char[] guess = new char[text.length];

        alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        incorrect = 0;

        while(true) {

            // Find a random character to check for
            int rand = new Random().nextInt(alphabet.length - 1);
            char c = alphabet[rand];
            alphabet = removeElement(alphabet, rand);

            if(!contains(c, test)) {
                incorrect++;

                if(incorrect > 5) {
                    StringBuilder txt = new StringBuilder();
                    for (char value : guess) {
                        txt.append(value);
                    }

                    System.out.println("Failed to guess \"" + word + "\".");
                    System.out.println("Guess: \"" + txt + "\"");

                    break;
                }
            }

            if(contains(c, test)) {

                // Locate the index of the random character
                int index = -1;
                for (int i = 0; i < test.length; i++) {
                    if (test[i] == c) {
                        index = i;
                        break;
                    }
                }

                test = removeElement(test, index);
                guess = fillGuess(guess, index, c);

                if(Arrays.equals(guess, text)) {
                    StringBuilder txt = new StringBuilder();
                    for (char value : guess) {
                        txt.append(value);
                    }

                    System.out.println("Your word was \"" + txt + "\".\n");
                    break;
                }
            }
        }
    }

    public static char[] removeElement(char[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        char[] newArray = new char[arr.length - 1];

        System.arraycopy(arr, 0, newArray, 0, index);
        System.arraycopy(arr, index + 1, newArray, index, arr.length - index - 1);

        return newArray;
    }

    public static char[] fillGuess(char[] arr, int index, char value) {
        char[] newArray;

        newArray = ArrayUtils.insert(index, arr, value);
        newArray = ArrayUtils.remove(newArray, newArray.length - 1);

        return newArray;
    }

    static boolean contains(char c, char[] array) {
        for (char x : array) {
            if (x == c) {
                return true;
            }
        }
        return false;
    }
}
