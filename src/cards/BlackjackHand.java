package cards;

import participant.Player;
import cards.Card.Rank;

public class BlackjackHand extends Hand {
	
	boolean playable = true;
	/**
	 * Get Hand total value
	 * @param
	 * @return value Integer value of Hand's total value of all Cards
	 */
	public int getBlackjackValue() {
		int value = 0;
		/*
		 * int cardPoints;
		 */
		Card currentCard;
		boolean ace = false;
    	/*
    	 * Gather all points from each card in player's hand
    	 */
		for (int i=0; i<getNumberCards(); i++) {
			currentCard = hand.get(i);
			
			if (checkFaceValue(currentCard)) {
				value += 10;
			} else if (checkAce(currentCard)) {
				ace = true;
				value += 11;
			} else {
				value += currentCard.getPoints();
			}
			
			if (value > 21 && ace) {
				value -= 10;
			}
		}
		
		return value;
		//public int count() {
//		int total = 0;
//		
//		for (int i=0; i<hand.size(); i++) {
//			total += hand.get(i).getPoints();
//		}
//		
//		return total;
		//}
	}
	
	/*
	 * Helper method that checks whether the card that's being passed as a parameter
	 * is a face card (ie. jack, queen, or king) and returns true if it is.
	 */
	
	private boolean checkFaceValue(Card card) {
		if (card.getPoints() > 10) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Helper method that checks whether the card passed as a parameter is an
	 * ace card and returns true if it is.
	 */
	
	private boolean checkAce(Card card) {
		if (card.getRank() == Rank.ACE) {
			return true;
		} else {
			return false;		}
	}
	
	/**
	 * Checks whether a Hand has gone over the 21 value limit
	 * @param
	 * @return Returns whether a Hand has gone over the 21 value limit
	 */
   public boolean isBust() {
       if ( getBlackjackValue() > 21 ) {
    	   setDone();
    	   return true;
       } else 
    	   return false;
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
   
}