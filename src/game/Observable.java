package game;

import game.Observer;

/**
 * Observable Interface.
 */
public interface Observable {
	/**
	 * Notify the observers.
	 */
	void notifyObservers();

	/**
	 * Add the observer.
	 * @param obsToAdd Observer to be added.
	 */
	void addObservers(Observer obsToAdd);

	/**
	 * Remove the observer.
	 * @param obsToRemove Observer to be removed.
	 */
	void removeObservers(Observer obsToRemove);
}
