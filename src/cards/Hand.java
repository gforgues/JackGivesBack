package cards;

import java.util.ArrayList;
/**
 * 
 * Hand object: List of cards
 * @author JackGivesBack
 *
 */

public class Hand implements Cloneable {
	ArrayList<Card> hand;
	
	/**
	 * Constructs an empty Hand of Cards
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}
	 /**
	  * Clears the cards in the Hand
	  */
	public void clearHand() {
		hand.clear();
	}
	 /**
	  * Clears the cards in the Hand
	  *@param card - The card to be added
	  */
	public void addCard(Card card) {
		hand.add(card);
	}
	
	 /**
	  * Clears the card in the Hand at a certain position
	  *@param position - The position where the cards needs to be removed
	  *@return the removed card
	  */
	public Card removeCard(int position) {
		return hand.remove(position);
	}
	
	 /**
	  * Checks if the removed card is in the hand
	  *@param card - The card to be removed
	  *@return true if the card was in the hand
	  */
	public boolean removeCard(Card card) {
		return hand.remove(card);
	}
	
	 /**
	  * Gets the list of cards in the Hand
	  *@return the list of cards in the Hand
	  */
	public ArrayList<Card> getHand() {
		return hand;
	}
	 /**
	  * Gets the number of cards in the hand
	  *@return the number of cards in the hand
	  */
	public int getNumberCards() {
		return hand.size();
	}
	
	/**
	 * Adds all the cards in one hand to the given hand.
	 * @param pHand - The Hand of cards to add.
	 */
	public void addAll(Hand pHand) {
		for (int i=0; i<pHand.getNumberCards(); i++) {
			this.hand.addAll(pHand.getHand());
		}
	}
	
	/**
	 * Clones the hand into a new hand.
	 * @return the cloned Hand.
	 */
	@Override
	public Hand clone() {
		Hand fClonedHand = new Hand();
		fClonedHand.addAll(this);
		
		//Instantiate a copy for now.
		return fClonedHand;
	}
	
}