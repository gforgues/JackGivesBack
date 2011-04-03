package cards;

import participant.Player;
import cards.Card.Rank;
import cards.Card.Suit;

import java.util.*;

public class BlackjackHand extends Hand {
	
	/*
	 * Field to keep track state of BlackjackHand
	 */
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
	
	public boolean checkPair() {
		boolean paired = false;
		if (getNumberCards() == 2) {
			if (getHand().get(0).getPoints() == getHand().get(1).getPoints())
				paired = true;
		} 
		return paired;
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
   
	/**
	 * Returns all the cards in the hand that match a particular suit.
	 * @param pSuit The suit to match.
	 * @return A copy of the hand with only the cards matching the suit.
	 */
	public Hand getCardsOfSuit(Suit pSuit) {
		Hand fSuitList = new Hand();
		for (Card c : hand) {
			if (c.getSuit().equals(pSuit)) {
				fSuitList.addCard(c);
			}
		}
		return fSuitList;
	}

	/**
	 * Returns all the cards in the hand that
	 * do not match a particular suit.
	 * @param pSuit The suit to avoid.
	 * @return A copy of the hand with only the cards not matching the suit.
	 */
	public Hand getCardsNotOfSuit(Suit pSuit) {
		Hand fSuitList = new Hand();
		for (Card c : hand) {
			if (!c.getSuit().equals(pSuit)) {
				fSuitList.addCard(c);
			}
		}
		return fSuitList;
	}
	
	/**
	 * Returns all the cards in the hand that match a particular rank.
	 * @param pRank The rank to match.
	 * @return A copy of the hand with only the cards matching the rank.
	 */
	public Hand getCardsOfRank(Rank pRank) {
		Hand fRankList = new Hand();
		for (Card c : hand) {
			if (c.getRank() == (pRank)) {
				fRankList.addCard(c);
			}
		}
		return fRankList;
	}
	
	public int getNumberCardsOfRank(Rank pRank) {
		int counter = 0;
		
		for (int i=0; i<hand.size(); i++) {
			if (hand.get(i).getRank().equals(pRank)) {
				counter+=1;
			}
		}
		
		return counter;
	}

	/**
	 * Returns all the cards in the hand that
	 * do not match a particular rank.
	 * @param pRank The rank to avoid.
	 * @return A copy of the hand with only the cards not matching the rank.
	 */
	public Hand getCardsNotOfRank(Rank pRank) {
		Hand fRankList = new Hand();
		for (Card c : hand) {
			if (c.getRank() != (pRank)) {
				fRankList.addCard(c);
			}
		}
		return fRankList;
	}
	
	/**
	 * Checks to see if a given BlackjackHand contains a Blackjack
	 * @param 
	 * @return True if Blackjack, false otherwise
	 */
    public boolean checkBlackjack() {
    	/*
    	 * Check hand size exactly 2
    	 */
    	if ( getNumberCards() == 2 ) {
    		/*
    		 * Check if 1 ace present
    		 */
    		if ( getCardsOfRank(Rank.ACE).getNumberCards() == 1 ) {
    			/*
    			 * Check if 1 ten,jack,queen,king present
    			 */
    			if ( getCardsOfRank(Rank.TEN).getNumberCards() == 1 || getCardsOfRank(Rank.JACK).getNumberCards() == 1 ||
    					getCardsOfRank(Rank.QUEEN).getNumberCards() == 1 || getCardsOfRank(Rank.KING).getNumberCards() == 1 )
    			return true;
    		}
    	} 
    	return false;
    }
    
    public String toString() {
    	StringBuilder string = new StringBuilder();
    	
    	for (int i=0; i<hand.size(); i++) {
    		string.append(hand.get(i).toString() + "\n");
    	}
    	
    	return string.toString();
    }
	
	
}