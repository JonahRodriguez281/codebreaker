package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Implements the
 */
public class Code {

  private final char[] secret;

  /**
   * Generates a secret code to then be solved by the user. Uses a certain pool of characters, with
   * a determined length, then randomizes the characters to generate a secret code.
   *
   * @param pool Allotted pool of characters to use.
   * @param length Length of the code generated.
   * @param rng Instance of Random to generate the code.
   */
  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < secret.length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }
  }

  @Override
  public String toString() {
    return new String(secret);
  }

  /**
   * Represents an attempt to guess the code
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";

    private final String text;
    private final int correct;
    private final int close;

    /**
     * Receives and compares the input guess to the secret code and calculates the amount correct,
     * and the amount close to the secret code.
     *
     * @param text Guess input from the user.
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getLetterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);

      for (int i = 0; i < work.length; i++) {
        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains(i)) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }

      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();
          }
        }
      }

      this.correct = correct;
      this.close = close;
    }

    private Map<Character, Set<Integer>> getLetterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i);
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    /**
     * Returns the text of the guess
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the amount of correct inputs (correct guess in correct position).
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the amount of close inputs (correct guess in wrong position).
     */
    public int getClose() {
      return close;
    }
  }

}
