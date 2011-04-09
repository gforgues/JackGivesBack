package cards;


import cards.Card.Rank;
import cards.Card.Suit;

/**
 * 
 * BlackjackHand object: hand specified for the Blackjack game 
 * @author JackGivesBack
 */



public class BlackjackHand extends Hand {
	// Field to keep track state of BlackjackHand
	boolean playable = true;
 
	 /**
	  * Get Hand total value
	  * @return value the Hand's total value of all Cards
	  */
	 public int getBlackjackValue() {
		int value = 0;
		// int cardPoints;
		Card currentCard;
		boolean ace = false;
     
		// Gather all points from each card in player's hand
      
		for (int i=0; i<getNumberCards(); i++) {
			currentCard = hand.get(i);
		   
			if (checkFaceValue(currentCard)) {
				value += 10;
			} else if (checkAce(currentCard)) {
				ace = true;
				value += 1;
			} else {
				value += currentCard.getPoints();
			}
		}
		
		if (ace && value+10 <= 21) {
			value+=10;
		} 
		return value;
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
			return false;  
		}
	}
 
	 /**
	  * Checks whether a Hand has gone over the 21 value limit
	  * @return true if the hand has a matching pair, remember to ignore the suits
	  */
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
	  * @return true if a Hand has gone over the 21 value limit,
	  */
	public boolean isBust() {
		if ( getBlackjackValue() > 21 ) {
			setDone();
			return true;
		} else {
			return false;
		}
	}
   
	 /**
	  * Sets the Hand's playable to be false once Stand has been played
	  */
	public void setDone() {
		playable = false;
	}
	 
	 /**
	  * Checks whether Stand has been played on Hand 
	  * @return true if Stand has not been played, false if it has
	  */
	public boolean isPlayable() {
		return playable;
	}
   
	 /**
	  * Returns all the cards in the hand that match a particular suit.
	  * @param pSuit The suit to match.
	  * @return a copy of the hand with only the cards matching the suit.
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
	  * Returns all the cards in the hand that do not match a particular suit.
	  * @param pSuit The suit to avoid.
	  * @return a copy of the hand with only the cards not matching the suit.
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
	  * @return a copy of the hand with only the cards matching the rank.
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

	 /**
	  * Returns all the cards in the hand that
	  * do not match a particular rank.
	  * @param pRank The rank to avoid.
	  * @return a copy of the hand with only the cards not matching the rank.
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
	  * @return true if Blackjack, false otherwise
	  */
	public boolean checkBlackjack() {
		// Check hand size exactly 2
		if ( getNumberCards() == 2 ) {
			// Check if 1 ace present
			if ( getCardsOfRank(Rank.ACE).getNumberCards() == 1 ) {
				// Check if 1 ten,jack,queen,king present
				
			   if ( getCardsOfRank(Rank.TEN).getNumberCards() == 1 || getCardsOfRank(Rank.JACK).getNumberCards() == 1 ||
					getCardsOfRank(Rank.QUEEN).getNumberCards() == 1 || getCardsOfRank(Rank.KING).getNumberCards() == 1 )  
					return true;
			}
		} 
		return false;
	}
  
	/**
	 * Generates the string output of the hand
	 * @return the string from of the current player's hand
	 */	
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int i=0; i<hand.size(); i++) {
			string.append(hand.get(i).toString() + "\n");
		}
		return string.toString();
	}

	/**
	 * Generates the cloned version of the player's hand
	 * @return the cloned version of player's hand which is of datatype black hack hand
	 */
	public BlackjackHand clone() {
		BlackjackHand fClonedHand = new BlackjackHand();
		fClonedHand.addAll(this);
		fClonedHand.removeCard(1);
		fClonedHand.removeCard(0);
	  
	   //  Instantiate a copy for now.
		return fClonedHand;
	}

}