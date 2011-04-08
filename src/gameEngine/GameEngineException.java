package gameEngine;

/**
 * Indicates a misuse of a GameEngine class.
 * @author JackGivesBack
 */
@SuppressWarnings("serial")
public class GameEngineException extends RuntimeException  {
	/**
	 * Generates a standard error message.
	 * @param message The message for the exception.
	 */
	public GameEngineException(String message) {
		super(message);
	}
}