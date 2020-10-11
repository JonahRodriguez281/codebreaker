package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

  private static final String BAD_GUESS_PATTERN_FORMAT = "^.*[^%s].*$";
  private static final String ILLEGAL_LENGTH_MESSAGE =
      "Invalid guess length: required=%d; provided=%d";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess includes invalid characters: required=%s; provided=%s.";

  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String badGuessPattern;

  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    badGuessPattern = String.format(BAD_GUESS_PATTERN_FORMAT, pool);
  }


  public Code getCode() {
    return code;
  }

  public List<Guess> getGuesses() {
    return Collections.unmodifiableList(guesses);
  }

  public String getPool() {
    return pool;
  }

  public int getLength() {
    return length;
  }

  public int getGuessCount() {
    return guesses.size();
  }

  /**
   * Evaluates the submitted {@link Guess} submitted by the user to then determine whether or not
   * the submitted answer is a legal guess. Defines {@link IllegalGuessCharacterException} and
   * {@link IllegalGuessLengthException} and compares the guess to determine eligibility for the
   * game.
   * @param text The submitted text of the guess
   * @return Returns inputted guess
   * @throws IllegalGuessLengthException Matches the inputted {@link Guess} length to
   * the {@link Code}.
   * @throws IllegalGuessCharacterException Determines whether the {@link Guess} characters match
   * those allowed in the pool of eligible characters.
   */
  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException{
    if (text.length() != length) {
      throw new IllegalGuessLengthException(
          String.format(ILLEGAL_LENGTH_MESSAGE, length, text.length()));
    }
    if (text.matches(badGuessPattern)) {
      throw new IllegalGuessCharacterException(String.format(
          ILLEGAL_CHARACTER_MESSAGE, pool, text));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

  public void restart() {
    guesses.clear();
  }
}
