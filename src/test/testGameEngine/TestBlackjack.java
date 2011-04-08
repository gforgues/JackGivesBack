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
	
	public void testCheckDeckLessThanDeckResetValue() {
		deck.addDeck(1);
		blackjack.setDeck(deck);
		
		expectedValue = 6*52;
		blackjack.checkDeck();
		actualValue = blackjack.getDeck().size();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testCheckDeckGreaterThanDeckResetValue() {
		deck.addDeck(6);
		
		expectedValue = 6*52;
		blackjack.checkDeck();
		actualValue = blackjack.getDeck().size();
		
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
	
	public void testPlaysSplitHands() {
		hand.addCard(AllCards.C2C);
		hand.addCard(AllCards.C2D);
		
		expectedValue = hand.getNumberCards();
		ArrayList<BlackjackHand> bothHands = blackjack.split(hand);
		
		blackjack.playsSplitHands(bothHands);
	}
	
}