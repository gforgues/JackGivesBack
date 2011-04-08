package test.testCards;

import junit.framework.*;
import cards.*;

public class TestCard extends TestCase{
	String expectedString;
	String actualString;
	int expectedValue;
	int actualValue;
	
	
	public void testDrawOne() {
		Deck deck = new Deck();
		Card card = Card.fromString("THREE", "HEARTS");
		deck.addCard(card);
		
		Card drawnCard = deck.draw();
		actualString = drawnCard.toString();
		expectedString = "THREE of HEARTS";
		assertEquals(actualString, expectedString);
	}
	
	public void testDrawTwo() {
		Deck deck = new Deck();
		Card card = Card.fromString("THREE", "HEARTS");
		deck.addCard(card);
		
		Card drawnCard = deck.draw();
		assertEquals(drawnCard.toString(), "THREE of HEARTS");
		
		card = Card.fromString("FOUR", "DIAMONDS");
		deck.addCard(card);
		drawnCard = deck.draw();
		assertEquals(drawnCard.toString(), "FOUR of DIAMONDS");
	}
	
	public void testGetPoints() {
		Card card = Card.fromString("TEN", "SPADES");
		actualValue = card.getPoints();
		expectedValue = 10;
		assertEquals(expectedValue, actualValue);
		
		card = Card.fromString("ACE", "DIAMONDS");
		actualValue = card.getPoints();
		expectedValue = 1;
		assertEquals(expectedValue, actualValue);
		
		card = Card.fromString("QUEEN", "HEARTS");
		actualValue = card.getPoints();
		expectedValue = 12;
		assertEquals(expectedValue, actualValue);
		
		card = Card.fromString("JACK", "CLUBS");
		actualValue = card.getPoints();
		expectedValue = 11;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testCompactStringOne() {
		Card card = Card.fromString("ACE","CLUBS");
		
		actualString = card.toCompactString();
		expectedString = "AC";
		assertEquals(actualString, expectedString);
	}
	
	public void testCompactStringTWO() {
		Deck deck = new Deck();
		Card card = Card.fromString("JACK","HEARTS");
		Card cardTwo = Card.fromString("KING","HEARTS");
		deck.addCard(card);
		deck.addCard(cardTwo);
		
		actualString = deck.draw().toCompactString();
		expectedString = "JH";
		assertEquals(actualString, expectedString);
		
		actualString = deck.draw().toCompactString();
		expectedString = "KH";
		assertEquals(actualString, expectedString);
	}
		
}
