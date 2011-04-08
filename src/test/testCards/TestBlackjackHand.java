package test.testCards;

import cards.Card.Rank;
import cards.Card.Suit;
import cards.*;
import game.*;
import junit.framework.*;

public class TestBlackjackHand extends TestCase {
	private int expectedValue;
	private int actualValue;
	boolean failed;

	public void testGetBlackjackValueIs21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CTC);
		hand.addCard(AllCards.C9D);
		hand.addCard(AllCards.C2H);
		
		boolean is21 = hand.getBlackjackValue() == 21;
		assertEquals(is21, true);
	}
	
	public void testGetBlackjackValueLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C9S);
		hand.addCard(AllCards.CTH);
		
		boolean is19 = hand.getBlackjackValue() == 19;
		assertEquals(is19, true);
	}
	
	public void testGetBlackjackValueGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CJC);
		hand.addCard(AllCards.CTD);
		hand.addCard(AllCards.C3C);
		
		boolean isBust = hand.getBlackjackValue() > 21;
		assertEquals(isBust, true);
		
		boolean is23 = hand.getBlackjackValue() == 23;
		assertEquals(is23, true);
	}
	
	public void testGetBlackjackValueWithAceLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAS);
		hand.addCard(AllCards.C9H);
		
		boolean is19 = hand.getBlackjackValue() == 20;
		assertEquals(is19, true);
		
		boolean isTen = hand.getBlackjackValue() == 10;
		assertEquals (isTen, false);
	}
	
	public void testGetBlackjackValueWithAceGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAD);
		hand.addCard(AllCards.CQH);
		hand.addCard(AllCards.C3S);
		
		boolean is14 = hand.getBlackjackValue() == 14;
		assertEquals(is14, true);
		
		boolean is24 = hand.getBlackjackValue() == 24;
		assertEquals(is24, false);
	}
	
	public void testGetBlackjackValueWithAceIs21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAH);
		hand.addCard(AllCards.CJD);
		
		boolean is21 = hand.getBlackjackValue() == 21;
		assertEquals(is21, true);
		
		boolean is11 = hand.getBlackjackValue() == 11;
		assertEquals(is11, false);
	}
	
	public void testGetBlackjackValueWith4Aces() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAH);
		hand.addCard(AllCards.CAD);
		hand.addCard(AllCards.CAS);
		hand.addCard(AllCards.CAC);
		
		boolean isBust = hand.getBlackjackValue() > 21;
		assertEquals(isBust, false);
		
		boolean is14 = hand.getBlackjackValue() == 14;
		assertEquals(is14, true);
	}
	
	public void testCheckPairWithPair() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C8H);
		hand.addCard(AllCards.C8S);
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, true);
	}
	
	public void testCheckPairWith2DifferentCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C9H);
		hand.addCard(AllCards.CTH);
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, false);
	}
	
	public void testCheckPairWith3SameCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C9H);
		hand.addCard(AllCards.C9S);
		hand.addCard(AllCards.C9D);
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, false);
	}
	
	public void testIsBustWithGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C9H);
		hand.addCard(AllCards.CTD);
		hand.addCard(AllCards.CJS);
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, true);
	}
	
	public void testIsBustWithLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C9H);
		hand.addCard(AllCards.C3D);
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, false);
	}
	
	public void testIsBustWith21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAH);
		hand.addCard(AllCards.CTD);
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, false);
	}
	
	public void testGetCardsOfSuitAllHandIsSameSuit() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.C2H);
		hand.addCard(AllCards.C5H);
		hand.addCard(AllCards.C7H);
		
		//Hand getCardsOfSuit(Suit pSuit) 
		
		//HOW WOULD YOU TEST THIS METHOD WITH THE ASSERTION THING??
	}
	
	public void testCheckBlackjackWithBlackJack() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CAH);
		hand.addCard(AllCards.CTH);
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, true);
	}
	
	public void testCheckBlackjackWith2FaceCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CKH);
		hand.addCard(AllCards.CQC);
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, false);
	}
	
	public void testCheckBlackjackWith3Cards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CKH);
		hand.addCard(AllCards.CAS);
		hand.addCard(AllCards.CQD);
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, false);
	}
	public void testClone() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(AllCards.CKH);
		hand.addCard(AllCards.CAS);
		
		BlackjackHand clonedHand = hand.clone();
	
		
		actualValue = clonedHand.getBlackjackValue();
		expectedValue = hand.getBlackjackValue();
		assertEquals(actualValue, expectedValue);
		
}
	}