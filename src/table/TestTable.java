package table;

import game.Blackjack;
import junit.framework.*;
import java.util.*;

import cards.*;
import cards.Card.Rank;
import cards.Card.Suit;
import participant.*;
import storage.Statistics;
import storage.Storage;

public class TestTable extends TestCase {
	private Player p1; 
    private Player p2;
    private Player p3;
    private Player tableOwner; 
    private Table table;
    
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
	
	public void testSaveOnePlayer() {
		p1.requestJoin(table, true);
		
	}
//	public void testSavePlayer() {
//		Statistics abc = Storage.loadPlayer("abc","123");
//		
//		abc.setChips(5);
//		Storage.savePlayer(abc);
//		abc = Storage.loadPlayer("abc","123");
//		actualValue = abc.getChips();
//		expectedValue = 5;
//		
//		assertEquals(actualValue, expectedValue);
//	}
	
	public void testSavePlayers() {
		p1.requestJoin(table, true);
		p2.requestJoin(table, true);
		
		assertEquals(table.savePlayers(), true);
		//Deck deck, int gameID, Blackjack game
	}
	
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