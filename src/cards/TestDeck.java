package cards;
import junit.framework.*;

public class TestDeck extends TestCase {
	String expectedString;
	String actualString;
	int expectedValue;
	int actualValue;
	boolean failed;
	
	public void testEmptyDeck() {
		Deck deck = new Deck();
		actualValue = deck.size();
		expectedValue = 0;
		
		assertEquals(actualValue, expectedValue);
	}
	
	public void testAddCard() {
		Deck deck = new Deck();
		Card card = Card.fromString("ACE", "CLUBS");
		deck.addCard(card);
		
		assertEquals(deck.size(), 1);
		
		expectedString = "ACE of CLUBS";
		actualString = deck.draw().toString();
		
		assertEquals(expectedString, actualString);
	}
	
	public void testDrawEmptyDeck() {
		failed = false;
		Deck deck = new Deck();
		try {
			deck.draw();
		} catch (EmptyDeckException e) {
			failed = true;
		}
		assertEquals(failed, true);
	}
	
	public void testNegativeDeck() {
		Deck deck = new Deck();
		deck.addDeck(-1);
		actualValue = deck.size();
		expectedValue = 0;
		
		assertEquals(actualValue, expectedValue);
	}
	
	public void testOneDeck() {
		Deck deck = new Deck();
		deck.addDeck(1);
		actualValue = deck.size();
		expectedValue = 52;
		
		assertEquals(actualValue, expectedValue);
	}
	
	public void testSixDecks() {
		Deck deck = new Deck();
		deck.addDeck(6);
		actualValue = deck.size();
		expectedValue = 52*6;
		
		assertEquals(actualValue, expectedValue);
	}
	
}
