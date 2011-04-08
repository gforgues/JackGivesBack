package game;


import java.util.ArrayList;

import cards.*;

import junit.framework.TestCase;

public class TestBlackjack extends TestCase {
	private int expectedValue;
	private int actualValue;
	boolean failed;

	public void testCheckDeckWithDeckSizeLessThanDeckResetValue() {
		Deck deck = new Deck();
		deck.addDeck(1);
		Blackjack jack = new Blackjack();
		
		expectedValue = deck.size() + 5*52;
		jack.checkDeck();
		actualValue = jack.getDeck().size();
		
		assertEquals(expectedValue, actualValue);
	}

	public void testHitNumberOfCards(){
		Deck deck= new Deck();
		deck.addDeck(1);
		Card card = deck.draw();
		Blackjack jack = new Blackjack();
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(card);
		expectedValue = hand.getNumberCards();
		hand.removeCard(card);
		jack.hit(hand);
		actualValue = hand.getNumberCards();
		assertEquals(expectedValue, actualValue);
	}
	public void testHitCardValue(){
		Deck deck= new Deck();
		deck.addDeck(1);
		Card card = deck.draw();
		Blackjack jack = new Blackjack();
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(card);
		expectedValue = hand.getBlackjackValue();
		System.out.println(hand.toString());
		hand.removeCard(card);
		jack.hit(hand);
		actualValue = hand.getBlackjackValue();
		System.out.println(hand.toString());
		assertNotSame(expectedValue, actualValue);
	}
	
	public void testDoubleDownNumberofCards(){
		Deck deck= new Deck();
		deck.addDeck(1);
		Card card = deck.draw();
		Blackjack jack = new Blackjack();
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(card);
		expectedValue = hand.getNumberCards();
		hand.removeCard(card);
		jack.doubleDown(hand);
		actualValue = hand.getNumberCards();
		assertEquals(expectedValue, actualValue);
	}
	
	public void testSplitNumberofCards(){
		Deck deck= new Deck();
		deck.addDeck(1);
		Blackjack jack = new Blackjack();
		BlackjackHand hand = new BlackjackHand();
		ArrayList<BlackjackHand> twoHands=new ArrayList<BlackjackHand>();
		twoHands.add(hand);
		twoHands.add(hand);
		expectedValue = twoHands.size();
		jack.hit(hand);
		jack.hit(hand);
		while(hand.checkPair()==true){
		actualValue = jack.split(hand).size();
		}
		assertEquals(expectedValue, actualValue);
	}
	

}
