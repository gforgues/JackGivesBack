package storage;
import junit.framework.*;

import cards.AllCards;
import cards.Card;
import cards.Deck;
import cards.Hand;

public class TestBlackjackStorage extends TestCase {
	int expectedValue;
	int actualValue;
	
	public void testDeck(){
		Deck deck = new Deck();
		deck.addDeck(1);
		deck.draw();
		deck.draw();
		expectedValue = 50;
		
		BlackjackStorage.saveDeck(deck, 1);
		actualValue = BlackjackStorage.loadDeck(1).size();
		
		assertEquals(expectedValue, actualValue);
	}
	public void testDeckSix(){
		Deck deck = new Deck();
		deck.addDeck(6);
		expectedValue = 52*6;
		
		BlackjackStorage.saveDeck(deck, 1);
		actualValue = BlackjackStorage.loadDeck(1).size();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testHand() {
		Hand hand = new Hand();
		hand.addCard(AllCards.C2C);
		BlackjackStorage.saveHand("bill", hand, 1);
		
		String expectedString = "TWO of CLUBS";
		Card card = BlackjackStorage.loadHand("bill", 1).removeCard(0);
		assertEquals(expectedString, card.toString());
	}
	public void testMultipleHands() {
		Hand handEmily = new Hand();
		handEmily.addCard(AllCards.C4S);
		BlackjackStorage.saveHand("emily", handEmily, 1);
		
		String expectedString = "FOUR of SPADES";
		Card card = BlackjackStorage.loadHand("emily", 1).removeCard(0);
		assertEquals(expectedString, card.toString());
		
		Hand handJim = new Hand();
		handJim.addCard(AllCards.C4S);
		handJim.addCard(AllCards.C4S);
		BlackjackStorage.saveHand("jim", handJim, 1);
		
		expectedString = "FOUR of SPADES";
		card = BlackjackStorage.loadHand("jim", 1).removeCard(0);
		assertEquals(expectedString, card.toString());
		
		card = BlackjackStorage.loadHand("jim", 1).removeCard(0);
		assertEquals(expectedString, card.toString());
	}
}
