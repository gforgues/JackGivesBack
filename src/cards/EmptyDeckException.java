package cards;

/**
 * Indicates an attempt to draw a card from an empty deck.
 * @author JackGivesBack
 */
@SuppressWarnings("serial")
public class EmptyDeckException extends RuntimeException  {
	/**
	 * Generates a standard error message.
	 */
	public EmptyDeckException() {
		super("Attempting to draw a card from an empty deck");
	}
}
