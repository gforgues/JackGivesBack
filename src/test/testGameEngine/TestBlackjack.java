package test.testGameEngine;

import java.util.ArrayList;

import game.Blackjack;
import participant.Player;
import junit.framework.*;
import cards.*;
import storage.*;
import table.Table;

public class TestBlackjack extends TestCase {
	int expectedValue;
	int actualValue;
	Blackjack blackjack;
	Deck deck;
	BlackjackHand hand;
	
	public void setUp() { 
		blackjack = new Blackjack();
		deck = new Deck();
		hand = new BlackjackHand();
	}
	
	public void testCheckDeckWithDeckSizeLessThanDeckResetValue() {
		Deck deck = new Deck();
		deck.addDeck(1);
		Blackjack jack = new Blackjack();
		
		expectedValue = deck.size() + 5*52;
		jack.checkDeck();
		actualValue = jack.getDeck().size();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testCheckDeckGreaterThanDeckResetValue() {
		deck.addDeck(6);
		
		expectedValue = 6*52;
		blackjack.checkDeck();
		actualValue = blackjack.getDeck().size();
		
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
		
		//BlackjackHand hand = new BlackjackHand();
		hand.addCard(AllCards.CAH);
		hand.addCard(AllCards.CAD);
		twoHands.add(hand);
		
		expectedValue = 0;
		
		for (int i=0; i<twoHands.size(); i++) {
			expectedValue += twoHands.get(i).getNumberCards();
		}
		ArrayList<BlackjackHand> h = jack.split(hand);
		
		actualValue = 0;
		for (int i=0; i<h.size(); i++) {
			actualValue += h.get(i).getNumberCards();
		}
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testSplitWithMatchingCards() {
		hand.addCard(AllCards.C2C);
		hand.addCard(AllCards.C2D);
		
		expectedValue = hand.getNumberCards();
		ArrayList<BlackjackHand> newHand = blackjack.split(hand);
		int totalCards = 0;
		
		for (int i=0; i<newHand.size(); i++) {
			totalCards += newHand.get(i).getNumberCards();
		}
		
		assertEquals(expectedValue, totalCards);

	}
	
	public void testDealNumberOfCards() {
		blackjack.deal(hand);
		
		expectedValue = 2;
		actualValue = hand.getNumberCards();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testDealHand() {
		BlackjackHand h = blackjack.dealHand(hand);
		
		expectedValue = 2;
		actualValue = h.getNumberCards();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testHitWithOneCard() {
		hand.addCard(AllCards.C2C);
		
		expectedValue = hand.getNumberCards() + 1;
		blackjack.hit(hand);
		actualValue = hand.getNumberCards();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testHitThreeTimes() {
		hand.addCard(AllCards.C3C);
		hand.addCard(AllCards.CAD);
		
		expectedValue = hand.getNumberCards() + 3;
		blackjack.hit(hand);
		blackjack.hit(hand);
		blackjack.hit(hand);
		actualValue = hand.getNumberCards();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testStand() {
		blackjack.stand(hand);
		
		boolean playable = hand.isPlayable();
		
		assertEquals(playable, false);
	}
	
}