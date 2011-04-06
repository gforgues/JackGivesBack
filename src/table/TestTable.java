package table;

import game.Blackjack;
import gameEngine.GameEngine;
import junit.framework.*;
import java.util.*;

import cards.*;
import cards.Card.Rank;
import cards.Card.Suit;
import participant.*;
import storage.BlackjackStorage;
import storage.Statistics;
import storage.Storage;

public class TestTable extends TestCase {
	private Player p1; 
    private Player p2;
    private Player p3;
    private Player tableOwner; 
    private Table table;
    boolean failed;
    int expectedValue;
    int actualValue;
    
    public void setUp() { 
       p1 = new Player("jessica", "password");
       p2 = new Player("jack", "Black");
       p3 = new Player("abc", "123");
       tableOwner = new Player("emily", "abc");
       table = new Table(tableOwner);
    }
	
	public void testRequestJoinOnePlayerTableAccepts() {
		table.requestJoin(p1, true);
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestJoin2PlayersTableRejectsOne() {		
		p1.requestJoin(table, true);
		p2.requestJoin(table, false);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestJoinAcceptAllThreePlayers() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		assertEquals(table.getAllPlayers().size(), 4);
	}
	
	public void testRequestJoinRejectAllThreePlayers() {
		p1.requestJoin(table, false);
		p2.requestJoin(table, false);
		p3.requestJoin(table, false);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveAcceptOnePlayer() {
		p1.requestJoin(table, true);
		p1.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveRejectPlayer() {
		p1.requestJoin(table, true);
		p1.requestLeave(table, false);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestLeaveAcceptAllPlayers() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, true);
		p2.requestLeave(table, true);
		p3.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestLeaveFor3PlayersAcceptTwoOfThem() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, false);
		p2.requestLeave(table, true);
		p3.requestLeave(table, true);
		
		assertEquals(table.getAllPlayers().size(), 2);
	}
	
	public void testRequestLeaveRejectAll3Players() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		p3.requestJoin(table, true);
		
		p1.requestLeave(table, false);
		p2.requestLeave(table, false);
		p3.requestLeave(table, false);
		
		assertEquals(table.getAllPlayers().size(), 4);
	}
	
	public void testRequestViewAcceptTwoSpectators() {
		Spectator spect = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello","bye");
		
		spect.requestView(table, true);
		s2.requestView(table, true);
		
		assertEquals(table.getAllSpectators().size(), 2);
	}
	
	public void testRequestViewRejectTwoSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, false);
		s2.requestView(table, false);
		
		assertEquals(table.getAllSpectators().size(), 0);
	}
	
	public void testRequestViewAcceptOneSpectatorRejectOther() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, false);
		
		assertEquals(table.getAllSpectators().size(), 1);
	}
	
	public void testRequestLeaveAcceptAllSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, true);
		s2.requestLeave(table, true);
		
		assertEquals(table.getAllSpectators().size(), 0);
	}
	
	public void testRequestLeaveRejectAllSpectators() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, false);
		s2.requestLeave(table, false);
		
		assertEquals(table.getAllSpectators().size(), 2);
	}
	
	public void testRequestLeaveRejectOneSpectatorAcceptOther() {
		Spectator s1 = new Spectator("jessica", "password");
		Spectator s2 = new Spectator("hello", "bye");
		
		s1.requestView(table, true);
		s2.requestView(table, true);
		
		s1.requestLeave(table, true);
		s2.requestLeave(table, false);
		
		assertEquals(table.getAllSpectators().size(), 1);
	}
	
	public void testSaveAndLoadDeck() {
		Deck deck = new Deck();
		deck.addDeck(1);
		deck.draw();
		deck.draw();
		deck.draw();
		expectedValue = 49;
		
		BlackjackStorage.saveDeck(deck, 1);
		actualValue = BlackjackStorage.loadDeck(1).size();
		
		assertEquals(expectedValue, actualValue);
	}
	
	public void testSaveAndLoadThreeDecks() {
		Deck deck = new Deck();
		deck.addDeck(3);
		expectedValue = 52*3;
		
		BlackjackStorage.saveDeck(deck, 3);
		actualValue = BlackjackStorage.loadDeck(3).size();
		
		assertEquals(expectedValue, actualValue);
	}
	
//	public void testSaveAndLoadGameOnePlayer() {
//		
//	}
	
//	public void testSaveAndLoadOnePlayerWithThreeCards() {
//		Deck deck = new Deck();
//		HashMap<Player, ArrayList<BlackjackHand>> playersAndHands = new HashMap<Player, ArrayList<BlackjackHand>>();
//		ArrayList<BlackjackHand> hand = new ArrayList<BlackjackHand>();
//		BlackjackHand h = new BlackjackHand();
//		
//		p1.requestJoin(table, true);
//		deck.addDeck(1);
//		for (int i=0; i<3; i++) {
//			h.addCard(deck.draw());
//			hand.add(h);
//		}
//		playersAndHands.put(p1, hand);
//		table.saveGame(deck, 2, playersAndHands);
//		
//		String expectedStringValue = p1.getUsername();
//		expectedValue = playersAndHands.get(p1).size();
//		table.loadPlayers(2);
//		String actualStringValue = table.getAllPlayers().get(1).getUsername();
//		
//		assertEquals(expectedStringValue, actualStringValue);
//	}
	
//	public void testLoadOnePlayerAndHand() {
//		failed = false;
//		
//		try {
//			ArrayList<Player> players = table.loadPlayers(1);
//			assertEquals(players.size(), 1);
//		} catch (IllegalArgumentException e) {
//			failed = true;
//		}
//		assertEquals(failed, false);
//		
//	}
//	
//	public void testLoadThreePlayersAndHands() {
//		
//	}
	
//	public void testSaveHands() {
//		p1.requestJoin(table, true);
//		p2.requestJoin(table, true);
//		HashMap<Player,BlackjackHand> playerHand = new HashMap<Player,BlackjackHand>();
//		Hand hand = new Hand();
////		hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
////		hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
//		
//		//playerHand.put(p1,);
//		
//		//assertEquals(table.saveHands(hand, 2), true);
//	}
}