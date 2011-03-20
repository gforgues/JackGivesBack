package cards;

import cards.Card.Suit;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * A cloneable, bounded, sorted list of cards.
 * The cards are sorted by increasing rank
 * and by suit. Does not support duplicates.
 */
public class CardList implements Cloneable, Iterable<Card> {
	/**
	 * Create a new bounded card list.
	 * @param pMaxCards The maximum number of cards allowed in this list.
	 */
	public CardList(int pMaxCards) {
		fCList = new ArrayList<Card>(pMaxCards);
		fMaxLength = pMaxCards;
	}

	/**
	 * Get the maximum cards allowed in this list.
	 * @return The maximum number of cards allowed in this list.
	 */
	public int getMaxLength() {
		return fMaxLength;
	}

	/**
	 * Get the size of the list.
	 * @return The number of cards actually in the list.
	 */
	public int size() {
		return fCList.size();
	}

	/**
	 * Add a card to the list.
	 * @param card The card to add.
	 * @throws CardListException if a card is added to a full list
	 * or already exists in the list.
	 */
	public void add(Card card) throws CardListException {
		if (!fCList.contains(card) && fCList.size() < fMaxLength) {
			fCList.add(card);
		} else {
			throw new CardListException("Error: "
					+ "The CardList is full or you "
					+ "are adding a duplicate card.");
		}
	}

	/**
	 * Adds all the cards in cardList to this list.
	 * @param cardList The list of cards to add.
	 * @throws CardListException if a card is added to a full list
	 * or already exists in the list.
	 */
	public void addAll(CardList cardList) throws CardListException {
		for (Card c : cardList.fCList) {
			/*
			 * The card adding procedure will be check under
			 * CardList.add().
			 */
			this.add(c);
		}
	}

	/**
	 * Removes the first card from the list that is equal to card.
	 * Does nothing if the card is not in the list.
	 * @param card The card to remove (or one equal to it).
	 * @throws CardListException if the card is not in the list.
	 */
	public void remove(Card card) throws CardListException {
		if (fCList.contains(card)) {
			fCList.remove(card);
		} else {
			throw new CardListException("Error: You are removing "
					+ "a card that is not in the list");
		}
	}

	/**
	 * Removes card at index i and returns it.
	 * @param index The index to select the card.
	 * @return The card at index i.
	 */
	public Card remove(int index) {
		Card cTBRemoved;
		if (index < fCList.size()) {
			cTBRemoved = fCList.remove(index);
		} else {
			cTBRemoved = null;
		}
		return cTBRemoved;
	}

	/**
	 * @return an iterator for the card list
	 */
	@Override
	public Iterator<Card> iterator() {
		return fCList.iterator();
	}

	/**
	 * Removes all the cards in the list.
	 */
	public void clear() {
		fCList.clear();
	}

