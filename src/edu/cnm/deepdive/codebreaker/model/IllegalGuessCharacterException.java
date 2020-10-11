package edu.cnm.deepdive.codebreaker.model;

/**
 * Displays a message to the user if the {@link edu.cnm.deepdive.codebreaker.model.Code.Guess}
 * contains characters that aren't allowed in the secret {@link Code}.
 */
public class IllegalGuessCharacterException extends IllegalArgumentException {

  public IllegalGuessCharacterException() {
  }

  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }
}
