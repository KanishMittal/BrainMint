import java.util.*;

public class Word_Scrambler_Game {
    public static void main(String[] args) {
        String[] words = { "apple", "banana", "watermelon", "chikoo", "kiwi" };
        int score = 0;
        Scanner sc = new Scanner(System.in);
        while (score < 5) {
            Random r = new Random();
            String word = words[r.nextInt(words.length)];
            String scrambledWord = scrambleWord(word);
            System.out.println("Scrambled word: " + scrambledWord);
            System.out.print("Enter your guess: ");
            String guess = sc.nextLine();
            if (guess.equalsIgnoreCase(word)) {
                System.out.println("Guessed Correctly ! You get +1 point.");
                score++;
            } else {
                System.out.println("Guessed Incorrect. You lose -1 point.");
                score--;
                if (score < 0)
                    score = 0;
            }
            System.out.println("Your current score: " + score);
        }
        System.out.println("Congratulations! You reached 5 points and won the game!");
        sc.close();
    }

    public static String scrambleWord(String word) {
        char[] letters = word.toCharArray();
        Random r = new Random();

        for (int i = 0; i < letters.length; i++) {
            int randomIndex = r.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[randomIndex];
            letters[randomIndex] = temp;
        }

        return new String(letters);
    }
}