	/**
	 * Returns a one line compact representation of
	 * all the cards in the list.
	 * @return A string with all the cards in the hand.
	 */
	@Override
	public String toString() {
		String allCards = "";
		for (Card c: fCList){
			if (c.equals(AllCards.C2C)) allCards += "2C ";
			if (c.equals(AllCards.C3C)) allCards += "3C ";
			if (c.equals(AllCards.C4C)) allCards += "4C ";
			if (c.equals(AllCards.C5C)) allCards += "5C ";
			if (c.equals(AllCards.C6C)) allCards += "6C ";
			if (c.equals(AllCards.C7C)) allCards += "7C ";
			if (c.equals(AllCards.C8C)) allCards += "8C ";
			if (c.equals(AllCards.C9C)) allCards += "9C ";
			if (c.equals(AllCards.CTC)) allCards += "TC ";
			if (c.equals(AllCards.CJC)) allCards += "JC ";
			if (c.equals(AllCards.CQC)) allCards += "QC ";
			if (c.equals(AllCards.CKC)) allCards += "KC ";
			if (c.equals(AllCards.CAC)) allCards += "AC ";
	
			if (c.equals(AllCards.C2D)) allCards += "2D ";
			if (c.equals(AllCards.C3D)) allCards += "3D ";
			if (c.equals(AllCards.C4D)) allCards += "4D ";
			if (c.equals(AllCards.C5D)) allCards += "5D ";
			if (c.equals(AllCards.C6D)) allCards += "6D ";
			if (c.equals(AllCards.C7D)) allCards += "7D ";
			if (c.equals(AllCards.C8D)) allCards += "8D ";
			if (c.equals(AllCards.C9D)) allCards += "9D ";
			if (c.equals(AllCards.CTD)) allCards += "TD ";
			if (c.equals(AllCards.CJD)) allCards += "JD ";
			if (c.equals(AllCards.CQD)) allCards += "QD ";
			if (c.equals(AllCards.CKD)) allCards += "KD ";
			if (c.equals(AllCards.CAD)) allCards += "AD ";
			
			if (c.equals(AllCards.C2S)) allCards += "2S ";
			if (c.equals(AllCards.C3S)) allCards += "3S ";
			if (c.equals(AllCards.C4S)) allCards += "4S ";
			if (c.equals(AllCards.C5S)) allCards += "5S ";
			if (c.equals(AllCards.C6S)) allCards += "6S ";
			if (c.equals(AllCards.C7S)) allCards += "7S ";
			if (c.equals(AllCards.C8S)) allCards += "8S ";
			if (c.equals(AllCards.C9S)) allCards += "9S ";
			if (c.equals(AllCards.CTS)) allCards += "TS ";
			if (c.equals(AllCards.CJS)) allCards += "JS ";
			if (c.equals(AllCards.CQS)) allCards += "QS ";
			if (c.equals(AllCards.CKS)) allCards += "KS ";
			if (c.equals(AllCards.CAS)) allCards += "AS ";
			
			if (c.equals(AllCards.C2H)) allCards += "2H ";
			if (c.equals(AllCards.C3H)) allCards += "3H ";
			if (c.equals(AllCards.C4H)) allCards += "4H ";
			if (c.equals(AllCards.C5H)) allCards += "5H ";
			if (c.equals(AllCards.C6H)) allCards += "6H ";
			if (c.equals(AllCards.C7H)) allCards += "7H ";
			if (c.equals(AllCards.C8H)) allCards += "8H ";
			if (c.equals(AllCards.C9H)) allCards += "9H ";
			if (c.equals(AllCards.CTH)) allCards += "TH ";
			if (c.equals(AllCards.CJH)) allCards += "JH ";
			if (c.equals(AllCards.CQH)) allCards += "QH ";
			if (c.equals(AllCards.CKH)) allCards += "KH ";
			if (c.equals(AllCards.CAH)) allCards += "AH ";
			
		}
		return allCards;
	}

	/**
	 * Clone the List into a new list.
	 * @return Cloned list from the original one.
	 */
	@Override
	public CardList clone() {
		CardList fClonedList = new CardList(this.fMaxLength);
		fClonedList.addAll(this);
		/*
		 *  Instantiate a copy for now.
		 */
		return fClonedList;
	}

	/**
	 * Returns true if the list contains card.
	 * @param card The card to check.
	 * @return True if card is in the list.
	 */
	public boolean contains(Card card) {
		return fCList.contains(card);
	}

	/**
	 * Returns all the cards in the hand that match a particular suit.
	 * @param pSuit The suit to match.
	 * @return A copy of the hand with only the cards matching the suit.
	 */
	public CardList getCardsOf(Suit pSuit) {
		CardList fSuitList = new CardList(fMaxLength);
		for (Card c : fCList) {
			if (c.getSuit().equals(pSuit)) {
				fSuitList.add(c);
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
	public CardList getCardsNotOf(Suit pSuit) {
		CardList fSuitList = new CardList(fMaxLength);
		for (Card c : fCList) {
			if (!c.getSuit().equals(pSuit)) {
				fSuitList.add(c);
			}
		}
		return fSuitList;
	}
	
	/**
	 * The field to store card.
	 */
	private ArrayList<Card> fCList;

	/**
	 * The field to store the amount of cards allow.
	 */
	private int fMaxLength;
}