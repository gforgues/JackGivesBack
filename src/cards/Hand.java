package cards;

import java.util.ArrayList;

public class Hand implements Cloneable {
	ArrayList<Card> hand;
	boolean playable = true;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Card removeCard(int position) {
		return hand.remove(position);
	}
	
	public boolean removeCard(Card card) {
		return hand.remove(card);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getNumberCards() {
		return hand.size();
	}
	
	/**
	 * Sets the Hand's playable to be false once Stand has been played
	 */
	public void setDone() {
		playable = false;
	}
	
	/**
	 * Checks whether Stand has been played on Hand 
	 * @return playable True if Stand has not been played, false if it has
	 */
	public boolean isPlayable() {
		return playable;
	}
	
	/**
	 * Adds all the cards in hand to this list.
	 * @param pHand The list of cards to add.
	 */
	public void addAll(Hand pHand) {
		for (Card c : pHand.hand) {
			pHand.addCard(c);
		}
	}
	
	/**
	 * Clone the hand into a new hand.
	 * @return Cloned hand from the original one.
	 */
	@Override
	public Hand clone() {
		Hand fClonedHand = new Hand();
		fClonedHand.addAll(this);
		/*
		 *  Instantiate a copy for now.
		 */
		return fClonedHand;
	}
	
}