package cards;

import cards.Card.Rank;

public class BlackjackHand extends Hand {
	
	public int getBlackjackValue() {
		int value = 0;
		//int cardPoints;
		Card currentCard;
		boolean ace = false;
		
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
}