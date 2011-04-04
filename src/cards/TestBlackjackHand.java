package cards;
import cards.Card.Rank;
import cards.Card.Suit;
import junit.framework.*;

public class TestBlackjackHand extends TestCase {
	private int expectedValue;
	private int actualValue;
	boolean failed;
	
	public void testGetBlackjackValueIs21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		hand.addCard(new Card(Rank.NINE, Suit.CLUBS));
		hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
		
		boolean is21 = hand.getBlackjackValue() == 21;
		assertEquals(is21, true);
	}
	
	public void testGetBlackjackValueLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		
		boolean is19 = hand.getBlackjackValue() == 19;
		assertEquals(is19, true);
	}
	
	public void testGetBlackjackValueGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.JACK, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		hand.addCard(new Card(Rank.THREE, Suit.SPADES));
		
		boolean isBust = hand.getBlackjackValue() > 21;
		assertEquals(isBust, true);
		
		boolean is23 = hand.getBlackjackValue() == 23;
		assertEquals(is23, true);
	}
	
	public void testGetBlackjackValueWithAceLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.SPADES));
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		
		boolean is19 = hand.getBlackjackValue() == 20;
		assertEquals(is19, true);
		
		boolean isTen = hand.getBlackjackValue() == 10;
		assertEquals (isTen, false);
	}
	
	public void testGetBlackjackValueWithAceGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		hand.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		hand.addCard(new Card(Rank.THREE, Suit.SPADES));
		
		boolean is14 = hand.getBlackjackValue() == 14;
		assertEquals(is14, true);
		
		boolean is24 = hand.getBlackjackValue() == 24;
		assertEquals(is24, false);
	}
	
	public void testGetBlackjackValueWithAceIs21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		hand.addCard(new Card(Rank.JACK, Suit.DIAMONDS));
		
		boolean is21 = hand.getBlackjackValue() == 21;
		assertEquals(is21, true);
		
		boolean is11 = hand.getBlackjackValue() == 11;
		assertEquals(is11, false);
	}
	
	public void testGetBlackjackValueWith4Aces() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		hand.addCard(new Card(Rank.ACE, Suit.SPADES));
		hand.addCard(new Card(Rank.ACE, Suit.CLUBS));
		
		boolean isBust = hand.getBlackjackValue() > 21;
		assertEquals(isBust, false);
		
		boolean is14 = hand.getBlackjackValue() == 14;
		assertEquals(is14, true);
	}
	
	public void testCheckPairWithPair() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
		hand.addCard(new Card(Rank.EIGHT, Suit.SPADES));
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, true);
	}
	
	public void testCheckPairWith2DifferentCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, false);
	}
	
	public void testCheckPairWith3SameCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		hand.addCard(new Card(Rank.NINE, Suit.SPADES));
		hand.addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		
		boolean isPair = hand.checkPair();
		assertEquals(isPair, false);
	}
	
	public void testIsBustWithGreaterThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		hand.addCard(new Card(Rank.JACK, Suit.SPADES));
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, true);
	}
	
	public void testIsBustWithLessThan21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
		hand.addCard(new Card(Rank.THREE, Suit.DIAMONDS));
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, false);
	}
	
	public void testIsBustWith21() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		
		boolean isBust = hand.isBust();
		assertEquals(isBust, false);
	}
	
	public void testGetCardsOfSuitAllHandIsSameSuit() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
		hand.addCard(new Card(Rank.FIVE, Suit.HEARTS));
		hand.addCard(new Card(Rank.SEVEN, Suit.HEARTS));
		
		//Hand getCardsOfSuit(Suit pSuit) 
		
		//HOW WOULD YOU TEST THIS METHOD WITH THE ASSERTION THING??
	}
	
	public void testCheckBlackjackWithBlackJack() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, true);
	}
	
	public void testCheckBlackjackWith2FaceCards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.KING, Suit.HEARTS));
		hand.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, false);
	}
	
	public void testCheckBlackjackWith3Cards() {
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(new Card(Rank.KING, Suit.HEARTS));
		hand.addCard(new Card(Rank.ACE, Suit.SPADES));
		hand.addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
		
		boolean isBlackjack = hand.checkBlackjack();
		assertEquals(isBlackjack, false);
	}
}