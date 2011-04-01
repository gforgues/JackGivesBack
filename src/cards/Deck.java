package cards;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a deck of 52 cards (no joker).
 */
public class Deck  {
	private final ArrayList<Card> cDeck;
	
	/**
	 * Creates a new empty deck.
	 */
	public Deck() {
		cDeck = new ArrayList<Card>();
	}
	
	/**
	 * Adds a specific card to the deck.
	 */
	public void addCard(Card card) {
		cDeck.add(card);
	}
	/**
	 * Adds decks to an initial deck
	 * @param numDecks Number of decks to add
	 */
	public void addDeck(int numDecks) {
		
		for (int i=0; i < numDecks; i++) {
			
			/*
			 * Add Cards from Clubs Suite
			 */
			cDeck.add(AllCards.CAC);
			cDeck.add(AllCards.C2C);
			cDeck.add(AllCards.C3C);
			cDeck.add(AllCards.C4C);
			cDeck.add(AllCards.C5C);
			cDeck.add(AllCards.C6C);
			cDeck.add(AllCards.C7C);
			cDeck.add(AllCards.C8C);
			cDeck.add(AllCards.C9C);
			cDeck.add(AllCards.CTC);
			cDeck.add(AllCards.CJC);
			cDeck.add(AllCards.CQC);
			cDeck.add(AllCards.CKC);
			/*
			 * Add Cards from Diamond Suite
			 */
			cDeck.add(AllCards.CAD);
			cDeck.add(AllCards.C2D);
			cDeck.add(AllCards.C3D);
			cDeck.add(AllCards.C4D);
			cDeck.add(AllCards.C5D);
			cDeck.add(AllCards.C6D);
			cDeck.add(AllCards.C7D);
			cDeck.add(AllCards.C8D);
			cDeck.add(AllCards.C9D);
			cDeck.add(AllCards.CTD);
			cDeck.add(AllCards.CJD);
			cDeck.add(AllCards.CQD);
			cDeck.add(AllCards.CKD);
			/*
			 * Add Cards from Hearts Suite
			 */
			cDeck.add(AllCards.CAH);
			cDeck.add(AllCards.C2H);
			cDeck.add(AllCards.C3H);
			cDeck.add(AllCards.C4H);
			cDeck.add(AllCards.C5H);
			cDeck.add(AllCards.C6H);
			cDeck.add(AllCards.C7H);
			cDeck.add(AllCards.C8H);
			cDeck.add(AllCards.C9H);
			cDeck.add(AllCards.CTH);
			cDeck.add(AllCards.CJH);
			cDeck.add(AllCards.CQH);
			cDeck.add(AllCards.CKH);
			/*
			 * Add Cards from Spade Suite
			 */
			cDeck.add(AllCards.CAS);
			cDeck.add(AllCards.C2S);
			cDeck.add(AllCards.C3S);
			cDeck.add(AllCards.C4S);
			cDeck.add(AllCards.C5S);
			cDeck.add(AllCards.C6S);
			cDeck.add(AllCards.C7S);
			cDeck.add(AllCards.C8S);
			cDeck.add(AllCards.C9S);
			cDeck.add(AllCards.CTS);
			cDeck.add(AllCards.CJS);
			cDeck.add(AllCards.CQS);
			cDeck.add(AllCards.CKS);
		}
	}

	/**
	 * Refill and shuffle the deck of cards by randomizing
	 * their order.
	 */
	public void shuffle() {
		Collections.shuffle(cDeck);
	}

	/**
	 * Draws a card from the deck and removes the card from the deck.
	 * @return final.size() == initial.size() - 1 or exception
	 * @throws EmptyDeckException if called on an empty deck.
	 */
	public Card draw() throws EmptyDeckException {
		/*
		 * Get the card from the top of the deck
		 */
		if (cDeck.size() != 0) {
			Card drawnCard = cDeck.remove(0);
			return drawnCard;
		} else {
			throw new EmptyDeckException();
		}
	}

	/**
	 * Returns the size of the deck.
	 * @return The number of cards in the deck.
	 */
	public int size() {
		return cDeck.size();
	}

	/**
	 * The field to store deck of cards.
	 */
}