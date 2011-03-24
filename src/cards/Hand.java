package cards;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand;
	
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
}