package edu.cnm.deepdive.codebreaker.model;

public class IllegalGuessLengthException extends IllegalArgumentException {

  /**
   * Displays a message to the user if the {@link edu.cnm.deepdive.codebreaker.model.Code.Guess}
   * contains more characters than the generated  {@link Code}.
   */
  public IllegalGuessLengthException() {
  }

  public IllegalGuessLengthException(String message) {
    super(message);
  }

  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}
